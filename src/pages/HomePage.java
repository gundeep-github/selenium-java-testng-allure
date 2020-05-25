package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	
	@FindBy(xpath = "//a[text() ='Log Out']")
	private WebElement logOut;
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public boolean isLogoutDisplayed() {
		return logOut.isDisplayed();
	}

}
