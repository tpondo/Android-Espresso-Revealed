Feature: Add todo

  Scenario Outline: Successfully added todo
    Given I can see todo list
    When I click add button
    And I enter todo title "<title>"
    And I enter todo description "<description>"
    And I click done button
    Then I expect to see successfully added todo on the list "<title>"

    Examples:
      | title      | description      |
      | todo title | todo description |