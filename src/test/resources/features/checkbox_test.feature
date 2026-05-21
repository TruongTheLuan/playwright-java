Feature: Elements - Checkbox
  Background:
    Given User open checkbox page

  Scenario: User can click all checkboxes
    When user click on all checkboxes
    Then user should see selected value
