package motion.fashion.network.model;

public class User {
    
	 
    private Long userId;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private String company;
    private String email;
    private String encrytedPassword;
 
    public User() {
 
    }
 
    public User(Long userId, String firstName, String lastName, boolean enabled, String company, String email, String encrytedPassword) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.company = company;
        this.email = email;
        this.encrytedPassword = encrytedPassword;
    }
 
    public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
  
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getEncrytedPassword() {
        return encrytedPassword;
    }
 
    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }
}