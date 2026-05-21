package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AutoCompletePage;

public class AutoCompleteSteps {
    AutoCompletePage autoCompletePage = new AutoCompletePage();
    @Given("User open autocomplete page")
    public void userNavigateToButtonPage() {
        autoCompletePage.openAutoCompletePage();
    }

    @When("user select {string} in auto complete")
    public void userSelectInAutoComplete(String itemLabel){
        autoCompletePage.selectItemInAutoComplete(itemLabel);
    }

    @Then("User should see an value of auto complete contains {string}")
    public void userShouldSeeAnValueOfAutoCompleteContains(String itemLabel) {
        autoCompletePage.verifyValueOfAutoCompleteSelectAppears(itemLabel);
    }
}
