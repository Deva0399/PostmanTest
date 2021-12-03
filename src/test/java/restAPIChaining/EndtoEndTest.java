package restAPIChaining;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndtoEndTest {
	
	@Test
	public void test1(){
		
        RestAssured.baseURI = "http://localhost:7000";
		
		//==============Post Call=====================
        RequestSpecification Postrequest = RestAssured.given();
		
		Map<String, Object> PostBody = new HashMap<String, Object>();
		PostBody.put("name", "Ram");
		PostBody.put("salary", "3000");
		
		
		
		Response Postresponse = Postrequest.contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .body(PostBody)
                                    .post("/employees/create");
		String ResponseBody = Postresponse.body().asString();
		System.out.println(ResponseBody);
		
		JsonPath jpath = Postresponse.jsonPath();
		String ResponseID = jpath.get("id").toString();
		
		//=============Put Call========================
		
        RequestSpecification Putrequest = RestAssured.given();
		
		Map<String, Object> PutBody = new HashMap<String, Object>();
		PutBody.put("name", "Shyam");
		PutBody.put("salary", "3000");
		
		
		
		Response Putresponse = Putrequest.contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .body(PutBody)
                                    .put("/employees/" + ResponseID);
	    ResponseBody = Putresponse.body().asString();
		System.out.println(ResponseBody);
		
		//===========Delete Call=================================
        
		RequestSpecification Deleterequest = RestAssured.given();
		
		Response Deleteresponse=Deleterequest.delete("/employees/" + ResponseID);
		
		int ActResponse = Deleteresponse.statusCode();
		int ExpResponse = 200;
		
		AssertJUnit.assertEquals(ActResponse, ExpResponse);
		
		//=============Get Call====================
        RequestSpecification GetRequest = RestAssured.given();
		
		Response GetResponse = GetRequest.get("/employees/" + ResponseID);
		
		ActResponse = GetResponse.statusCode();
		ExpResponse = 404;
		
		AssertJUnit.assertEquals(ActResponse, ExpResponse);
		
		
		
	}

}
