package org.testscript;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Brokenlinks {
public static void main(String[] args) throws IOException {
	
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless"); // Run Chrome in headless mode (optional)
    WebDriver driver = new ChromeDriver(options);
    
	driver.get("https://magento.softwaretestingboard.com/");
   
	/*
	List<WebElement> links = driver.findElements(By.tagName("a"));

    for (WebElement link : links) {
        String url = link.getAttribute("href");
        
        if (url != null && !url.isEmpty()) {
            try {
                URL linkURL = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) linkURL.openConnection();
                httpURLConnection.setConnectTimeout(5000); // Set a timeout for the connection

                int statusCode = httpURLConnection.getResponseCode();
                if (statusCode != 200) {
                    System.out.println("Broken Link: " + url + " - Status Code: " + statusCode);
                }
            } catch (Exception e) {
                System.err.println("Error checking link: " + url + " - " + e.getMessage());
            }
        }}
      */
	
		List<WebElement> images =driver.findElements(By.tagName("img"));
		
		for(WebElement image : images) {
			String src = image.getAttribute("src");
			if(src !=null){
				URL urls = new URL(src);
				HttpURLConnection http =(HttpURLConnection) urls.openConnection();
				http.setRequestMethod("HEAD");
				int responseCode = http.getResponseCode();
				
				 if (responseCode == 200) {
	                    System.out.println("Broken image: " + src);
	                }
        }}	 
			
        driver.close();      
    }    
}