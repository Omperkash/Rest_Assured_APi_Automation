package qatests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Shared {

    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    String token = "dfd4dcc3b40efb2a81f199786d2938b47051a5bf1f7cd24cd197723af510a4ec";


    @BeforeSuite
    public void ExtentReportGenerator() {
        System.out.println("Before  suite");
        htmlReporter = new ExtentHtmlReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

    }

    @AfterSuite
    public void teardown(){
        extent.flush();

    }

}
