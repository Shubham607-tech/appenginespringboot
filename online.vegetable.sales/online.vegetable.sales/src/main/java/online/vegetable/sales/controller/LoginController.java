package online.vegetable.sales.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import online.vegetable.sales.exception.UserAlreadyExitException;
import online.vegetable.sales.exception.UserNotFoundException;
import online.vegetable.sales.model.AppUser;
import online.vegetable.sales.model.Message;
import online.vegetable.sales.service.LoginService;


@CrossOrigin(origins = "*")
@RestController
public class LoginController {
	
	private static final Logger LOG= LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/adduser")
	public AppUser addUser(@RequestBody AppUser emp) {
		try {
			LOG.info("controller-addAppUsers");
			return loginService.saveUser(emp);
		} catch (UserAlreadyExitException e) {
			LOG.error("UserAlreadyExit",e.getMessage());		}
		return null;		
	}
	
	@PutMapping("/edituser")
	public AppUser updateUser(@RequestBody AppUser emp) {
		try {
			LOG.info("controller-updateAppUsers");
			return loginService.updateUser(emp);			
		} catch (UserNotFoundException e) {
			LOG.error("UserNotFound",e.getMessage());
		}
		return null;
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public void deleteUser(@PathVariable("userId") int userId) {
		try {
			LOG.info("controller-deleteAppUsers");
			loginService.deleteUser(userId);
		} catch (UserNotFoundException userNotFoundException) {
			LOG.error("UserNotFound",userNotFoundException.getMessage());
			
		}		
		
	}
		

	@GetMapping("/getuserbyid/{id}")
	public AppUser getUserById(@PathVariable int id) throws UserNotFoundException {
		AppUser response = null;
		try {
			LOG.info("controller-getUserByID");
			response= loginService.getUserById(id);
		} catch (UserNotFoundException userNotFoundException) {
			LOG.error("UserNotFound",userNotFoundException.getMessage());
		}
		return response;
	}

	
	@GetMapping("/viewuser")
	public ResponseEntity<List<AppUser>> viewAllUser() {
		List<AppUser> response = null;
		try {
		LOG.info("controller-viewAllAppusers");
		List<AppUser> emp = loginService.getAllUser();
		return ResponseEntity.ok().body(emp);
		}
		catch (UserNotFoundException e) {
			LOG.error("UserNotFound",e.getMessage(),e);
		}
		return null;		
	}
	
	
	@GetMapping("/getuserbyemail")
	public List<AppUser>  viewByEmail( @RequestParam String email) {
		List<AppUser>  response=null;
		try {
			LOG.info("controller-viewByEmail");	
			response=loginService.getByEmail(email);
		} catch (UserNotFoundException e) {
			LOG.error("UserNotFound",e.getMessage(),e);
		}
		return response;
	}
	

}
