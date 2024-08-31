package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC02_Login extends BaseClass{
	
	@Test(groups = {"Sanity","Master"})
	void login_test()
	{
		logger.info("***** TC02_Login Execution Started *****");
		try {
			Homepage hp = new Homepage(driver);
			hp.myacc();
			hp.login();
			
			LoginPage lp = new LoginPage(driver);
			lp.enteremail(p.getProperty("email"));
			lp.enterpassword(p.getProperty("password"));
			lp.click_loginbtn();
			
			MyAccountPage mp = new MyAccountPage(driver);
			boolean header_status = mp.myaccpage_exists();
			
		//	Assert.assertEquals(header_status, true);
			Assert.assertTrue(header_status);
		}
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
			Assert.fail();
		}
		
		logger.info("***** TC02_Login Execution Completed *****");
	}

}
