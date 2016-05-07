package pp.ui;

import javafx.scene.text.Text;

public abstract class Screen extends Text {
	protected View view;
	
	public Screen(View view) {
		setId("screen");
		this.view = view;
	}
	
	public abstract void processInput(String input);

}
