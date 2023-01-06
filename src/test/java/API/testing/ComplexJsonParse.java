package API.testing;

import java.util.List;

import org.testng.Assert;

import files.SampleBody;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		JsonPath js=new JsonPath(SampleBody.CoursePrice());
	int count=	js.getInt("courses.size()");
	System.out.println(count);
	int purchaseamount=js.getInt("dashboard.purchaseAmount");
	System.out.println(purchaseamount);
	int total=0;
	for(int i=0;i<count;i++) {
		String title=js.getString("courses.title["+i+"]");
	 int price = js.getInt("courses.price["+i+"]");
	 int copies = js.getInt("courses.copies["+i+"]");
	int amount = price*copies;
	total=total+amount;
	System.out.println(title+"-->price= "+price);
	System.out.println(title+"-->copies= "+copies);
	System.out.println(title+"-->price*copies= "+amount);
	
	}
	//no of copies sold by cypress
	for(int i=0;i<count;i++) {
		String title=js.getString("courses.title["+i+"]");

		if(title.equalsIgnoreCase("cypress"))
			 
		System.out.println("copies of cypress "+js.getInt("courses.copies["+i+"]"));
	}
	
	System.out.println(total);
	Assert.assertEquals(purchaseamount, total);
	}
	}
