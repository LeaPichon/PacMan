package lib;

import java.util.Random;
import java.util.Vector;


public class Ghost extends Entity
{	
	private int x;
	private int y;
	private Random rand = new Random();
	private Grid grid;
	private Direction lastdir;
	private boolean[] possibility;
	
	
	public Ghost(Grid grid) 
	{
        super();
        x = 9;
        y = 13;
        possibility = new boolean[4];
        this.grid=grid;
        this.grid.possibilite(this);
        lastdir=Direction.debut;
    }

	public int getX() 
	{
		return x;
	}
	
	public int getY() 
	{
		return y;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public void setY(int y) 
	{
		this.y = y;
	}
	
	public Direction getdir()
	{
		return lastdir;
	}
	
        public boolean[] getPossibility()
        {
            return possibility;
        }
	public void setPossibility(boolean[] result)
	{
		for(int i=0; i<4; i++)
		{
			possibility[i] = result[i];
		}
	}
	
	public int countPossibility()
	{
		int compt=0;
		for(int i=0; i<4; i++)
		{
			if(possibility[i]) compt++;
		}
		return compt;
	}
	
	public void moveGhostRandom() // changer, le fante ne bouge pas
	{
		/*
		int deltaX = rand.nextInt(2);
		int deltaY = rand.nextInt(2);
		int sensX = rand.nextInt(2);
		int sensY = rand.nextInt(2);
		grid = new Grid();
		
        if(sensX == 0 && sensY == 0)
        	if(!grid.getCell(x + deltaX, y + deltaY))
	        {
	        	x += deltaX;
	        	y += deltaY;
	        }
        if(sensX == 0 && sensY == 1)
        	if(!grid.getCell(x + deltaX, y - deltaY))
	        {
	        	x += deltaX;
	        	y -= deltaY;
	        }
        if(sensX == 1 && sensY == 0)
        	if(!grid.getCell(x - deltaX, y + deltaY))
	        {
	        	x -= deltaX;
	        	y += deltaY;
	        }
        if(sensX == 1 && sensY == 1)
        	if(!grid.getCell(x - deltaX, y - deltaY))
	        {
	        	x -= deltaX;
	        	y -= deltaY;
	        }
	        */
		int number = countPossibility();
		
		int choix = rand.nextInt(number);
		
		Vector<Direction> directionPossible = new Vector<Direction>();
			if(possibility[0]) directionPossible.addElement(Direction.haut);
			if(possibility[1]) directionPossible.addElement(Direction.bas);
			if(possibility[2]) directionPossible.addElement(Direction.gauche);
			if(possibility[3]) directionPossible.addElement(Direction.droite);
		
		
		switch(choix)
		{
		case 0 : 
			lastdir = directionPossible.get(0);
			break;
		case 1 : 
			lastdir = directionPossible.get(1);
			break;
		case 2 : 
			lastdir = directionPossible.get(2);
			break;
		case 3 : 
			lastdir = directionPossible.get(3);
			break;
		}
	}
}