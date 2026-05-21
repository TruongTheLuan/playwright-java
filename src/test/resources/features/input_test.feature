Feature: Elements - Input
  Background:
    Given User open input page

  Scenario Outline: Verify that user can input data In Normal Input And Text Area fields
    When User input data in "<inputField>"
    Then User should see an expected value contains "<inputField>"
    Examples:
      | inputField |
      | Hello! |
      | Test with me! |
      | Input password |

  Scenario: Verify that user can input data in Input Number field
    When User input data in Input Number field
    Then User should see an expected value
    And User input data by click increase and decrease buttons

  Scenario: Verify that user can input data in OTP Box
    When User input data in OTP Box
    Then User should see an expected OTP value




