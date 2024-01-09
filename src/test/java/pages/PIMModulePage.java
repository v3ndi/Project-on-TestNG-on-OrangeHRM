package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public PIMModulePage(WebDriver driver){
        PageFactory.initElements(driver, this);
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
}
