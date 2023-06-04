package org.example;

import org.example.pageObject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class CheckConstructorTest {
    MainPage objMainPage;
    WebDriver driver;
    @Before
    public void before() {
        driver = WebDriverFactory.getDriver("chrome"); // ok
//        driver = WebDriverFactory.getDriver("yandex"); // ok
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        objMainPage = new MainPage(driver);
    }

    @Test
    public void checkSauceSection() {
        objMainPage.openMainPage();
        assertTrue("Error in section links-Sauce", objMainPage.sauceButtonIsDisplayed());
    }

    @Test
    public void checkBunsSection() {
        objMainPage.openMainPage();
        assertTrue("Error in section Links-Buns", objMainPage.bunsButtonDisplayed());
    }

    @Test
    public void checkFillingSection() {
        objMainPage.openMainPage();
        assertTrue("Error in section Links-Stuffing", objMainPage.fillingButtonIsDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }

}