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
    
    @Override
    public void run() 
    {
        while(true) 
        {
           
        	// A la place de l'aléatoire, écouter le clavier.
        	int x = getPX();
        	int y = getPY();
        	
        	 int deltaX = rand.nextInt(2);
             int deltaY = rand.nextInt(2);
             
             if (!grid.getCell(x + deltaX, y + deltaY)) 
             {
                 pacman.setX(x + deltaX);
                 pacman.setY(y + deltaY);
                 System.out.println("1");
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
