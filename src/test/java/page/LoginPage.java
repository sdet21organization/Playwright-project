package page;

import com.microsoft.playwright.Locator;
import context.TestContext;
import io.qameta.allure.Step;
import utils.ConfigurationReader;

public class LoginPage {

    TestContext context;

    public Locator usernameInput;
    public Locator passwordInput;
    public Locator loginButton;

    public LoginPage(TestContext context){
        this.context = context;
        this.usernameInput = context.page.getByTestId("username");
        this.passwordInput = context.page.getByTestId("password");
        this.loginButton = context.page.getByTestId("login-button");
    }

    @Step("Вход в систему как стандартный пользователь")
    public LoginPage loginAsStandardUser(){
        return loginAs(ConfigurationReader.get("standard_login"),ConfigurationReader.get("password"));
    }

    private LoginPage loginAs(String login, String password){
       usernameInput.fill(login);
       passwordInput.fill(password);
       loginButton.click();
       return this;
    }
}