package testing.SeleniumFramework;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testing.TestComponents.BaseTest;
public class StandAloneTest extends BaseTest{
	String productName="ZARA COAT 3";
	@Test(dataProvider= "getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException {
		// TODO Auto-generated method stub
		ProductCatalogue productCatalogue=landingpage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCart();
		Boolean match=cartPage.confirmProduct(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartPage.checkout();
		checkoutpage.countryAddress("India");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder(); 
		String confirmMessage=confirmationpage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue=landingpage.loginApplication("kichuad@gmail.com","Kichuad@123");
		OrderPage ordersPage=productCatalogue.goToOrder();
		ordersPage.VerifyOrderDisplay(productName);
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*HashMap <String,String>map=new HashMap<String,String>();
		map.put("email", "kichuad@gmail.com");
		map.put("password", "Kichuad@123");
		map.put("product","ZARA COAT 3");
		HashMap <String,String>map1=new HashMap<String,String>();
		map1.put("email", "gunnuad@gmail.com");
		map1.put("password", "Gunnuad@123");
		map1.put("product","ADIDAS ORIGINAL");*/
		List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\testing\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		//return new Object[][] {{"kichuad@gmail.com","Kichuad@123","ZARA COAT 3"},{"gunnuad@gmail.com","Gunnuad@123","ZARA COAT 3"}};
	}
}
