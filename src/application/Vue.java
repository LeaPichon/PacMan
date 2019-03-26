package application;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lib.Grid;

public class Vue extends Application 
{
	private Grid grid;
	
    public Vue() { }
    
    @Override
    public void start(Stage stage) 
    {
    	
    	stage.setTitle("Pacman");
    	StackPane root = new StackPane();
    	Scene scene = new Scene(root, 810, 630, Color.WHITE);
    	
    	//initialisation du mod�le
    	Model model = new Model();
    	
    	//pr�paration des images    	
    	Image wall = new Image("images/wall.png");
    	Image empty = new Image("images/empty.png");
    	Image pacman = new Image("images/pacman.png");
    	Image ghost1 = new Image("images/ghost1.png");
    	Image ghost2 = new Image("images/ghost2.png");
    	Image ghost3 = new Image("images/ghost3.png");
    	Image ghost4 = new Image("images/ghost4.png");
    	Image pacgomme = new Image("images/pacgomme.png");
    	Image superpacgomme = new Image("images/superpacgomme.png");
    	Image ghostSPG = new Image("images/ghostSPG.png");
    	
    	//initialisaton de la grille et des tableaux
    	grid = new Grid();
    	GridPane gridpane = new GridPane();
    	ImageView[][] tabIV = new ImageView[grid.getIndexL()][grid.getIndexC()];
    	int[][] tabEmpty = new int[grid.getIndexL()][grid.getIndexC()];
    	for(int i = 0; i < grid.getIndexL(); i++)
    	{
    		for(int j = 0; j < grid.getIndexC(); j++)
    		{
    			if (grid.getCell(i, j))
    			{
    				ImageView imgWall = new ImageView(wall);
    				imgWall.setFitHeight(30);
    		    	imgWall.setPreserveRatio(true);
    		    	tabIV[i][j] = imgWall;
    				gridpane.add(imgWall, j, i);
    			}
    			else
    			{
    				ImageView imgPacGomme = new ImageView(pacgomme);
    				imgPacGomme.setFitHeight(30);
    				imgPacGomme.setPreserveRatio(true);
    				tabIV[i][j] = imgPacGomme;
    				tabEmpty[i][j] = 0;
    				gridpane.add(imgPacGomme, j, i);
    			}
    		}
    	}
    	
	    Observer obs = new Observer() 
	    {
	        @Override
	        public void update(Observable obs, Object arg) 
	        {
	        	
	            for (int i = 0; i < grid.getIndexL(); i++) // rafraichissement graphique
	            { 
	                for (int j = 0; j < grid.getIndexC(); j++) 
	                {
	                	if(grid.getCell(i, j) == true)
	        		    	tabIV[i][j].setImage(wall);
	                	else if (tabEmpty[i][j] == 1)
	                		tabIV[i][j].setImage(empty);
	                	else if (grid.SPG(i,j))
                                {
                                    tabIV[i][j].setImage(superpacgomme);
                                        System.out.println(grid.SPG(i,j) + "i = " + i + "j = " + j );
                                }
	        			
	                	else
	        			tabIV[i][j].setImage(pacgomme);
	                	
	                	if(model.getPX() == i && model.getPY() == j)
	                	{
	                		tabIV[i][j].setImage(pacman);
	                		tabEmpty[i][j] = 1;
	                	}
	                    if(model.getGX1() == i && model.getGY1()==j)
	                    	tabIV[i][j].setImage(ghost1);
	                    if(model.getGX2() == i && model.getGY2()==j)
	                    	tabIV[i][j].setImage(ghost2);
	                    if(model.getGX3() == i && model.getGY3()==j)
	                    	tabIV[i][j].setImage(ghost3);
	                    if(model.getGX4() == i && model.getGY4()==j)
	                    	tabIV[i][j].setImage(ghost4);
	                }
	            }
	        }
	    };
	    
	    model.addObserver(obs);
	    model.start();
	    
	    root.getChildren().add(gridpane);
	    stage.setScene(scene);
	    stage.show();
	    
	    root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() // on �coute le clavier
	    { 
	    	@Override
            public void handle(javafx.scene.input.KeyEvent event) 
	    	{
	    		if (event.getCode().equals(KeyCode.Z)) {
                    model.setdir("z");
                }
	    		if (event.getCode().equals(KeyCode.Q)) {
	    			model.setdir("q");
                }
	    		if (event.getCode().equals(KeyCode.S)) {
	    			model.setdir("s");
                }
	    		if (event.getCode().equals(KeyCode.D)) {
	    			model.setdir("d");
                }
            }
        });
	    
        gridpane.requestFocus();
    }
   
    public static void main(String[] args) 
    {
        launch(args);
    }
}


