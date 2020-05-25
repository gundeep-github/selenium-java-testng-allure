package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "username")
	private WebElement txtBox_uname;

	@FindBy(name = "password")
	private WebElement txtBox_password;

	@FindBy(xpath = "//input[@value='Log In']")
	private WebElement btn_login;

	public HomePage login(String uname, String password) {
		txtBox_uname.sendKeys(uname);
		txtBox_password.sendKeys(password);
		btn_login.submit();
		return new HomePage(driver);
	}
}