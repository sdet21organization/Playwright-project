package tests;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Route;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NetworkTests extends TestBase {

    @Test
    public void logNetworkTest() {
        String testData = "{key: 'value'}";

        context.page.onRequest(request -> System.out.println(">> " + request.method() + " " + request.url() + " " + request.postData()));
        context.page.onResponse(response -> System.out.println("<<" + response.status() + " " + response.url() + " " + new String(response.body(), StandardCharsets.UTF_8)));

        context.page.route("**", route -> route.fulfill(new Route.FulfillOptions()
                .setStatus(200)
                .setBody(testData)));


        context.page.navigate("https://example.com");
    }

    @Test
    public void abortionTest() {
        context.page.route("**/*.{png,jpg,jpeg}", route -> route.abort());
        context.page.navigate("https://en.wikipedia.org/wiki/Main_Page");
    }


    @Test
    public void modify1Test() {
        context.page.route("**/Main_Page", route -> {
            // Fetch original response.
            APIResponse response = route.fetch();
            // Add a prefix to the title.
            String body = response.text();
            body = body.replace("<title>", "<title>My prefix:");
            Map<String, String> headers = response.headers();
            headers.put("content-type", "text/html");
            route.fulfill(new Route.FulfillOptions()
                    // Pass all fields from the response.
                    .setResponse(response)
                    // Override response body.
                    .setBody(body)
                    // Force content type to be html.
                    .setHeaders(headers));
        });
        context.page.navigate("https://en.wikipedia.org/wiki/Main_Page");
    }

    @Test
    public void mockApiTest() {
        // Mock the API call before navigating
        context.page.route("*/**/api/v1/fruits", route -> {
            String json = "[{\"name\": \"Strawberry\", \"id\": 21}]";
            route.fulfill(new Route.FulfillOptions().setContentType("application/json").setBody(json));
        });

        // Go to the page
        context.page.navigate("https://demo.playwright.dev/api-mocking");

        // Assert that the Strawberry fruit is visible
        assertThat(context.page.locator("text=Strawberry")).isVisible();
    }

    @Test
    public void getsJsonFromApiAndAddsANewFruit() {
        // Get the response and add to it
        context.page.route("*/**/api/v1/fruits", route -> {

            APIResponse response = route.fetch();
            String json = response.text().toString();
            json = json.substring(0, json.length() - 2) + ",{\"name\": \"Playwright\", \"id\": 100}]"; // Add new fruit
            route.fulfill(new Route.FulfillOptions().setResponse(response).setBody(json));
        });

        // Go to the page
        context.page.navigate("https://demo.playwright.dev/api-mocking");

    }
}
