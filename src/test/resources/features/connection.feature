Feature: Connect to web Feature
  Verify if user is able to open chrome and navigate to a web page

Scenario: open http://34.209.123.174:3000/
    Given loads browser to check image
    When user navigates to http://34.209.123.174:3000/
    Then user sees element image1
    Then user takes a screenshot
    Then user closes browser

Scenario: open http://34.209.123.174:3000/ to check link1
    Given loads browser to check link1
    When user navigates to http://34.209.123.174:3000/ 
    Then user sees element link1
    Then user takes a screenshot of link1
    Then user closes browser

Scenario: open http://34.209.123.174:3000/ to check link2
    Given loads browser to check link2
    When user navigates to http://34.209.123.174:3000/ 
    Then user sees element link2
    Then user takes a screenshot of link2
    Then user closes browser
