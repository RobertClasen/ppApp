package pp.ui;

import javafx.scene.text.Text;

public class Screen extends Text {
	protected View view;
	protected String text;
	
	public Screen(View view) {
		setId("screen");
		this.view = view;
		text = "";
	}
	
	public void appendText(String s) {
		text += s;
		setText(text);
	}
	
}
