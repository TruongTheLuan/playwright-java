package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SwitchTest extends MasterTest {
    @Test
    void verifySwitch(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/switch");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String switchXpath = "//div[@role='separator' and .//text()='Switch']//following::button[contains(concat(' ',normalize-space(@class),' '),' ant-switch ')]";
        Locator switchLocator = page.locator(switchXpath);
        boolean input = true;
        if(Boolean.valueOf(switchLocator.getAttribute("aria-checked")) != input){
            switchLocator.click();
        }
        String expectedElementXpath = "//div[contains(., 'Current Value: ') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        assertThat(page.locator(expectedElementXpath)).hasText(String.format("Current Value: %s", input));
    }
}
