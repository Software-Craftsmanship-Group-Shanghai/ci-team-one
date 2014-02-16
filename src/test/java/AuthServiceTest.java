import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dandelion.springTemplate.AuthService;
import com.dandelion.springTemplate.AuthServiceImpl;

public class AuthServiceTest {
	
	@Test
	public void can_validate_user() {
		AuthService authService = new AuthServiceImpl();
		assertTrue(authService.validateUser("Joseph@gmail.com", "123456"));
	}
}
