Feature: Elements - Dropdown
  Background:
    Given User open dropdown page

  Scenario Outline: Verify that user can select all in dropdown list
    When User select "<item>" in dropdown
    Then User should see an value of dropdown contains "<item>"
    Examples:
      | item          |
      | 1st menu item |
      | 2nd menu item |
      | 3rd menu item |
      | 4rd menu item |