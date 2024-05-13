package rahulshettyacademy.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import rahulshettyacademy.pageobjects.*;
import rahulshettyacademy.testcomponents.BaseTest;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    ProductCatalogue productCatalogue;
    ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce page")
    public void i_landed_on_Ecommerce_page() throws IOException {
        landingPage= launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username,String password){
        productCatalogue=landingPage.loginApplication(username,password);
    }

    @When("^I add product (.+) to cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        productCatalogue.addProductToCart(productName);
    }

    @When("^checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName){
        CartPage cartPage=productCatalogue.goToCartPage();
        boolean match=cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage=cartPage.goToCheckOut();
        checkoutPage.selectCountry("india");
        confirmationPage=checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed in the ConfirmationPage")
    public void message_displayed_confirmation(String string){
        String validationMessage=confirmationPage.getConfirmationMessage();
        Assert.assertTrue(validationMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void error_message_displayed_confirmation(String string){
        Assert.assertEquals(landingPage.getErrorMessage(),string);
        driver.close();
    }
}
