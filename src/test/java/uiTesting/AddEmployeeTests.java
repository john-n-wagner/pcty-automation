package uiTesting;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pctyBenefitsDashPages.DashboardAddEmployeePopupPage;
import pctyBenefitsDashPages.DashboardBenefitsPage;
import pctyBenefitsDashPages.DashboardLoginPage;

public class AddEmployeeTests {
	String driverPath = "C:\\Users\\johnw\\chromeDriver\\chromedriver.exe";
	String baseURL = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/Account/Login";
	WebDriver driver;
	DashboardLoginPage dashLoginPage;
	DashboardBenefitsPage dashBenefitsPage;
	DashboardAddEmployeePopupPage addEmployeePopup;
	int numOfDependants;

	// Initializing driver and starting off at login screen, logging in before each
	// Test
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
		dashLoginPage = new DashboardLoginPage(driver);
		dashLoginPage.insertUsernameField("TestUser80");
		dashLoginPage.insertPasswordField("DAA-v$E7WhF&");
		dashLoginPage.clickLoginButton();
		Random rn = new Random();
		numOfDependants = (rn.nextInt(10) + 1);
	}

	//adding an employee, validating row count is no longer the same (i.e. we created a new employee)
	@Test
	public void testAddEmployee() throws InterruptedException {
		dashBenefitsPage = new DashboardBenefitsPage(driver);
		int initalRowSize = dashBenefitsPage.getRowSize();
		System.out.println(initalRowSize);
		dashBenefitsPage.clickAddEmployeeButton();
		addEmployeePopup = new DashboardAddEmployeePopupPage(driver);
		addEmployeePopup.insertFirstNameField("Jane");
		addEmployeePopup.insertLastNameField("Doe");
		addEmployeePopup.insertDependantsField(String.valueOf(numOfDependants));
		addEmployeePopup.clickAddButton();
		dashBenefitsPage = new DashboardBenefitsPage(driver);
		Thread.sleep(3000);
		int afterAdditionRowSize = dashBenefitsPage.getRowSize();
		System.out.println(afterAdditionRowSize);
		Assert.assertNotEquals(initalRowSize, afterAdditionRowSize, "no additional employee created, we have failed");

	}

	// tearing down our running driver after each test
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
