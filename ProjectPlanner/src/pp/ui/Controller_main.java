package pp.ui;

public class Controller_main extends Controller {
	
	public Controller_main(View view) {
		super(view);
		String text = "\n" + 
					  "0) Projects" + "\n" +
				  	  "1) Your activities" + "\n" +
				  	  "2) Your projects" + "\n" +
				  	  "3) logout" + "\n" +
				  	  "\n";
		view.getScreen().appendText(text);
	}
	
	@Override
	public void processInput(String input) {
		if ("0".equals(input)) {
//			view.setController(new Controller_???(view));
		} else if ("1".equals(input)) {
			view.setController(new Controller_activities(view));
		} else if ("2".equals(input)) {
//			view.setController(new Controller_???(view));
		} else if ("3".equals(input)) {
			//TODO ppApp.logout
			view.setController(new Controller_start(view));
		}
	}

}
