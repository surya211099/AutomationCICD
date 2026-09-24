package testing.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testing.SeleniumFramework.CartPage;
import testing.SeleniumFramework.CheckOutPage;
import testing.SeleniumFramework.ConfirmationPage;
import testing.SeleniumFramework.LandingPage;
import testing.SeleniumFramework.ProductCatalogue;
import testing.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecomnmerce_Page() throws IOException
	{
		landingPage=launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password)
	{
		productCatalogue=landingPage.loginApplication(username, password);
	}
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName)
	{
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage=productCatalogue.goToCart();
		Boolean match=cartPage.confirmProduct(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartPage.checkout();
		checkoutpage.countryAddress("India");
		confirmationPage=checkoutpage.submitOrder();
		
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage=confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string1) throws Throwable
	{
		Assert.assertEquals(string1, landingPage.getErrorMessage());
		driver.close();
	}
}
