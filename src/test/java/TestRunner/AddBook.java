package TestRunner;

import org.testng.annotations.Test;

import files.ReUsuableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddBook {
@Test
public void getExcelData() throws IOException {
	DataDrivenTesting ddt=new DataDrivenTesting();
	 ArrayList data = ddt.getData("RestAssured", "RestAssured", "Testcases");
	HashMap<String,Object> map=new HashMap<>();
	map.put("name",data.get(1));
	map.put("isbn",data.get(2));
	map.put("aisle", data.get(3));
	map.put("author", data.get(4));
	
	
	RestAssured.baseURI="http://216.10.245.166";
	 String response = given().log().all().header("Content-Type","application/json")
	.body(map)
    .when().post("/Library/Addbook.php").
    then().extract().response().asString();
	 System.out.println(response);
	JsonPath js=ReUsuableMethods.getJsonPath(response);
	String id = js.get("ID");
	 System.out.println(id);
	 
	 HashMap<String,Object> map2=new HashMap<>();
	 map2.put("ID", id);
	String response1 = given().log().all().header("Content-Type","application/json")
		.body(map2)
	    .when().post("/Library/DeleteBook.php").
	    then().extract().response().asString();
		 System.out.println(response1);
		JsonPath js1=ReUsuableMethods.getJsonPath(response1);
		String msg = js1.get("msg");
		 System.out.println(msg);
}
}
