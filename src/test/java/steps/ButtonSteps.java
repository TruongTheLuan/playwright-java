package steps;

import com.microsoft.playwright.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ButtonPage;

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
    public void userShouldSeeAMessageContainsLabel(String buttonLabel) {
        buttonPage.verifyExpectedMessageAppears(buttonLabel);
    }
}
