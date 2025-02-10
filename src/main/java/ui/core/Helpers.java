package ui.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Set;

public class Helpers {

    private static final Logger log = LogManager.getLogger(Helpers.class);
    protected static WebDriver driver;

    public static String getProperty(String key) throws IOException {
        FileReader reader = new FileReader("credentials.properties");
        Properties p = new Properties();
        p.load(reader);

        return p.getProperty(key);
    }

    public static String switchToWindowByPartialWindowName(WebDriver driver, String partialWindowName, int timeout) {
        Set<String> allWindows;
        long end;
        long start = System.currentTimeMillis();
        do {
            allWindows = driver.getWindowHandles();
            end = System.currentTimeMillis();
            for (String curWindow : allWindows) {
                driver.switchTo().window(curWindow);
                if (driver.getTitle().contains(partialWindowName)) {
                    log.info("Switching to " + driver.getTitle() + ": " + driver.getCurrentUrl());
                    return driver.getWindowHandle();
                }
            }
        } while (end - timeout * 1000 < start);
        log.error("The window with title '" + partialWindowName + "' was not found");
        return driver.getWindowHandle();
    }

    public static String getFormattedSystemTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d'th', yyyy 'at' h:mm a");
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }
}