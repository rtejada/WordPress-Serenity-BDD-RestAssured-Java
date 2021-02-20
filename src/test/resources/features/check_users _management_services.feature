Feature: Users management
  as a user with permissions
  I want to make sure that the responses from the services are correct.

  Scenario Outline: Get a specific user
    Given the application is running
    When I want to get a specific user "<userId>"
    Then the API should return the status "200"

    Examples:
    | userId |
    | 100    |

  Scenario Outline: Create a new user
    Given the application is running
    When I want to create a new user "<username>", "<name>", "<first_name>", "<last_name>", "<email>"
    Then the API should return the status "201"

    Examples:
    | username | name    | first_name | last_name | email   |
    | nick     | Michael | MacDonald  | Town      | michael |

  Scenario Outline: Update a specific user
    Given the application is running
    When I want to update a specific user "<userId>"
    Then the API should return the status "200"
    And the user ID modified is <userId>

    Examples:
      | userId |
      | 100    |

  Scenario: Create and also delete user
    Given  a new user has been created
    When I want to delete the new user
    Then the API should return the status "200"

