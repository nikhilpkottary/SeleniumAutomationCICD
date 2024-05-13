package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement countryEle;

    @FindBy(xpath="//button[contains(@class,'ta-item')] [2]")
    WebElement selectCountry;

    @FindBy(css = ".action__submit")
    WebElement submit;

    By countryResults=By.cssSelector(".ta-results");

    public void selectCountry(String countryName){
        Actions actions=new Actions(driver);
        actions.sendKeys(countryEle,countryName).build().perform();
        waitForElementsToAppear(countryResults);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }

}
