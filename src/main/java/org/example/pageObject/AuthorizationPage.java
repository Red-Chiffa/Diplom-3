package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationPage {
    private final WebDriver driver;
    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By loginButton = By.className("button_button__33qZ0");

    public void authorization (String email, String password){
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Соберите бургер']")));
    }

    public String checkLoginButton () {
        new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return driver.findElement(loginButton).getText();
    }
}