package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RadioPage;

public class RadioSteps {
    RadioPage radioPage = new RadioPage();
    @Given("User open radio page")
    public void userNavigateToRadioPage(){
        radioPage.openRadioPage();
    }

    @When("User click on {string} radio")
    public void userSelectItemInRadio(String radioLabel){
        radioPage.selectItemInRadio(radioLabel);
    }

    @Then("User should see an value of radio contains {string}")
    public void verifyUserShouldSeeValueOfRadio(String radioLabel){
        radioPage.verifyUserCanSelectItemInRadio(radioLabel);
    }
}
