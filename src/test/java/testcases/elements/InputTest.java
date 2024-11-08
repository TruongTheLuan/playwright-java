package testcases.elements;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InputTest extends MasterTest{
    @ParameterizedTest
    @ValueSource(strings = { "Hello!", "Test with me!" , "Input password"})
    void verifyUserCanInputDataInNormalInputAndTextArea(String inputPlaceholder){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/input");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String normalInputXpath = String.format("//input[@placeholder='%s']", inputPlaceholder);
        String textAreaInputXpath = String.format("//textarea[@placeholder='%s']", inputPlaceholder);
        String passwordInputXpath = String.format("//input[@placeholder='%s']", inputPlaceholder);
        String valueNormalInputXpath = String.format("//input[@placeholder='%s']//following-sibling::div[contains(.,'Value:')][1]", inputPlaceholder);
        String valueTextAreaInputXpath = String.format("//textarea[@placeholder='%s']//following-sibling::div[contains(.,'Value:')][1]", inputPlaceholder);
        String valuePasswordXpath = String.format("//input[@placeholder='%s']//following::div[contains(.,'Value:')][1]", inputPlaceholder);
        String inputXpath = String.format("%s | %s | %s", normalInputXpath, textAreaInputXpath, passwordInputXpath);
        String valueXpath = String.format("%s | %s | %s", valueNormalInputXpath, valueTextAreaInputXpath,valuePasswordXpath);
        Locator inputLocator = page.locator(inputXpath);
        Locator valueLocator = page.locator(valueXpath);
        inputLocator.fill(inputPlaceholder);
        assertThat(valueLocator).hasText(String.format("Value: %s", inputPlaceholder));
    }

}
