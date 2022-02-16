/*
 *  This is maven project , test result can be viewed in extent Report
 *
 * */
package qatests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class RestAssuredAPITests {

    String token = "d640e5e3fe005efda204263e83c0c49707dc99a699e9f29447c6ba5d116985d2";

    @Test(priority = 1)
    public void postRequest() {

        JSONObject req = new JSONObject();
        int randomNum= (int)(Math.random()*(200-1+1)+1);
        req.put("name", "apnaeqa oskar ericsson");
        req.put("email", "oskferar81"+randomNum+ "@gmail.com");
        req.put("gender", "male");
        req.put("status", "active");

        System.out.println(req.toJSONString());

        RequestSpecification httpRequest = given().auth().oauth2(token).
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(req.toJSONString());
        Response response = httpRequest.post("https://gorest.co.in/public/v1/users");
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);

    }

    @Test(priority = 2)
    public void getRequest() {
        RequestSpecification httpRequest = given();
        Response response = httpRequest.when().get("http://gorest.co.in/public/v1/users/127");
        System.out.println(response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

    }

    @Test(priority = 3)
    public void deleteRequest() {

        baseURI = "https://gorest.co.in/public/v1/users/128";

        RequestSpecification httpRequest = given().auth().oauth2(token);
        //Response object
        Response response = httpRequest.delete(baseURI);
        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

}
