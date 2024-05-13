package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement password;

    @FindBy(id="login")
    WebElement submit;

    @FindBy(css ="div[role='alert']")
    WebElement errorMessage;

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCatalogue loginApplication(String username, String pwd){
        userEmail.sendKeys(username);
        password.sendKeys(pwd);
        submit.click();
        return new ProductCatalogue(driver);
    }

    public String getErrorMessage(){
        waitForWebElementsToAppear(errorMessage);
        String alertMessage=errorMessage.getText();
        return alertMessage;
    }

}
