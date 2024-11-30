package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PopConfirmTest extends MasterTest {
    @ParameterizedTest
    @ValueSource(strings = { "No", "Yes" })
    void verifyPopConfirm(String option) throws InterruptedException {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/pop-confirm");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String deleteButtonXpath = "(//div[normalize-space()='Pop confirm']//following::button[.//text()[normalize-space()='Delete']])[1]";
        Locator deleteButtonLocator = page.locator(deleteButtonXpath);
        deleteButtonLocator.click();
        String optionButtonXpath = String.format("//div[@role='tooltip' and .//div[normalize-space(text())='Delete the task']]//button[.//text()[normalize-space()='%s']]", option);
        Locator optionButtonLocator = page.locator(optionButtonXpath);
        optionButtonLocator.click();
        String expectedElementXpath = String.format("//div[contains(concat(' ',normalize-space(@class),' '),' ant-message-notice-content ') and .//text()[normalize-space()='Click on %s']]", option);
        Locator expectedElementLocator = page.locator(expectedElementXpath);
        assertThat(expectedElementLocator).hasText(String.format("Click on %s", option));
    }
}
