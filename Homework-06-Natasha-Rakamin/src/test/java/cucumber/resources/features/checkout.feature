Feature: Fill information for shipping product

  @Positive
  Scenario: User is filling out all the information needed for shipping
  Given The page of shipping information in Swag Labs
  Then User inputs her first name
  And User inputs her last name
  Then User inputs postal code area
  And User taps the continue & Finish button
  Then User verifies successful fill information for shipping

  @Negative
  Scenario: User isn't filling out the zip for shipping
  Given The page of shipping information in Swag Labs
  Then User inputs her first name
  And User inputs her last name
  And User taps the continue button
  Then User verifies failed filling information with error message

