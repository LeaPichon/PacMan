package lib;

import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;




public class Ghost extends Entity
{	

	private Random rand = new Random();
	private Grid grid;
	private Direction lastdir;
	private boolean[] possibility;
	
	
    public Ghost(Grid grid, int x, int y) 
    {
        super(x,y);
        possibility = new boolean[4];
        this.grid=grid;
        this.grid.possibilite(this);
        lastdir=Direction.debut;
    }

	
    public void resetGhost()
    {
        setX(7);
        setY(13);
    }

    public Direction getdir()
    {
        return lastdir;
    }
	
    public boolean[] getPossibility()
    {
        return possibility;
    }
    
    public void setPossibility(boolean[] result)
    {
        for(int i=0; i<4; i++)
        {
                possibility[i] = result[i];
        }
    }
	
    public int countPossibility()
    {
        int compt=0;
        for(int i=0; i<4; i++)
        {
            if(possibility[i]) compt++;
        }
        return compt;
    }

    public void moveGhostRandom() // changer, le fante ne bouge pas
    {
        int number = countPossibility();

        int choix = rand.nextInt(number);

        Vector<Direction> directionPossible = new Vector<Direction>();
            if(possibility[0]) directionPossible.addElement(Direction.haut);
            if(possibility[1]) directionPossible.addElement(Direction.bas);
            if(possibility[2]) directionPossible.addElement(Direction.gauche);
            if(possibility[3]) directionPossible.addElement(Direction.droite);


        switch(choix)
        {
        case 0 : 
            lastdir = directionPossible.get(0);
            break;
        case 1 : 
            lastdir = directionPossible.get(1);
            break;
        case 2 : 
            lastdir = directionPossible.get(2);
            break;
        case 3 : 
            lastdir = directionPossible.get(3);
            break;
        }
    }
}