@tag
Feature: Error Validation
I want to use this template for my feature file

@ErrorValidations
Scenario Outline: Negative testcase of password
Given I landed on Ecommerce Page
When Logged in with username <name> and password <password>
Then "Incorrect email or password." message is displayed

Examples:
|name				|password			|
|kichuad@gmail.com	|Kichuad@13		|
|gunnuad@gmail.com	|Gunnuad@1234		|
