package pp.ui;

import pp.app.Project;
import pp.app.StatusReport;

public class Controller_leadingProject extends Controller {
	Project project;

	public Controller_leadingProject(View view, Project project) {
		super(view);
		this.project = project;
		
		String output = "You are managing this project" + "\n" + project.toString() + "\n" +
						"0) Create activity" + "\n" +
						"1) Assign user to activity" + "\n" +
						"2) Query status report";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		if ("0".equals(input)) {
			// TODO view.setController(new Controller_???(view));
		} else if ("1".equals(input)) {
			// TODO view.setController(new Controller_???(view));
		} else if ("2".equals(input)) {
			// TODO view.setController(new Controller_???(view));
			screen.appendText(new StatusReport(project).generate());
		} else if ("3".equals(input)) {
			// TODO view.setController(new Controller_???(view));
		} else {
			screen.invalidInput();			
		}
	}
}