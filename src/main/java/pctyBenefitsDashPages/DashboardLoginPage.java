package pctyBenefitsDashPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardLoginPage {

	WebDriver driver;
	@FindBy(xpath = "//input[@id='Username']")
	WebElement userNameField;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement passwordField;

	@FindBy(xpath = "//button[contains(text(),'Log In')]")
	WebElement loginButton;

	public DashboardLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void insertUsernameField(String usr) {
		userNameField.sendKeys(usr);
	}

	public void insertPasswordField(String pwd) {
		passwordField.sendKeys(pwd);
	}

	public void clickLoginButton() {
		loginButton.click();
	}

}
