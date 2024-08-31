package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {
	
	public Homepage(WebDriver driver) {
		
		super(driver);
	}
	
	
	@FindBy(xpath = "//a[@title=\"My Account\"]") WebElement header_myacc;
	@FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a") WebElement link_register;
	@FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a") WebElement link_login;
	
	public void myacc()
	{
		header_myacc.click();
	}

	public void register()
	{
		link_register.click();
	}
	
	public void login() {
		link_login.click();
		
	}
}
