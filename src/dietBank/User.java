package dietBank;

/**
 * class User contains the fields, constructor, and getter methods that define what a user is
 * A user will have a Username, Email, and Password
 */
public class User {
	private final String username;
	private final String email;
	private final String password;
	
	/**
	 * Constructor for class User
	 * Initializes the 3 fields
	 * @param username Users Username
	 * @param email Users email
	 * @param password Users password
	 */
	public User(String username, String email, String password){
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	/**
	 * Getter method for username
	 * @return the users username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Getter method for email
	 * @return the users emai
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Getter method for email
	 * @return the users email
	 */
	public String getPassword() {
		return password;
	}
}