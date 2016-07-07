package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.UserMap;
import edu.csupomona.cs480.util.ResourceResolver;

/**
 * The implementation of {@link UserManager} interface
 * using file system.
 * <p>
 * This class demonstrates how you can use the file system
 * as a database to store your data.
 *
 */
public class FSUserManager implements UserManager {

	public static int[] lines = new int[90];
	
	/**
	 * We persist all the user related objects as JSON.
	 * <p>
	 * For more information about JSON and ObjectMapper, please see:
	 * http://www.journaldev.com/2324/jackson-json-processing-api-in-java-example-tutorial
	 *
	 * or Google tons of tutorials
	 *
	 */
	private static final ObjectMapper JSON = new ObjectMapper();

	/**
	 * Load the user map from the local file.
	 *
	 * @return
	 */
	private UserMap getUserMap() {
		lines[0] = 1;
		UserMap userMap = null;
		lines[1] = 1;
		File userFile = ResourceResolver.getUserFile();
		lines[2] = 1;
		if (userFile.exists()) {
			// read the file and convert the JSON content
			// to the UserMap object
			try {
				lines[3] = 1;
				userMap = JSON.readValue(userFile, UserMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			lines[4] = 1;
			userMap = new UserMap();
		}
		lines[5] = 1;
		return userMap;
	}

	/**
	 * Save and persist the user map in the local file.
	 *
	 * @param userMap
	 */
	private void persistUserMap(UserMap userMap) {
		try {
			// convert the user object to JSON format
			lines[6] = 1;
			JSON.writeValue(ResourceResolver.getUserFile(), userMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(String userId) {
		lines[7] = 1;
		UserMap userMap = getUserMap();
		lines[8] = 1;
		return userMap.get(userId);
	}

	@Override
	public void updateUser(User user) {
		UserMap userMap = getUserMap();
		userMap.put(user.getId(), user);
		persistUserMap(userMap);
	}

	@Override
	public void deleteUser(String userId) {
		UserMap userMap = getUserMap();
		userMap.remove(userId);
		persistUserMap(userMap);
	}

	@Override
	public List<User> listAllUsers() {
		UserMap userMap = getUserMap();
		return new ArrayList<User>(userMap.values());
	}

}
