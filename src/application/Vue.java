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
    	//initialisation de la fenêtre
    	stage.setTitle("Pacman");
    	StackPane root = new StackPane();
    	Scene scene = new Scene(root, 810, 630, Color.WHITE);
    	
    	//initialisation du modèle
    	Model model = new Model();
    	
    	//préparation des images    	
    	Image wall = new Image("images/wall.png");
    	Image empty = new Image("images/empty.png");
    	Image pacman = new Image("images/pacman.png");
    	
    	//initialisaton de la grille et du tableau
    	grid = new Grid();
    	GridPane gridpane = new GridPane();
    	ImageView[][] tabIV = new ImageView[grid.getIndexL()][grid.getIndexC()];
    	for(int i = 0; i < grid.getIndexL(); i++)
    	{
    		for(int j = 0; j < grid.getIndexC(); j++)
    		{
    			if(grid.getCell(i, j) == true)
    			{
    				ImageView imgWall = new ImageView(wall);
    				imgWall.setFitHeight(30);
    		    	imgWall.setPreserveRatio(true);
    		    	tabIV[i][j] = imgWall;
    				gridpane.add(imgWall, j, i);
    			}
    			else
    			{
    				ImageView imgEmpty = new ImageView(empty);
    				imgEmpty.setFitHeight(30);
    				imgEmpty.setPreserveRatio(true);
    				tabIV[i][j] = imgEmpty;
    				gridpane.add(imgEmpty, j, i);
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
	        			else
	        				tabIV[i][j].setImage(empty);
	                	
	                	if(model.getPX() == i && model.getPY() == j)
	                    	tabIV[i][j].setImage(pacman);
	                    /*if(spm.getFX() == i && spm.getFY()==j) 
	                    {
	                    	tab[i][j]=.setImage(imFA);
	                   	}*/
	                }
	            } 
	        }
	    };
	    
	    model.addObserver(obs);
	    model.start();
	    
	    root.getChildren().add(gridpane);
	    stage.setScene(scene);
	    stage.show();
	    
	    root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() // on écoute le clavier
	    { 
	    	@Override
            public void handle(javafx.scene.input.KeyEvent event) 
	    	{
	    		if (event.getCode().equals(KeyCode.Z)) {
                    model.deplacer("z");
                }
	    		if (event.getCode().equals(KeyCode.Q)) {
	    			model.deplacer("q");
                }
	    		if (event.getCode().equals(KeyCode.S)) {
	    			model.deplacer("s");
                }
	    		if (event.getCode().equals(KeyCode.D)) {
	    			model.deplacer("d");
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


