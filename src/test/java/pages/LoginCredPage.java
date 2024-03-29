package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class LoginCredPage {
    @FindBy (name = "username")
    WebElement usernameText;
    @FindBy (name = "password")
    WebElement passwordText;
    @FindBy (className = "oxd-button")
    WebElement loginButton;
    @FindBy(className = "oxd-userdropdown-name")
    WebElement lblButton;
    @FindBy(className = "oxd-userdropdown-link")
    List<WebElement> dropDownBtn;

    public LoginCredPage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void doLogin(String username, String password){
        usernameText.sendKeys(username);
        passwordText.sendKeys(password);
        loginButton.click();
    }
    public void unableToLogOut(){
        lblButton.click();
        dropDownBtn.get(2).click();
    }
    public void dologOut(){
        lblButton.click();
        dropDownBtn.get(3).click();
    }
}

