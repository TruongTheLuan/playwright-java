package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AutoCompleteTest extends MasterTest {
    @Test
    void verifyAutoCompleteSelectDowningStreet(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/auto-complete");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String inputValue = "Downing Street";
        selectItemInAutoComplete(inputValue);
        String expectedElementXpath = "//div[contains(., 'Value: ') and contains(., ' was selected!') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        assertThat(page.locator(expectedElementXpath)).hasText(String.format("Value: %s was selected!", inputValue));
    }

    @Test
    void verifyAutoCompleteSelectBurnsBayRoad(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/auto-complete");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String inputValue = "Burns Bay Road";
        selectItemInAutoComplete(inputValue);
        String expectedElementXpath = "//div[contains(., 'Value: ') and contains(., ' was selected!') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        assertThat(page.locator(expectedElementXpath)).hasText(String.format("Value: %s was selected!", inputValue));
    }

    private void selectItemInAutoComplete(String inputValue) {
        String autocompleteInputXpath = "//div[@role='separator' and normalize-space(.//text())='Auto complete']//following::input[@type='search'][1]";
        Locator autocompleteInputLocator = page.locator(autocompleteInputXpath);
        autocompleteInputLocator.click();
        autocompleteInputLocator.fill(inputValue);
        String selectItemXpath = String.format("//div[contains(concat(' ', normalize-space(@class),' '),' ant-select-item-option-content ') and text()[normalize-space()='%s']]", inputValue);
        page.locator(selectItemXpath).click();
    }
}
