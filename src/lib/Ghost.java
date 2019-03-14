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
	
	public void moveGhostRandom()
	{
		int n = rand.nextInt(4);
		grid = new Grid();
		
		switch(n)
	    {
	        case 0 :
	        	if (!grid.getCell(x - 1, y))
	        		setX(x - 1);
	        	break;
	        case 1 :
	        	if (x == 9 && y == 26)
	        		setY(0);
	        	else if (!grid.getCell(x, y + 1)) 
	        		setY(y + 1);
	        	break;
	        case 2 :
	        	if (!grid.getCell(x + 1, y))
	        		setX(x + 1);
	        	break;
	        case 3 :
	        	if (x == 9 && y == 0)
	        		setY(26);
	        	else if (!grid.getCell(x, y - 1)) 
	        		setY(y - 1);
	        	break;
	        default:
	        	break;
	    }
	}
}