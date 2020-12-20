package RuaridhBell.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User implements IRecord{

	private String username;
	private String password;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String insertInto(String database) {
		
		String sql = "INSERT INTO " + database + " VALUES ('"+username+"', '"+password+"');";
		return sql;
	}
}
