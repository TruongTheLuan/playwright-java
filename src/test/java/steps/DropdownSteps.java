package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DropdownPage;

public class DropdownSteps {
    DropdownPage dropdownPage = new DropdownPage();
    @Given("User open dropdown page")
    public void userNavigateToDropdownPage(){
        dropdownPage.openDropdownPage();
    }

    @When("User select {string} in dropdown")
    public void userSelectInDropdown(String dropdownItem){
        dropdownPage.selectOptionInDropdown(dropdownItem);
    }

    @Then("User should see an value of dropdown contains {string}")
    public void userShouldSeeAnValueOfDropdownContains(String dropdownItem) {
        dropdownPage.verifyUserCanSelectOptionInDropdown(dropdownItem);
    }
}
