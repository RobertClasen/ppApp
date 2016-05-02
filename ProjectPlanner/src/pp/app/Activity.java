package pp.app;

import java.time.LocalDate;

public class Activity {
	private PpApp ppApp;
	private String title;
	private String description;
	private LocalDate startDate;
	private int estimatedTime;
	
	public Activity(PpApp ppApp){
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

	public void setEstimatedTime(int i) {
		if (i > 0){			
			this.estimatedTime = i;
		}else{
			throw new InputException("Input is not equal to or higher than zero.");
		}
	}

	public String getTitle() {return title;}
	public String getDescription() {return description;}
	public LocalDate getStartDate() {return startDate;}
	public int getEstimatedTime(){return this.estimatedTime;}
	
}
