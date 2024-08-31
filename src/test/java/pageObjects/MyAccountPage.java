package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath ="//*[@id=\"content\"]/h2[1]") WebElement header_myacc;
	@FindBy(xpath ="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement link_logout;

	public boolean myaccpage_exists()
	{
		try {
			return header_myacc.isDisplayed();
			
		}catch (Exception e) {
			return false;
		}
		

	}
	
	public void click_logout()
	{
		link_logout.click();
	}
}
