package pp.app;

import java.time.LocalDate;

public class WorkSession {
	protected LocalDate date;
	protected Activity activity;
	protected Long workTime;
	
	public WorkSession(LocalDate today, Activity activity, Long workTime){
		this.date = today;
		this.activity = activity;
		this.workTime = workTime;
	}
}
