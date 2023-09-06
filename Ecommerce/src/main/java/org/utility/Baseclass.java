package org.utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {
	
	public static WebDriver driver;

	public void Browserlaunch() {
		
		WebDriverManager.chromedriver().setup();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("start-maximized");
		options.addArguments("--disable-notifications");
		
		
		options.setExperimentalOption ("excludeSwitches", Collections.singletonList ("enable-automation"));
		options.setExperimentalOption ("useAutomationExtension", false);
		options.addArguments("--user-data-dir=C:\\Users\\1\\AppData\\Local\\Google\\Chrome\\User Data\\");
		options.addArguments("--profile-directory=Profile 1");

		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	
	}
	
	public void Urllaunch() {
		driver.get("https://magento.softwaretestingboard.com/");
	}

}
