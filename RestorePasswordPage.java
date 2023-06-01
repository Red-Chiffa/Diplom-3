package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RestorePasswordPage {
    private final WebDriver driver;
    private final static String restorePasswordPage = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final By singInButtonRestorePassForm = By.xpath(".//div/main/div/div/p/a");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openRestorePage (){
        driver.get(restorePasswordPage);
    }

    public void clickSingInButtonRestorePassForm(){
        new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(singInButtonRestorePassForm));
        driver.findElement(singInButtonRestorePassForm).click();
    }
}