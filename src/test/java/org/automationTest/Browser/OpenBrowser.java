package org.automationTest.Browser;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class OpenBrowser {
    static WebDriver driver = null;

    public static WebDriver start(String myBrowser) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities caps = new DesiredCapabilities();

        if((myBrowser.equalsIgnoreCase("chrome"))){
            caps.setBrowserName(myBrowser);

            options.addArguments("--disable-dev-shm-usage");
            caps.setPlatform(Platform.WINDOWS);
            options.merge(caps);
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\BS907\\IdeaProjects\\AutomationTask\\chromedriver.exe");
            driver = new ChromeDriver(options);
        }

        return driver;
    }
}
