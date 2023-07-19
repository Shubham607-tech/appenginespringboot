package online.vegetable.sales.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.vegetable.sales.model.AppUser;
import online.vegetable.sales.repository.LoginRepository;



@Service
public class LoginService {
	private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private LoginRepository loginRepository;
	
	private AppUser currentAppUser; // access control to APIs

	@Transactional
	public List<AppUser> getAllUser() {
		LOG.info("service-getAllAppUser");
		return loginRepository.findAll();
	}

	public AppUser saveUser(AppUser emp) {
		LOG.info("service-addAppUser");
		return loginRepository.save(emp);
	}

	
	@Transactional
	public AppUser getUserById(int id) {
		LOG.info("service-getUserByID");
		return loginRepository.findById(id).get();
	}

	@Transactional
	public int deleteUser(int empId) {
		LOG.info("service-deleteAppUser");
		loginRepository.deleteById(empId);
		return empId;
	}

	@Transactional
	public AppUser updateUser(AppUser emp) {
		LOG.info("service-updateAppUser");
		return loginRepository.save(emp);
	}
	
	
	// Login
	@Transactional
		public AppUser login(AppUser AppUser) {
			LOG.info("LoginService");
//			currentAppUser = loginRepository.findByEmail(AppUser.getEmail());
			currentAppUser = loginRepository.findByEmailAndPassword(AppUser.getEmail(), AppUser.getPassword());
			return currentAppUser;
		}

	// logout
	@Transactional
		public String logout() {
			LOG.info("logoutService");
			currentAppUser = null;
			return " Logged out successfully";
		}

	// login status
	@Transactional
		public AppUser loginStatus() {
			LOG.info("loginStatusService");
			return currentAppUser;
		}

		@Transactional
		public  List<AppUser> getByEmail(String email) {
			LOG.info("loginService-findEmail");
			return loginRepository.findByEmail(email);

			//			return loginRepository.findByUserName(email);
		}

//		public AppUser getByEmail(String string) {
//			currentAppUser = loginRepository.findByEmail(string.getEmail());
//			return currentAppUser;
//		}
//
//		public Object getByPasword(AppUser AppUser) {
//			currentAppUser = loginRepository.findByPassword(AppUser.getPassword());
//			return currentAppUser;
//		}
}
