package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class StandAloneTest {
    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));

        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("nikhil.p.kottary@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("$Sel12345");
        driver.findElement(By.id("login")).click();

        /*
        List<WebElement> products=driver.findElements(By.xpath("//div[contains(@class,'col-lg-4')]"));
        String item="ADIDAS ORIGINAL";

        for(int i=0;i<products.size();i++){
            String productName=products.get(i).findElement(By.xpath("//h5/b")).getText();
            System.out.println(productName);
            if(item.equals(productName)){
                products.get(i).findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
                break;
            }
        }*/

        String item="ADIDAS ORIGINAL";

        List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
        WebElement matchedProduct=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(item)).findFirst().orElse(null);
        matchedProduct.findElement(By.cssSelector("button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

        List<WebElement> cartItems=driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match=cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(item));
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions actions=new Actions(driver);
        actions.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')] [2]")).click();

        driver.findElement(By.cssSelector(".action__submit")).click();

        String validationMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(validationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


    }
}
