Feature: Google Search
  Abilty to search Google
  
  Scenario: Verify search in Google.com
    Given I have opened Google.com
    When I search for "Wikipedia"
    Then search results should be displayed