package Utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFatory {
    public static String dominio = "br.com.xds.gratidao";
    private  static AndroidDriver<MobileElement> driver;

    public  static AndroidDriver<MobileElement> getDriver (){
        if (driver == null) {
            createDriver();

        }

        return driver;


    }
    public static void createDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Moto G5s");
        //desiredCapabilities.setCapability("udid", "0045186241");
        desiredCapabilities.setCapability("platformVersion", "Android 8.1.0");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability("appPackage", dominio);
        desiredCapabilities.setCapability("appActivity", dominio + ".app.splash.SplashActivity");
        desiredCapabilities.setCapability("skipDeviceInitialization", "true");
//        desiredCapabilities.setCapability("noReset", "true");
//        desiredCapabilities.setCapability("fullReset", "false");
//        desiredCapabilities.setCapability("noSign", "true");
//        desiredCapabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\Andre\\Documents\\base.apk");



        try {
            driver = new  AndroidDriver(new URL("http://localhost:4723/wd/hub"),desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(800, TimeUnit.MILLISECONDS);
    }
    public static void killDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
