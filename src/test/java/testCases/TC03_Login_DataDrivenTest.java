package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC03_Login_DataDrivenTest extends BaseClass{

	@Test(dataProvider = "Logindata", dataProviderClass = DataProviders.class)
	public void login_DDT(String email, String pwd,String exp_result) {
		
		try {
			Homepage hp = new Homepage(driver);
			hp.myacc();
			hp.login();
			
			LoginPage lp = new LoginPage(driver);
			lp.enteremail(email);
			
			lp.enterpassword(pwd);
			Thread.sleep(4000);
			lp.click_loginbtn();
		
			
			MyAccountPage mp = new MyAccountPage(driver);
			boolean header_status = mp.myaccpage_exists();
			
			if(exp_result.equalsIgnoreCase("Valid"))
			{
				if(header_status==true)
				{
					driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']")).click();
					Assert.assertTrue(true);
					
				}
				else
				{
					Assert.assertTrue(false);
				}	
			}
		if(exp_result.equalsIgnoreCase("Invalid"))
			{
				if(header_status==true)
				{
					driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']")).click();
					Assert.assertTrue(false);
					
				}
				else
				{
					Assert.assertTrue(true);
				}	
			}
		}catch (Exception e) {
			System.out.println(e.getMessage().toString());
			Assert.fail();
		}
	}
}
