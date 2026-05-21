package pages;

import com.microsoft.playwright.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static steps.Hooks.page;

public class CheckboxPage {
    public void openCheckboxPage(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/checkbox");
    }

    public void selectCheckboxByLabel(){
        String checkBoxByLabel = "Apple, Pear, Orange";
        for (String itemString : checkBoxByLabel.split(",")){
            String checkboxXpath = String.format("//label[.//text()[normalize-space()='%s']]", itemString.trim());
            Locator checkbox = page.locator(checkboxXpath);
            if(!checkbox.isChecked()) {
                checkbox.click();
            }
        }
    }

    public void verifyThatUserCanSeeSelectedValue() {
        String expectedElementXpath = "//div[contains(.//text(), 'Selected values:')]";
        Locator expectedElement = page.locator(expectedElementXpath);
        assertThat(expectedElement).hasText("Selected values: Apple Pear Orange");
    }
}
