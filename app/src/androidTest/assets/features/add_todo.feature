Feature: Add todo

  @wip
  Scenario Outline: Successfully added todo
    Given I can see todo list
    When I click add button
    And I enter todo title "<title>"
    And I enter todo description "<description>"
    And I click done button
    Then I expect to see successfully added todo on the list "<title>" "<description>"

    Examples:
      | title                              | description                       |
      | todo title                         | todo description                  |
      | Only Title                         |                                   |
      |                                    | Only Description                  |
      | !\"#$%&'()*+,-./:;<=>?@[\]^_`{\|}~ | !#$%&'()*+,-./:;<=>?@[\]^_`{\|\}~ |
      | 1234567890                         | 1234567890                        |

  @wip
  Scenario: Add empty todo
    Given I can see todo list
    When I click add button
    And I click done button
    Then I expect to see information that todo item can not be empty
