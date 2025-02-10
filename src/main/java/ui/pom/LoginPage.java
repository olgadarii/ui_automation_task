package ui.pom;

import ui.controls.WebButton;
import ui.controls.WebTextBlock;
import ui.controls.WebTextInput;
import ui.core.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ui.core.Helpers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static ui.core.BaseTest.waitForPageLoad;

public class LoginPage extends AbstractPOM {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text()='Log in']")
    private WebButton logInButton;

    @FindBy(xpath = "//input[@name='email']")
    private WebTextInput email;

    @FindBy(xpath = "//input[@name='password']")
    private WebTextInput password;

    @FindBy(xpath = "//div/h2[text()='Active session detected']")
    private List<WebTextBlock> activeSessionAsList;

    @FindBy(xpath = "//button[text()='Yes']")
    private WebButton yesButton;

    @FindBy(xpath = "//button[text()='No']")
    private WebButton noButton;

    @FindBy(xpath = "//label[text()='One-time password']//following-sibling::div/input")
    private List<WebTextInput> oneTimePassword;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebButton login;

    public void logInUser() throws IOException {
        email.sendKeys(Helpers.getProperty("user"));
        password.sendKeys(Helpers.getProperty("password"));
        logInButton.click();
        waitForPageLoad();
    }

    public void fillOneTimeCode(String code){
        List<Character> codeDigits = code.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        for (int i = 0; i < codeDigits.size(); i++) {
            oneTimePassword.get(i).sendKeys(codeDigits.get(i).toString());
        }

        if(!activeSessionAsList.isEmpty())
            yesButton.click();
        waitForPageLoad();
    }
}