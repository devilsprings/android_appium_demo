package mobile.android.testSteps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

  public static AppiumDriver<MobileElement> driver;

  @BeforeTest
  public void setup() {
    try {
      DesiredCapabilities caps = new DesiredCapabilities();
      caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
      caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
      caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
      caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
      caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
//      caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
      caps.setCapability("appPackage", "com.todoist");
      caps.setCapability("appActivity", "com.todoist.activity.HomeActivity");
      caps.setCapability(MobileCapabilityType.NO_RESET, true);

      URL url = new URL("http://127.0.0.1:4723/wd/hub");

      driver = new AppiumDriver<MobileElement>(url, caps);
      driver = new AndroidDriver<MobileElement>(url, caps);
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    } catch (Exception exp) {
      System.out.println("Cause is: " + exp.getCause());
      System.out.println("Cause is: " + exp.getMessage());
      exp.printStackTrace();
    }
  }

  @AfterTest
  public void tearDown() {
    driver.closeApp();
    driver.quit();
  }
}
