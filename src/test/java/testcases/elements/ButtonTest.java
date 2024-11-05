package testcases.elements;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ButtonTest {
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void openButtonPage() {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/button");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        Locator pageHeader = page.locator("//div[@role[normalize-space()='separator']]//span[text()[normalize-space()='Common button type']]");
        assertThat(pageHeader).hasText("Common button type");
    }

    @Test
    void shouldClickDivButton(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/button");
        Locator divButton = page.locator("//div[text()[normalize-space()='Div button']]");
        divButton.click();
        Locator buttonResult = page.locator("//div[text()[contains(.,'Button')]]");
        assertThat(buttonResult).hasText("Button Div button was clicked!");
    }

    @Test
    void shouldClickInputButton(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/button");
        Locator inputButton = page.locator("//input[@value[normalize-space()='Input button']]");
        inputButton.click();
        Locator buttonResult = page.locator("//div[text()[contains(.,'Button')]]");
        assertThat(buttonResult).hasText("Button Input button was clicked!");
    }
}
