package pp.app;

public class User {
	private String firstname;
	private String lastname;
	private String userId;	
	private final static int NAME_MAX_LENGTH = 15;
	private final static int NAME_MIN_LENGTH = 2;
	
	public User(String firstname, String lastname) throws RegistrationException {
		validateNames(firstname, lastname);
	}
	
	/**
	 * User names are constrained by certain rules. 
	 */
	private void validateNames(String firstname, String lastname) throws RegistrationException {
		checkForLength(firstname, lastname);
		checkForIllegalCharacters(firstname, lastname);
		assignNames(firstname, lastname);
	}
	/**
	 * Length of names must be between 2 and 15, both inclusive. 
	 */
	private void checkForLength(String firstname, String lastname) throws RegistrationException {
		if (firstname.length() > NAME_MAX_LENGTH || lastname.length() > NAME_MAX_LENGTH ||
				firstname.length() < NAME_MIN_LENGTH || lastname.length() < NAME_MIN_LENGTH) {
				throw new RegistrationException("Invalid name length.");
			}
	}
	/**
	 * Only letters are allowed.
	 * TODO: Test for two-part names? 
	 */
	private void checkForIllegalCharacters(String firstname, String lastname) throws RegistrationException {
		String name = firstname + lastname;
		for (int i = 0; i < name.length(); i++) {
			if (!Character.isLetter(name.charAt(i))) {
				throw new RegistrationException("Name contains illegal character(s).");
			}
		}
	}
	/**
	 * If both firstname and lastname complies with above rules, they are assigned to the class' fields.
	 */
	private void assignNames(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	/**
	 * Getters and setter 
	 */
	public String getFirstname() { return firstname; }
	public void setFirstname(String firstname) { this.firstname = firstname; }
	public String getLastname() { return lastname; }
	public void setLastname(String lastname) { this.lastname = lastname; }
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }
	
}
