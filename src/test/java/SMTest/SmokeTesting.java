package SMTest;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

public class SmokeTesting {

    public static WebDriver dr;
    ExtentSparkReporter spRk;
    ExtentReports ext;
    ExtentTest log;


    @BeforeTest
    public void setupRepo(){

        spRk = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "SmokeReports" + File.separator + "sparkReport.html");
        spRk.config().setTheme(Theme.STANDARD);
        spRk.config().setDocumentTitle("SMOKE TEST");
        spRk.config().setReportName("Smoke Testing Report Results");

        ext = new ExtentReports();
        ext.attachReporter(spRk);

    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setBrows(String browser, Method testMethod){

        log = ext.createTest(testMethod.getName());
        callBr(browser);

        dr.manage().window().maximize();
        dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        dr.get("file:///C:/Users/Jithin/Downloads/JAVA%20projects/SPA%20project/SPA.html");


    }

    public void callBr(String browserType){

        if(browserType.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            dr = new ChromeDriver();
        }
        else if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            dr = new FirefoxDriver();
        }
    }

    @AfterMethod
    public void setupLogger(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            log.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "TEST CASE FAILED", ExtentColor.RED));
            log.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test case failed", ExtentColor.RED));
        }
        else if (result.getStatus() == ITestResult.SKIP) {
            log.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+ "TEST CASE SKIPPED", ExtentColor.ORANGE));
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
            log.log(Status.PASS, MarkupHelper.createLabel(result.getName()+ "TEST CASE PASSED", ExtentColor.GREEN));
        }

        dr.quit();
    }

    @AfterTest
    public void infoLogged(){
        ext.flush();
    }



}
