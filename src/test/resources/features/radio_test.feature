Feature: Elements - Radio
  Background:
    Given User open radio page

  Scenario Outline: Verify that user can click all radios
    When User click on "<label>" radio
    Then User should see an value of radio contains "<label>"
    Examples:
      | label |
      | Apple |
      | Pear |
      | Orange |