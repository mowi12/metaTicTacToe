package de.wieland.MetaTicTacToe;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * Class SubSection represents one row in one section of the board.
 * Contains 3 Fields.
 * 
 * @author Moritz Wieland
 * @version 1.0
 */
public class SubSection {
	//game
	private Section parent;
	private Field[] fields = new Field[3];
	
	//javafx
	private HBox hbox = new HBox();
	
	/**
	 * Public constructor.
	 * 
	 * @param parent Reference to the parent of each subsection
	 */
	public SubSection(Section parent) {
		this.parent = parent;
		
		//setup each field and add each field to the HBox
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new Field(this);
			hbox.getChildren().add(fields[i].getButton());
		}
		
		//define HBox
		hbox.setAlignment(Pos.CENTER);
	}
	
	//getter methods
	public Section getParent() { return parent; }
	public Field[] getFields() { return fields; }
	public HBox getHBox() { return hbox; }
}
