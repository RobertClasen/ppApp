package pp.app;

import java.time.LocalDate;

public class Project {

	private PpApp ppApp;
	private String title;
	private String description;
	private LocalDate startDate;

	public Project(PpApp ppApp) {
		this.ppApp = ppApp;
	}
	
	public void setTitle(String title) {
		if (ppApp.getInputValidation().stringLength(title, 2, 25))
			this.title = title;
	}

	public void setDescription(String description) {
		if (ppApp.getInputValidation().stringLength(description, 2, 256))
			this.description = description;
	}
	
	public void setStartDate(LocalDate startDate) {
		if (ppApp.getInputValidation().dateIsNotInPast(startDate))
			this.startDate = startDate;
	}

	public String getTitle() {	return title; }
	public String getDescription() { return description; }
	public LocalDate getStartDate() { return startDate; }

}
