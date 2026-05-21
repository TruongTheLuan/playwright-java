package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static steps.Hooks.page;

public class ButtonPage {
    public void openButtonPage(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/button");
    }

    public void clickOnButtonByLabel(String buttonLabel) {
        String normalButtonXpath = String.format("//button[normalize-space(.//text())='%s']", buttonLabel);
        String tagButtonXpath = String.format("//*[@role='button' and normalize-space(.//text())='%s']", buttonLabel);
        String inputButtonXpath = String.format("//input[@type='button' and @value='%s']", buttonLabel);
        String buttonXpath = String.format("%s | %s | %s", normalButtonXpath, tagButtonXpath, inputButtonXpath);
        Locator buttonLocator = page.locator(buttonXpath);
        buttonLocator.click();
    }

    public void verifyExpectedMessageAppears(String buttonLabel) {
        String expectedElementXpath = "//div[contains(., 'Button') and contains(., 'was clicked!') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        Locator expectedLocator = page.locator(expectedElementXpath);
        assertThat(expectedLocator).hasText(String.format("Button %s was clicked!", buttonLabel));
    }
}
