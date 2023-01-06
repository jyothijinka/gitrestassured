package pojo;

public class Mobile {
	private String courseTitle;
	private String price;
	private Mobile mobile;
	
	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}
	public Mobile getMobile() {
		return mobile;
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
