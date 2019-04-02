package lib;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Pacman extends Entity
{	
    
    private boolean powerUp;
	
    public Pacman() 
    {
        super(1,1);
        powerUp = false;
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
