/*package mvc;

import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import lib.Fantom;
import lib.Grid;
import lib.Pacman;

public class Modele extends Observable implements Runnable
{
    private Grid grid;
    int x, y, sizeX, sizeY;
    private Pacman pacman;
    private Fantom fantom;
    Random r = new Random();
    
    public Modele(int sizeX, int sizeY)
    {
    	this.grid = new Grid();
    	this.x = 0;
    	this.y = 0;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
    public void initXY() 
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
    
	public int PgetX() 
	{
		return pacman.getx();
	}
	
	public int PgetY() 
	{
		return pacman.gety();
	}
	
	public int FgetX() 
	{
		return Fantom.getx();
	}
	
	public int FgetY() 
	{
		return Fantom.gety();
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
        	// spm descend dans la grille à chaque pas de temps
	        int deltaX = r.nextInt(2);
			
	        // Changer le if pour gérer la collision avec les murs
			if (x + deltaX > 0 && x + deltaX < sizeX) 
			{
				x += deltaX;
			}
			
			int deltaY = r.nextInt(2);
			if (y + deltaY > 0 && y + deltaY < sizeX) 
			{
				y += deltaY;
			}
			
			//System.out.println(x + " - " + y);
			setChanged(); 
			notifyObservers(); // notification de l'observer
			try 
			{
				Thread.sleep(300); // pause
			} 
			catch (InterruptedException ex) 
			{
				Logger.getLogger(Modele.class.getName()).log(Level.SEVERE, null, ex);
			}   
        }
    }
}*/