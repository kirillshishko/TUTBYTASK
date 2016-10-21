package by.tut.pages;

import by.tut.locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class TutByEmailPage {


    private static final By SENT_LINK = Locators.get("SentLink");
    private static final By INBOX_LINK = Locators.get("InboxLink");
    private static final By MAIL_SUBJECT = Locators.get("MailSubject");
    private static final By MAIN_PAGE_LINK = Locators.get("MainPageLink");


    public static void clickSentLink(WebDriver driver) {
        driver.findElement(SENT_LINK).click();
    }

    public static void clickInboxLink(WebDriver driver) {
        driver.findElement(INBOX_LINK).click();
    }

    public  static  void backToMainPage(WebDriver driver){
        driver.findElement(MAIN_PAGE_LINK).click();
    }
    public static boolean checkMail(WebDriver driver, String subject) {

        return driver.findElements(MAIL_SUBJECT).get(0).getAttribute("title").contains(subject);
    }


}
