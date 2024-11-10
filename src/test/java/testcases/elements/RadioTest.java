package testcases.elements;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RadioTest extends MasterTest{
    void selectItemInRadio(String radioValue){
        String radioXpath = String.format("//input[@value='%s' and contains(concat(' ',normalize-space(@class),' '),' ant-radio-input ')]", radioValue);
        Locator radioLocator = page.locator(radioXpath);
        radioLocator.check();
        assertTrue(radioLocator.isChecked());
    }
    @ParameterizedTest
    @ValueSource(strings = { "Apple", "Pear", "Orange" })
    void verifyUserCanSelectItemInRadio(String radioValue) {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/radio");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        Locator valueLocator = page.locator("//input[@value='Apple' and contains(concat(' ',normalize-space(@class),' '),' ant-radio-input ')]//following::div[contains(.,'Value:')][1]");
        selectItemInRadio(radioValue);
        assertThat(valueLocator).hasText(String.format("Value: %s", radioValue));
    }
}
