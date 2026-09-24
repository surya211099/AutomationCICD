package testing.SeleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testing.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement SelectCountry;
	
	@FindBy(css=".action__submit")
	WebElement Submit;
	
	public CheckOutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public void countryAddress(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		SelectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		Submit.click();
		return new ConfirmationPage(driver);
	}
	
}
