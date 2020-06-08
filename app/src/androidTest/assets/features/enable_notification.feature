Feature: Enable notifications

@wip
  Scenario: Enables notifications
    Given I can see list of todos
    When I click hamburger menu
    And I select settings option
    And I select notifications option
    And I click on enable notifications option
    Then I expect to see 3 additional options enabled