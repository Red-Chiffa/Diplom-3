package org.example;

import org.example.pageObject.AuthorizationPage;
import org.example.pageObject.MainPage;
import org.example.pageObject.RegistrationPage;
import org.example.pageObject.UserAccountPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class UserAccountPageTest {
    RegistrationPage objRegistrationPage;
    AuthorizationPage objAuthorizationPage;
    MainPage objMainPage;
    WebDriver driver;
    private String name;
    private String email;
    private String password;

    @Before
    public void before() {
        driver = WebDriverFactory.getDriver("chrome"); // ok
//        driver = WebDriverFactory.getDriver("yandex"); // ok
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
        objAuthorizationPage = new AuthorizationPage(driver);
        objMainPage = new MainPage(driver);
    }

    @Test
    public void checkPersonalAccountTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objUserAccountPage = new UserAccountPage(driver);
        assertEquals("Entering was  Failed", "Выход", objUserAccountPage.checkLogInPersonalAccount());
    }

    @Test
    public void checkExitButtonTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objPersonalAccountPage = new UserAccountPage(driver);
        objPersonalAccountPage.clickExitButton();
        assertEquals("ExitFailed", "Войти", objAuthorizationPage.checkLoginButton());
    }

    @Test
    public void checkLogoButtonTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objPersonalAccountPage = new UserAccountPage(driver);
        objPersonalAccountPage.clickLogoButton();
        assertEquals("LogoButtonFailed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    public void checkConstructorButtonTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objPersonalAccountPage = new UserAccountPage(driver);
        objPersonalAccountPage.clickConstructorButton();
        assertEquals("ConstructorButtonFailed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}