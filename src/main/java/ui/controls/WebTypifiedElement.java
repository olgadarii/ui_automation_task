package ui.controls;

import ui.core.Helpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class WebTypifiedElement extends TypifiedElement {

    private static final Logger log = LogManager.getLogger(WebTypifiedElement.class);

    public WebTypifiedElement(WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public void click() {
        log.info("Click on element " + getName());
        getWrappedElement().click();
    }

    public void checkIfDisplayed() {
        Assert.assertTrue(this.isDisplayed(), "Element " + getName() + " is displayed");
        log.info("Element " + getName() + " is displayed");
    }

    public boolean isElementPresent() {
        boolean flag;
        try {
            this.isDisplayed();
            log.info("Element " + getName() + " is displayed");
            flag = true;
        } catch (Exception e) {
            log.info("Element " + getName() + " is NOT displayed");
            flag = false;
        }
        return flag;
    }
}