package RuaridhBell.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
public class DemoApplication {

	public static final String connectionUrl = "jdbc:sqlserver://localhost;database=Music";
	public static final String dbUser = "MusicAdmin";
	public static final String dbPassword = "password";
	
	public static void main(String[] args) {
		
		try {
			Loader ld = new Loader();
			ld.loadFiles();
		}
		catch(Exception e) {
			System.out.println(e);
			System.exit(0);
		}
		
		SpringApplication.run(DemoApplication.class, args);
		
		
	}

}
