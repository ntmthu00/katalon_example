package com.kms.example.rcp.core.object;

import java.util.ArrayList;
import java.util.List;

public enum UserProvider {
	INSTANCE;

	private List<User> userList;

	private UserProvider() {
		userList = new ArrayList<User>();
		userList.add(new User("avatar/john.jpg", 1, "john_doe", Date.parseDate("29/08/1993"), "MALE", "John", "Doe",
				"1234"));
		userList.add(new User("avatar/katie.jpg", 2, "katie_holmes", Date.parseDate("10/10/1973"), "FEMALE", "Katie",
				"Holmes", "1234"));
	}

	public List<User> getAllUsers() {
		return userList;
	}
}
