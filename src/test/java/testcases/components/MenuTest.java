package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MenuTest extends MasterTest {
    @ParameterizedTest
    @CsvSource({
            "'Option 1',         '1'",
            "'Option 2',         '2'",
            "'Option 3',         '3'",
            "'Option 4',        '4'"
    })
    void verifyMenu(String input, String expectedValue){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/menu");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String menuInputXpath = "//div[@role='menuitem' and .//text()='My Menu']";
        Locator menuInputLocator = page.locator(menuInputXpath);
        String itemXpath = String.format("//li[@role='menuitem' and .//text()[normalize-space()='%s']]", input);
        Locator itemLocator = page.locator(itemXpath);
        String expectedElementXpath = "//div[contains(., 'Current value: ') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        menuInputLocator.hover();
        itemLocator.click();
        assertThat(page.locator(expectedElementXpath)).hasText(String.format("Current value: setting:%s", expectedValue));
    }
}
