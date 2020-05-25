package tests;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

public class BaseTest {

	protected static WebDriver driver;
	protected static BasePage basePage;
	protected Logger logger;

	protected static Properties prop;

	public BaseTest() {
		logger = LoggerFactory.getLogger(BaseTest.class);
		logger.debug(this.getClass().toString());
		
		setFirefoxDriverProperty();

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		try (InputStream input = new FileInputStream("data/config.properties")) {

			prop = new Properties();
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		driver.get(prop.getProperty("url"));
		basePage = new BasePage(driver);
	}

	public static WebDriver getDriver() {
		return driver;
	}

	protected static void waitForPageToLoad()
	{
		new WebDriverWait(driver,30).until((ExpectedCondition<Boolean>) wd ->
		   ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
	
	private static void setChromeDriverProperty() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//libs//chromedriver.exe");
	}

	private static void setFirefoxDriverProperty() {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//libs//geckodriver");
	}

	public boolean switchToTabAndVerifyTitle(String pageTitle, WebDriver driver, WebElement link){
	
		String currentPageHandle = driver.getWindowHandle();                
		link.click();
		ArrayList<String> tabHandles = new ArrayList<String>(driver.getWindowHandles());
		boolean myNewTabFound = false;
		for(String eachHandle : tabHandles)
		{
		    driver.switchTo().window(eachHandle);
		    System.out.println(driver.getTitle());
		    if(driver.getTitle().equalsIgnoreCase(pageTitle))
		    {
		        driver.close();
		        driver.switchTo().window(currentPageHandle);
		        myNewTabFound = true;
		    }
	        driver.switchTo().window(currentPageHandle);
		}
		return myNewTabFound;
	}
}
