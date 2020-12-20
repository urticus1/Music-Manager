package RuaridhBell.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ServiceLayer {

	private Connection con;
	
	public ServiceLayer() throws Exception {
		con = DriverManager.getConnection(DemoApplication.connectionUrl,DemoApplication.dbUser,DemoApplication.dbPassword);
	}
	
	public boolean validLogin(String username, String password) throws Exception {
		
		Statement stmt = con.createStatement();
		String sql ="SELECT * FROM users WHERE username ='"+username+"' AND password ='"+password+"'";
		ResultSet user = stmt.executeQuery(sql);
		

		return user.next();
	}
	
	public void addRecord(String database, IRecord record) throws SQLException {
		
		Statement stmt = con.createStatement();
		stmt.execute(record.insertInto(database));
	}
	
	public ArrayList<IRecord> search(String query) throws Exception{

		ArrayList<IRecord> records = new ArrayList<IRecord>();
		Statement stmt = con.createStatement();
		String sql ="SELECT * FROM singers WHERE name ='"+query+"' OR company = '"+query+"' OR sex = '"+query+"';";
		ResultSet rslt = stmt.executeQuery(sql);
		
		int length = rslt.getMetaData().getColumnCount();
		while(rslt.next()) {
			String[] fields = new String[length];
			
			for(int i =0; i < length; i++) {
				fields[i] = rslt.getString(i+1);
				System.out.println(fields[i]);
			}
			
			Singer singer = new Singer(fields[0],fields[1],fields[2],fields[3]);
			records.add(singer);
			
		}
		
		sql ="SELECT * FROM albums WHERE singer ='"+query+"' OR album = '"+query+"' OR company = '"+query+"';";
		rslt = stmt.executeQuery(sql);
		 length = rslt.getMetaData().getColumnCount();
		while(rslt.next()) {
			String[] fields = new String[length];
			
			for(int i =0; i < length; i++) {
				fields[i] = rslt.getString(i+1);
			}
			
			Album album = new Album(fields[0],fields[1],fields[2],fields[3]);
			records.add(album);

			
		}
		return records;
	}
}