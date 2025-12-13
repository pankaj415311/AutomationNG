Feature: Amazon Search 

@smoke
Scenario: Search Product Three
	Given I have a search field on amazon
	When I search for a product named "Iphone 16" and price 1000
	Then product with name "iphone 16" should be displayed
@smoke	
Scenario: Search Product Four
	Given I have a search field on amazon
	When I search for a product named "Iphone 16" and price 1000
	Then product with name "iphone 16" should be displayed
