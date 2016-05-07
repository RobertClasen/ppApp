package pp.ui;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class View extends BorderPane {
	private ScrollPane scrollPane;
	private Screen text;
	private InputField textField;

	public View() {
		setId("root");
		
		createNodes();
		arrangeNodes();
	}
	
	private void createNodes() {
		scrollPane = new ScrollPane();
		text = new MainScreen(this);
		textField = new InputField(this);
	}
	
	private void arrangeNodes() {
		setCenter(scrollPane);
		setBottom(textField);
		scrollPane.setContent(text);
	}

	public void setScreen(Screen newScreen) { this.text = newScreen; }
	public Screen getScreen() { return this.text; }

	public ScrollPane getScrollPane() {
		return scrollPane;
	}
}
