Feature: Add todo

  Scenario: Successfully added todo
    Given I can see todo list
    When I click on fab add button
    Then I can see todo editor view