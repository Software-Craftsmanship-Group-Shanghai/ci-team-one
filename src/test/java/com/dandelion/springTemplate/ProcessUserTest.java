package com.dandelion.springTemplate;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProcessUserTest {
	@Test
	public void read_user()
	{
		ProcessUser processor = new ProcessUserImpl();
		assertTrue(processor.validateUserIsEffictive("joseph@gmail.com", "123456"));
	}

}
