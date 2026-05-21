Feature: Components - AutoComplete
  Background:
    Given User open autocomplete page

  Scenario Outline: Verify that user can select all items in auto complete component
    When user select "<item>" in auto complete
    Then User should see an value of auto complete contains "<item>"
    Examples:
      | item            |
      | Burns Bay Road  |
      | Downing Street  |
      | Wall Street     |
