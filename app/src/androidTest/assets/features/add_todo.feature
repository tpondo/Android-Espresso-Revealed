Feature: Add todo

  Scenario: Successfully added todo
    Given I can see todo list
    When I click add button
    And I enter todo title
    And I click description field
    And I enter description
    And I click done button
    Then I expect to see successfully added todo on the list