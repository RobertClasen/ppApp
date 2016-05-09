package pp.ui;

import pp.app.Project;
import pp.app.ProjectException;

public class Controller_main extends Controller {
	
	public Controller_main(View view) {
		super(view);
		
		String output = "0) Your projects" + "\n" +
				  	    "1) Your activities" + "\n" +
				  	    "2) Manage project" + "\n" +
				  	    "3) Create project" + "\n" +
				  	    "4) Register new user" + "\n" +
				  	    "5) Register absence" + "\n" +
				  	    "6) Logout";
		
		screen.appendText(output);
	}
	
	@Override
	public void processInput(String input) {
		if ("0".equals(input)) {
			String output = "You are currently contributing to the following projects" + "\n";
			for (Project p : view.getPpApp().getLoggedInUser().getProjects()){
				output += "\t" + p.getTitle() + "\n";
			}
			screen.appendText(output);
		} else if ("1".equals(input)) {
			if (view.getPpApp().getLoggedInUser().getActivities().size() > 0 ||
					view.getPpApp().getLoggedInUser().getAssistanceActivities().size() > 0) {
				view.setController(new Controller_activities(view));
			} else {
				screen.appendText("You are currently not assigned to any activities.");				
			}
			
		} else if ("2".equals(input)) {
			boolean projectLeader = false;
			for (Project p : view.getPpApp().getProjects()) {
				if (p.getProjectLeader().equals(view.getPpApp().getLoggedInUser())) {
					view.setController(new Controller_leadingProject(view, p));
					projectLeader = true;
				}
			}
			if (!projectLeader) {
				screen.appendText("You are currently not leading any projects." + "\n");			
			}
			
		} else if ("3".equals(input)) {
			view.setController(new Controller_projectTitle(view));
		} else if ("4".equals(input)) {
			view.setController(new Controller_userFirstName(view));
		} else if ("5".equals(input)) {
			view.setController(new Controller_absenceStartDate(view));
		} else if ("6".equals(input)) {
			view.getPpApp().setLoggedIn(false);
			screen.appendText("Logged out.");
			view.setController(new Controller_start(view));	
		} else {
			screen.invalidInput();			
		}
	}

}
