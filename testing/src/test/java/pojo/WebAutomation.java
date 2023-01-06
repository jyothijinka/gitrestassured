package pojo;

public class WebAutomation {
	private String courseTitle;
	private String price;
	private WebAutomation webAutomation;
	
	public void setWebAutomation(WebAutomation webAutomation) {
		this.webAutomation = webAutomation;
	}
	public WebAutomation getWebAutomation() {
		return webAutomation;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public String getPrice() {
		return price;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
