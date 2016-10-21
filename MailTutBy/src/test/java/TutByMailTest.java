import by.tut.baseClasses.TestBase;
import by.tut.helperClasses.Listener;
import by.tut.helperClasses.Parsers;
import by.tut.helperClasses.RandomClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import by.tut.pages.TutByMainPage;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

import static by.tut.helperClasses.EmailOperations.sendSimpleMessage;
import static org.testng.Assert.assertTrue;
import static by.tut.pages.TutByEmailPage.*;
import static by.tut.pages.TutByMainPage.exitUser;


@Listeners(Listener.class)
public class TutByMailTest extends TestBase {
    private static String[] testEmailXML = Parsers.recieveDataFrom(Parsers.ParserTypes.xml);
    private static String[] testEmailCSV = Parsers.recieveDataFrom(Parsers.ParserTypes.csv);
    private static String[] testEmailSQL = Parsers.recieveDataFrom(Parsers.ParserTypes.sql);
    private static String firstRandomSubject = RandomClass.getRandomSubject();
    private static String secondRandomSubject = RandomClass.getRandomSubject();
    private static String thirdRandomSubject = RandomClass.getRandomSubject();
    private static String fourthRandomSubject = RandomClass.getRandomSubject();
    private static String fifthRandomSubject = RandomClass.getRandomSubject();
    private static String firstUserEmail= testEmailXML[0];
    private static String firstUserPassword = testEmailXML[1];
    private static String secondUserEmail = testEmailXML[2];
    private static String secondUserPassword = testEmailXML[3];
    private static String thirdUserEmail = testEmailCSV[4];
    private static String thirdUserPassword = testEmailCSV[5];
    private static String fourthUserEmail = testEmailCSV[6];
    private static String fourthUserPassword = testEmailCSV[7];
    private static String fifthUserEmail = testEmailCSV[8];
    private static String fifthUserPassword = testEmailCSV[9];
    private static String sixthUserEmail = testEmailSQL[10];
    private static String sixthUserPassword = testEmailSQL[11];
    private static String seventhUserEmail = testEmailSQL[12];
    private static String seventhUserPassword = testEmailSQL[13];
    private static String eighthUserEmail = testEmailSQL[14];
    private static String eighthUserPassword = testEmailSQL[15];
    private static String ninthUserEmail = testEmailSQL[16];
    private static String ninthUserPassword = testEmailSQL[17];
    private static String tenthUserEmail = testEmailCSV[18];
    private static String tenthUserPassword = testEmailCSV[19];
    private static String letterBody = "Hello World!!";

    static
    @DataProvider
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
                            String recieverPassword, String subject) throws UnsupportedEncodingException, MessagingException {


        sendSimpleMessage(senderLogin, senderPassword, senderLogin, recieverLogin, subject, letterBody);

        TutByMainPage.enterTheEmail(driver, senderLogin, senderPassword);
        clickSentLink(driver);
        assertTrue(checkMail(driver, subject));
        backToMainPage(driver);
        exitUser(driver);

        TutByMainPage.enterTheEmail(driver, recieverLogin, recieverPassword);
        assertTrue(checkMail(driver, subject));
        backToMainPage(driver);
        exitUser(driver);
    }
}
