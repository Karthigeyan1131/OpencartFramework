package pageObjects;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath ="//*[@id=\"input-email\"]") WebElement txt_email;
	@FindBy(xpath ="//*[@id=\"input-password\"]") WebElement txt_password;
	@FindBy(xpath ="//*[@id=\"content\"]/div/div[2]/div/form/input") WebElement btn_login;

	public void enteremail(String email)
	{
		txt_email.clear();
		txt_email.sendKeys(email);
	}

	public void enterpassword(String pass)
	{
		txt_password.clear();
		txt_password.sendKeys(pass);
	}

	public void click_loginbtn()
	{
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).
				withTimeout(Duration.ofSeconds(30)).
				pollingEvery(Duration.ofSeconds(5)).
				ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_login));
		btn_login.click();
	}
}
