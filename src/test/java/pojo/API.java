package pojo;

public class API {
	private String courseTitle;
	private String price;
	private API Api;
	
	public void setApi(API Api) {
		this.Api = Api;
	}
	public API getAPI() {
		return Api;
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
