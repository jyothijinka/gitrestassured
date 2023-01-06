package TestRunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import files.ReUsuableMethods;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.API;
import pojo.GetCourse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class OAuth2 {
@Test
public void oauth2Test() throws InterruptedException {
	/*System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verify");
	driver.findElement(By.id("identifierId")).sendKeys("jyothimamilla7@gmail.com");
	driver.findElement(By.xpath("//span[text()='Next']")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//div[text()='Enter your password']")).sendKeys("subbarayudu9294");
	driver.findElement(By.xpath("//span[text()='Next']")).click();
	Thread.sleep(2000);
	String coderesponse = driver.getCurrentUrl();
	
	This is not required anymore google will not authorise to login using automation
	*/
	String[] courseTitles= {"Selenium Webdriver Java", "Cypress", "Protractor"};
String url="https://rahulshettyacademy.com/getCourse.php?state=verify&code=4%2F0AWgavddj99cDiwQS5EiTcXgQxnkZT1sTQlYjWvOnusK0cyCf-ya78p80al_dWTQ3sBWDAQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
	String Partialcode=url.split("code=")[1];
	String code=Partialcode.split("&scope")[0];
	System.out.println("code ="+code);
	
	
	
	String accesstokenResponse = given().urlEncodingEnabled(false).queryParams("code", code)
	.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
	.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
	.queryParams("grant_type", "authorization_code")
	.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
	System.out.println(accesstokenResponse);
	JsonPath js1=ReUsuableMethods.getJsonPath(accesstokenResponse);
	String accesstoken = js1.getString("access_token");
	System.out.println("accesstoken"+accesstoken);
	
	 GetCourse gc = given().queryParam("access_token", accesstoken).expect().defaultParser(Parser.JSON)
	.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
	System.out.println(gc.getLinkedIn());
	System.out.println(gc.getInstructor());
	List<API> apicourses = gc.getCourses().getApi();
	for(int i=0;i<apicourses.size();i++) {
		
	System.out.println(gc.getCourses().getApi().get(i).getCourseTitle());
	}
	
	int apicount = apicourses.size();
List<WebAutomation> wb = gc.getCourses().getWebAutomation();
ArrayList<String> a=new ArrayList<String>();
	for(int i=0;i<wb.size();i++) {
		a.add(wb.get(i).getCourseTitle());
	}
	List<String> expectedList=Arrays.asList(courseTitles);
	Assert.assertTrue(a.equals(expectedList));
	for(int i=0;i<apicount;i++)
	{
		if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java")){
			System.out.println(apicourses.get(i).getPrice());
		}
	}
}
}
