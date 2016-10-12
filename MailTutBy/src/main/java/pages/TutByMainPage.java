package pages;

import locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TutByMainPage {
    private static final By ENTER = Locators.get("EnterLink");
    private static final By LOGIN = Locators.get("LoginLink");
    private static final By PASSWORD = Locators.get("PasswordLink");
    private static final By SUBMIT = Locators.get("SubmitLink");
    private static final By MAIL_LINK = Locators.get("MailLink");
    private static final By TUT_BY_LOGO = Locators.get("TutByLogo");
    private  static final By EXIT_USER = Locators.get("ExitUserLink");

    public static void enterTheEmail(WebDriver driver, String userLogin, String userPassword) {
        driver.findElement(ENTER).click();
        driver.findElement(LOGIN).sendKeys(userLogin);
        driver.findElement(PASSWORD).sendKeys(userPassword);
        driver.findElement(SUBMIT).click();

        driver.findElement(MAIL_LINK).click();
    }

    public static  void exitUser(WebDriver driver){
        driver.findElement(ENTER).click();
        driver.findElement(EXIT_USER).click();
    }

    public static boolean isLogoDisplyed(WebDriver driver) {
        if (!driver.findElement(TUT_BY_LOGO).isDisplayed()) {
            return false;
        }
        return true;
    }

}

