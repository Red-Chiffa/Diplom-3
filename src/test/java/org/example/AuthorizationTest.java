package org.example;

import org.example.pageObject.AuthorizationPage;
import org.example.pageObject.MainPage;
import org.example.pageObject.RegistrationPage;
import org.example.pageObject.RestorePasswordPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class AuthorizationTest {
    RegistrationPage objRegistrationPage;
    AuthorizationPage objAuthorizationPage;
    WebDriver driver;
    private String name;
    private String email;
    private String password;

    @Before
    public void before() {
        driver = WebDriverFactory.getDriver("chrome"); // ok
//        driver = WebDriverFactory.getDriver("yandex"); //ok
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        UserCreate user = new UserCreate();
        name = user.getRandomName();
        email = user.getRandomEmail();
        password = user.getRandomPassword();
        objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.openRegistrationPage();
        objRegistrationPage.createNewUser(name,email,password);
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Вход']")));
    }

    @Test
    public void mainPageAuthorizationButtonTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickAuthorizationButton();
        objAuthorizationPage = new AuthorizationPage(driver);
        objAuthorizationPage.authorization(email, password);
        assertEquals("Authorization was Failed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    public void authorizationByPersonalAccountTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickUserAccountButton();
        objAuthorizationPage = new AuthorizationPage(driver);
        objAuthorizationPage.authorization(email,password);
        assertEquals("Authorization was Failed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    public void authorizationByRegistrationSectionTest() {
        objRegistrationPage.openRegistrationPage();
        objRegistrationPage.clickSingInButtonRegForm();
        AuthorizationPage objAuthorizationPage = new AuthorizationPage(driver);
        objAuthorizationPage.authorization(email,password);
        MainPage objMainPage = new MainPage(driver);
        assertEquals("Authorization was Failed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    public void authorizationByRestorePasswordSectionTest() {
        RestorePasswordPage objRestorePasswordPage = new RestorePasswordPage(driver);
        objRestorePasswordPage.openRestorePage();
        objRestorePasswordPage.clickSingInButtonRestorePassForm();
        AuthorizationPage objAuthorizationPage = new AuthorizationPage(driver);
        objAuthorizationPage.authorization(email,password);
        MainPage objMainPage = new MainPage(driver);
        assertEquals("Authorization was Failed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
