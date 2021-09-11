package de.wieland.MetaTicTacToe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Class Field represents one Field of the board.
 * Contains a Button.
 * 
 * @author moritz
 * @version 1.0
 */
public class Field {
	//constants
	private static final int BUTTONSIZE = App.WIDTH / 9;
	
	//counter + id
	private static int counter = 0;
	private int id;
	
	//game
	private SubSection parent;
	private Status status = Status.EMPTY;
	private Field selfReference;
	
	//javafx
	private Button button = new Button();
	private EventHandler<ActionEvent> buttonClickHandler;
	
	/**
	 * Public constructor.
	 * 
	 * @param parent Reference to the parent of each field
	 */
	public Field(SubSection parent) {
		this.parent = parent;
		selfReference = this;
		
		//define id and increase counter
		//reset counter after 9 fields are created
		if (counter < 9) {
			id = counter;
			counter++;
		} else {
			counter = 0;
			id = counter;
			counter++;
		}
		
		//setup eventHandler
		setupEventHandler();
		
		//define Button
		button.setPrefSize(BUTTONSIZE, BUTTONSIZE);
		button.setOnAction(buttonClickHandler);
	}
	
	/**
	 * Private Method to define the buttonClickHandler.
	 */
	private void setupEventHandler() {
		buttonClickHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//check if X or O has clicked and edit status and text of button
				if (parent.getParent().getParent().getParent().getTurn() == Status.X) {
					button.setText(Status.X.name());
					status = Status.X;
				} else if (parent.getParent().getParent().getParent().getTurn() == Status.O) {
					button.setText(Status.O.name());
					status = Status.O;
				}
				
				//disable button after clicked + update board
				button.setDisable(true);
				parent.getParent().getParent().getParent().update(selfReference);
			}
		};
	}
	
	//getter and setter methods
	public int getID() { return id; }
	public SubSection getParent() { return parent; }
	public Status getStatus() { return status; }
	public void setStatus(Status status) { this.status = status; }
	public Button getButton() { return button; }
}
