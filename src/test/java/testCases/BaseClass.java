package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	//In the class we are maintaining the common methods Which can be used for all the TestCases

	public static WebDriver driver; 
	public Logger logger;
	public Properties p;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups = {"Regression","Master", "Sanity"})
	@Parameters({"browser","os"})
	public void launch_browser(String br,String os) throws IOException
	{
		//Loading Config.properties file 
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toUpperCase())
			{

			case "CHROME": driver = new ChromeDriver(); break;
			case "EDGE":   driver = new EdgeDriver(); break;
			default: System.out.println("Invalid Browser"); return;

			}
			
		}

		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{

			DesiredCapabilities cap = new DesiredCapabilities();

			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No Matching Platform");
				return;
			}
			
			switch(br.toLowerCase())
			{

			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge": cap.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("Invalid Browser"); return;

			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);

		}
		
//	     driver.get("https://tutorialsninja.com/demo/");
				driver.get(p.getProperty("appURL"));   //Reading the URL from Properties 
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().window().maximize();
	}
	@AfterClass(groups = {"Regression","Master", "Sanity"})
	public void terminate()
	{
		driver.quit();
	}



	//Methods for Randomly Generating the TestData 

	public String randomAlphabetic()
	{
		String randomalpha = RandomStringUtils.randomAlphabetic(8);
		return randomalpha;
	}

	public String randomNumber()
	{
		String randomnumb = RandomStringUtils.randomNumeric(10);
		return randomnumb;
	}

	public String randomalphanumeric()
	{
		String randomalphanumeric = RandomStringUtils.randomAlphanumeric(8);
		return randomalphanumeric+"@";
	}

	public String capturescreen(String testname)
	{

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;

		File sourcefile = ts.getScreenshotAs(OutputType.FILE);

		String targefilepath = System.getProperty("user.dir")+"\\Screenshots\\"+testname+"_"+timestamp+".png";
		File targetfile = new File(targefilepath);
		sourcefile.renameTo(targetfile);

		return targefilepath;
	}


}
