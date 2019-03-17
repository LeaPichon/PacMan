package lib;

import java.util.Random;

public class Ghost extends Entity
{	
	private int x;
	private int y;
	private Random rand = new Random();
	private Grid grid;
	
	public Ghost() 
	{
        super();
        x = 9;
        y = 13;
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
	
	public void moveGhostRandom() //à changer, le fantôme ne bouge pas
	{
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
	}
}