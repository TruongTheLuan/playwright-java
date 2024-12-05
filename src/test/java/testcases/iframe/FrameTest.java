package testcases.iframe;

import com.microsoft.playwright.FrameLocator;
import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FrameTest extends MasterTest {
    @Test
    void verifyIframe(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/frames");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        FrameLocator iframe = page.frameLocator("//iframe[@title='Example Frame']");
        assertThat(iframe.getByText("Test With Me aka Tho Test")).isVisible();
    }
}
