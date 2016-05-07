package pp.ui;

public abstract class Controller {
	View view;

	public Controller(View view) {
		this.view = view;
	}

	public abstract void processInput(String input);
	
}
