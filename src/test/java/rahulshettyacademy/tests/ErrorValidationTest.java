package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;
import rahulshettyacademy.testcomponents.Retry;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"ErrorValidation"},retryAnalyzer = Retry.class)
    public void loginErrorValidation(){
        ProductCatalogue productCatalogue=landingPage.loginApplication("nikhil.p.kottary@gmail.com","$Sel1234");
        Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
    }

    @Test
    public void cartValidation() throws InterruptedException {
        String item="ADIDAS ORIGINAL";

        ProductCatalogue productCatalogue=landingPage.loginApplication("nikhil.p.kottary@gmail.com","$Sel12345");

        productCatalogue.addProductToCart(item);
        CartPage cartPage=productCatalogue.goToCartPage();

        boolean match=cartPage.verifyProductDisplay("ADIDAS ORIGINAL01");
        Assert.assertFalse(match);
    }

}
