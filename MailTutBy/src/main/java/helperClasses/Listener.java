package helperClasses;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static baseClasses.TestBase.LOG;

/**
 * Created by user on 10.10.2016.
 */
public class Listener  implements ITestListener{
    public void onTestStart(ITestResult itr) {
        LOG.info("Test started!"+itr.getName());
    }

    public void onTestSuccess(ITestResult itr) {
        LOG.info("Test succeeded"+itr.getName());

    }

    public void onTestFailure(ITestResult itr) {

        ScreenShot.makeScreenshot("failure screenshot");
        LOG.info("Test failed"+itr.getName());
        try {
            ScreenShot.shoot("failure screenshot");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult itr) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult itr) {

    }

    public void onStart(ITestContext itr) {

    }

    public void onFinish(ITestContext itr) {

    }
}

