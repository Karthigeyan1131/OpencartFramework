package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Account_RegistrationPage;
import pageObjects.Homepage;

public class TC01_Account_Registration extends BaseClass {



	@Test(groups = {"Regression","Master"})
	public void register() throws InterruptedException
	{
		Homepage hp = new Homepage(driver);
		hp.myacc();
		hp.register();

		Account_RegistrationPage ap = new Account_RegistrationPage(driver);

		//instead giving single test data.. we can provide many test datas inorder to check the testcase for multiple time/ multiple runs

		/*ap.firstname("John");
		ap.lastname("Durairaj");
		ap.email("jd1131@gmail.com");
		ap.password("jd@1997");
		ap.confirmpassword("jd@1997");
		ap.telephone("7373084271"); */
		try {
			logger.info("********TESTCASE EXECUTION STARTED***********");

			ap.firstname(randomAlphabetic());
			ap.lastname(randomAlphabetic());

			Thread.sleep(5000);

			ap.telephone(randomNumber());
			ap.email(randomAlphabetic()+ "@gmail.com");

			//Random Methods will create a random data for every time when it was called, Here password and confirm password has to be same
			//Storing it in a variable and passing inside the method
			Thread.sleep(5000);

			String random_password = randomalphanumeric();
			ap.password(random_password);
			ap.confirmpassword(random_password);


			Thread.sleep(5000);

			ap.radio_no();
			ap.checkbox();
			ap.continue_btn();


			Thread.sleep(4000);

			//Validation the successful Registration 
			logger.info("**********Validating the Regisration Completion*************");
			String actual_msg= ap.confirmMessage();
			String expected_msg = "Your Account Has Been Created!";
			if(actual_msg.contains(expected_msg))
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Test Failed.......");
				logger.debug("Debugs Logs.......");
				Assert.assertTrue(false);
			}
			
			//	Assert.assertEquals(actual_msg, expected_msg);
			logger.info("********TESTCASE EXECUTION COMPLETED***********");
		}catch (Exception e) {
			Assert.fail();
		}

	}




}
