package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

@Listeners(listener.AllureReportListener.class)
public class HomePageTest extends BaseTest {
	LoginPage loginPage;
	HomePage homePage; 

	@BeforeClass
	public void setup() {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		homePage = loginPage.login(prop.getProperty("username"),
				prop.getProperty("password"));
	}

	@Test
	public void verifyPageTitle() {
		String expectedTitle = "ParaBank | Accounts Overview";
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	
	@Test
	public void verifyLogoutLnk() {
		Assert.assertTrue(homePage.isLogoutDisplayed());
	}
	
	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}
}
