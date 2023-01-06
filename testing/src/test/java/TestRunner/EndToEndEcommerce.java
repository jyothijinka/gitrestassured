package TestRunner;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.CreateOrder;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetail;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class EndToEndEcommerce {
@Test
public void endToEndTest() {
	//login requestbody
	LoginRequest l=new LoginRequest();
	l.setuserEmail("jyothimamilla7@gmail.com");
	l.setuserPassword("naresh4189");
	//login using pojoclasses
	
	
	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
	.setContentType(ContentType.JSON).build();
	RequestSpecification request = given().log().all().spec(req).body(l);
	 LoginResponse response = request.when().log().all().post("/api/ecom/auth/login").then().extract().response()
	.as(LoginResponse.class);
	 String token = response.getToken();
	 String userid = response.getUserId();
	System.out.println(response.getToken());
     System.out.println(response.getMessage());
     System.out.println(response.getUserId());
     
     
   // Add product
     
     RequestSpecification addproductbasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
    			.addHeader("authorization", token).build();
     RequestSpecification addproductrequest = given().log().all().spec(addproductbasereq).param("productName","qwerty")
    		 .param("productAddedBy", userid).param("productCategory", "fashion")
    		 .param("productSubCategory", "shirts").param("productPrice", 11500)
    		 .param("productDescription", "Addias Originals")
    		 .param("productFor", "women").
    		 multiPart("productImage", new File("C:\\Users\\jinka\\Postman\\files\\abcd.png"));
     
     
    String addproductresponse = addproductrequest.when().post("/api/ecom/product/add-product").then()
     .extract().asString();
    JsonPath js=new JsonPath(addproductresponse);
   String  productId= js.getString("productId");
   System.out.println(productId);

//create order
   
   RequestSpecification createproductbasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		   .addHeader("authorization", token).setContentType(ContentType.JSON).build();
   
   OrderDetail od=new OrderDetail();
   od.setcountry("India");
   od.setproductOrderedId(productId);
   List<OrderDetail> orderdetaillist=new ArrayList<OrderDetail>();
   orderdetaillist.add(od);
   CreateOrder co=new CreateOrder();
   co.setOrders(orderdetaillist);
   
   
   RequestSpecification createorderrequest = given().log().all().spec(createproductbasereq).body(co);
  String createorderresponse = createorderrequest.when().log().all().post("/api/ecom/order/create-order")
		  .then().log().all().extract().response().asString();
  System.out.println(createorderresponse);
  
  
  
  //Delete order
  RequestSpecification deleteproductbasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		   .addHeader("authorization", token).setContentType(ContentType.JSON).build();
  RequestSpecification deleteorderrequest = given().log().all().spec(deleteproductbasereq).pathParam("productId",productId);
  String deleteorderresponse = deleteorderrequest.when().log().all().delete("/api/ecom/product/delete-product/{productId}")
		  .then().log().all().extract().response().asString();
  System.out.println(deleteorderresponse);
  JsonPath js1=new JsonPath(deleteorderresponse);
  Object output = js1.get("message");
  System.out.println(output);
 Assert.assertEquals("product Deleted Successfully",js1.get("message"));
  
}

}
