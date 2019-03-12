package lib;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	
	public Grid()
	{
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
	
}
