package pctyBenefitsDashPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardBenefitsPage {

	WebDriver driver;
	@FindBy(xpath = "//button[@id='add']")
	WebElement addEmployeeButton;

	@FindBy(xpath = "//table[@id='employeesTable']")
	WebElement employeeTable;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/table[1]/tbody[1]/tr")
	List<WebElement> e;

	public DashboardBenefitsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickAddEmployeeButton() throws InterruptedException {
		addEmployeeButton.click();
	}
	
	public int getRowSize() {
		return employeeTable.findElements(By.xpath("//tbody/tr")).size();
	}
}
