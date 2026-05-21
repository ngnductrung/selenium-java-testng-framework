package listeners.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext context){
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/reportHTML.html");
        sparkReporter.config().setDocumentTitle("Automation Report"); //Title of report
        sparkReporter.config().setReportName("Functional Testing"); //Name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Computer Name", "localhost");
        extent.setSystemInfo("Environment", "local");
        extent.setSystemInfo("Tester Name", "Raph");
        extent.setSystemInfo("Operation System", "MacOS");
        extent.setSystemInfo("Browser Name", "Firefox");
    }

    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getName()); //Create a new entry in the report
        test.log(Status.PASS, "Test case PASS is:" + result.getName()); //Update status
    }

    public void onTestFailure(ITestResult result){
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test case FAIL because: " + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result){
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test case SKIP is: " + result.getName());
    }

    public void onFinish(ITestContext context){
        extent.flush();
    }

}
