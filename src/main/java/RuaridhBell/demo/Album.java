package RuaridhBell.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Album implements IRecord {

	private String singer;
	private String album;
	private String year;
	private String company;
	
	
	public Album(String singer, String album, String year, String company) {
		this.singer = singer;
		this.album = album;
		this.year = year;
		this.company = company;
	}
	
	
	public String insertInto(String database) {
		
		String sql = "INSERT INTO " + database + " VALUES ('" +
		singer +"', '"+album+"', '" + year +"', '" + company+"');";
		return sql;
	}
	
	
	public String getRecord() {
		return "Album";
	}
	
	public String getName() {
		return album;
	}

}
