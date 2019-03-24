package lib;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector; 

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Grid {
	
	private boolean[][] grid;
	private int MAX_L = 30;
	private int MAX_C = 30;
	private int indexL;
	private int indexC;
	private Scanner scan;
	private Scanner scan2;
	private File file;
        private Pacman pacman;
        private Vector<Ghost> ghosts;
	
	public Grid()
	{
            ghosts = new Vector<Ghost>();
		try
		{
			grid = new boolean[MAX_L][MAX_C];
			
			file = new File("grille.txt");
			scan = new Scanner(file);
			
			indexL = 0;
			
			//scanne ligne par ligne
			while(scan.hasNextLine())
			{
				String ligne = scan.nextLine();
				scan2 = new Scanner(ligne);
				scan2.useDelimiter("");
				indexC = 0;
				
				//puis scanne charactere par charactere
				while(scan2.hasNext())
				{
					String s = scan2.next();
					int i = Integer.parseInt(s);
					
					//remplit la grille
					if(i == 1)
					{
						grid[indexL][indexC] = true;
					}
					else if(i == 0)
					{
						grid[indexL][indexC] = false;
					}
					indexC++;
				}
				indexL++;
	        }
	        scan.close();
		}
		catch (FileNotFoundException e) 
		{
	        e.printStackTrace();
	    }
	}
	
	public int getIndexL()
	{
		return indexL;
	}
	
	public int getIndexC()
	{
		return indexC;
	}
	
	public boolean getCell(int i, int j)
	{
		return grid[i][j];
	}
	
	public void possibilite(Ghost ghost)
        {
		boolean[] dummy = new boolean[4];
		if (!this.getCell(ghost.getX()-1, ghost.getY()))
			dummy[0]=true;
		else dummy[0]=false;
	
		if (!this.getCell(ghost.getX()+1, ghost.getY())) 
			dummy[1]=true;
		else dummy[1]=false;
	
		if (!this.getCell(ghost.getX(), ghost.getY()-1)) 
			dummy[2]=true;
		else dummy[2]=false;
	
		 if (!this.getCell(ghost.getX(), ghost.getY()+1)) 
			dummy[3]=true;
		 else dummy[3]=false;
		 ghost.setPossibility(dummy);
        }
        
    public void addEntities(Ghost ghost)
    {
        ghosts.addElement(ghost);
    }
    
    public void addPacman(Pacman pacman)
    {
        this.pacman = pacman;
    }
    
    public boolean collision(Pacman pacman, int X, int Y)
    {
        for(int i=0; i<4;i++)
        {
            if(ghosts.elementAt(i).getX() == pacman.getX() + X && ghosts.elementAt(i).getY() == pacman.getY() + Y) return true;
        }
        return false;  
    }
    
    public boolean collisionGhost(Ghost ghost, int X, int Y)
    {
        
       if(ghost.getX() + X == this.pacman.getX() && ghost.getY() + Y == this.pacman.getY()) return true; 
       return false;  
    }
}
