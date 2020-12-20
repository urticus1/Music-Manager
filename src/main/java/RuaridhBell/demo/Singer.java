package RuaridhBell.demo;



import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Singer implements IRecord {

	private String name;
	private String dob;
	private String sex;
	private String company;
	

	public Singer(String name, String dob, String sex, String company) {
		
		this.name = name;
		this.dob = dob;
		this.sex = sex;
		this.company = company;
	}
	

	public String insertInto(String database) {
		
		String sql = "INSERT INTO " + database + " VALUES ('" +
		name +"', '" + dob +"', '"+sex+"', '" +company+"');";
		return sql;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRecord() {
		return "Singer";
	}

}
