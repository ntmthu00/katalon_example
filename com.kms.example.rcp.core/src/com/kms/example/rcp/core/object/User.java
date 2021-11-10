package com.kms.example.rcp.core.object;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class User {
	private String avaFilePath;
	private int id;
	private String username;
	private Date dob;
	private String gender;
	private String firstName;
	private String lastName;
	private String password;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public User() {
	}

	public User(String avaFilePath, String username, Date dob, String gender, String firstName, String lastName,
			String password) {
		super();
		this.avaFilePath = avaFilePath;
		this.username = username;
		this.dob = dob;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public User(String avaFilePath, int id, String username, Date dob, String gender, String firstName, String lastName,
			String password) {
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

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
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
		propertyChangeSupport.firePropertyChange("id", this.id, this.id = id);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		propertyChangeSupport.firePropertyChange("username", this.username, this.username = username);
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		propertyChangeSupport.firePropertyChange("dob", this.dob, this.dob = dob);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		propertyChangeSupport.firePropertyChange("gender", this.gender, this.gender = gender);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		propertyChangeSupport.firePropertyChange("firstName", this.firstName, this.firstName = firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		propertyChangeSupport.firePropertyChange("lastName", this.lastName, this.lastName = lastName);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		propertyChangeSupport.firePropertyChange("password", this.password, this.password = password);
	}
}
