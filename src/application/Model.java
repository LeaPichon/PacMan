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
	int x, y;
    private Grid grid;
    private Pacman pacman;
    //private Fantom fantom;
    
    Random rand = new Random();
    
    
    public Model() 
    {
        x = 0; 
        y = 0;
    }
    
    
    public int getX()
    {
    	return x;
    }
    
    public int getY()
    {
    	return y;
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
    
    public void initXY() {
        x = 0;
        y = 0;
    }
    
    public void start() 
    {
        new Thread(this).start();
    }
    
    @Override
    public void run() 
    {
        while(true) 
        { 
        	// pm descent dans la grille à chaque pas de temps
        	int deltaX = 0;
        	int deltaY = 0;
           
        	// A la place de l'aléatoire, écouter le clavier.
        	/*if(grid.getCell(deltaX, deltaY) == true)
        	{
        		deltaX = rand.nextInt(grid.getIndexL());
            	deltaY = rand.nextInt(grid.getIndexC());
        	}*/

        	if (grid.getCell(x + deltaX, y + deltaY) == false) 
        	{
        		x += deltaX;
        		y += deltaY;
        	}           
           
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
