package API.testing;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.SampleBody;
import io.restassured.path.json.JsonPath;

public class ComplexjsonParseusingTestNg {
@Test
public void SumOfAmount() {
	JsonPath js=new JsonPath(SampleBody.CoursePrice());
	int purchaseamount=js.getInt("dashboard.purchaseAmount");
	int count=js.getInt("courses.size()");
	int total=0;
	for(int i=0;i<count;i++) {
		int price = js.getInt("courses["+i+"].price");
		int copies = js.getInt("courses["+i+"].copies");
		int amount=price*copies;
		System.out.println(amount);
		total=total+amount;
	}
	System.out.println(total);
	Assert.assertEquals(purchaseamount, total);
}
}
