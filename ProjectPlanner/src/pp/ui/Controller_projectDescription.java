package pp.ui;

import pp.app.InputException;
import pp.app.Project;

/**
 * @author Robert
 */

public class Controller_projectDescription extends Controller {
	private Project project;
	
	public Controller_projectDescription(View view, Project project) {
		super(view);

		this.project = project;
		
		String output = "Project description?";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		try {
			project.setDescription(input);
			view.setController(new Controller_projectStartDate(view, project));
		} catch (InputException e) {
			screen.appendText("Invalid description length. Try again");			
		}
	}
}
