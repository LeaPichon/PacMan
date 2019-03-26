package application;

import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import lib.Ghost;
import lib.Grid;
import lib.Pacman;
import lib.Direction;


public class Model extends Observable implements Runnable 
{
    private Grid grid;
    private Pacman pacman;
    private Ghost ghost1;
    private Ghost ghost2;
    private Ghost ghost3;
    private Ghost ghost4;
    
    private Direction lastdir;
    
    Random rand = new Random();
    
    public Model() 
    {
        pacman = new Pacman();
        grid = new Grid();
        
        lastdir = Direction.debut;
        ghost1 = new Ghost(grid);
        ghost2 = new Ghost(grid);
        ghost3 = new Ghost(grid);
        ghost4 = new Ghost(grid);
        
        grid.addPacman(pacman);
        
        grid.addEntities(ghost1);
        grid.addEntities(ghost2);
        grid.addEntities(ghost3);
        grid.addEntities(ghost4);
    }
    
    public int getPX() 
    {
        return pacman.getX();
    }
    
    public int getPY() 
    {
        return pacman.getY();
    }
    
    public boolean getPState()
    {
    	return pacman.state();
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
	    ghost1.setY(9);
	    ghost2.setX(11);
	    ghost2.setY(12);
	    ghost3.setX(7);
	    ghost3.setY(14);
	    ghost4.setY(17);
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
    
    
    void setdir(String dir)
    {
    	switch(dir)
    	{
    		case "z" : 
    			lastdir = Direction.haut;
    			break;
    		case "s" :
    			lastdir = Direction.bas;
    			break;
    		case "q" :
    			lastdir = Direction.gauche;
    			break;
    		case "d" :
    			lastdir = Direction.droite;
    			break;
    	}
    }
    
    void deplacer()
    {
    	switch(lastdir)
    	{
	    	case haut :
	    		if (!grid.getCell(pacman.getX()-1, pacman.getY()))
                {
	    			for (int i = 0; i < 4; i++)
	    				if (getPX() == grid.getSPG(i).getX() && getPY() == grid.getSPG(i).getY())
	    				{
	    					pacman.changeState(true);
	    					grid.setScore(grid.getScore() + 10);
	    				}
	    			
                    if (grid.collision(pacman,-1,0))
                    {
                        if (pacman.state()) 
                        	pacman.setX(pacman.getX()-1);
                        else 
                    	{
                    		pacman = new Pacman();
                    		grid.addPacman(pacman);
                    	}
                    }
                    else 
                    	pacman.setX(pacman.getX()-1);
                }                            
	    		break;
	    		
	    	case bas :
	    		if (!grid.getCell(pacman.getX()+1, pacman.getY()))
                {
	    			for (int i = 0; i < 4; i++)
	    				if (getPX() == grid.getSPG(i).getX() && getPY() == grid.getSPG(i).getY())
	    				{
	    					pacman.changeState(true);
	    					grid.setScore(grid.getScore() + 10);
	    				}
	    			
                    if(grid.collision(pacman,1,0))
                    {
                        if(pacman.state()) 
                        	pacman.setX(pacman.getX()+1);
                        else
                        {
                    		pacman = new Pacman();
                    		grid.addPacman(pacman);
                    	}
                    }
                    else 
                    	pacman.setX(pacman.getX()+1);
                }		
	    		break;
	    		
	    	case gauche :
	    		if (pacman.getX() == 9 && pacman.getY() == 0)
	    			pacman.setY(26);
	    		else if (!grid.getCell(pacman.getX(), pacman.getY()-1))
                {
	    			for (int i = 0; i < 4; i++)
	    				if (getPX() == grid.getSPG(i).getX() && getPY() == grid.getSPG(i).getY())
	    				{
	    					pacman.changeState(true);
	    					grid.setScore(grid.getScore() + 10);
	    				}
	    			
	                if(grid.collision(pacman,0,-1))
	                {
	                    if(pacman.state()) 
	                    	pacman.setY(pacman.getY()-1);
	                    else
	                    {
	                		pacman = new Pacman();
	                		grid.addPacman(pacman);
	                	}
	                }
	                else 
	                	pacman.setY(pacman.getY()-1);
                }
	    		break;
	    		
	    	case droite :
	    		if (pacman.getX() == 9 && pacman.getY() == 26)
	    			pacman.setY(0);
	    		else if (!grid.getCell(pacman.getX(), pacman.getY()+1))
                {
	    			for (int i = 0; i < 4; i++)
	    				if (getPX() == grid.getSPG(i).getX() && getPY() == grid.getSPG(i).getY())
	    				{
	    					pacman.changeState(true);
	    					grid.setScore(grid.getScore() + 10);
	    				}
	    			
                    if(grid.collision(pacman,0,1))
                    {
                        if(pacman.state()) 
                        	pacman.setY(pacman.getY()+1);
                        else 
                        {
                    		pacman = new Pacman();
                    		grid.addPacman(pacman);
                    	}
                    }
                    else 
                    	pacman.setY(pacman.getY()+1);
                }	    			
	    		break;
	    		
	    	default:
	    		break;
    	}
    }
    
    void deplacerGhost(Ghost ghost)
    {
    	Direction dir;
    	if(grid.intersection(ghost)) 
    		ghost.moveGhostRandom(); 
    	dir = ghost.getdir();
    	
    	switch(dir)
    	{
	    	case haut :
	    		if (!grid.getCell(ghost.getX() - 1, ghost.getY()))
                {
                    if (grid.collisionGhost(ghost, -1, 0))
                    {
                        if (pacman.state()) 
                        {
                        	pacman.setX(pacman.getX()-1);
                        	ghost.resetGhost();
                        	System.out.println("prout");
                        }
                        else
                        {
                             ghost.setX(ghost.getX()-1);
                             pacman = new Pacman();
                             grid.addPacman(pacman);
                        }
                    }
                    else 
                    	ghost.setX(ghost.getX()-1);
                }    
	    		break;
	    		
	    	case bas :
	    		if (!grid.getCell(ghost.getX()+1, ghost.getY()))
                {
                    if (grid.collisionGhost(ghost,1,0))
                    {
                        if (pacman.state()) 
                        {
                        	pacman.setX(pacman.getX()+1);
                        	ghost.resetGhost();
                        	System.out.println("prout");
                        }
                        else
                        {
                             ghost.setX(ghost.getX()+1);   
                             pacman = new Pacman();
                             grid.addPacman(pacman);
                        } 
                    }
                    else 
                    	ghost.setX(ghost.getX()+1);
                } 
	    		break;
	    	case gauche :
	    		if (ghost.getX() == 9 && ghost.getY() == 0)
	    			ghost.setY(26);
	    		else if (!grid.getCell(ghost.getX(), ghost.getY()-1))
                {
                    if (grid.collisionGhost(ghost,0,-1))
                    {
                        if (pacman.state()) 
                        {
                        	pacman.setY(pacman.getY()-1);
                        	ghost.resetGhost();
                        	System.out.println("prout");
                        }
                        else
                        {
                             ghost.setY(ghost.getY()-1);    
                             pacman = new Pacman();
                             grid.addPacman(pacman);
                        } 
                    }
                    else
                        ghost.setY(ghost.getY()-1);
                } 
	    		break;
	    	case droite :
	    		if (ghost.getX() == 9 && ghost.getY() == 26)
	    			ghost.setY(0);
	    		else if (!grid.getCell(ghost.getX(), ghost.getY()+1)) 
               {
                    if(grid.collisionGhost(ghost,0,1))
                    {
                        if(pacman.state()) 
                        {
                        	pacman.setY(pacman.getY()+1);
                        	ghost.resetGhost();
                        	System.out.println("prout");
                        }
                        else
                        {
                             ghost.setY(ghost.getY()+1);    
                             pacman = new Pacman();
                             grid.addPacman(pacman);
                        } 
                    }
                    else                          
                        ghost.setY(ghost.getY()+1);
                }
	    		break;
	    	default:
	    		break;
    	}
        grid.possibilite(ghost);
    	
    }
    
    boolean detectionMur(Ghost ghost)
    {
    	Direction dir=ghost.getdir();
    	switch(dir)
    	{
	    	case haut :
	    		if (!grid.getCell(ghost.getX()-1, ghost.getY()))
	    			return true;
	    		else return false;
	    	case bas :
	    		if (!grid.getCell(ghost.getX()+1, ghost.getY())) 
	    			return true;
	    		else return false;
	    	case gauche :
	    		if (!grid.getCell(ghost.getX(), ghost.getY()-1)) 
	    			return true;
	    		else return false;
	    	case droite :
	    		 if (!grid.getCell(ghost.getX(), ghost.getY()+1)) 
	    			return true;
	    		 else return false;
	    	default:
	    		return true;
    	}
    }
    
    @Override
    public void run() 
    {
    	int compt = 8;
    	int c = 0;
    	
        this.initGhosts();
        while(true) 
        {
        	if (c == 30)
        	{
        		pacman.changeState(false);
        		c = 0;
        	}
        	compt--;
        	if(compt==0)
        	{
        		ghost1.moveGhostRandom();
        		while(!this.detectionMur(ghost1)) ghost1.moveGhostRandom();
        			
        		ghost2.moveGhostRandom();
        		while(!this.detectionMur(ghost2)) ghost2.moveGhostRandom();
        			
        		ghost3.moveGhostRandom();
        		while(!this.detectionMur(ghost3)) ghost3.moveGhostRandom();
        			
        		ghost4.moveGhostRandom();
                while(!this.detectionMur(ghost4)) ghost4.moveGhostRandom();
        		compt=8;
        	}
            
        	this.deplacer();
        	this.deplacerGhost(ghost1);
    		this.deplacerGhost(ghost2);
    		this.deplacerGhost(ghost3);
    		this.deplacerGhost(ghost4);
        	
    		if(pacman.state())
    			c++;
    		
    		setChanged(); 
        	notifyObservers(); // notification de l'observer
           
           try 
           {
               Thread.sleep(200); // pause
           } 
           catch (InterruptedException ex) 
           {
               Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }
}
