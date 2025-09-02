package page;

import com.microsoft.playwright.Locator;
import context.TestContext;
import io.qameta.allure.Step;

public class MainPage {

    TestContext context;

    public Locator footer;
    public Locator addToCartButton;
    public Locator cartCounter;

    public MainPage(TestContext context){
        this.context = context;
        this.addToCartButton = context.page.locator(".btn_inventory");
        this.cartCounter = context.page.getByTestId("shopping-cart-badge");
        this.footer = context.page.getByTestId("footer-copy");
    }
    @Step("Добавить все товары в корзину")
    public MainPage clickAllItems(){
        addToCartButton.all().forEach(Locator::click);
        return this;
    }

}
