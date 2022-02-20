/*
 *  This is maven project , test result can be viewed in extent Report
 *
 * */
package qatests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class RestAssuredAPITests extends Shared {


    @Test(priority = 1)
    public void postRequest() {


        JSONObject req = new JSONObject();

        req.put("name", "apn Premkrar ericsson");
        req.put("email", "osekrar781@gmail.com");
        req.put("gender", "male");
        req.put("status", "active");

        System.out.println(req.toJSONString());

        RequestSpecification httpRequest = given().auth().oauth2(token).
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(req.toJSONString());
        //Response object
        Response response = httpRequest.post("https://gorest.co.in/public/v1/users");

        System.out.println("Reponse code is :"+response.getStatusCode());

        ExtentTest test_Results1 = extent.createTest("Post Reqest Result", "It is test report of post request ");

        if(response.getStatusCode()!=201 ){
            System.out.println("Test failed becuase resource is already availble,  make change in email to make this test pass ");
            test_Results1.log(Status.INFO, "Test failed");
            test_Results1.info("Reponce code is not as expected");
            test_Results1.fail("Test is Failed");
        }
        else {
            System.out.println("Test Passed responce code is as expected ");
            test_Results1.log(Status.INFO, "Test passed");
            test_Results1.info("Reponce code is as expected");
            test_Results1.pass("Test is passed");
        }
        //status code validation
        Assert.assertEquals(response.getStatusCode(), 201);

    }


    @Test(priority = 2)
    public void getRequest() {

        RequestSpecification httpRequest = given();
        Response response = httpRequest.when().get("http://gorest.co.in/public/v1/users/129");
        System.out.println("Responce code is : "+response.getStatusCode());

        ExtentTest   test_Results2 = extent.createTest("Get Reqest Result", "It is test report of get request ");

        if(response.getStatusCode()!=200){
            System.out.println("Test is failed becuase resource is not available");
            test_Results2.log(Status.INFO, "Test failed");
            test_Results2.info("Reponce code is not as expected");
            test_Results2.fail("Test is Failed");
        }
        else {
            System.out.println("Test Passed");
            test_Results2.log(Status.INFO, "Test passed");
            test_Results2.info("Reponce code is as expected");
            test_Results2.pass("Test is passed");
        }
        //status code validation
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 3)
    public void deleteRequest() {

        baseURI = "https://gorest.co.in/public/v1/users/123";

        RequestSpecification httpRequest = given().auth().oauth2(token);
        //Response object
        Response response = httpRequest.delete(baseURI);

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);

        ExtentTest test_Results3 = extent.createTest("Delete Reqest Result", "It is test report of Delete request ");

        if(response.getStatusCode()!=204){
            System.out.println("Test is failed becuase resource is not available");
            test_Results3.log(Status.INFO, "Test failed");
            test_Results3.info("Reponce code is not as expected");
            test_Results3.fail("Test is Failed");
        }
        else {
            System.out.println("Test Passed");
            test_Results3.log(Status.INFO, "Test passed");
            test_Results3.info("Reponce code is as expected");
            test_Results3.pass("Test is passed");
        }
        //status code validation
        Assert.assertEquals(response.getStatusCode(), 204);



    }








}
