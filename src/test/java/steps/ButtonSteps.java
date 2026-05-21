package steps;

import com.microsoft.playwright.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ButtonPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static steps.Hooks.page;

public class ButtonSteps {
    ButtonPage buttonPage = new ButtonPage();
    @Given("User open button page")
    public void userNavigateToButtonPage() {
        buttonPage.openButtonPage();
    }


    @When("User click on {string} button")
    public void userClickOnWithLabelIsLabel(String buttonLabel){
        buttonPage.clickOnButtonByLabel(buttonLabel);
    }

    @Then("User should see an expected message contains {string}")
    public void userShouldSeeAMessageIs(String buttonLabel) {
        buttonPage.verifyExpectedMessageAppears(buttonLabel);
    }
}
