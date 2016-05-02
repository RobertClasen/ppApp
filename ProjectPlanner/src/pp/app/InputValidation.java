package pp.app;

import java.time.LocalDate;

public class InputValidation {
	
	
	
	public void stringLength(String s, int min, int max) {
		if (s.length() > max || s.length() < min) {
				throw new InputException("Invalid length.");
		}
	}

	public void dateIsNotInPast(LocalDate startDate) {
	
		System.out.println("Foo bar");
	}
	
	
	
}