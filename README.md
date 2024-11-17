# Banking Application

This is a simple banking application that allows users to register an account, deposit money, withdraw funds, transfer money, and delete their account. The application also sends a confirmation email after account registration.

## Features

- **User Registration**  
  Users can register with their **Email**, **Name**, **Gender**, and **Account Type**.
  After successful registration, the user will receive a confirmation email.

- **Account Operations**
    - **Deposit**: Users can deposit money into their account.
    - **Withdraw**: Users can withdraw money from their account.
    - **Transfer**: Users can transfer money to another account.
    - **Delete Account**: Users can delete their account when they no longer need it.

- **Email Confirmation**  
  After registering, a confirmation email is sent to the user’s provided email address.

## Technologies Used

- **Spring Boot**: For building the back-end of the application.
- **Thymeleaf**: For rendering dynamic web pages.
- **Bootstrap 4**: For responsive and modern UI.
- **JavaMail API**: For sending confirmation emails.
- **MySQL Database**: In-memory database for storing user accounts.
- **Maven**: For project dependency management.

## Prerequisites

1. **Java**: Ensure that **JDK 11 or later** is installed on your system.
2. **Maven**: Used for managing project dependencies.
3. **Email Service**: Ensure you have a valid email service (like Gmail) for sending confirmation emails.


### Key Updates:
- **MySQL Database Setup**: Instructions to create a MySQL database (`bankingApp`) and configure it in `application.properties`.
- **Email Configuration**: Added details on configuring email settings for sending registration confirmation emails.
- **How to Run the Application**: Clarified the steps to run the Spring Boot application and hit http://localhost:8080/.


## Installation

### Clone the repository

```bash
git clone https://github.com/your-username/banking-application.git
cd banking-application



