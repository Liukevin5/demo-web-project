package edu.csupomona.cs480.data.provider;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.csupomona.cs480.data.User;

public class FSUserManagerTest {

	private FSUserManager userManager;
	
	@Before
	public void setup() {
		userManager = new FSUserManager();
		User user1 = new User();
		user1.setId("001");
		user1.setName("Stu1");
		user1.setMajor("CS");		
		userManager.updateUser(user1);
		
		User user2 = new User();
		user2.setId("002");
		user2.setName("Stu2");
		user2.setMajor("CS");
		userManager.updateUser(user2);
	}
	
	@Test
	public void testGetUser() {		
		User user = userManager.getUser("001");
		Assert.assertNotNull(user);
		Assert.assertEquals("001", user.getId());
		Assert.assertEquals("Stu1", user.getName());
		Assert.assertEquals("CS", user.getMajor());
		
		System.out.println(Arrays.toString(userManager.lines));
	}
	
	@Test
	public void testGetUserNonExist() {
		User user = userManager.getUser("999NONEXIST");
		Assert.assertNull(user);
	}
	
	@After
	public void cleanup() {
		userManager.deleteUser("001");
		userManager.deleteUser("002");
	}
}
