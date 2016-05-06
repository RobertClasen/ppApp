package pp.ui;

public class MainScreen extends Screen {

	public MainScreen() {
		setText("0) foo" + "\n" +
				"1) bar" + "\n" +
				"2) moo");
	}
	
	@Override
	public void processInput(String input) {
		if ("0".equals(input)) {
			// Do stuff
			System.out.println("Did stuff...");
		} else if ("1".equals(input)) {
			// Do other stuff
		} else {
			// Do nothing...
		}
	}

}
