package mobile.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import mobile.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URI;
import java.net.URISyntaxException;

public class AndroidCreateWebSessionTest extends BaseTest {
    private AndroidDriver<WebElement> driver;

    @BeforeClass
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("browserName", "Chrome");
        driver = new AndroidDriver<WebElement>(getServiceUrl(), capabilities);
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingAnyFreePort().withArgument(() -> "--allow-insecure","chromedriver_autodownload"));

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test()
    public void testCreateWebSession() throws URISyntaxException {
        driver.get(new URI("http://www.google.com").toString());
        String title = driver.getTitle();
        Assert.assertEquals(title, "Google");
    }
}