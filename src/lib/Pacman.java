package lib;

public class Pacman extends Entity
{	
	private int x;
	private int y;
	
	public Pacman() 
	{
        super();
        x = 1;
        y = 1;
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
}
