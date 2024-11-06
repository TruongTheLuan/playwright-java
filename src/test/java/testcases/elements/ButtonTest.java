package testcases.elements;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ButtonTest extends MasterTest{
    void clickButton(String buttonLabel){
        String normalButtonXpath = String.format("//button[normalize-space(.//text())='%s']", buttonLabel);
        String tagButtonXpath = String.format("//*[@role='button' and normalize-space(.//text())='%s']", buttonLabel);
        String inputButtonXpath = String.format("//input[@type='button' and @value='%s']", buttonLabel);
        String buttonXpath = String.format("%s | %s | %s", normalButtonXpath, tagButtonXpath, inputButtonXpath);
        Locator buttonLocator = page.locator(buttonXpath);
        buttonLocator.click();
    }

    @ParameterizedTest
    @ValueSource(strings = { "Div button", "Origin button", "Input button", "Default", "Primary", "Dashed", "Text", "Link", "Icon button" })
    void verifyClickAllButtons(String buttonLabel){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/button");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        clickButton(buttonLabel);
        String expectedElementXpath = "//div[contains(., 'Button') and contains(., 'was clicked!') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        Locator expectedLocator = page.locator(expectedElementXpath);
        assertThat(expectedLocator).hasText(String.format("Button %s was clicked!", buttonLabel));
    }
}
