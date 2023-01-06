package pojo;

import java.util.List;

public class AddPlace {
private Location location;
private int accuracy;
private String name; 
private String phonenumber;
private String address;
private List<String> types;
private String website;
private String language;
public Location getLocation() {
	return location;
}
public int getAccuracy() {
	return accuracy;
}
public String getName() {
	return name;
}
public String getPhonenumber() {
	return phonenumber;
}
public String getAddress() {
	return address;
}
public List<String> getTypes() {
	return types;
}
public String getWebsite() {
	return website;
}
public String getLanguage() {
	return language;
}
public void setLocation(Location location) {
	this.location = location;
}
public void setAccuracy(int accuracy) {
	this.accuracy = accuracy;
}
public void setName(String name) {
	this.name = name;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
public void setAddress(String address) {
	this.address = address;
}
public void setTypes(List<String> types) {
	this.types = types;
}
public void setWebsite(String website) {
	this.website = website;
}
public void setLanguage(String language) {
	this.language = language;
}
}
