package pp.ui;

import java.time.LocalDate;

import pp.app.InputException;
import pp.app.Project;

/**
 * @author Pelle
 */

public class Controller_projectStartDate extends Controller {
private Project project;
	
	public Controller_projectStartDate(View view, Project project) {
		super(view);

		this.project = project;
		
		String output = "Project start date? (YYYY-MM-DD)";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		int year, month, day;
		try {
			year = Integer.parseInt(input.substring(0, 4));
			month = Integer.parseInt(input.substring(5, 7));
			day = Integer.parseInt(input.substring(8, 10));
			LocalDate startDate = LocalDate.of(year, month, day);
			project.setStartDate(startDate);
			view.getPpApp().addProject(project);
			screen.appendText("Project succesfully created" + "\n\n" + project.toString());
			view.setController(new Controller_main(view));
		} catch (StringIndexOutOfBoundsException e) {
			screen.appendText("Invalid format. Try again.");			
		} catch (InputException e) {
			screen.appendText("Date must be in the future. Try again.");			
		}
	}

}
