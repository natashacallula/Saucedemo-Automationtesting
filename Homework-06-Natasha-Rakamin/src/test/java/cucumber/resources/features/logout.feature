Feature: Logout from the website

  @Positive
  Scenario: User log out from website
  Given Page of products in Swag Labs
  Then User taps the three-bar button at the upper left corner
  And User taps the logout button
  Then user verifies successful log out from the website
