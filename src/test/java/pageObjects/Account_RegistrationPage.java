package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

public class Account_RegistrationPage extends BasePage {

	public Account_RegistrationPage(WebDriver driver) {

		super(driver);
	}

	Actions act = new Actions(driver);
	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).
			withTimeout(Duration.ofSeconds(30)).
			pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

	@FindBy(xpath="//*[@id='input-firstname']") WebElement txt_firstname;
	@FindBy(xpath="//*[@id='input-lastname']") WebElement txt_lastname;
	@FindBy(xpath="//*[@id='input-email']") WebElement txt_email;
	@FindBy(xpath="//*[@id='input-telephone']") WebElement txt_telephone;
	@FindBy(xpath="//*[@id='input-password']") WebElement txt_password;
	@FindBy(xpath="//*[@id='input-confirm']") WebElement txt_confirmpassword;
	@FindBy(xpath="//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input") WebElement radiobtn_yes;
	@FindBy(xpath="//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input") WebElement radiobtn_no;
	@FindBy(xpath="//*[@id=\"content\"]/form/div/div/input[1]") WebElement checkbox_privacy;
	@FindBy(xpath="//*[@id=\"content\"]/form/div/div/input[2]") WebElement btn_continue;
	@FindBy(xpath="//div[@id='content']/h1") WebElement confirmmessage;

	public void firstname(String firstname)
	{
		txt_firstname.sendKeys(firstname);
	}

	public void lastname(String lastname)
	{
		txt_lastname.sendKeys(lastname);
	}
	public void email(String email)
	{
		txt_email.sendKeys(email);
	}
	public void telephone(String telephone)
	{
		txt_telephone.sendKeys(telephone);
	}
	public void password(String pass)
	{
		txt_password.sendKeys(pass);
	}
	public void confirmpassword(String cnfpass)
	{
		txt_confirmpassword.sendKeys(cnfpass);
	}

	public void radio_yes()
	{
		radiobtn_yes.click();
	}
	public void radio_no ()
	{
		
		boolean status = radiobtn_no.isSelected();
	try {
		if(status == false)
		{
			radiobtn_no.click();
		}

		else if(status == true)
		{
			return;
		}
		
	}catch (Exception e) {
		// TODO: handle exception
	}
		
	}
	public void checkbox()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", checkbox_privacy);
	}
	public void continue_btn()
	{
		act.moveToElement(btn_continue).click().build().perform();
	}

	public String confirmMessage()
	{
		try
		{
			String actualmsg= confirmmessage.getText();
			return actualmsg;
		}catch (Exception e) {

			String actualresult = e.getMessage().toString();
			return actualresult;
		}
	}


}
