package lib;

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
}
