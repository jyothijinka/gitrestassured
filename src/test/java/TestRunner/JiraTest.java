package TestRunner;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.ReUsuableMethods;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraTest {
@Test
public void postJiraTest() {
	RestAssured.baseURI="http://localhost:8080";
	//login scenario
	SessionFilter session=new SessionFilter();
	String response = given().header("content-type","application/json")
	.body("{\r\n"
			+ "    \"username\": \"jyothijinka\",\r\n"
			+ "    \"password\": \"naresh4189\"\r\n"
			+ "}").filter(session)
	.when().log().all().post("/rest/auth/1/session")
	.then().extract().response().asString();
	String expectedmessage="HI HOW ARE YOU?";
	
	//createissue
	given().log().all()
	.header("content-type","application/json")
	.body("{\r\n"
			+ "    \"fields\":{\r\n"
			+ "        \"project\":{\r\n"
			+ "            \"key\":\"JIRA\"\r\n"
			+ "        },\r\n"
			+ "        \"summary\":\"Debitcard defect\",\r\n"
			+ "        \"description\":\"Creating my firstbug\",\r\n"
			+ "        \"issuetype\":{\r\n"
			+ "            \"name\":\"Bug\"\r\n"
			+ "        }\r\n"
			+ "    }\r\n"
			+ "}").filter(session).when().post("/rest/api/2/issue")
	.then().assertThat().log().all().statusCode(201);
	//addcomment issue
	String comment=given().pathParam("key", "10029").header("content-type","application/json")
	.body("{\r\n"
			+ "    \"body\": \""+expectedmessage+"\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}").filter(session).log().all()
	.when().post("/rest/api/2/issue/{key}/comment")
	.then().assertThat().statusCode(201).extract().response().asString();
	JsonPath js=ReUsuableMethods.getJsonPath(comment);
	String expectedcommentid = js.getString("id");
	
	//add attachment
	given().log().all().pathParam("key", "10029").header("X-Atlassian-Token","no-check")
	.header("content-type","multipart/form-data").filter(session)
	.multiPart("file",new File("./src/test/jira.txt")).when().post("/rest/api/2/issue/{key}/attachments")
	.then().assertThat().log().all().statusCode(200);
	
	//get issue
	String issuedetails=given().filter(session).pathParam("key", "10029")
	.queryParam("fields","comment").when().get("/rest/api/2/issue/{key}")
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1=ReUsuableMethods.getJsonPath(issuedetails);
	int commentcount=js1.getInt("fields.comment.comments.size()");
	for(int i=0;i<commentcount;i++) {
		String actualcommentid = js1.get("fields.comment.comments["+i+"].id").toString();
		if(expectedcommentid.equalsIgnoreCase(actualcommentid)) {
			String actualmessage = js1.get("fields.comment.comments["+i+"].body").toString();
			System.out.println(actualmessage);
			Assert.assertEquals(expectedmessage, actualmessage);
		}
		
	}
}
}
