package testcases.window;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WindowTest extends MasterTest {
    void clickButtonByLabel(String label){
        String buttonXpath = String.format("//button[normalize-space(.//text())='%s']",label);
        Locator buttonLocator = page.locator(buttonXpath);
        buttonLocator.click();
    }
    @Test
    void verifyOpenNewTab() throws InterruptedException {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/windows");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        Page newPage = context.waitForPage(() ->{
            clickButtonByLabel("Open New Tab");
        });
        Thread.sleep(3000);
        assertThat(newPage.getByText("Welcome to Test With Me")).isVisible();
    }
}
