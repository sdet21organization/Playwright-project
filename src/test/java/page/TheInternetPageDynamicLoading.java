package page;

import com.microsoft.playwright.Locator;
import context.TestContext;

public class TheInternetPageDynamicLoading {
    TestContext context;
    public Locator startButton;
    public Locator finishText;

      public TheInternetPageDynamicLoading(TestContext context){
        this.context = context;
        this.startButton = context.page.locator("#start > button");
        this.finishText = context.page.locator("#finish > h4");
    }
}
