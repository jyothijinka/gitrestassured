package pojo;

import java.util.List;

public class Courses {
	private List<WebAutomation> webAutomation;
	private List<API> Api;
	private List<Mobile> mobile;
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public void setApi(List<API> Api) {
		this.Api = Api;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public List<API> getApi() {
		return Api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
}
