package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import freemarker.template.SimpleDate;
import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;
	
	String reportname;
	
	public void onStart(ITestContext context) {
		
	//	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	//	Date dt = new Date();
	//	String timestamp = df.format(dt);
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		reportname = "Test-Report"+ timestamp + ".html";
		
		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/"+ reportname);
		
		spark.config().setDocumentTitle("Opencart Automation Report");
		spark.config().setReportName("Functional Test");
		spark.config().setTheme(Theme.DARK); 
		
		extent = new ExtentReports();
		extent.attachReporter(spark);
		
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Brwoser", browser);
		
		List<String> includedgrps = context.getCurrentXmlTest().getIncludedGroups();
		extent.setSystemInfo("Groups", includedgrps.toString());	
	}
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+ "Successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+ "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			
			String imgpath = new BaseClass().capturescreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+ "got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
		String pathofextentreport = System.getProperty("user.dir")+"/Reports/" + reportname ;
		File extentreport = new File(pathofextentreport);
		
		try {
			
			Desktop.getDesktop().browse(extentreport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
