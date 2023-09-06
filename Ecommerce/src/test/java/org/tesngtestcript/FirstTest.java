package org.tesngtestcript;

import org.openqa.selenium.WebDriver;
import org.pagerefactory.Logincls;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.utility.Baseclass;

public class FirstTest extends Baseclass  {
	
	 WebDriver driver;
	@BeforeMethod
	public void launchurl() {
		
		Baseclass base = new Baseclass();
		base.Browserlaunch();
		base.Urllaunch();
		
	}
	
	@Test
	public void testone() {
		Logincls log = new Logincls(driver);
		log.signinuser("muthu123", "muthu");
	}
	
	

} 
