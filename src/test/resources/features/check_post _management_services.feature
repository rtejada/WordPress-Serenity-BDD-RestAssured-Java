Feature:Posts Management
  As a user with permissions
  I want to make sure that the steps reported in the documentation do what it is supposed to do...
  what it is supposed to do...

  Scenario Outline: Create a new post
    Given the application is open and running
    When I want to create a new post "<status>", "<password>", "<comment_status>", "<title>"
    Then the API should return the code status "201"

    Examples:
    | status  | password | comment_status | title         |
    | private | pwd      | open           | Paradigm Post |

  Scenario: Get a specific post
    Given the application is open and running
    When I want to retrieve the post with id "650"
    Then the API should return the code status "200"


  Scenario: Update a specific post
    Given the application is open and running
    When I want to update a post with id "650"
    Then the API should return the code status "200"

  Scenario: Create and delete a specific post
    Given the application is open and running
    And a new post has been created
    When I want to delete the post I created
    Then the API should return the code status "200"


