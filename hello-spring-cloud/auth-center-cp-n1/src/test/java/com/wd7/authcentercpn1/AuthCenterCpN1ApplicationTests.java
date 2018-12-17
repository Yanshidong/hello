package com.wd7.authcentercpn1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthCenterCpN1ApplicationTests {

	private BCryptPasswordEncoder encoder;

	@Before
	public void initSome()
	{
		this.encoder=new BCryptPasswordEncoder();

	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testPassword()
	{
		String pwd="$2a$10$a1ZEXiZQr604LN.wVxet.etPm6RvDs.HIaXP48J2HKRaEnZORTVwe";

		Assert.assertTrue(encoder.matches("owen",pwd));
	}


}

