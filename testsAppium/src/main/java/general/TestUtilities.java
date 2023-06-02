package general;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestUtilities extends TestListenerAdapter {

    private static final ThreadLocal<SoftAssert> assertGeneric = new ThreadLocal<>();

    protected void assertTrue(boolean validation, String msg){
        assertGeneric.get().assertTrue(validation,msg);
    }

    protected void assertFalse(boolean validation, String msg){
        assertGeneric.get().assertFalse(validation,msg);
    }

    protected void assertAll(){
        assertGeneric.get().assertAll();
    }

    @BeforeMethod
    protected void setSoftAssertValidation() {
        assertGeneric.set(new SoftAssert());
    }

    protected void takeDeviceSnapshot(ThreadLocal<AndroidDriver> mobAndroidDriver, String testName) {
        try {
            File srcFile =  mobAndroidDriver.get().getScreenshotAs(OutputType.FILE);
            Date d = new Date();
            String TimeStamp = d.toString().replace(":","_").replace(" ","_");
            FileUtils.copyFile(srcFile, new File("./Screenshots/" +
                    testName + "_" + TimeStamp + ".png"));
        } catch (IOException e) {
            ErrorsManager.errNExpManager(e);
        }
    }

    @BeforeMethod
    protected void testStartingMsg() {
        System.out.println("[STARTING THE TEST]...\n...");
    }

    @AfterMethod
    protected void testEndingMsg() {
        System.out.println("[ENDING THE TEST]...\n...");
    }

    protected static String basicErrorMsg(String msg) {
        return "[error]  -----> " + msg;
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        Reporter.log("Listener: onTestSuccess");
        super.onTestSuccess(tr);
    }
}
