import baseClasses.TestBase;
import helperClasses.Listener;
import helperClasses.Parsers;
import helperClasses.RandomClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.TutByMainPage;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

import static helperClasses.EmailOperations.sendSimpleMessage;
import static org.testng.Assert.assertTrue;
import static pages.TutByEmailPage.*;
import static pages.TutByMainPage.exitUser;


@Listeners(Listener.class)
public class TutByMailTest extends TestBase {
    static String[] testEmailXML = Parsers.recieveDataFrom(Parsers.ParserTypes.xml);
    static String[] testEmailCSV = Parsers.recieveDataFrom(Parsers.ParserTypes.csv);
    static String[] testEmailSQL = Parsers.recieveDataFrom(Parsers.ParserTypes.sql);
    static String firstRandomSubject = RandomClass.getRandomSubject();
    static String secondRandomSubject = RandomClass.getRandomSubject();
    static String thirdRandomSubject = RandomClass.getRandomSubject();
    static String fourthRandomSubject = RandomClass.getRandomSubject();
    static String fifthRandomSubject = RandomClass.getRandomSubject();
    static String firstUserEmail = testEmailXML[0];
    static String firstUserPassword = testEmailXML[1];
    static String secondUserEmail = testEmailXML[2];
    static String secondUserPassword = testEmailXML[3];
    static String thirdUserEmail = testEmailCSV[4];
    static String thirdUserPassword = testEmailCSV[5];
    static String fourthUserEmail = testEmailCSV[6];
    static String fourthUserPassword = testEmailCSV[7];
    static String fifthUserEmail = testEmailCSV[8];
    static String fifthUserPassword = testEmailCSV[9];
    static String sixthUserEmail = testEmailSQL[10];
    static String sixthUserPassword = testEmailSQL[11];
    static String seventhUserEmail = testEmailSQL[12];
    static String seventhUserPassword = testEmailSQL[13];
    static String eighthUserEmail = testEmailSQL[14];
    static String eighthUserPassword = testEmailSQL[15];
    static String ninthUserEmail = testEmailSQL[16];
    static String ninthUserPassword = testEmailSQL[17];
    static String tenthUserEmail = testEmailCSV[18];
    static String tenthUserPassword = testEmailCSV[19];
    static String letterBody ="Hello World!!";

    static @DataProvider
    public Object[][] testData() {

        return new Object[][]{
                {
                        firstUserEmail, firstUserPassword, secondUserEmail, secondUserPassword, firstRandomSubject},
                {
                        thirdUserEmail, thirdUserPassword, fourthUserEmail, fourthUserPassword, secondRandomSubject},
                {
                        fifthUserEmail, fifthUserPassword, sixthUserEmail, sixthUserPassword, thirdRandomSubject},
                {
                        seventhUserEmail, seventhUserPassword, eighthUserEmail, eighthUserPassword, fourthRandomSubject},
                {
                        ninthUserEmail, ninthUserPassword, tenthUserEmail, tenthUserPassword, fifthRandomSubject}
        };
    }

    @Test(dataProvider = "testData")
    public void sendLetters(String senderLogin, String senderPassword, String recieverLogin,
                            String recieverPassword , String subject) throws UnsupportedEncodingException, MessagingException {


        sendSimpleMessage(senderLogin, senderPassword, senderLogin, recieverLogin, subject, letterBody);

        TutByMainPage.enterTheEmail(driver, senderLogin, senderPassword);
        clickSentLink(driver);
        assertTrue(checkMail(driver, subject));
        backToMainPage(driver);
        exitUser(driver);

        TutByMainPage.enterTheEmail(driver,recieverLogin,recieverPassword);
        assertTrue(checkMail(driver,subject));
        backToMainPage(driver);
        exitUser(driver);
    }
}
