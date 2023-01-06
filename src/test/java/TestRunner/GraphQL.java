package TestRunner;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
public class GraphQL {
	@Test
public void setQueryGraphQL() {
		int characterId = 129;
		String response = given().log().all().header("Content-Type","application/json")
	.body("{\"query\":\"query($characterId:Int!,$episodeId:Int!,$locationId:Int!)\\n{\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId: $locationId) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $episodeId) {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters:{name:\\\"Ivy Pepper - Poison Ivy\\\"}){\\n    info{\\n      count\\n    }\\n    result{\\n      name\\n      type\\n    }\\n  }\\nepisodes(filters:{episode:\\\"Red-Headed\\\"}){\\n  info{\\n    count\\n  }\\n  result{\\n    id\\n    name\\n    air_date\\n  }\\n}\\n\\n}\",\"variables\":{\"characterId\":"+characterId+",\"episodeId\":93,\"locationId\":167}}")
	.when().post("https://rahulshettyacademy.com/gq/graphql")
	.then().extract().response().asString();
	System.out.println(response);
	JsonPath js=new JsonPath(response);
	String ExpectedName = js.getString("data.character.name");
	Assert.assertEquals(ExpectedName, "Robin hook");

	String name = "Robin hook";
	String mutualresponse = given().log().all().header("Content-Type","application/json")
	.body("{\"query\":\"mutation($locationName:String!,$characterName:String!,$episodeName:String!)\\n{\\n  createLocation(location:{name:$locationName,type:\\\"south\\\",dimension:\\\"345\\\"}){\\n    id\\n  }\\n  createCharacter(character:{name:$characterName,type:\\\"Macho\\\",status:\\\"Alive\\\",species:\\\"human\\\",gender:\\\"male\\\",image:\\\"png\\\",originId:168,locationId:168}){\\n    id\\n  }\\n  createEpisode(episode:{name:$episodeName,air_date:\\\"1-4-2023\\\",episode:\\\"Red_Velvet_Cake\\\"}){\\n    id\\n  }\\n  deleteLocations(locationIds:[164,165]){\\n    locationsDeleted\\n  }\\n}\",\"variables\":{\"locationName\":\"Australia\",\"characterName\":\""+name+"\",\"episodeName\":\"Red_velvet_Episode\"}}")
.when().post("https://rahulshettyacademy.com/gq/graphql")	
.then().extract().response().asString();
	System.out.println(mutualresponse);
}
}