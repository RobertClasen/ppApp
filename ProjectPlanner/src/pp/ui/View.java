package pp.ui;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class View extends BorderPane {
	private VBox vBox;
	private Screen screen;
	private TextField textField;

	public View() {
		setId("root");
		vBox = new VBox();
		screen = new MainScreen();
		textField = new InputField(this);
		attachNodes();
	}
	
	private void attachNodes() {
		setCenter(vBox);
		setBottom(textField);
		
		vBox.getChildren().add(screen);
	}

	public void setScreen(Screen newScreen)	{ this.screen = newScreen; }
	public Screen getScreen() { return this.screen; }
	
}
