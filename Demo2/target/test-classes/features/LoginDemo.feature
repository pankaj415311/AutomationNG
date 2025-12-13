
Feature: Test Login Functionality of Facebook

Scenario: Validate Login functionality with valid user Id and Password

Given Browser is open 
And User is on Login Page
When  User Enter Uname in uname field
And User Enters Password in password field
And User Click on Login Button
Then User should be able to login successfully and home page should be displayed.


