package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;
import org.openqa.selenium.JavascriptExecutor;


import java.util.List;

public class PIMModulePage {
    @FindBy(className = "oxd-main-menu-item--name")
    List<WebElement> menuItem;
    @FindBy(className = "oxd-button--medium")
    List<WebElement> addBtn;
    @FindBy(name = "firstName")
    WebElement firstNameText;
    @FindBy(name = "lastName")
    WebElement lastNameText;
    @FindBy(className = "oxd-switch-input--active")
    WebElement toggleBtn;
    @FindBy(className = "oxd-input")
    List<WebElement> toggleEnableAndUserPassField;
    @FindBy(className = "orangehrm-left-space")
    WebElement submitBtn;
    @FindBy(className = "oxd-input")
    List<WebElement> employeeIDText;
    @FindBy(xpath = "//input[@placeholder=\"Type for hints...\"]")
    WebElement searchBoxWithHint;
    @FindBy(xpath = "//button[@type=\"submit\"]")
    WebElement searchButton;



    public PIMModulePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void notCreatingUser() throws InterruptedException {
        menuItem.get(1).click();
        addBtn.get(2).click();
        Thread.sleep(1000);
        submitBtn.click();
    }
    public void filUp(String firstName,String lastName,String username,String password) {
        menuItem.get(1).click();
        addBtn.get(2).click();
        firstNameText.sendKeys(firstName);
        lastNameText.sendKeys(lastName);
        toggleBtn.click();
        toggleEnableAndUserPassField.get(5).sendKeys(username);
        toggleEnableAndUserPassField.get(6).sendKeys(password);
        toggleEnableAndUserPassField.get(7).sendKeys(password);
        submitBtn.click();
    }
    public String getEmployeeID() {
        return employeeIDText.get(4).getAttribute("value");
    }
    public void searchEmployeeById(String employeeIdJson) throws InterruptedException {
        menuItem.get(1).click();
        employeeIDText.get(1).sendKeys(employeeIdJson);
        Thread.sleep(1000);
        employeeIDText.get(1).sendKeys(Keys.ENTER);
    }

    public void searchEmployeeByName(String firstName) throws InterruptedException {
        menuItem.get(8).click();
        searchBoxWithHint.click();
        searchBoxWithHint.sendKeys(firstName);
        Thread.sleep(5000);
        searchBoxWithHint.sendKeys(Keys.ARROW_DOWN);
        searchBoxWithHint.sendKeys(Keys.ENTER);
        searchButton.click();
        Thread.sleep(3000);
    }

}
