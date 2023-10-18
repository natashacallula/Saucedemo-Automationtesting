Feature: Checkout product from cart

  @Positive
  Scenario: User is checking out the cart that contains items
  Given The page of products in Swag Labs
  Then User adds an item to the cart
  And User taps the cart button
  And User taps the checkout button
  Then User verifies the success status

  @Negative
  Scenario: User is checking out the empty cart
  Given The page of products in Swag Labs
  Then User taps the empty cart button
  And User taps the checkout button
  Then User verifies the failed checkout

