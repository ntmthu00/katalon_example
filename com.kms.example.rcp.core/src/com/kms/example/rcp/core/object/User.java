package com.kms.example.rcp.core.object;

public class User {
	private String avaFilePath;
	private int id;
	private String username;
	private Date dob;
	private boolean gender;
	private String firstName;
	private String lastName;
	private String password;

	public User(String avaFilePath, int id, String username, Date dob, boolean gender, String firstName,
			String lastName, String password) {
		super();
		this.avaFilePath = avaFilePath;
		this.id = id;
		this.username = username;
		this.dob = dob;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public String getAvaFilePath() {
		return avaFilePath;
	}

	public void setAvaFilePath(String avaFilePath) {
		this.avaFilePath = avaFilePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
