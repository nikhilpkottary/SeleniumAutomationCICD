package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".cartSection h3" )
    List<WebElement> productTitles;

    @FindBy(css=".totalRow button")
    WebElement checkoutEle;

    public boolean verifyProductDisplay(String name){
        boolean match=productTitles.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(name));
        return match;
    }

    public CheckoutPage goToCheckOut(){
        checkoutEle.click();
        return new CheckoutPage(driver);
    }

}
