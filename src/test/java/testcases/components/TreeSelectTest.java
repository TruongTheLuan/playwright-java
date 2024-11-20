package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TreeSelectTest extends MasterTest {
    /*@ParameterizedTest
    @ValueSource(strings = { "Light", "Bamboo", "Cedar", "Pine", "Oak" })*/

    @ParameterizedTest
    @CsvSource({
            "Light,         Light",
            "Light,         Bamboo",
            "Light,         Cedar",
            "Light,         Pine",
            "Light,        Oak",
            "Heavy,        Heavy",
            "Heavy,        Mahogany",
            "Heavy,        Walnut"
    })
    void verifyTreeSelect(String treeSwitcher, String input){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/tree-select");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String inputXpath = "//div[contains(concat(' ',normalize-space(@class),' '),' ant-select-selector ')]";
        Locator inputLocator = page.locator(inputXpath);
        inputLocator.click();

        String treeSwitcherXpath= String.format("(//span[normalize-space(text())='%s']//preceding::span[contains(concat(' ',normalize-space(@class),' '),' ant-select-tree-switcher ')])[last()]", treeSwitcher);
        Locator treeSwitcherLocator = page.locator(treeSwitcherXpath);
        treeSwitcherLocator.click();

        String inputItemXpath = String.format("//span[normalize-space(text())='%s']", input);
        Locator inputItemLocator = page.locator(inputItemXpath);
        inputItemLocator.click();

        String expectedElementXpath = "//div[contains(., 'Current value: ') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        assertThat(page.locator(expectedElementXpath)).hasText(String.format("Current value: %s", input.toLowerCase()));
    }
}
