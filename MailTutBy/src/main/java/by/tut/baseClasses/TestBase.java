package by.tut.baseClasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public static    WebDriver driver;
    public static Logger LOG;

    @BeforeTest
    public  void setup(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.tut.by/");
        LOG = Logger.getLogger("Logger");
    }
    @AfterTest
    public void tearDown(){
       // driver.close();
    }
}
