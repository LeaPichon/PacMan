package lib;

import java.util.logging.Level;
import java.util.logging.Logger;

import application.Model;

public class Pacman extends Entity
{	
	private int x;
	private int y;
    private boolean powerUp;
	
	public Pacman() 
	{
        super();
        x = 1;
        y = 1;
        powerUp = false;
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
        
    public boolean state()
    {
        return powerUp;
    }
    
    public void changeState(boolean state)
    {
    	this.powerUp = state;
    }
}
