package tests;

import org.junit.jupiter.api.Test;
import page.TheInternetPageDynamicControls;
import page.TheInternetPageDynamicLoading;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WaitTests extends TestBase{

    /*
   Для сравнения сначала тест на Selenium
   @Test
    void helloWorldTest(){
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.findElement(By.cssSelector("#start > button")).click();
        WebElement text = driver.findElement(By.cssSelector("#finish > h4"));

        wait.until(ExpectedConditions.visibilityOf(text));

        Assert.assertEquals(text.getText(), "Hello World!");
    }
*/

    @Test
    void helloWorldTest(){
        context.page.navigate("https://the-internet.herokuapp.com/dynamic_loading/1");
        TheInternetPageDynamicLoading page = new TheInternetPageDynamicLoading(context);

        page.startButton.click();
        assertThat(page.finishText).containsText("Hello World!");
    }

   /* @Test
    void waitForEnable(){
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(By.xpath("//button[text()='Enable']")).click();

        WebElement input = driver.findElement(By.cssSelector("#input-example > input[type=text]"));

        wait.until(ExpectedConditions.elementToBeClickable(input));

        input.sendKeys("Hello world!");

        WebElement message = driver.findElement(By.cssSelector("#message"));
        Assert.assertEquals(message.getText(), "It's enabled!");
    }*/

    @Test
    void waitForEnable(){
        context.page.navigate("https://the-internet.herokuapp.com/dynamic_controls");
        TheInternetPageDynamicControls page = new TheInternetPageDynamicControls(context);

        page.enableButton.click();
        page.input.fill("Hello world!");

        assertThat(page.message).containsText("It's enabled!");
    }

}
