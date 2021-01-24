package model;

import java.io.Serializable;

public class User
  implements Serializable
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
  private String lastname;
  private String firstname;
  private String email;
  private String gender;
  private String userType;
  private String password;
  


public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getUserType() {
	return userType;
}
public void setUserType(String userType) {
	this.userType = userType;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public User() {};
public User(String lastname,String firstname,String email, String gender,String userType,String password){
	this.email=email;
	
	this.lastname=lastname;
	this.firstname = firstname;
	this.gender=gender;
	this.userType = userType;
	this.password = password;
}
}
