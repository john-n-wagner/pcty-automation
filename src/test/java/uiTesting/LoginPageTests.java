package uiTesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pctyBenefitsDashPages.DashboardBenefitsPage;
import pctyBenefitsDashPages.DashboardLoginPage;
import pctyBenefitsDashPages.ErrorPage;

public class LoginPageTests {
	String driverPath = "C:\\Users\\johnw\\chromeDriver\\chromedriver.exe";
	String baseURL = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/Account/Login";
	WebDriver driver;
	DashboardLoginPage dashLoginPage;
	DashboardBenefitsPage dashBenefitsPage;
	ErrorPage errPage;

//Initializing driver and starting off at login screen before each Test
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
	}

// Validating we can login to the dashboard with correct credentials
	@Test(priority = 1)
	public void validateLoginToDashboard() {
		String expectedPageTitle;
		expectedPageTitle = "Log In - Paylocity Benefits Dashboard";
		dashLoginPage = new DashboardLoginPage(driver);
		Assert.assertEquals(driver.getTitle(), expectedPageTitle, "page titles did not match");
		dashLoginPage.insertUsernameField("TestUser80");
		dashLoginPage.insertPasswordField("DAA-v$E7WhF&");
		dashLoginPage.clickLoginButton();
		expectedPageTitle = "Employees - Paylocity Benefits Dashboard";
		dashBenefitsPage = new DashboardBenefitsPage(driver);
		Assert.assertEquals(driver.getTitle(), expectedPageTitle, "page titles did not match");
	}

	/*
	 * Although a bug (need to have invalid credential msgs), validating error page
	 * via bad credentials
	 */
	@Test(priority = 2)
	public void validateErrorOnBadCredentials() {
		String expectedPageTitle;
		expectedPageTitle = "Log In - Paylocity Benefits Dashboard";
		dashLoginPage = new DashboardLoginPage(driver);
		Assert.assertEquals(driver.getTitle(), expectedPageTitle, "page titles did not match");
		dashLoginPage.insertUsernameField("bad");
		dashLoginPage.insertPasswordField("credential");
		dashLoginPage.clickLoginButton();
		errPage = new ErrorPage(driver);
		Assert.assertTrue(errPage.isErrorPresent());
	}

	// tearing down our running driver after each test
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
