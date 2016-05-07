package pp.ui;

public class MainScreen extends Screen {
	private String text;

	public MainScreen(View view) {
		super(view);
		text = "0) foo" + "\n" +
		       "1) bar" + "\n" +
			   "2) moo";
		setText(text);
	}
	
	@Override
	public void processInput(String input) {
		if ("0".equals(input)) {
			// Do stuff
			System.out.println("Did stuff...");
		} else if ("1".equals(input)) {
			text += "\n" + "Lorem ipsum dolor sit amet...";
			setText(text);
			view.getScrollPane().setVvalue(1.0);
		} else {
			// Do nothing...
		}
	}

}
