/**
 * 
 */
package maintestcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.AppiumHelper;
import common.BaseClass;
import common.DataProviders;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

/**
 * @author Hema Sumanjali
 *
 */
public class Prodinfoserach extends BaseClass{
	
	
	@Test(dataProvider="login",dataProviderClass=DataProviders.class)
	public void Addtocart(String username, String password) throws Exception {
		driver.findElementByXPath(AppiumHelper.signin).click();
		driver.findElementByXPath(AppiumHelper.useemailorusername).click();
		driver.findElementById(AppiumHelper.username).click();
		driver.findElementById(AppiumHelper.username).sendKeys(username);

		driver.findElementById(AppiumHelper.passowrd).click();
		driver.findElementById(AppiumHelper.passowrd).sendKeys(password);
		driver.findElementByXPath(AppiumHelper.signin).click();
		
		driver.findElementById("com.ebay.mobile:id/search_box").click();
		driver.findElementById("com.ebay.mobile:id/search_src_text").click();
		driver.findElementById("com.ebay.mobile:id/search_src_text").sendKeys("65-inch TV");
		
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		
		driver.findElementByAccessibilityId("When you save a search, we'll let you know when a new item is listed double tap to dismiss").click();
		
		helper.scrollUp(driver);
		
	List<MobileElement> listofjobs=driver.findElementsById("com.ebay.mobile:id/cell_collection_item");
			
		System.out.println(listofjobs.get(listofjobs.size()-2).findElementById("com.ebay.mobile:id/search_item_text_header").getText());
		listofjobs.get(listofjobs.size()-2).findElementById("com.ebay.mobile:id/search_item_text_header").click();
		String titleinproductpage=driver.findElementById("com.ebay.mobile:id/initial_title_textview").getText();
		System.out.println("Name in product page : " +titleinproductpage);

		String Priceinproductpage=driver.findElementById("com.ebay.mobile:id/textview_item_price").getText();
		System.out.println("Price in product page : " +Priceinproductpage);
		helper.scrollUp(driver);
		//System.out.println(driver.findElementById("com.ebay.mobile:id/textview_item_name").getText());
		helper.clickOnElementByID(driver, "com.ebay.mobile:id/button_add_to_cart");
	
		//go to cart
		helper.clickOnElementByID(driver, "com.ebay.mobile:id/call_to_action_button");
		
		String TitleInCartPage=driver.findElementById("com.ebay.mobile:id/item_title").getText();
		System.out.println("Name in cart page : " +TitleInCartPage);

		String Priceincartpage=driver.findElementById("com.ebay.mobile:id/item_price").getText();
		System.out.println("Price in cart page : " +Priceincartpage);

		Assert.assertTrue(titleinproductpage.equals(TitleInCartPage), "Title not matched");
		Assert.assertTrue(Priceinproductpage.equals(Priceincartpage), "price not matched");
		
driver.findElementById("com.ebay.mobile:id/shopping_cart_checkout").click();

String TitleInCheckoutPage=driver.findElementById("com.ebay.mobile:id/item_title").getText();
System.out.println("Name in Checkout page : " +TitleInCheckoutPage);

String Priceincheckoutpage=driver.findElementById("com.ebay.mobile:id/item_price").getText();
System.out.println("Price in checkout page : " +Priceincheckoutpage);

Assert.assertTrue(TitleInCartPage.equals(TitleInCheckoutPage), "Title Not matched");
Assert.assertTrue(Priceincartpage.equals(Priceincheckoutpage), "price not matched");

	}
	
		
}
