package rahulshettyacademy.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.*;
import rahulshettyacademy.testcomponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @DataProvider
    public Object[][] getData() throws IOException {
        //return new Object[][] {{"nikhil.p.kottary@gmail.com","$Sel12345","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};

        /*HashMap<String,String> user1=new HashMap<String,String>();
        user1.put("email","nikhil.p.kottary@gmail.com");
        user1.put("password","$Sel12345");
        user1.put("item","ZARA COAT 3");

        HashMap<String,String> user2=new HashMap<String,String>();
        user2.put("email","anshika@gmail.com");
        user2.put("password","Iamking@000");
        user2.put("item","ADIDAS ORIGINAL");*/

        List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//main//java//rahulshettyacademy//data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }

    @Test(dataProvider = "getData", groups = "Purchase")
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
        //String item="ADIDAS ORIGINAL";

        ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));

        productCatalogue.addProductToCart(input.get("item"));
        CartPage cartPage=productCatalogue.goToCartPage();

        boolean match=cartPage.verifyProductDisplay(input.get("item"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage=cartPage.goToCheckOut();

        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage=checkoutPage.submitOrder();

        String validationMessage=confirmationPage.getConfirmationMessage();
        Assert.assertTrue(validationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    //validate orders
    @Test(dependsOnMethods = "submitOrder")
    public void ordersTest(){
        String item="ADIDAS ORIGINAL";

        ProductCatalogue productCatalogue=landingPage.loginApplication("nikhil.p.kottary@gmail.com","$Sel12345");
        OrderPage orderPage=productCatalogue.gotToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(item));;
    }

}
