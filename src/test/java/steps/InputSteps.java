package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.InputPage;
import testcases.elements.InputTest;

public class InputSteps {
    InputPage inputPage = new InputPage();

    @Given("User open input page")
    public void userOpenInputPage(){
        inputPage.openInputPage();
    }

    @When("User input data in {string}")
    public void userInputDataInTextField(String inputPlaceholder){
        inputPage.userInputDataInTextField(inputPlaceholder);
    }

    @Then("User should see an expected value contains {string}")
    public void userShouldSeeExpectedValue(String inputPlaceholder){
        inputPage.verifyExpectedValueAppears(inputPlaceholder);
    }

    @When("User input data in Input Number field")
    public void userInputDataInInputNumberField(){
        inputPage.userInputDataInInputNumber();
    }

    @Then("User should see an expected value")
    public void userShouldSeeAnExpectedValue(){
        inputPage.verifyExpectedValueOfInputNumberAppears();
    }

    @And("User input data by click increase and decrease buttons")
    public void userInputDataByClickIncreaseAndDecreaseButtons(){
       inputPage.verifyUserInputDataByDecreaseAndIncreaseButtons();
    }

    @When("User input data in OTP Box")
    public void userInputDataInOTPBox(){
        inputPage.userInputDataInOTPBox();
    }

    @Then("User should see an expected OTP value")
    public void userShouldSeeAnExpectedOTPValue() {
        inputPage.verifyExpectedOTPValueAppears();
    }
}
