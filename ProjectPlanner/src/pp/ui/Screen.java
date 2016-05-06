package pp.ui;

import javafx.scene.control.Label;

public abstract class Screen extends Label {
	
	public Screen() {
		
	}
	
	public abstract void processInput(String input);

}
