Java Swing Login Form Task

A Java Swing Desktop Application created for user login validation, hardcoded user credentials matching, and role handling.


 Project Structure & Validation Screenshots

 1. Successful Login
[Successful Login](success.jpeg)
Description: Successful authentication using valid credentials (`student1` / `1234` with the `Student` role). Upon clicking Login, the main login window closes, and the `WelcomePage` interface opens displaying the personalized greeting.


 2. Invalid Credentials Validation
[Invalid Credentials](invalid.jpeg)
Description: Attempting to log in with incorrect credentials or an unmatched role triggers an error response. A dynamic red warning label displays the attempt counter (`Invalid credentials! Attempt 1 of 3`).


 3. Bonus Feature: Account Lockout
[Button Disabled](disabled.jpeg)
Description: After 3 consecutive failed login attempts, the application locks out the user by disabling the **Login** button (`setEnabled(false)`) to prevent further unauthorized attempts.
