package pp.ui;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Kristian
 */

public class InputField extends TextField {
	private View view;
	
	public InputField(View view) {
		this.view = view;
		
		setOnKeyPressed(certainKeyRules);
		setOnAction(e -> { handleInput(); });
	}
	
	private void handleInput() {
		String input = getText();
		view.getController().processInput(input);
	}

	private EventHandler<KeyEvent> certainKeyRules = keyEvent -> {
		if (keyEvent.getCode() == KeyCode.ESCAPE) {
			if (view.getPpApp().isLoggedIn()) {
				view.setController(new Controller_main(view));
			}
		}
	};

	
	
}
