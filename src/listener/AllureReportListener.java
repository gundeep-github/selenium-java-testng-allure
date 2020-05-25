package listener;

import org.testng.ITestListener;
import org.testng.ITestResult;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import tests.BaseTest;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class AllureReportListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = BaseTest.getDriver();
		// attach screenshots to report
		Allure.addAttachment(result.getMethod().getMethodName() + "_FAILEDscreenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
	}
}
