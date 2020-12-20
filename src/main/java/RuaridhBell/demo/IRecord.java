package RuaridhBell.demo;

public interface IRecord {

	
	//produces sql statement that will insert object into the supplied database
	public String insertInto(String database);
}
