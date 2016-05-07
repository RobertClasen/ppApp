package pp.ui;

import pp.app.User;

public abstract class Controller {
	protected View view;
	protected Screen screen;
	protected User user; // Gets assigned when a user logs in. 

	public Controller(View view) {
		this.view = view;
		this.screen = this.view.getScreen();
	}

	public abstract void processInput(String input);
	
}
