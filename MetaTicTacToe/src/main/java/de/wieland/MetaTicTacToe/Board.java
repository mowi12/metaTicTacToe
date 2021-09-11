package de.wieland.MetaTicTacToe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Class handles the main game.
 * 
 * @author Moritz Wieland
 * @version 1.0
 */
public class Board {
	//game
	private Status turn = Status.X;
	private int xPoints = 0;
	private int oPoints = 0;
	
	//javafx - game
	private VBox gameVBox = new VBox();
	private Row[] rows = new Row[3];
	//javafx - turn
	private VBox turnVBox = new VBox();
	private Label turnTextLabel;
	private Label statusLabel;
	
	//logic
	private Status wonA = Status.EMPTY;
	private Status wonB = Status.EMPTY;
	private Status wonC = Status.EMPTY;
	private Status wonD = Status.EMPTY;
	private Status wonE = Status.EMPTY;
	private Status wonF = Status.EMPTY;
	private Status wonG = Status.EMPTY;
	private Status wonH = Status.EMPTY;
	private Status wonI = Status.EMPTY;
	
	/**
	 * Public constructor.
	 */
	public Board() {
		//setup rows and add each row to the gameVBox
		for (int i = 0; i < rows.length; i++) {
			rows[i] = new Row(this);
			gameVBox.getChildren().add(rows[i].getHBox());
		}
		
		//setup all other javafx parts
		setupJavafx();
	}
	
	/**
	 * Private Method to setup all other javafx parts.
	 */
	private void setupJavafx() {
		//define turnTextLabel
		turnTextLabel = new Label("Turn:");
		turnTextLabel.setFont(Font.font(App.FONTSIZE));
		
		//define statusLabel
		statusLabel = new Label(Status.X.name());
		statusLabel.setFont(Font.font(App.FONTSIZE));
		
		//define turnVBox and add turnTextLabel and statusLabel
		turnVBox.setAlignment(Pos.CENTER);
		turnVBox.setPadding(new Insets(App.INSET, 0, 0, 0));
		turnVBox.getChildren().addAll(turnTextLabel, statusLabel);
		
		//define gameVBox
		gameVBox.setAlignment(Pos.CENTER);
		gameVBox.setSpacing(10);
	}
	
	//------------------------- LOGIC -------------------------//
	
	/**
	 * Public Method updates the board and all its components.
	 * 
	 * @param field Field contains the Button which executed this method
	 */
	public void update(Field field) {
		//check if game is won or the board is full, else next turn
		if (!checkVictory()) {
			if (!checkFull()) {
				nextTurn();
				disableAllButtons();
				enableButtons(field);
			}
		}
	}
	
	/**
	 * Private Method checks if one player has won the game.
	 * If one player has won, a victory alert is poping up.
	 * 
	 * @return true if one player won, else false
	 */
	private boolean checkVictory() {
		//before checking the big sections, each section has to be checked for a tiny victory
		updateTinyVictories();
		
		//check horizontal victory
		if (wonA == wonB && wonB == wonC) {
			if (wonA == Status.X) {
				victoryAlert(Status.X);
				return true;
			} else if (wonA == Status.O) {
				victoryAlert(Status.O);
				return true;
			}
		}
		if (wonD == wonE && wonE == wonF) {
			if (wonD == Status.X) {
				victoryAlert(Status.X);
				return true;
			} else if (wonD == Status.O) {
				victoryAlert(Status.O);
				return true;
			}
		}
		if (wonG == wonH && wonH == wonI) {
			if (wonG == Status.X) {
				victoryAlert(Status.X);
				return true;
			} else if (wonG == Status.O) {
				victoryAlert(Status.O);
				return true;
			}
		}
		
		//check vertical victory
		if (wonA == wonD && wonD == wonG) {
			if (wonA == Status.X) {
				victoryAlert(Status.X);
				return true;
			} else if (wonA == Status.O) {
				victoryAlert(Status.O);
				return true;
			}
		}
		if (wonB == wonE && wonE == wonH) {
			if (wonB == Status.X) {
				victoryAlert(Status.X);
				return true;
			} else if (wonB == Status.O) {
				victoryAlert(Status.O);
				return true;
			}
		}
		if (wonC == wonF && wonF == wonI) {
			if (wonC == Status.X) {
				victoryAlert(Status.X);
				return true;
			} else if (wonC == Status.O) {
				victoryAlert(Status.O);
				return true;
			}
		}
		
		//check diagonal victory
		if (wonA == wonE && wonE == wonI) {
			if (wonA == Status.X) {
				victoryAlert(Status.X);
				return true;
			} else if (wonA == Status.O) {
				victoryAlert(Status.O);
				return true;
			}
		}
		if (wonC == wonE && wonE == wonG) {
			if (wonC == Status.X) {
				victoryAlert(Status.X);
				return true;
			} else if (wonC == Status.O) {
				victoryAlert(Status.O);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Private Method update if a player has won a game in a section (not the whole game).
	 * Sets corresponding logic variables if a player has won a section.
	 */
	private void updateTinyVictories() {
		//iterate through all sections
		for (Row row : rows) {
			for (Section section : row.getSections()) {
				int sectionID = section.getID();
				
				Field f1 = section.getSubSections()[0].getFields()[0];
				Field f2 = section.getSubSections()[0].getFields()[1];
				Field f3 = section.getSubSections()[0].getFields()[2];
				Field f4 = section.getSubSections()[1].getFields()[0];
				Field f5 = section.getSubSections()[1].getFields()[1];
				Field f6 = section.getSubSections()[1].getFields()[2];
				Field f7 = section.getSubSections()[2].getFields()[0];
				Field f8 = section.getSubSections()[2].getFields()[1];
				Field f9 = section.getSubSections()[2].getFields()[2];
				
				//check tiny horizontal victory
				if (f1.getStatus() == f2.getStatus() && f2.getStatus() == f3.getStatus()) {
					if (f1.getStatus() == Status.X) {
						setWon(sectionID, Status.X);
					} else if (f1.getStatus() == Status.O) {
						setWon(sectionID, Status.O);
					}
				}
				if (f4.getStatus() == f5.getStatus() && f5.getStatus() == f6.getStatus()) {
					if (f4.getStatus() == Status.X) {
						setWon(sectionID, Status.X);
					} else if (f4.getStatus() == Status.O) {
						setWon(sectionID, Status.O);
					}
				}
				if (f7.getStatus() == f8.getStatus() && f8.getStatus() == f9.getStatus()) {
					if (f7.getStatus() == Status.X) {
						setWon(sectionID, Status.X);
					} else if (f7.getStatus() == Status.O) {
						setWon(sectionID, Status.O);
					}
				}
				
				//check tiny vertical victory
				if (f1.getStatus() == f4.getStatus() && f4.getStatus() == f7.getStatus()) {
					if (f1.getStatus() == Status.X) {
						setWon(sectionID, Status.X);
					} else if (f1.getStatus() == Status.O) {
						setWon(sectionID, Status.O);
					}
				}
				if (f2.getStatus() == f5.getStatus() && f5.getStatus() == f8.getStatus()) {
					if (f2.getStatus() == Status.X) {
						setWon(sectionID, Status.X);
					} else if (f2.getStatus() == Status.O) {
						setWon(sectionID, Status.O);
					}
				}
				if (f3.getStatus() == f6.getStatus() && f6.getStatus() == f9.getStatus()) {
					if (f3.getStatus() == Status.X) {
						setWon(sectionID, Status.X);
					} else if (f3.getStatus() == Status.O) {
						setWon(sectionID, Status.O);
					}
				}
				
				//check tiny diagonal victory
				if (f1.getStatus() == f5.getStatus() && f5.getStatus() == f9.getStatus()) {
					if (f1.getStatus() == Status.X) {
						setWon(sectionID, Status.X);
					} else if (f1.getStatus() == Status.O) {
						setWon(sectionID, Status.O);
					}
				}
				if (f3.getStatus() == f5.getStatus() && f5.getStatus() == f7.getStatus()) {
					if (f3.getStatus() == Status.X) {
						setWon(sectionID, Status.X);
					} else if (f3.getStatus() == Status.O) {
						setWon(sectionID, Status.O);
					}
				}
			}
		}
	}
	
	/**
	 * private Method sets corresponding logic variables.
	 * 
	 * @param sectionID Section which should be set
	 * @param status Status to which the section should be set
	 */
	private void setWon(int sectionID, Status status) {
		switch(sectionID) {
			case 0: wonA = status;
					break;
			case 1: wonB = status;
					break;
			case 2: wonC = status;
					break;
			case 3: wonD = status;
					break;
			case 4: wonE = status;
					break;
			case 5: wonF = status;
					break;
			case 6: wonG = status;
					break;
			case 7: wonH = status;
					break;
			case 8: wonI = status;
					break;
		}
	}
	
	/**
	 * Private Method checks if all fields are labeled.
	 * Pops up a reset alert.
	 * 
	 * @return true if board is full, else false
	 */
	private boolean checkFull() {
		for (Row row : rows) {
			for (Section section : row.getSections()) {
				for (SubSection subSection : section.getSubSections()) {
					for (Field field : subSection.getFields()) {
						if (field.getStatus() == Status.EMPTY) {
							return false;
						}
					}
				}
			}
		}
		
		resetAlert();
		return true;
	}
	
	/**
	 * Private Method creates a Confirmation Alert which player has won.
	 * By pressing OK the game resets, else the game closes.
	 * 
	 * @param status Player who won the game
	 */
	private void victoryAlert(Status status) {
		//increase score
		if (status == Status.X) {
			xPoints++;
		} else {
			oPoints++;
		}
		
		//create alert
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setResizable(false);
		alert.setHeaderText(status.name() + " won the game!\n " + xPoints + " - " + oPoints + "\nGame is resetting!");
		alert.setContentText("Press OK to confirm - Press CANCEL to end the game");
		alert.setTitle("Reset");
		alert.showAndWait();
		
		if (alert.getResult().equals(ButtonType.OK)) {
			reset();
		} else if (alert.getResult().equals(ButtonType.CANCEL)) {
			System.exit(0);
		}
	}
	
	/**
	 * Private Method creates a Confirmation Alert.
	 * By pressing OK the game resets, else the game closes.
	 */
	private void resetAlert() {
		//create alert
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setResizable(false);
		alert.setHeaderText("Game is resetting!");
		alert.setContentText("Press OK to confirm - Press CANCEL to end the game");
		alert.setTitle("Reset");
		alert.showAndWait();
		
		if (alert.getResult().equals(ButtonType.OK)) {
			reset();
		} else if (alert.getResult().equals(ButtonType.CANCEL)) {
			System.exit(0);
		}
	}
	
	/**
	 * Private Method resets the whole game.
	 */
	private void reset() {
		//iterate through all fields and reset status and labeled buttons
		for (Row row : rows) {
			for (Section section : row.getSections()) {
				for (SubSection subSection : section.getSubSections()) {
					for (Field field : subSection.getFields()) {
						field.getButton().setText("");
						field.getButton().setDisable(false);
						field.setStatus(Status.EMPTY);
					}
				}
			}
		}
		
		//set all section to won by no one
		wonA = Status.EMPTY;
		wonB = Status.EMPTY;
		wonC = Status.EMPTY;
		wonD = Status.EMPTY;
		wonE = Status.EMPTY;
		wonF = Status.EMPTY;
		wonG = Status.EMPTY;
		wonH = Status.EMPTY;
		wonI = Status.EMPTY;
	}
	
	/**
	 * Private Method updates the turn.
	 */
	private void nextTurn() {
		if (turn == Status.X) {
			turn = Status.O;
		} else {
			turn = Status.X;
		}
		
		//set text of statusLabel to current turn
		statusLabel.setText(turn.name());
	}
	
	/**
	 * Private Method disables all buttons.
	 */
	private void disableAllButtons() {
		//iterate through all field and disable them
		for (Row row : rows) {
			for (Section section : row.getSections()) {
				for (SubSection subSection : section.getSubSections()) {
					for (Field field : subSection.getFields()) {
						field.getButton().setDisable(true);
					}
				}
			}
		}
	}
	
	/**
	 * Private Method enables buttons in one specific section.
	 * The buttons of the specific section are defined by the button which was last pressed.
	 * 
	 * @param field Field which holds the button which was pressed last
	 */
	private void enableButtons(Field field) {
		//fieldID is the ID of the field which contains the button which was pressed last
		int fieldID = field.getID();
		
		//iterate through all fields and enable all fields in the section where the section has the same id as the pressed field
		for (Row row : rows) {
			for (Section section : row.getSections()) {
				if (section.getID() == fieldID) {
					for (SubSection subSection : section.getSubSections()) {
						for (Field vield : subSection.getFields()) {
							if (vield.getStatus() == Status.EMPTY) {
								vield.getButton().setDisable(false);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * TODO
	 * @return
	 */
//	private void playerResetAlert() {
//		Alert alert = new Alert(AlertType.CONFIRMATION);
//		alert.setResizable(false);
//		alert.setHeaderText("Game is resetting!");
//		alert.setContentText("Press OK to confirm - Press CANCEL to end the game");
//		alert.setTitle("Reset");
//		alert.showAndWait();
//		
//		if (alert.getResult().equals(ButtonType.OK)) {
//			reset();
//		}
//	}
	
	//getter methods
	public Status getTurn() { return this.turn; }
	public VBox getGameVBox() { return gameVBox; }
	public VBox getTurnVBox() { return turnVBox; }
}
