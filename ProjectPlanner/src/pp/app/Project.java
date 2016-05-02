package pp.app;

import java.time.LocalDate;

public class Project {
	
	private String title;
	private String description;
	private LocalDate startDate;
	
	public Project(){};
	
	public Project (String title, String description, LocalDate startDate) {
		this.title = title; 
		this.description = description;
		this.startDate = startDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
		
	}

	public String getDescription() {
		return description;
	}

}
