package tests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.core.Amount;
import ui.core.BaseTest;
import ui.core.PBX;
import ui.pom.EmailVendorPage;

import java.io.IOException;

public class DepositUpdateTest extends BaseTest {

    Amount amount = Amount.$100;

    @Test
    public void test() throws InterruptedException, IOException {

        PBX pbx = PBX.init(driver);
        pbx.loginPage.logInUser();
        pbx.loginPage.fillOneTimeCode(new EmailVendorPage(driver).getOneTimeCode());
        pbx.dashboard.openProfileCard();
        pbx.dashboard.openCompany();
        pbx.companyPage.openBillingTab();
        double initialBalance = pbx.companyPage.getBalance();
        pbx.companyPage.addBalance(amount);
        pbx.companyPage.waitSuccessMessage(driver);
        Assert.assertEquals(pbx.companyPage.getBalance(),
                initialBalance + Amount.getAmountAsDouble(amount));
    }
}
