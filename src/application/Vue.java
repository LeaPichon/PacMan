package application;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Vue extends Application 
{
	private Model model;
	
    public Vue() { }
    
    @Override
    public void start(Stage stage) 
    {
    	stage.setTitle("Pacman");
    	StackPane root = new StackPane();
    	Scene scene = new Scene(root, 810, 630, Color.WHITE);
    	
    	//initialisation du modèle
    	model = new Model();
    	
    	//préparation des images    	
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
    	GridPane gridpane = new GridPane();
    	ImageView[][] tabIV = new ImageView[model.getGrid().getIndexL()][model.getGrid().getIndexC()];
    	int[][] tabEmpty = new int[model.getGrid().getIndexL()][model.getGrid().getIndexC()];
    	for(int i = 0; i < model.getGrid().getIndexL(); i++)
    	{
    		for(int j = 0; j < model.getGrid().getIndexC(); j++)
    		{
    			if (model.getGrid().getCell(i, j))
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
	            for (int i = 0; i < model.getGrid().getIndexL(); i++) // rafraichissement graphique
	            { 
	                for (int j = 0; j < model.getGrid().getIndexC(); j++) 
	                {
	                	if(model.getGrid().getCell(i, j) == true)
	        		    	tabIV[i][j].setImage(wall);
	                	
	                	else if (model.getGrid().SPG(i,j))
                                {
                                    tabIV[i][j].setImage(superpacgomme);
                                    //System.out.println(model.getGrid().SPG(i,j) + "i = " + i + "j = " + j );
                                }
                                else if(model.getGrid().PG(i,j))
	                		tabIV[i][j].setImage(pacgomme);
	                	else
                                    tabIV[i][j].setImage(empty);
	                	if(model.getPX() == i && model.getPY() == j)
	                	{
	                		tabIV[i][j].setImage(pacman);
	                		tabEmpty[i][j] = 1;
	                	}
	                    if(model.getGX1() == i && model.getGY1()==j)
	                    	if (model.getPState())
	                    		tabIV[i][j].setImage(ghostSPG);
	                    	else
	                    		tabIV[i][j].setImage(ghost1);
	                    if(model.getGX2() == i && model.getGY2()==j)
	                    	if (model.getPState())
	                    		tabIV[i][j].setImage(ghostSPG);
	                    	else
	                    		tabIV[i][j].setImage(ghost2);
	                    if(model.getGX3() == i && model.getGY3()==j)
	                    	if (model.getPState())
	                    		tabIV[i][j].setImage(ghostSPG);
	                    	else
	                    		tabIV[i][j].setImage(ghost3);
	                    if(model.getGX4() == i && model.getGY4()==j)
	                    	if (model.getPState())
	                    		tabIV[i][j].setImage(ghostSPG);
	                    	else
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
	    
	    root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() // on écoute le clavier
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


