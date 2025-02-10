package ui.core;

import org.openqa.selenium.WebDriver;
import ui.pom.*;

public class PBX {
    private static PBX pbx;
    public Dashboard dashboard;
    public LoginPage loginPage;
    public CompanyPage companyPage;

    private PBX(WebDriver driver) {
        dashboard = new Dashboard(driver);
        loginPage = new LoginPage(driver);
        companyPage = new CompanyPage(driver);
    }

    public static PBX init(WebDriver driver) {
        if (pbx == null) {
            pbx = new PBX(driver);
        }
        return pbx;
    }
}