package application;

import java.awt.Color;
import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import lib.Grid;
import lib.Pacman;


public class Model extends Observable implements Runnable 
{
    private Grid grid;
    private Pacman pacman;
    //private Fantom fantom;
    
    Random rand = new Random();
    
    public Model() 
    {
        grid = new Grid();
        pacman = new Pacman();
    }
    
    public int getPX() 
    {
        return pacman.getX();
    }
    
    public int getPY() 
    {
        return pacman.getY();
    }
    
	//public int FgetX() {
	    //return Fantom.getx();
	//}
	
	//public int FgetY() {
	    //return Fantom.gety();
	//}
    
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
	    		if(pacman.getX() == 9 && pacman.getY() == 0)
	    			pacman.setY(26);
	    		else if (!grid.getCell(pacman.getX(), pacman.getY()-1)) 
	    			pacman.setY(pacman.getY()-1);
	    		break;
	    	case "d" :
	    		if(pacman.getX() == 9 && pacman.getY() == 26)
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
        	//int x = getPX();
        	//int y = getPY();
        	
        	 //int deltaX = rand.nextInt(2);
             //int deltaY = rand.nextInt(2);
             
            // if (!grid.getCell(x + deltaX, y + deltaY)) 
             //{
              //   pacman.setX(x + deltaX);
               //  pacman.setY(y + deltaY);
             //}      
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
