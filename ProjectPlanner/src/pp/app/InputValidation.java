package pp.app;

import java.time.LocalDate;

public class InputValidation {
	
	public boolean stringLength(String s, int min, int max) {
		if (s.length() > max || s.length() < min) {
			throw new InputException("Invalid length.");
		}
		return true;
	}

	public boolean dateIsNotInPast(LocalDate startDate) {
		if (!startDate.isAfter(LocalDate.now())) {
			throw new InputException("Date is in the past.");
		}
		return true;
	}
	
	
	
}