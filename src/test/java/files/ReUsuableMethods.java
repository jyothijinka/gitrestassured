package files;

import io.restassured.path.json.JsonPath;

public class ReUsuableMethods {
public static JsonPath getJsonPath(String response) {
	
	JsonPath js=new JsonPath(response);
			return js;
}
}
