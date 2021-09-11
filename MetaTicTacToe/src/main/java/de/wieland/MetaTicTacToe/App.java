package de.wieland.MetaTicTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Class to display GUI.
 * 
 * @author Moritz Wieland
 * @version 1.0
 */
public class App extends Application {
	//constants
	public static final int WIDTH = 414;
	public static final int HEIGHT = 896;
	public static final int FONTSIZE = 30;
	public static final int INSET = 100;
	
	//game
	private Board board;
	
	//javafx
	private Scene scene;
	private BorderPane root;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		board = new Board();
		
		//top contains "Turn:" Label and "X" / "O" Label
		//center contains game
		root.setTop(board.getTurnVBox());
		root.setCenter(board.getGameVBox());
		
		scene = new Scene(root, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setTitle("META-TicTacToe");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
