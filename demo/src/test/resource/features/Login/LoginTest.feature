@LoginFunctionallity
 Feature: Login Functionallity Test

    @LoginPositive
    Scenario: Login Positive - Successfully Login with Valid Credentials
        Given User open Browser and Navigate to URL
        When User Input username with "tomsmith" and password with "SuperSecretPassword!"
        And User Click button Login
        Then User Successfully Login and direct to HomePage

    @LoginNegative
    Scenario: Login Negative - Failed Login with Invalid Credentials
        Given User open Browser and Navigate to URL
        When User Input username with "jhondoe" and password with "SuperSecretPassword!"
        And User Click button Login
        Then User failed Login and Validate error message