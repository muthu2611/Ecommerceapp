package org.testscript;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Javascript;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Webtable {
	
public static void main(String[] args) throws InterruptedException {

		//C:\Users\1\AppData\Local\Google\Chrome\User Data\Profile 6
		WebDriverManager.chromedriver().setup();		
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");
		//options.addArguments("--no-sandbox");
		
	
		options.setExperimentalOption ("excludeSwitches", Collections.singletonList ("enable-automation"));
		options.setExperimentalOption ("useAutomationExtension", false);
		
		//options.addArguments("--disable-infobars");
		options.addArguments("--user-data-dir=C:\\Users\\1\\AppData\\Local\\Google\\Chrome\\User Data\\");
		options.addArguments("--profile-directory=Profile 1");
		
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	
		
		//launch app
		Thread.sleep(2000);		
		driver.get("https://magento.softwaretestingboard.com/");
/*
		//user login
		driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.id("email")).sendKeys("user3mk@abrc.com");
		driver.findElement(By.id("pass")).sendKeys("qwerty@123#");
		driver.findElement(By.xpath("//button[@class=\"action login primary\"]")).click();
		
		driver.findElement(By.xpath("(//button[@class=\"action switch\"])[1]")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'My Account')])[1]")).click();
		
		/*
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement table =driver.findElement(By.xpath("//table[@id=\"my-orders-table\"]"));
		js.executeScript("arguments[0].scrollIntoView();", table);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
				
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells) {
				System.out.println(cell.getText());
			}
		}
		
		
		driver.findElement(By.xpath("(//span[contains(text(),'Edit')])[1]")).click();		
		driver.findElement(By.xpath("//input[@id=\"firstname\"]")).sendKeys("manione");
		driver.findElement(By.xpath("//button[@class=\"action save primary\"]")).click();
		WebElement checkusername=driver.findElement(By.xpath("(//li[@class=\"greet welcome\"])[1]"));
		System.out.println("the uername is :  "+ checkusername.getText());
*/
		driver.close();	
}}
