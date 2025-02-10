package ui.controls;

import org.openqa.selenium.WebElement;

public class WebLink extends WebTypifiedElement{

	public WebLink(WebElement wrappedElement) {
		super(wrappedElement);
	}

	public String getReference() {
        return getWrappedElement().getAttribute("href");
    }
}
