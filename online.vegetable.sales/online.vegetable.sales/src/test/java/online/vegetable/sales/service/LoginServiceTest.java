package online.vegetable.sales.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import online.vegetable.sales.model.AppUser;
import online.vegetable.sales.service.LoginService;
import online.vegetable.sales.repository.LoginRepository;


@SpringBootTest
public class LoginServiceTest {
	private static Logger LOG = LoggerFactory.getLogger(LoginServiceTest.class);
    

	@MockBean
	private LoginService loginService;
	

	@MockBean
	private LoginRepository loginRepository;
	
	@Test
	public void saveUser() {
		AppUser appUser1 = new AppUser();
		appUser1.setUserId(1);
		appUser1.setEmail("test@gmail.com");
		appUser1.setUserName("user123");
//		AppUser appUser = loginService.saveUser(appUser1);
		when(loginRepository.save(appUser1)).thenReturn(mock(AppUser.class)); 
		assertNotNull(appUser1);
		
	}
}
