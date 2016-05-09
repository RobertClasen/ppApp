package pp.ui;

import pp.app.InputException;
import pp.app.Project;

/**
 * @author Pelle
 */

public class Controller_projectTitle extends Controller {
	private Project project;
	
	public Controller_projectTitle(View view) {
		super(view);

		project = new Project(view.getPpApp());
		
		String output = "Project title?";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		try {
			project.setTitle(input);
			view.setController(new Controller_projectDescription(view, project));
		} catch (InputException e) {
			screen.appendText("Invalid title lenght. Try again.");			
		}
	}

}
