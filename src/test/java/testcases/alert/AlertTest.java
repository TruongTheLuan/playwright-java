package testcases.alert;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AlertTest extends MasterTest {
    void clickButtonByLabel(String label){
        String buttonXpath = String.format("//button[normalize-space(.//text())='%s']",label);
        Locator buttonLocator = page.locator(buttonXpath);
        buttonLocator.click();
    }

    @Test
    void verifySimpleAlert(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/alerts");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        page.onDialog(Dialog::accept);
        clickButtonByLabel("Show Alert");
    }

    @Test
    void verifyConfirmAlert(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/alerts");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        page.onDialog(Dialog::dismiss);
        clickButtonByLabel("Show Confirm");
        String expectedLabelXpath = "//div[./text()[normalize-space()='Selected value:']]";
        assertThat(page.locator(expectedLabelXpath)).hasText("Selected value: Cancel");
    }

    @Test
    void verifyPromptAlert(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/alerts");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        page.onDialog(dialog -> dialog.accept("ABD"));
        clickButtonByLabel("Show Prompt");
        String expectedLabelXpath = "//div[./text()[normalize-space()='Entered value:']]";
        assertThat(page.locator(expectedLabelXpath)).hasText("Entered value: ABD");
    }
}
