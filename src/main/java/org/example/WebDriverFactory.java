package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    public static WebDriver getDriver(String browserName) {

        WebDriver driver;
        switch(browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions()
                        .addArguments("--remote-allow-origins=*")
                );
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
                driver = new ChromeDriver(new ChromeOptions()
                        .setBinary("C:\\YandexBrowser\\Application\\browser.exe")
                );
                break;
            default: throw new RuntimeException("Browser " + browserName + " not exist");
        }


        return driver;
    }
}