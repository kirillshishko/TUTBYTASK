package helperClasses;

import baseClasses.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

public class ScreenShot {
    @Attachment(value = "(0)", type = "image/png")
    public static byte[] makeScreenshot(String name) {
        return ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void shoot(String name) throws IOException {

        File screenshot = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(screenshot, new File("Screenshot\\" +name+System.currentTimeMillis()+".png"));
    }
}
