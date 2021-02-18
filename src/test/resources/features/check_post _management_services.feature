Feature:Manage Posts in Wordpress
  As a user with permissions
  I want to make sure that the steps reported in the documentation do what it is supposed to do...
  what it is supposed to do...

  Scenario: Create a new post
    Given the application is open and running
    When I want to create a new post
    Then the API should return the code status "201"

  Scenario: Get a specific post
    Given the application is open and running
    When I want to retrieve the post with id "650"
    Then the API should return the code status "200"


