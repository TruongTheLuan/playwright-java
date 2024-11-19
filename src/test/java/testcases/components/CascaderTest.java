package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import testcases.MasterTest;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CascaderTest extends MasterTest {
    @ParameterizedTest
    @ValueSource(strings = { "Tho, Test, API", "Tho, Test, UI", "Test, With, Me", "Test, With, You" })
    void verifyCascader(String input){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/cascader");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String cascaderInputXpath = "//div[@role='separator' and normalize-space(.//text())='Cascader']//following::input[contains(concat(' ',normalize-space(@class),' '),' ant-select-selection-search-input ')][1]";
        Locator cascaderInputLocator = page.locator(cascaderInputXpath);
        cascaderInputLocator.click();
        String expectedXpath = "//div[contains(., 'Current value: ') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        Locator expectedLocator = page.locator(expectedXpath);
        for (String itemString : input.split(",")){
            String cascaderItemXpath = String.format("(//li[@role='menuitemcheckbox' and .//text()[normalize-space()='%s']])[last()]", itemString.trim());
            Locator cascaderItemLocator = page.locator(cascaderItemXpath);
            cascaderItemLocator.click();
        }
        assertThat(expectedLocator).hasText(String.format("Current value: %s", input));
    }

    @Test
    void verifyMultipleValueCascader(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/cascader");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String[] inputs = {"Light > Number 1", "Bamboo > Little > Toy Cards"};
        String cascaderInputXpath = "//div[@role='separator' and normalize-space(.//text())='Cascader multiple values']//following::input[contains(concat(' ',normalize-space(@class),' '),' ant-select-selection-search-input ')][1]";
        Locator cascaderInputLocator = page.locator(cascaderInputXpath);
        cascaderInputLocator.click();
        for(String input : inputs){
            String[] itemsString = input.split(">");
            for(int i = 0; i < itemsString.length; i++){
                String cascaderItemXpath = String.format("(//li[@role='menuitemcheckbox' and .//text()[normalize-space()='%s']])[last()]", itemsString[i].trim());
                Locator cascaderItemLocator = page.locator(cascaderItemXpath);
                String itemCheckboxLocatorpath = String.format("(%s)//span[contains(concat(' ',normalize-space(@class),' '),' ant-cascader-checkbox ')]", cascaderItemXpath);
                Locator itemCheckbox = page.locator(itemCheckboxLocatorpath);
                if(i < itemsString.length-1){
                    cascaderItemLocator.click();
                }
                else{
                    itemCheckbox.click();
                }
            }
        }
        String expectedXpath = "//div[contains(., 'Current value: ') and ./div[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        Locator expectedLocator = page.locator(expectedXpath);
        assertThat(expectedLocator).hasText(String.format(" Current value: light, 1bamboo, little, cards"));
    }
}
