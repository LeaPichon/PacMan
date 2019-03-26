package lib;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

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
    private Vector<SuperPacGomme> superPacgommes;
    private Vector<PacGomme> Pacgommes;
	
	public Grid()
	{
        ghosts = new Vector<Ghost>();
        superPacgommes = new Vector<SuperPacGomme>();
        Pacgommes = new Vector<PacGomme>();
        superPacgommes.addElement(new SuperPacGomme(1,12));
        superPacgommes.addElement(new SuperPacGomme(17,1));
        superPacgommes.addElement(new SuperPacGomme(1,25));
        superPacgommes.addElement(new SuperPacGomme(17,25));
        //superPacgommes.addElement(new SuperPacGomme(15,13));
            
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
					if (i == 1)
					{
						grid[indexL][indexC] = true;
                                                
					}
					else if (i == 0)
					{
						grid[indexL][indexC] = false;
                                                if(!SPG(indexL,indexC))Pacgommes.addElement(new PacGomme(indexL,indexC));
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
	
        public boolean SPGat(int i, int j)
        {
            for(int k=0; k<superPacgommes.size(); k++)
                if(superPacgommes.elementAt(k).getX() == i && superPacgommes.elementAt(k).getY() == j)
                    return false;
            return true;
        }
        
        public void deleteSPGat(int i, int j)
        {
            int index=0;
            for (int k = 0; k < superPacgommes.size(); k++)
            {
    		if (superPacgommes.get(k).getX() == i && superPacgommes.get(k).getY() == j) index = k;
	
            }
            //System.out.println(index);
            superPacgommes.remove(index);
            //System.out.println(superPacgommes.size());
        }
        
        public void deletePGat(int i, int j)
        {
            int index=0;
            for (int k = 0; k < Pacgommes.size(); k++)
            {
    		if (Pacgommes.get(k).getX() == i && Pacgommes.get(k).getY() == j) index = k;
	
            }
            
            Pacgommes.remove(index);
        }
        
	public void possibilite(Ghost ghost)
    {
		boolean[] dummy = new boolean[4];
		if (!this.getCell(ghost.getX() - 1, ghost.getY()))
			dummy[0] = true;
		else dummy[0] = false;
	
		if (!this.getCell(ghost.getX() + 1, ghost.getY())) 
			dummy[1] = true;
		else dummy[1] = false;
		
		if (ghost.getX() == 9 && ghost.getY() == 0) dummy[2] = true;
		else if (!this.getCell(ghost.getX(), ghost.getY() - 1)) 
			dummy[2] = true;
		else dummy[2] = false;
		
		if (ghost.getX() == 9 && ghost.getY() == 26) dummy[2] = true;
		else if (!this.getCell(ghost.getX(), ghost.getY() + 1)) 
			dummy[3] = true;
		else dummy[3] = false;
		ghost.setPossibility(dummy);
    }
     
	public boolean[] testPossibilite(Ghost ghost)
    {
		boolean[] dummy = new boolean[4];
		if (!this.getCell(ghost.getX() - 1, ghost.getY()))
			dummy[0] = true;
		else dummy[0] = false;
	
		if (!this.getCell(ghost.getX() + 1, ghost.getY())) 
			dummy[1] = true;
		else dummy[1] = false;
		
		if (ghost.getX() == 9 && ghost.getY() == 0) dummy[2] = true;
		else if (!this.getCell(ghost.getX(), ghost.getY() - 1)) 
			dummy[2] = true;
		else dummy[2] = false;
		
		if (ghost.getX() == 9 && ghost.getY() == 26) dummy[2] = true;
		else if (!this.getCell(ghost.getX(), ghost.getY() + 1)) 
			dummy[3] = true;
		else dummy[3] = false;
		return dummy;
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
        for(int i = 0; i < 4; i++)
        {
            if (ghosts.elementAt(i).getX() == pacman.getX() + X && ghosts.elementAt(i).getY() == pacman.getY() + Y) 
            	return true;
        }
        return false;  
    }
    
    public boolean collisionGhost(Ghost ghost, int X, int Y)
    {
        
       if (ghost.getX() + X == this.pacman.getX() && ghost.getY() + Y == this.pacman.getY())
       {
    	   return true; 
       }

       return false;  
    }
    
    public boolean compare(boolean[] possibility, boolean[] newpossibility)
	{
		for (int i = 0; i < 4; i++) 
			if (possibility[i] != newpossibility[i]) 
				return false;
		return true;
	}
    
    public boolean intersection(Ghost ghost)
    {
    	boolean[] temp = new boolean[4];
    	boolean[] temp2 = new boolean[4];
    	temp = ghost.getPossibility();
    	temp2 = testPossibilite(ghost);
    	return !compare(temp,temp2);
    }
    
    public boolean SPG(int i, int j)
    {
    	for (int k = 0; k < superPacgommes.size(); k++)
    	{
    		if (superPacgommes.get(k).getX() == i && superPacgommes.get(k).getY() == j)
                    return true;
	
    	}
    	return false;
    }
    
    
    public boolean PG(int i, int j)
    {
    	for (int k = 0; k < Pacgommes.size(); k++)
    	{
    		if (Pacgommes.get(k).getX() == i && Pacgommes.get(k).getY() == j) 
    			return true;
    	}
    	return false;
    }
    
    public SuperPacGomme getSPG(int i)
    {
    	return superPacgommes.get(i);
    }
    
    public Ghost ghostAt(int i, int j)
    {
        for(int k=0; k<ghosts.size(); k++)
        {
            if(ghosts.elementAt(k).getX() == i && ghosts.elementAt(k).getY() == j)
                return ghosts.elementAt(k);
        }
        return null;
    }
    
    public boolean isEmpty()
    {
    	if(Pacgommes.isEmpty()) return true;
    	else return false;
    }
}
