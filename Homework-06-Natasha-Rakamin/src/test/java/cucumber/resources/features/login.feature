Feature: Log in to the website

  @Positive
  Scenario: User login using registered username and password
  Given User launches the web
  Then User inputs a registered username
  And User inputs a registered password
  And User taps login button
  Then User verifies success login

  @Negative
  Scenario: User login using unregistered username and registered password
  Given User launches the web
  Then User inputs an unregistered username
  And User inputs a registered password
  And User taps login button
  Then User verifies failed logout with error message
