package org.testscript;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndtoEnd {
	public static void main(String[] args) throws InterruptedException {

		
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-notifications");
		
		WebDriverManager.chromedriver().setup();		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		//launch app
		driver.get("https://magento.softwaretestingboard.com/");

		//user login
		driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.id("email")).sendKeys("user3mk@abrc.com");
		driver.findElement(By.id("pass")).sendKeys("qwerty@123#");
		driver.findElement(By.xpath("//button[@class=\"action login primary\"]")).click();

		WebElement mens = driver.findElement(By.id("ui-id-5"));
		WebElement Tops = driver.findElement(By.xpath("(//span[contains(text(),'Tops')])[2]"));
		WebElement Jackets = driver.findElement(By.xpath("(//span[contains(text(),'Jackets')])[2]"));

		Actions a = new Actions(driver);
		a.moveToElement(mens).moveToElement(Tops).build().perform();
		Jackets.click();

		List<WebElement> alljackets = driver.findElements(By.xpath("(//div[@class=\"products wrapper grid products-grid\"])//strong//a"));

		// HashMap<String, String> Storevalues = new HashMap<String, String>();

		for (WebElement jacket : alljackets) {
			// String linkname =jacket.getAttribute("href");
			String jackname = jacket.getText();
			// Storevalues.put(linkname, textone);

			if (jackname.contains("Lando Gym Jacket")) {
				jacket.click();
				break;
			}
		}
		// driver.navigate().refresh();
		Thread.sleep(4000);

		WebElement getprice = driver.findElement(By.xpath("//div[@class=\"product-info-price\"]/div[1]"));
		String Pricevalue = getprice.getText();
		String priceString = Pricevalue.replaceAll("[^\\d.]", "");

		// get initial total amount
		double price = Double.parseDouble(priceString);
		System.out.println("Extracted Price: $" + price);
		double finprice = price;
		int quantity = 3;
		double expectedTotal = price * quantity;
		System.out.println(expectedTotal);

		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.findElement(By.id("option-label-size-143-item-167")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@aria-label=\"Blue\"]")).click();
		WebElement qty = driver.findElement(By.id("qty"));
		qty.clear();
		Thread.sleep(1000);
		qty.sendKeys("3");

		WebElement addcard = driver.findElement(By.xpath("//button[@class=\"action primary tocart\"]/span"));
		js.executeScript("arguments[0].scrollIntoView();", addcard);
		js.executeScript("arguments[0].click();", addcard);

		Thread.sleep(2000);
		WebElement gotocardicon = driver.findElement(By.xpath("//a[@class=\"action showcart\"]"));
		js.executeScript("arguments[0].scrollIntoView();", gotocardicon);
		gotocardicon.click();

		Thread.sleep(2000);
		WebElement gotocard = driver.findElement(By.xpath("//a[@class=\"action viewcart\"]"));
		js.executeScript("arguments[0].click();", gotocard);

		WebElement finalprice = driver
				.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[4]/span/span"));
		String totalamtonpage = finalprice.getText();
		String finalpriceafter = totalamtonpage.replaceAll("[^\\d.]", "");
		double priceoncurrentpage = Double.parseDouble(finalpriceafter);
		System.out.println(priceoncurrentpage);
		Assert.assertEquals(priceoncurrentpage, expectedTotal);

		/*
		driver.findElement(By.id("block-shipping-heading")).click();

		Thread.sleep(3000);
		WebElement country = driver.findElement(By.xpath("//div[@name=\"shippingAddress.country_id\"]//select"));
		Select s = new Select(country);
		s.selectByValue("IN");

		WebElement state = driver.findElement(By.xpath("//div[@name=\"shippingAddress.region_id\"]//select"));
		Select s1 = new Select(state);
		s1.selectByVisibleText("Tamil Nadu");

		WebElement code = driver.findElement(By.xpath("//input[@name=\"postcode\"]"));
		code.sendKeys("603103");
		*/
		
		Thread.sleep(6000);
		WebElement checkoutprice = driver.findElement(By.xpath("//table[@class=\"data table totals\"]//strong//span"));
		String chckouttotlamt = checkoutprice.getText();
		System.out.println(chckouttotlamt);

		WebElement pagecheckout = driver.findElement(By.xpath("//span[contains(text(),'Proceed to Checkout')]"));
		js.executeScript("arguments[0].scrollIntoView();", pagecheckout);
		Thread.sleep(2000);
		pagecheckout.click();

		// payment page
		/*
		 * 
		 * WebElement checkbox=driver.findElement(By.xpath(
		 * "(//input[@id=\"billing-address-same-as-shipping-checkmo\"])[1]")); Boolean
		 * statusofbox=checkbox.isSelected();
		 * System.out.println("the status is: "+statusofbox);
		 */
		Thread.sleep(18000);
		WebElement newaddr=driver.findElement(By.xpath("//span[contains(text(),'New Address')]"));
		js.executeScript("arguments[0].scrollIntoView();", newaddr);
		newaddr.click();

		// address fill
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name=\"company\"]")).sendKeys("heerrinbee");
		driver.findElement(By.xpath("//input[@name=\"street[0]\"]")).sendKeys("19222 logo plate mall");
		driver.findElement(By.xpath("//input[@name=\"street[1]\"]")).sendKeys("logoase plate city");
		driver.findElement(By.xpath("//input[@name=\"street[2]\"]")).sendKeys("logo town");
		driver.findElement(By.xpath("//input[@name=\"city\"]")).sendKeys("chennai");

		Thread.sleep(1000);
		// js.executeScript("arguments[0].click();", addcard);
		WebElement countryList = driver.findElement(By.xpath("//select[@name=\"country_id\"]"));
		js.executeScript("arguments[0].scrollIntoView();", countryList);
		Select selectcountry = new Select(countryList);
		selectcountry.selectByVisibleText("India");

		Thread.sleep(1000);
		WebElement regionids = driver.findElement(By.xpath("//select[@name=\"region_id\"]"));
		js.executeScript("arguments[0].scrollIntoView();", regionids);
		Select regions = new Select(regionids);
		regions.selectByVisibleText("Tamil Nadu");

		WebElement pincode = driver.findElement(By.xpath("//input[@name=\"postcode\"]"));
		js.executeScript("arguments[0].scrollIntoView();", pincode);
		pincode.sendKeys("603113");

		WebElement cellphone = driver.findElement(By.xpath("//input[@name=\"telephone\"]"));
		js.executeScript("arguments[0].scrollIntoView();", cellphone);
		cellphone.sendKeys("2000766780");

		// ship this address
		driver.findElement(By.xpath("//span[contains(text(),'Ship here')]")).click();

		WebElement continuebtn = driver.findElement(By.xpath("//button[@class=\"button action continue primary\"]"));
		js.executeScript("arguments[0].scrollIntoView();", continuebtn);
		Thread.sleep(1000);
		continuebtn.click();

		 Thread.sleep(7000);
		 WebElement placeorders=driver.findElement(By.xpath("//span[contains(text(),'Place Order')]//parent::button"));
		 js.executeScript("arguments[0].scrollIntoView();", placeorders);
		 placeorders.click();

		Thread.sleep(4000);
		WebElement ordernumber = driver.findElement(By.xpath("//a[@class=\"order-number\"]"));
		String orderidgenerated = ordernumber.getText();
		System.out.println("the order id of my current order is:  " + orderidgenerated);

		// driver.findElement(By.xpath("//a[contains(text(),'Print receipt')]"));
		driver.findElement(By.xpath("//span[contains(text(),'Continue Shopping')]")).click();

		String Pagetitle = driver.getTitle();
		Assert.assertEquals(Pagetitle, "Home Page");
		System.out.println("check user current page: " + Pagetitle);

		// driver.close();

	}
}

//shippingAddress.region_id
/*
 * for (Entry<String, String> entry : Storevalues.entrySet()) { String mapkey
 * =entry.getKey(); String mapvalue =entry.getValue();
 * System.out.println("The final value of key is :"+mapkey+
 * "    value is :"+mapvalue); }
 * 
 */
