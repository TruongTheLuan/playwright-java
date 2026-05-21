package pages;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static steps.Hooks.page;

public class RadioPage {
    public void openRadioPage() {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/radio");
    }

    public void selectItemInRadio(String radioValue) {
        String radioXpath = String.format("//input[@value='%s' and contains(concat(' ',normalize-space(@class),' '),' ant-radio-input ')]", radioValue);
        Locator radioLocator = page.locator(radioXpath);
        radioLocator.check();
    }

    public void verifyUserCanSelectItemInRadio(String radioValue) {
        Locator valueLocator = page.locator("//input[@value='Apple' and contains(concat(' ',normalize-space(@class),' '),' ant-radio-input ')]//following::div[contains(.,'Value:')][1]");
        assertThat(valueLocator).hasText(String.format("Value: %s", radioValue));
    }
}