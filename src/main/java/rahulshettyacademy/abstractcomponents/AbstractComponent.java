package rahulshettyacademy.abstractcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "button[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css="button[routerlink*='myorders']")
    WebElement ordersHeader;

    public AbstractComponent(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public void waitForElementsToAppear(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForWebElementsToAppear(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementsToDisappear(By locator) throws InterruptedException {
        Thread.sleep(2000);
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public CartPage goToCartPage(){
        cartHeader.click();
        return new CartPage(driver);
    }


    public OrderPage gotToOrderPage(){
        ordersHeader.click();
        return new OrderPage(driver);
    }
}
