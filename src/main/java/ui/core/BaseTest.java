package ui.core;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public abstract class BaseTest {

    private static final Logger log = LogManager.getLogger(BaseTest.class);
    protected static WebDriver driver;

    @BeforeClass
    public void setup() throws IOException {
        log.info("Open browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://app.pbxstage.net/login";
        log.info("Navigate to: " + url);
        driver.get(url);
    }

    @AfterClass
    public void close() {
        driver.close();
    }

    public static void waitForPageLoad() {
        new WebDriverWait(driver, 20).until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        log.info("Page Loaded");
    }
}