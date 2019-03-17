package application;

import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import lib.Ghost;
import lib.Grid;
import lib.Pacman;


public class Model extends Observable implements Runnable 
{
    private Grid grid;
    private Pacman pacman;
    private Ghost ghost1;
    private Ghost ghost2;
    private Ghost ghost3;
    private Ghost ghost4;
    
    Random rand = new Random();
    
    public Model() 
    {
        grid = new Grid();
        pacman = new Pacman();
        /*ghost1 = new Ghost();
        ghost2 = new Ghost();
        ghost3 = new Ghost();
        ghost4 = new Ghost();*/
    }
    
    public int getPX() 
    {
        return pacman.getX();
    }
    
    public int getPY() 
    {
        return pacman.getY();
    }
    
    public int getGX1() 
    {
        return ghost1.getX();
    }
    
    public int getGY1() 
    {
        return ghost1.getY();
    }
    public int getGX2() 
    {
        return ghost2.getX();
    }
    
    public int getGY2() 
    {
        return ghost2.getY();
    }
    public int getGX3() 
    {
        return ghost3.getX();
    }
    
    public int getGY3() 
    {
        return ghost3.getY();
    }
    public int getGX4() 
    {
        return ghost4.getX();
    }
    
    public int getGY4() 
    {
        return ghost4.getY();
    }
    
	public void initGhosts() 
	{
	    ghost1.setY(11);
	    ghost2.setY(12);
	    ghost3.setY(14);
	    ghost4.setY(15);
	}

	public void getGhostsOut() //à améliorer !
	{
		ghost2.setY(13);
	    ghost2.setX(8);
	    ghost2.setX(7);
    	ghost2.moveGhostRandom();
    	ghost3.setY(13);
	    ghost3.setX(8);
	    ghost3.setX(7);
    	ghost3.moveGhostRandom();		
    	ghost1.setY(12);		
    	ghost1.setY(13);
	    ghost1.setX(8);
	    ghost1.setX(7);
    	ghost1.moveGhostRandom();
	    ghost4.setY(12);		
    	ghost4.setY(13);
	    ghost4.setX(8);
	    ghost4.setX(7);
    	ghost4.moveGhostRandom();
	}
	
	public void moveGhostsRandom()
	{
    	ghost1.moveGhostRandom();
    	ghost2.moveGhostRandom();
		ghost3.moveGhostRandom();
		ghost4.moveGhostRandom();
	}
    
    public void start() 
    {
        new Thread(this).start();
    }
    
    void deplacer(String dir)
    {
    	switch(dir)
    	{
	    	case "z" :
	    		if (!grid.getCell(pacman.getX()-1, pacman.getY()))
	    			pacman.setX(pacman.getX()-1);
	    		break;
	    	case "s" :
	    		if (!grid.getCell(pacman.getX()+1, pacman.getY())) 
	    			pacman.setX(pacman.getX()+1);
	    		break;
	    	case "q" :
	    		if (pacman.getX() == 9 && pacman.getY() == 0)
	    			pacman.setY(26);
	    		else if (!grid.getCell(pacman.getX(), pacman.getY()-1)) 
	    			pacman.setY(pacman.getY()-1);
	    		break;
	    	case "d" :
	    		if (pacman.getX() == 9 && pacman.getY() == 26)
	    			pacman.setY(0);
	    		else if (!grid.getCell(pacman.getX(), pacman.getY()+1)) 
	    			pacman.setY(pacman.getY()+1);
	    		break;
	    	default:
	    		break;
    	}
    }
    
    @Override
    public void run() 
    {
        while(true) 
        {
        	//this.initGhosts();
        	//this.moveGhostsRandom();
            //this.getGhostsOut();
        	setChanged(); 
        	notifyObservers(); // notification de l'observer
           
           try 
           {
               Thread.sleep(300); // pause
           } 
           catch (InterruptedException ex) 
           {
               Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }
}
