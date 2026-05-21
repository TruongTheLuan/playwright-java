package pages;

import com.microsoft.playwright.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static steps.Hooks.page;

public class DropdownPage {
    public void openDropdownPage() {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/dropdown");
    }

    public void selectOptionInDropdown(String dropdownItem){
        Locator dropdownLocator = page.locator("//button[contains(concat(' ',normalize-space(@class),' '),' ant-dropdown-trigger ')]");
        String dropdownItemXpath = String.format("//ul[@role='menu']/li[contains(concat(' ',normalize-space(@class),' '),' ant-dropdown-menu-item ')]/span[normalize-space(.//text())='%s']", dropdownItem);
        Locator dropdownItemLocator = page.locator(dropdownItemXpath);
        dropdownLocator.hover();
        dropdownItemLocator.click();
    }


    public void verifyUserCanSelectOptionInDropdown(String dropdownItem){
        Locator valueLocator = page.locator("//button[contains(concat(' ',normalize-space(@class),' '),' ant-dropdown-trigger ')]//following::div[contains(.,'Value:')]");
        assertThat(valueLocator).hasText(String.format("Value: %s", dropdownItem));
    }
}
