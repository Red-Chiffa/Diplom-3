package org.example.pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;
    private final static String mainPage = "https://stellarburgers.nomoreparties.site/";
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By authorizationButton = By.xpath(".//*[text() = 'Войти в аккаунт']");
    private final By userAccountButton = By.xpath("//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");
    private final By sauceButton = By.xpath("//span[text()='Соусы']/..");
    private final By checkSauceDisplayed = By.xpath("//div[contains(span/text(),'Соусы') and contains(@class,'current')]");
    private final By bunsButton = By.xpath("//span[text()='Булки']/..");
    private final By  checkBunsDisplayed = By.xpath("//div[contains(span/text(),'Булки') and contains(@class,'current')]");
    private final By fillingButton = By.xpath("//span[text()='Начинки']/..");
    private final By checkFillingDisplayed = By.xpath("//div[contains(span/text(),'Начинки') and contains(@class,'current')]");
    private final By orderButton = By.className("button_button__33qZ0");

    public void openMainPage (){
        driver.get(mainPage);
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Соберите бургер']")));
    }

    public void clickAuthorizationButton() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(authorizationButton));
        Object elementAuthorizationButton = driver.findElement(authorizationButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementAuthorizationButton);
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(authorizationButton));
        driver.findElement(authorizationButton).click();
    }

    public void clickUserAccountButton() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(userAccountButton));
        driver.findElement(userAccountButton).click();
    }

    public Object textOrderButton () {
        WebElement textButton = new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        return textButton.getText();
    }

    public boolean sauceButtonIsDisplayed() {
        driver.findElement(sauceButton).click();
        return driver.findElement(checkSauceDisplayed).isDisplayed();
    }

    public boolean fillingButtonIsDisplayed() {
        driver.findElement(fillingButton).click();
        return driver.findElement(checkFillingDisplayed).isDisplayed();
    }

    public boolean bunsButtonDisplayed() {
        driver.findElement(sauceButton).click();
        new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        driver.findElement(bunsButton).click();

        return driver.findElement(checkBunsDisplayed).isDisplayed();
    }
}
