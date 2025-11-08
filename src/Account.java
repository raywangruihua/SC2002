package src;
public abstract class Account {
	private String userID;
	private String name;
	private String password;

	public Account(String userID, String name, String password){
		this.userID = userID; 
		this.name = name; 
		this.password = password; 
	}
	public String getUserID(){
		return this.userID;
	}

	public String getName(){
		return this.name;
	}

	public String getPassword(){
		return this.password; 
	}

	public void setPassword(String newPassword){
		this.password = newPassword; 
	}
}