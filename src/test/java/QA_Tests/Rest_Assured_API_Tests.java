/*
*  This is maven project , test result can be viewed in extent Report
*
* */
package QA_Tests;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Rest_Assured_API_Tests {

    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    String token = "d640e5e3fe005efda204263e83c0c49707dc99a699e9f29447c6ba5d116985d2";
    ExtentTest test_Results;

    @BeforeSuite
    public void ExtentReportGenerator() {
        System.out.println("Before  suite");
        htmlReporter = new ExtentHtmlReporter("extentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test(priority = 1)
    public void postRequest() {

        test_Results = extent.createTest("Post Reqest Result", "It is test report of post request ");

        JSONObject req = new JSONObject();

        req.put("name", "apnaa oskar ericsson");
        req.put("email", "oskfrar815@gmail.com");
        req.put("gender", "male");
        req.put("status", "active");

        System.out.println(req.toJSONString());

        given().auth().oauth2(token).
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(req.toJSONString()).
                when().
                post("https://gorest.co.in/public/v1/users").
                then().
                statusCode(anyOf(is(201),is(422))).
                log().
                all();

        test_Results.log(Status.INFO, "Test passed");
        test_Results.pass("Test has passed");

    }


    @Test(priority = 2)
    public  void getRequest() {


        test_Results = extent.createTest("Get Request Result","It is test report of get request ");

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.when().get("http://gorest.co.in/public/v1/users?page=198");
        System.out.println(response.getStatusCode());

        if(response.getStatusCode()==200) {

            test_Results.info("Test has successfully worked");
            test_Results.log(Status.INFO,"Test Status info");
            test_Results.pass("Test has passed");
            response.then().statusCode(200).log().all();
        }
        else{


            test_Results.log(Status.INFO,"Test Failed");
            test_Results.fail("Test has failed");
            response.then().statusCode(200).log().all();
        }

    }

    @Test(priority = 3)
    public void deleteRequest() {


        test_Results = extent.createTest("Delete request Results", "It is test report of delete request ");

        baseURI="http://gorest.co.in";

        RequestSpecification httpRequest = given().auth().oauth2(token);


        Response response = httpRequest.when().delete("/public/v1/users/123");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());

        if (response.getStatusCode() == 200) {

            test_Results.info("Resource is deleted successfully");
            test_Results.log(Status.INFO, "passed successfully");
            test_Results.pass("Test has passed");
            response.then().statusCode(200).log().all();
        } else {
            test_Results.info("Resource is not available");
            test_Results.log(Status.INFO, "Test Failed");
            test_Results.fail("Test has failed");
            response.then().statusCode(200).log().all();
        }


    }

    @AfterSuite
    public void tearDown() {
        System.out.println("extent flush");
        extent.flush();
    }
}
