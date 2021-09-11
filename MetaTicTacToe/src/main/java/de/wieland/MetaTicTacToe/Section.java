package de.wieland.MetaTicTacToe;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * Class Section represents one large section of the board.
 * Contains 3 Subsections.
 * 
 * @author Moritz Wieland
 * @version 1.0
 */
public class Section {
	//counter + id
	private static int counter = 0;
	private int id;
	
	//game
	private Row parent;
	private SubSection[] subSections = new SubSection[3];
	
	//javafx
	private VBox vbox = new VBox();;
	
	/**
	 * Public constructor.
	 * 
	 * @param parent Reference to the parent of each section
	 */
	public Section(Row parent) {
		//define id and increase counter
		id = counter;
		counter++;
		
		this.parent = parent;
		
		//setup subsections and add each subsection to the VBox
		for (int i = 0; i < subSections.length; i++) {
			subSections[i] = new SubSection(this);
			vbox.getChildren().add(subSections[i].getHBox());
		}
		
		//define VBox
		vbox.setAlignment(Pos.CENTER);
	}
	
	//getter methods
	public int getID() { return id; }
	public Row getParent() { return parent; }
	public SubSection[] getSubSections() { return subSections; }
	public VBox getVBox() { return vbox; }
}
