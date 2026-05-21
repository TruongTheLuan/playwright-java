package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckboxPage;

import static steps.Hooks.page;

public class CheckboxSteps {
    CheckboxPage checkboxPage = new CheckboxPage();
    @Given("User open checkbox page")
    public void userNavigateToCheckboxPage() {
        checkboxPage.openCheckboxPage();
    }

    @When("user click on all checkboxes")
    public void userClickOnAllCheckboxes() {
        checkboxPage.selectCheckboxByLabel();
    }

    @Then("user should see selected value")
    public void verifyUserCanSeeSelectedValue(){
        checkboxPage.verifyThatUserCanSeeSelectedValue();
    }
}
