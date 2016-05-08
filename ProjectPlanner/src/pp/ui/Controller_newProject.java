package pp.ui;

public class Controller_newProject extends Controller {

	public Controller_newProject(View view) {
		super(view);
		String output = "0) Yes" + "\n" +
					    "1) No";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		if ("0".equals(input)) {
			view.setController(new Controller_projectTitle(view));			
		} else if ("1".equals(input)) {
			view.setController(new Controller_main(view));
		} else {
			screen.invalidInput();
		}
	}
}
