package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private final WebDriver driver;
    public final By userNameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    public final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    public final By passwordField = By.xpath("//input[@type='password']");
    public final By registrationButton = By.className("button_button__33qZ0");
    public final By errorMessageText = By.xpath(".//fieldset[3]/div/p[contains(text(), 'Некорректный пароль')]");
    private final By singInButtonRegForm = By.className("Auth_link__1fOlj");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openRegistrationPage(){
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    public void createNewUser(String name, String email, String password) {
        driver.findElement(userNameField).clear();
        driver.findElement(userNameField).sendKeys(name);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        new WebDriverWait(driver,15).until(ExpectedConditions.elementToBeClickable(registrationButton));
        driver.findElement(registrationButton).click();

    }

    public void clickSingInButtonRegForm(){
        new WebDriverWait(driver,15).until(ExpectedConditions.elementToBeClickable(singInButtonRegForm));
        driver.findElement(singInButtonRegForm).click();
    }

    public boolean checkErrorMessageIsDisplayed() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(errorMessageText));
        return driver.findElement(errorMessageText).isDisplayed();
    }

    public String getTextErrorMessage () {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(errorMessageText));
        return driver.findElement(errorMessageText).getText();
    }
}