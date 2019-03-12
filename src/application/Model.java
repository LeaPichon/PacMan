package application;

import java.awt.Color;
import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Model extends Observable implements Runnable 
{
	int x, y, sizeX, sizeY;
    //private Grid grid;
    //private Pacman pacman;
    //private Fantom fantom;
    
    Random r = new Random();
    
    
    public Model(int _sizeX, int _sizeY) {
        x = 0; y = 0;
        
        sizeX = _sizeX;
        sizeY = _sizeY;
       
    }
    
    
    public int getX()
    {
    	return x;
    }
    
    public int getY()
    {
    	return y;
    }
    
    //public int PgetX() {
        //return pacman.getx();
    //}
    
    //public int PgetY() {
        //return pacman.gety();
    //}
    
	//public int FgetX() {
	    //return Fantom.getx();
	//}
	
	//public int FgetY() {
	    //return Fantom.gety();
	//}
    /*
    public void start() {
        new Thread(this).start();
    }*/
    
    public void initXY() {
        x = 0;
        y = 0;
    }
    
    public void start() {
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        while(true) { // spm descent dasn la grille Ã  chaque pas de temps
            
           int deltaX = r.nextInt(2);
           
           
           // Changer le if pour gèrer la collision avec les murs
           if (x + deltaX > 0 && x + deltaX < sizeX) {
               x += deltaX;
           }
           
           int deltaY = r.nextInt(2);
           if (y + deltaY > 0 && y + deltaY < sizeX) {
               y += deltaY;
           } 
           
           //System.out.println(x + " - " + y);
           
           setChanged(); 
           notifyObservers(); // notification de l'observer
           
            try {
                Thread.sleep(300); // pause
            } catch (InterruptedException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    
    }
	
}
