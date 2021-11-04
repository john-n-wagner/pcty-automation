package pctyBenefitsDashPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ErrorPage {
	WebDriver driver;
	@FindBy(xpath = "//h1[contains(text(),'Error.')]")
	WebElement errorHeader;

	public ErrorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isErrorPresent() {
		return errorHeader.isDisplayed();
	}
	
}
