package tests;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import page.MainPage;
import utils.ConfigurationReader;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends TestBase {

    @Test
    public void successLoginTest(){
        context.page.navigate(ConfigurationReader.get("base_url"));
        new LoginPage(context).loginAsStandardUser();
        Locator footer = new MainPage(context).footer;
        assertThat(footer).containsText("Sauce Labs");
    }
}
