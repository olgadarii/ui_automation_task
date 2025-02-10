package ui.pom;

import ui.controls.WebButton;
import ui.core.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static ui.core.BaseTest.waitForPageLoad;

public class Dashboard extends AbstractPOM {
    public Dashboard(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class,'profile-card') and @type='button']")
    private WebButton profileCard;

    @FindBy(xpath = "//a[@data-sidebar-button='/company']")
    private WebButton myCompany;

    public void openProfileCard(){
        profileCard.click();
        waitForPageLoad();
    }

    public void openCompany(){
        myCompany.click();
        waitForPageLoad();
    }
}