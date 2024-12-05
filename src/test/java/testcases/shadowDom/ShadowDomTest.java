package testcases.shadowDom;

import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ShadowDomTest extends MasterTest {
    @Test
    void verifyShadowDom(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/shadow-dom");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String inputCss = "#name-input";
        page.locator(inputCss).fill("Test With Me");
        String buttonSubmitCss = "#shadow-btn";
        page.locator(buttonSubmitCss).click();
        String expectedLabelCss = "#name-display";
        assertThat(page.locator(expectedLabelCss)).hasText("Test With Me");
    }
}
