package restAPIBDD;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteRequest {
	
	@Test
	public void DeleteCall() {
		
		//Map<String, Object> PostBody = new HashMap<String, Object>();
		//PostBody.put("name", "Dhivya");
		//PostBody.put("salary", "3000");
		
		
		RestAssured.given()
		             .baseUri("http://localhost:7000")
		             .when()
		             .delete("/employees/18")
		             .then()
		             .statusCode(200);
		             //.log()
		             //.all();

}
}
