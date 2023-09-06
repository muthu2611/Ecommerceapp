package org.pagerefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logincls {
	
	WebDriver driver;
	
	public Logincls(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy (linkText="Sign In")
	WebElement signin;
	
	@FindBy (id="email")
	WebElement username;
	
	@FindBy (id="pass")
	WebElement userpass;
	
	@FindBy (xpath="//button[@class=\\\"action login primary\\\"]")
	WebElement login;
	
	
	public void signinuser(String email , String password) {
		
		signin.click();
		username.sendKeys(email);
		userpass.sendKeys(password);
		login.click();
	}
}
