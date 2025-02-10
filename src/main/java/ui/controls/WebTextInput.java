package ui.controls;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class WebTextInput extends WebTypifiedElement {

	private static final Logger log = LogManager.getLogger(WebTextInput.class);
	
	public WebTextInput(WebElement wrappedElement) {
		super(wrappedElement);
	}
	
	@Override
    public String getText() {
        if ("textarea".equals(getWrappedElement().getTagName())) {
            return getWrappedElement().getText();
        }

        return Optional.ofNullable(getWrappedElement().getAttribute("value")).orElse("");
    }
	
	@Override
	public void sendKeys(CharSequence... keysToSend) {
		StringBuilder sb = new StringBuilder();
		for(CharSequence cs : keysToSend) {
			sb.append(cs);
		}
		log.info("Sendkeys for element " + getName() + " with value:'" + sb.toString() + "'");
		getWrappedElement().clear();
		getWrappedElement().sendKeys(keysToSend);
	}

    /**
     * Returns sequence of backspaces and deletes that will clear element.
     * clear() can't be used because generates separate onchange event
     * See https://github.com/yandex-qatools/htmlelements/issues/65
     */
    public String getClearCharSequence() {
        return StringUtils.repeat(Keys.DELETE.toString() + Keys.BACK_SPACE, getText().length());
    }
}
