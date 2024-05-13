package rahulshettyacademy.testcomponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import rahulshettyacademy.pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializer() throws IOException {
        Properties properties=new Properties();
        FileInputStream fileInputStream=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/rahulshettyacademy/resources/GlobalData.properties");
        properties.load(fileInputStream);

        String browserName=properties.getProperty("browser");
        System.out.println(browserName);
        if(browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }else if(browserName.equals("firefox")){
            //firefox
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver=initializer();
        landingPage=new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filename) throws IOException {
        //Read json to string
        String jsonContent= FileUtils.readFileToString(new File(filename));
        //string to hashmap - jsckson method
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
        return data;
    }

    public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot screenshot=(TakesScreenshot) driver;
        File source=screenshot.getScreenshotAs(OutputType.FILE);
        File destination= new File(System.getProperty("user.dir")+"/reports/"+testCaseName+".png");
        FileUtils.copyFile(source,destination);
        return System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
    }
}
