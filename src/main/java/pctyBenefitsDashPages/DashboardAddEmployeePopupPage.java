package pctyBenefitsDashPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardAddEmployeePopupPage {

	WebDriver driver;
	@FindBy(xpath = "//input[@id='firstName']")
	WebElement firstNameField;
	
	@FindBy(xpath = "//input[@id='lastName']")
	WebElement lastNameField;
	
	@FindBy(xpath = "//input[@id='dependants']")
	WebElement dependantsField;
	
	@FindBy(xpath = "//button[@id='addEmployee']")
	WebElement addButton;
	
	@FindBy(xpath = "//body/div[1]/main[1]/div[2]/div[1]/div[1]/div[3]/button[3]")
	WebElement cancelButton;
	
	public DashboardAddEmployeePopupPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void insertFirstNameField(String fname) {
		firstNameField.sendKeys(fname);
	}
	
	public void insertLastNameField(String lname) {
		lastNameField.sendKeys(lname);
	}
	
	public void insertDependantsField(String dep) {
		dependantsField.sendKeys(dep);
	}
	
	public void clickAddButton() {
		addButton.click();
	}
	
	public void clickCancelButton() {
		cancelButton.click();
	}
}
