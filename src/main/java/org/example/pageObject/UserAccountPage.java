package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UserAccountPage {
    private final WebDriver driver;
    private final By logoutButton = By.className("Account_button__14Yp3");
    private final By logoButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/' ]");
    private final By constructorButton = By.xpath("//a[@href='/' and @class='AppHeader_header__link__3D_hX']");

    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickExitButton() {
        new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.findElement(logoutButton).click();
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Вход']")));
    }

    public void clickLogoButton() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(logoButton));
        driver.findElement(logoButton).click();
    }

    public void clickConstructorButton() {
        new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
    }

    public String checkLogInPersonalAccount() {
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        return driver.findElement(logoutButton).getText();
    }
}