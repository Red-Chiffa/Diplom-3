package org.example;
import io.qameta.allure.junit4.DisplayName;
import org.example.pageObject.AuthorizationPage;
import org.example.pageObject.MainPage;
import org.example.pageObject.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    RegistrationPage objRegistrationPage;
    AuthorizationPage objAuthorizationPage;
    WebDriver driver;
    private String name;
    private String email;
    private String password;


    @Before
    public void before() {
//        driver = WebDriverFactory.getDriver("chrome"); // ok
        driver = WebDriverFactory.getDriver("yandex"); // ok
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        UserCreate user = new UserCreate();
        name = user.getRandomName();
        email = user.getRandomEmail();
        password = user.getRandomPassword();
        objRegistrationPage = new RegistrationPage(driver);
    }

    @Test
    @DisplayName("check Registration - creating user")
    public void checkRegistrationTest() {
        objRegistrationPage.openRegistrationPage();
        objRegistrationPage.createNewUser(name,email,password);
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Вход']")));
        objAuthorizationPage = new AuthorizationPage(driver);
        objAuthorizationPage.authorization(email, password);
        MainPage objMainPage = new MainPage(driver);
        assertEquals("Registration was Failed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    public void checkErrorMassageTest() {
        objRegistrationPage.openRegistrationPage();
        objRegistrationPage.createNewUser(name,email,"12345");
        objRegistrationPage.checkErrorMessageIsDisplayed();
        assertEquals("Registration was Failed", "Некорректный пароль", objRegistrationPage.getTextErrorMessage());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
