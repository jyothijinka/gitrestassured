package API.testing;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import static io.restassured.RestAssured.*;

import files.SampleBody;
public class Basics {

	public static void main(String[] args) throws IOException {
		
	//Given- preconditions -all input details
		//when- submit the API -resource,http method
		//Then- validate the response
		
		RestAssured.baseURI="http://rahulshettyacademy.com/";
		//post method
		String response = given().log().all().queryParam("KEY", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\jinka\\samplebody\\same.json"))))
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)")
		.extract().response().asString();
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String placeid = js.getString("place_id");
		System.out.println(placeid);
		String scope = js.getString("scope");
		System.out.println(scope);
		
		//update method
		String newaddress = "70 Summer walk, USA";
		given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
	

	//get method
	String getaddress = given().queryParam("key","qaclick123").queryParam("place_id",placeid)
	.when().get("/maps/api/place/get/json")
	.then().assertThat().statusCode(200).extract().response().asString();
	JsonPath js1=new JsonPath(getaddress);
	String actualaddress = js1.getString("address");
	System.out.println(actualaddress);
	//cucumber junit,testng
	Assert.assertEquals(newaddress, actualaddress);
	
	}

}
