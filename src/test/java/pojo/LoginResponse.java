package pojo;

public class LoginResponse {
private String token;
private String userId;
private String message;
public String getToken() {
	return token;
}
public String getUserId() {
	return userId;
}
public String getMessage() {
	return message;
}
public void setToken(String token) {
	this.token = token;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public void setMessage(String message) {
	this.message = message;
}

}
