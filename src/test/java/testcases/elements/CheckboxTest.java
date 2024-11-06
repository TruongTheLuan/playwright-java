package testcases.elements;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckboxTest extends MasterTest{
    void selectCheckboxByLabel(String checkboxLabel, boolean checked){
        String checkboxGeneric = "//label[.//text()[normalize-space()='%s']]";
        String checkboxXpath = String.format(checkboxGeneric, checkboxLabel);
        Locator checkbox = page.locator(checkboxXpath);
        if(checked) {
            if(!checkbox.isChecked()){
                checkbox.click();
            }
        }else{
            if(checkbox.isChecked()){
                checkbox.click();
            }
        }
    }

    @Test
    void verifyThatUserCanSelectAllCheckbox() {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/checkbox");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        Locator pageHeader = page.locator("//div[@role[normalize-space()='separator']]//span[text()[normalize-space()='Checkbox']]");
        assertThat(pageHeader).hasText("Checkbox");
        selectCheckboxByLabel("Apple", true);
        selectCheckboxByLabel("Pear", true);
        selectCheckboxByLabel("Orange", true);
        String expectedElementXpath = "//div[contains(.//text(), 'Selected values:')]";
        Locator expectedElement = page.locator(expectedElementXpath);
        assertThat(expectedElement).hasText("Selected values: Apple Pear Orange");
    }
}
