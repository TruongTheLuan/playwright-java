package pages;

import com.microsoft.playwright.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static steps.Hooks.page;

public class AutoCompletePage {
    public void openAutoCompletePage(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/auto-complete");
    }

    public void selectItemInAutoComplete(String inputValue){
        String autocompleteInputXpath = "//div[@role='separator' and normalize-space(.//text())='Auto complete']//following::input[@type='text']";
        Locator autocompleteInputLocator = page.locator(autocompleteInputXpath);
        autocompleteInputLocator.click();
        autocompleteInputLocator.fill(inputValue);
        String selectItemXpath = String.format("//div[contains(concat(' ', normalize-space(@class),' '),' ant-select-item-option-content ') and text()[normalize-space()='%s']]", inputValue);
        page.locator(selectItemXpath).click();
    }

    public void verifyValueOfAutoCompleteSelectAppears(String inputValue){
        String expectedElementXpath = "//div[contains(., 'Value: ') and contains(., ' was selected!') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        assertThat(page.locator(expectedElementXpath)).hasText(String.format("Value: %s was selected!", inputValue));
    }
}
