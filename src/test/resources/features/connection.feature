Feature: Connect to web Feature
  Verify if user is able to open chrome and navigate to a web page

  Scenario: open http://34.220.2.144:3000/
    Given user loads browser
    When user navigates to http://34.220.2.144:3000/
    Then user sees element image1
    Then user sees element link1
    Then user sees element link2
    Then user takes a screenshot
