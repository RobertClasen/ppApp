package pp.ui;

import javafx.scene.control.TextField;

public class InputField extends TextField {
	private View view;
	
	public InputField(View view) {
		this.view = view;
		
		setOnAction(e -> { handleInput(); });
	}
	
	private void handleInput() {
		String input = getText();
		view.getController().processInput(input);
	}

}
