package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ModalTest extends MasterTest {
    @ParameterizedTest
    @CsvSource({
            "Yes,         OK",
            "No,         CANCEL"
    })
    void verifyModal(String option, String expected){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/modal");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String buttonShowConfirmXpath = "//button[.//text()[normalize-space()='Show Confirm']]";
        Locator buttonShowConfirmLocator = page.locator(buttonShowConfirmXpath);
        buttonShowConfirmLocator.click();
        String optionXpath = String.format("//button[.//text()[normalize-space()='%s']]", option);
        Locator optionLocator = page.locator(optionXpath);
        optionLocator.click();
        String expectedElementXpath = "//div[contains(., 'Status: ') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        Locator expectedElementLocator = page.locator(expectedElementXpath);
        assertThat(expectedElementLocator).hasText(String.format("Status: %s", expected));
    }
}
