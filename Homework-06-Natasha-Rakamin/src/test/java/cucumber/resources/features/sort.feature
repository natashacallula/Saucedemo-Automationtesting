Feature: Sorting products by filter

  @Positive
  Scenario: User sort products by price fro high to low
  Given The page of products
  Then User taps sort price high to low
  And User verifies the successful sorting of products by price
