package pages;

import com.microsoft.playwright.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static steps.Hooks.page;

public class InputPage {
    public int dataNumber = 10;
    public int otpNumber = 567876;
    public void openInputPage(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/input");
    }

    public void userInputDataInTextField(String inputPlaceholder){
        String normalInputXpath = String.format("//input[@placeholder='%s']", inputPlaceholder);
        String textAreaInputXpath = String.format("//textarea[@placeholder='%s']", inputPlaceholder);
        String passwordInputXpath = String.format("//input[@placeholder='%s']", inputPlaceholder);
        String inputXpath = String.format("%s | %s | %s", normalInputXpath, textAreaInputXpath, passwordInputXpath);
        Locator inputLocator = page.locator(inputXpath);
        inputLocator.fill(inputPlaceholder);
    }

    public void userInputDataInInputNumber(){
        Locator inputLocator = page.locator("//input[@role='spinbutton']");
        inputLocator.fill(Integer.toString(dataNumber));
    }

    public void userInputDataInOTPBox(){
        Locator inputLocator = page.locator("//input[@aria-label[normalize-space()='OTP Input 1']]");
        inputLocator.fill(String.valueOf(otpNumber));
    }

    public void verifyExpectedValueAppears(String inputPlaceholder){
        String valueNormalInputXpath = String.format("//input[@placeholder='%s']//following-sibling::div[contains(.,'Value:')][1]", inputPlaceholder);
        String valueTextAreaInputXpath = String.format("//textarea[@placeholder='%s']//following-sibling::div[contains(.,'Value:')][1]", inputPlaceholder);
        String valuePasswordXpath = String.format("//input[@placeholder='%s']//following::div[contains(.,'Value:')][1]", inputPlaceholder);
        String valueXpath = String.format("%s | %s | %s", valueNormalInputXpath, valueTextAreaInputXpath,valuePasswordXpath);
        Locator valueLocator = page.locator(valueXpath);
        assertThat(valueLocator).hasText(String.format("Value: %s", inputPlaceholder));
    }

    public void verifyExpectedValueOfInputNumberAppears(){
        Locator valueLocator = page.locator("//input[@role='spinbutton']//following::div[contains(.,'Value:')][1]");
        assertThat(valueLocator).hasText(String.format("Value: %s", dataNumber));
    }

    public void verifyUserInputDataByDecreaseAndIncreaseButtons(){
        Locator inputLocator = page.locator("//input[@role='spinbutton']");
        Locator increaseLocator = page.locator("//span[@role='button' and @aria-label='Increase Value']");
        Locator decreaseLocator = page.locator("//span[@role='button' and @aria-label='Decrease Value']");
        Locator valueLocator = page.locator("//input[@role='spinbutton']//following::div[contains(.,'Value:')][1]");

        inputLocator.hover();
        increaseLocator.click();
        assertThat(valueLocator).hasText(String.format("Value: %s", dataNumber + 5));

        inputLocator.hover();
        decreaseLocator.click();
        assertThat(valueLocator).hasText(String.format("Value: %s", dataNumber));
    }

    public void verifyExpectedOTPValueAppears(){
        Locator valueLocator = page.locator("//div[contains(concat(' ',normalize-space(@class),' '),' ant-otp ')]//following-sibling::div[contains(.,'Value:')][1]");
        assertThat(valueLocator).hasText(String.format("Value: %s", otpNumber));
    }
}
