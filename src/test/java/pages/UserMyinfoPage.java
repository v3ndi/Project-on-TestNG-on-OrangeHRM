package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.util.List;

public class UserMyinfoPage {
    WebDriver driver;
    @FindBy(className = "oxd-main-menu-item--name")
    List<WebElement> menuItem;
    @FindBy(className = "oxd-radio-input--active")
    List<WebElement> genderRadioButton;
    @FindBy(className = "oxd-select-text-input")
    List<WebElement> bloodTypeDropDown;
    @FindBy(className = "oxd-button--secondary")
    List<WebElement> userSavebtn;
    public UserMyinfoPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void userMyInfo() throws InterruptedException {
        menuItem.get(2).click();
        Thread.sleep(3000);
        genderRadioButton.get(0).click();
        userSavebtn.get(0).click();
    }
    public void userBloodType() throws InterruptedException {
        menuItem.get(2).click();
        Thread.sleep(3000);
        bloodTypeDropDown.get(2).click();
        for(int i=0;i<5;i++){
            bloodTypeDropDown.get(2).sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
        }
        bloodTypeDropDown.get(2).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        userSavebtn.get(1).click();
    }
}
