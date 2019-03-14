package xyz.aingaran.framework.core;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;

/**
 * Core class that manages Driver.
 * All functions inside this will and should be static.
 */
public class Framework {

    private static final Logger logger = Logger.getLogger(Framework.class);
    public static String username = "sonali.dutta1";
    public static String authkey = "u4QqoEX92figo4CeGUb2AnZD9wLnHK0SNWN2GqXLt1aXOI8QsR";
    public static RemoteWebDriver webDriver = null;
    public static String gridURL = "@hub.lambdatest.com/wd/hub";
    //private static WebDriver webDriver = null;

    private Framework() {
        // Private constructor to hide the default implicit constructor.
        // This class should not be used as an instance.
    }

    public static void init()  {
        /*System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriverlinux");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("--window-size=1366x768");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();*/
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "70.0");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        capabilities.setCapability("idleTimeout", 270); //To increase the idle timeout
        capabilities.setCapability("visual",true);
        try {
            System.out.println("gridUrL " + gridURL);
            webDriver = new RemoteWebDriver(new URL("http://" + username + ":" + authkey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL" + gridURL);
        } catch (Exception e) {
            System.out.println(e.getMessage() + gridURL);
        }
    }

    public static WebDriver getWebDriver()  {
        if (null == webDriver)  {
            init();
            return webDriver;
        } else  {
            return webDriver;
        }
    }

    public static byte[] takeScreenShot(String scenario, String testCase) {
        File shot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(shot, new File("src/test/resources/screenshot/" + LocalDate.now()
                    + File.separator + scenario + File.separator + testCase + ".jpeg"));
        } catch (IOException ioException)   {
            logger.error("Cannot save screenshot", ioException);
        }
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void destroy()    {
        if(null != webDriver)   {
            webDriver.close();
            webDriver.quit();
            webDriver = null;
        }
    }
}
