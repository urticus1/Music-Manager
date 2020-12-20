package RuaridhBell.demo;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Loader {

	private String sourceDataPath ="src/main/resources/data.txt" ;
	private BufferedReader br;
	private Connection con;
	
	public Loader() {
		
		try {
			con = DriverManager.getConnection(DemoApplication.connectionUrl,DemoApplication.dbUser,DemoApplication.dbPassword);
			Statement stmt = con.createStatement();
			
			
			//Create tables in Music database: USERS, SINGERS, ALBUMS
			
			
			String sql = "DROP TABLE IF EXISTS users;" + 
					   " CREATE TABLE users" +
	                   " (username VARCHAR(15), " + 
	                   " password VARCHAR(15), " + 
	                   " PRIMARY KEY ( username ));"; 
			stmt.executeUpdate(sql);
			
			
			sql = "DROP TABLE IF EXISTS singers;" +  
					   "CREATE TABLE singers " +
	                   "(name VARCHAR(50), " + 
	                   "dob DATE, " +
	                   "sex VARCHAR(6), " + 
	                   "company VARCHAR(50), " +
	                   " PRIMARY KEY ( name ));"; 
			stmt.executeUpdate(sql);
			
			
			sql = "DROP TABLE IF EXISTS albums;" + 
					   "CREATE TABLE albums " +
	                   "(singer VARCHAR(50), " + 
	                   "album VARCHAR(50), " + 
	                   "year INTEGER," +
	                   "company VARCHAR(50), " +
	                   "PRIMARY KEY ( album ))"; 
			stmt.executeUpdate(sql);
			
		}
		catch(Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	 

	public void loadFiles() throws Exception{
		
		String line;

		br = new BufferedReader(new FileReader(sourceDataPath));
		Statement stmt = con.createStatement();
		while((line = br.readLine()) != null){
				
			String[] fields  = line.split("\\|");
			String sql = "";
			
			switch(fields[0]){
				
			case "A": //albums
				
				sql = "INSERT INTO albums VALUES(";
				break;
				
			case "S":  //singers

				sql = "INSERT INTO singers VALUES(";
				break;
				
				
			case "U": //users

				sql = "INSERT INTO users VALUES(";
				break;
			}
			
			for(int i =1; i< fields.length;i++) {
				sql+= i < fields.length-1 ? "'" + fields[i].trim().replaceAll("'", "''") +"', " : "'" + fields[i].trim().replaceAll("'", "''") + "'";
			}
			sql += ");";

			stmt.executeUpdate(sql);
		}

	}
}
