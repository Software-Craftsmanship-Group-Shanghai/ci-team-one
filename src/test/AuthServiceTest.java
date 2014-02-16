import static org.junit.Assert.*;

import java.com.dandelion.springTemplate.AuthService;
import java.com.dandelion.springTemplate.AuthServiceImpl;

import org.junit.Test;

public class AuthServiceTest {
	@Test
	public void can_validate_user()
	{
		AuthService authService = new AuthServiceImpl();
		assertTrue(authService.validateUser("Joseph@gmail.com", "123456"));
	}
}
