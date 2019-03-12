package application;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
    	
    	Group root = new Group();
    	Scene scene = new Scene(root, 750, 570, Color.BEIGE);
    	
    	grid = new Grid();
    	GridPane gridpane = new GridPane();
    	
    	for(int i = 1; i < grid.getIndexL()-1; i++)
    	{
    		for(int j = 1; j < grid.getIndexC()-1; j++)
    		{
    			if(grid.getCell(i, j) == true)
    			{
    				ImageView wall = new ImageView(new Image("images/wall.png"));
    				wall.setFitHeight(30);
    		    	wall.setPreserveRatio(true);
    				gridpane.add(wall, j, i);
    			}
    			else
    			{
    				ImageView empty = new ImageView(new Image("images/empty.png"));
    		    	empty.setFitHeight(30);
    		    	empty.setPreserveRatio(true);
    				gridpane.add(empty, j, i);
    			}
    		}
    	}
    	gridpane.setVgap(0);
    	
    	root.getChildren().add(gridpane);
        stage.setScene(scene);
        stage.show();
    
	    Observer o =  new Observer() { // l'observer observe l'obervable (update est exécuté dès notifyObservers() est appelé côté modèle )
	        @Override
	        public void update(Observable o, Object arg) {
	            /*for (int i = 0; i < SIZE_X; i++) { // rafraichissement graphique
	                for (int j = 0; j < SIZE_Y; j++) {
	                    
	                	//if(spm.getPX() == i && spm.getPY()==j) 
	                    if (spm.getX() == i && spm.getY() == j) { // spm est à la position i, j => le dessiner
	                        tab[i][j].setImage(imPM);
	                        
	                    } 
	                    //if(spm.getFX() == i && spm.getFY()==j) {
	                    //tab[i][j]=.setImage(imFA);
	                    //
	                   	//}
	                    	else {
	                    	
	                    		tab[i][j].setImage(imVide);
	                    	}
	                }
	            } */   
	        }
	    };
    }
    
    public static void main(String[] args) 
    {
        launch(args);
    }
}


