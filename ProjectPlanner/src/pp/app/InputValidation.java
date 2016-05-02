package pp.app;

import java.time.LocalDate;

public class InputValidation {
	
	
	
	public void stringLength(String s, int min, int max) {
		if (s.length() > max || s.length() < min) {
				throw new InputException("Invalid length.");
		}
	}

	public void dateIsNotInPast(LocalDate startDate) {
		if (!startDate.isAfter(LocalDate.now())) {
			throw new InputException("Date is in the past.");
		}
		
	}
	
	
	
}