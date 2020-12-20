package RuaridhBell.demo;

import java.sql.SQLException;
import java.util.List;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * A REST controller for managing the music catalogue via the Music service
 *
 * TODO: Add more methods
 *
 * @author nextgate.employee
 */
@RestController
public class MusicRestController {
    
    private ServiceLayer service;
    
    public MusicRestController() {
    	try {
    		
    		service = new ServiceLayer();
    	}
    	catch(Exception e) {
    		System.out.println("Could not initialise service layer: exiting");
    		System.exit(0);
    	}
    }
    

    @RequestMapping("/login")
    public RedirectView login(@RequestParam("username") String user, @RequestParam("password") String password) {
    	
    	RedirectView rd = new RedirectView();
    	rd.setUrl("/Home.html");
    	System.out.println(rd);
    	
    	try {
	    	if(service.validLogin(user, password) )
	    		return rd;
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    	return new RedirectView("/loginPage.html");
    }
    
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addUser(User data) {
    	try {
    		service.addRecord("users", data);
    	}
    	catch(SQLException e) {
    		switch(e.getErrorCode()) {
    		case 2627:
    			return "Duplicate record Error";
    		default:
    			return "Server Error";
    		}
    		

    	}
    	return "success";
    }
    
    @RequestMapping(value = "/addAlbum", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addAlbum(Album data) {
    	try {
    		service.addRecord("albums", data);
    	}
    	catch(SQLException e) {
    		switch(e.getErrorCode()) {
    		case 241:
    			return "Invalid data format";
    		case 2627:
    			return "Duplicate record Error";
    		default:
    			System.out.println(e);
    			return "Server Error";
    		}
    		
    	}
    	return "Success";
    }
    
    @RequestMapping(value = "/addSinger", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addAlbum(Singer data) {
    	try {
 
    		service.addRecord("singers", data);
    	}
    	catch(SQLException e) {
    		switch(e.getErrorCode()) {
    		case 241:
    			return "Invalid data format";
    		case 2627:
    			return "Duplicate record Error";
    		default:
    			System.out.println(e);
    			return "Server Error";
    		}
    		
    	}
    	return "Success";
    }
    
    
    @GetMapping("/search")
    public List<IRecord> search(@RequestParam("query") String query) {
    	
    	try {
    		return service.search(query);
    	}
    	catch(Exception E) {
    		System.out.println(E);
    	}
    	return null;
    }
    
    @GetMapping("/shutdown")
    public void shutdown() {
    	
    	System.exit(0);
    }
    
}
