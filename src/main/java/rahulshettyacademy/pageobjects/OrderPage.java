package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractcomponents.AbstractComponent;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productNames;

    public OrderPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public boolean verifyOrderDisplay(String name){
        boolean match=productNames.stream().anyMatch(productName->productName.getText().equalsIgnoreCase(name));
        return match;
    }
}
