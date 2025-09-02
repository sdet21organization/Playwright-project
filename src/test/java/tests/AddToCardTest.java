package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import page.MainPage;
import utils.ConfigurationReader;

public class AddToCardTest extends TestBase {
    @Test
    public void AddAllItemsTest(){
        context.page.navigate(ConfigurationReader.get("base_url"));
        new LoginPage(context).loginAsStandardUser();
        String count = new MainPage(context).clickAllItems().cartCounter.textContent();
        Assertions.assertEquals(6, Integer.parseInt(count));
    }
}
