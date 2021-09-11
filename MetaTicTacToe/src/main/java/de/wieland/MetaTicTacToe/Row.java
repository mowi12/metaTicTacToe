package de.wieland.MetaTicTacToe;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * Class Row represents one large row of the board.
 * Contains 3 Sections.
 * 
 * @author Moritz Wieland
 * @version 1.0
 */
public class Row {
	//game
	private Board parent;
	private Section[] sections = new Section[3];
	
	//javafx
	private HBox hbox = new HBox();
	
	/**
	 * Public constructor.
	 * 
	 * @param parent Reference to the parent of each row
	 */
	public Row(Board parent) {
		this.parent = parent; 
		
		//setup sections and add each section to the HBox
		for(int i = 0; i < sections.length; i++) {
			sections[i] = new Section(this);
			hbox.getChildren().add(sections[i].getVBox());
		}
		
		//define HBox
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(10);
	}
	
	//getter methods
	public Board getParent() { return parent; }
	public Section[] getSections() { return sections; }
	public HBox getHBox() { return hbox; }
}
