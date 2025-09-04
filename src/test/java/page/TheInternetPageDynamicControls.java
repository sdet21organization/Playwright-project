package page;

import com.microsoft.playwright.Locator;
import context.TestContext;

public class TheInternetPageDynamicControls {
    TestContext context;
    public Locator enableButton;
    public Locator input;
    public Locator message;

      public TheInternetPageDynamicControls(TestContext context){
        this.context = context;
        this.enableButton = context.page.locator("//button[text()='Enable']");
        this.input = context.page.locator("#input-example > input[type=text]");
        this.message = context.page.locator("#message");
    }
}
