package TestRunner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsuableMethods;
import files.SampleBody;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicjsonLoading {
@Test(dataProvider="BooksData")

//postmethod
public static void postBook(String aise,String isbn) {
RestAssured.baseURI="http://216.10.245.166";
 String response=given().log().all().header("Content-Type","application/json")
 .body(SampleBody.addBook(aise,isbn))
 .when().post("/Library/Addbook.php")
 .then().assertThat().log().all().statusCode(200).extract().response().asString();
 System.out.println(response);
 JsonPath js=ReUsuableMethods.getJsonPath(response);
 String id=js.getString("ID");
 System.out.println(id);
//deleteBook(id);
}

/*/eleteMethod
@Test
public static void deleteBook(String bookid) {
	RestAssured.baseURI="http://216.10.245.166";
	given().queryParam("id", bookid).header("Content-Type","application/json").body(SampleBody.getBookID(bookid))
.when().post(":/Library/DeleteBook.php")
.then().log().all().statusCode(200);
}*/
@DataProvider(name="BooksData")
public Object[][] getBookData() {
	return new Object[][]{{"core java7","12345"},{"database17","45678"},{"sql15","67899"}};
}
}
