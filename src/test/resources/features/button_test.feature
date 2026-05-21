Feature: Elements - Button
  Background:
    Given User open button page

  Scenario Outline: Verify that user can click all buttons
    When User click on "<label>" button
    Then User should see an expected message contains "<label>"
    Examples:
      | label |
      | Div button |
      | Origin button |
      | Input button  |
      | Default       |
      | Primary       |
      | Dashed        |
      | Text          |
      | Link          |
      | Icon button   |