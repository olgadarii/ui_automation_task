package ui.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.controls.WebButton;
import ui.controls.WebTextBlock;
import ui.core.AbstractPOM;
import ui.core.Amount;

import static ui.core.BaseTest.waitForPageLoad;

public class CompanyPage extends AbstractPOM {

    protected WebDriver driver;
    public CompanyPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }
    @FindBy(xpath = "//button[@id='billing-tab']")
    private WebButton billingTab;

    @FindBy(xpath = "//span[text()='$50']")
    private WebButton usd50;

    @FindBy(xpath = "//span[text()='$100']")
    private WebButton usd100;

    @FindBy(xpath = "//span[text()='$200']")
    private WebButton usd200;

    @FindBy(xpath = "//button[text()='Top-up now']")
    private WebButton topUpNow;

    @FindBy(xpath = "//button[text()='Yes']")
    private WebButton yesButton;

    @FindBy(xpath = "//div[contains(@class,'balance-card')]/span[2]")
    private WebTextBlock balance;

    @FindBy(xpath = "//li[@role='status']")
    private WebTextBlock successMessage;

    public void openBillingTab(){
        billingTab.click();
        waitForPageLoad();
    }

    public double getBalance(){
        return Double.parseDouble(balance.getText()
                .replace(",", "").substring(1));
    }

    public void addBalance(Amount amount) throws InterruptedException {
        switch (amount) {
            case $50 -> usd50.click();
            case $100 -> usd100.click();
            case $200 -> usd200.click();
            default -> throw new IllegalArgumentException("Unsupported amount: " + amount);
        }

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'42')]")));
        topUpNow.click();
        yesButton.click();
    }

    public void waitSuccessMessage(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='status']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[@role='status']")));
    }
}
