package ui.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.controls.WebButton;
import ui.controls.WebTextBlock;
import ui.controls.WebTextInput;
import ui.core.AbstractPOM;
import ui.core.Helpers;

import java.io.IOException;
import java.util.List;

import static ui.core.BaseTest.waitForPageLoad;

public class EmailVendorPage extends AbstractPOM {

    @FindBy(xpath = "//input[@id='username']")
    private WebTextInput inputEmail;

    @FindBy(xpath = "//input[@id='password']")
    private WebTextInput password;

    @FindBy(xpath = "//button[text()='Sign in']")
    private WebButton signInButton;

    @FindBy(xpath = "//span[contains(text(),'Inbox')]")
    private WebTextBlock inbox;

    @FindBy(xpath = "//span[contains(text(),'PBX')]")
    private List<WebTextBlock> messages;

    @FindBy(xpath = "//div[@id='proton-root']//table//h3/b")
    private WebTextBlock oneTimeCode;

    @FindBy(xpath = "//iframe[@title='Email content']")
    private WebElement frame;
    protected static WebDriver driver;

    public EmailVendorPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getOneTimeCode() throws InterruptedException, IOException {
        ((JavascriptExecutor) driver).executeScript("window.open('https://account.proton.me/mail');");
        Helpers.switchToWindowByPartialWindowName(driver, "Proton", 5);
        waitForPageLoad();

        System.out.println("Working with Disposeable Mail");
        inputEmail.sendKeys(Helpers.getProperty("user"));
        password.sendKeys(Helpers.getProperty("password"));
        signInButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[contains(text(),'Inbox')]")));
        inbox.click();

        //Not recommended to use Thread.sleep, but this is third-party vendor automation which was out of scope.
        Thread.sleep(5000);

        messages.get(0).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//iframe[@title='Email content']")));

        driver.switchTo().frame(frame);
        waitForPageLoad();
        String code = oneTimeCode.getText();
        driver.close();
        Helpers.switchToWindowByPartialWindowName(driver, "PBX", 5);
        return code;
    }
}
