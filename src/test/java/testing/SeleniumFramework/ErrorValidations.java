package testing.SeleniumFramework;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import testing.TestComponents.BaseTest;
import testing.TestComponents.Retry;

public class ErrorValidations extends BaseTest{
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		landingpage.loginApplication("kichuad@gmail.com","Kichud@123");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}
	
	@Test(groups= {"ErrorHandling"})
	public void ProductErrorValidation()
	{
		String productName="ZARA COAT 3";
		ProductCatalogue productCatalogue=landingpage.loginApplication("kichuad@gmail.com","Kichuad@123");
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCart();
		Boolean match=cartPage.confirmProduct("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
