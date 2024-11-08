package testcases.elements;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DropdownTest extends MasterTest{
    void selectOptionInDropdown(String dropdownItem){
        Locator dropdownLocator = page.locator("//button[contains(concat(' ',normalize-space(@class),' '),' ant-dropdown-trigger ')]");
        String dropdownItemXpath = String.format("//ul[@role='menu']/li[contains(concat(' ',normalize-space(@class),' '),' ant-dropdown-menu-item ')]/span[normalize-space(.//text())='%s']", dropdownItem);
        Locator dropdownItemLocator = page.locator(dropdownItemXpath);
        dropdownLocator.hover();
        dropdownItemLocator.click();
    }

    @ParameterizedTest
    @ValueSource(strings = { "1st menu item", "2nd menu item" , "3rd menu item", "4rd menu item"})
    void verifyUserCanSelectOptionInDropdown(String dropdownItem){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/dropdown");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        selectOptionInDropdown(dropdownItem);
        Locator valueLocator = page.locator("//button[contains(concat(' ',normalize-space(@class),' '),' ant-dropdown-trigger ')]//following::div[contains(.,'Value:')]");
        assertThat(valueLocator).hasText(String.format("Value: %s", dropdownItem));
    }
}
