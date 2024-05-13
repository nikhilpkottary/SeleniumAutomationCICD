package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import rahulshettyacademy.abstractcomponents.AbstractComponent;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".mb-3")
    List<WebElement> productList;
    By findBy=By.cssSelector(".mb-3");
    By addToCart=By.cssSelector("button:last-of-type");
    By toastMessage=By.cssSelector("#toast-container");
    By spinner=By.cssSelector(".ng-animating");
    public List<WebElement> getProductList(){
        waitForElementsToAppear(findBy);
        return productList;
    }

    public WebElement getProductByName(String name){
        WebElement prod=getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(name)).findFirst().orElse(null);
        return prod;
    }
    public void addProductToCart(String name) throws InterruptedException {
        WebElement product=getProductByName(name);
        product.findElement(addToCart).click();
        waitForElementsToAppear(toastMessage);
        waitForElementsToDisappear(spinner);
    }
}
