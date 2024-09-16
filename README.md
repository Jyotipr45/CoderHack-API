# CoderHack API

Welcome to the **CoderHack API**! This is a RESTful service developed using **Spring Boot** and **MongoDB** to manage a coding platform's leaderboard. It allows users to track their scores and earn badges based on their performance. 

## Project Overview

The CoderHack API is designed to manage the leaderboard for a single coding contest, storing users' scores and the badges they earn. This service includes various functionalities like creating, updating, deleting, and retrieving user information while validating user scores and badges.

### Features
- **User Management**: Add, update, delete, and retrieve user information.
- **Leaderboard Management**: Automatically sort users based on their scores in descending order.
- **Badge System**: Award badges to users based on their performance.
- **Input Validation**: Ensure that user input, such as scores and badges, is valid.

## Setup Instructions

### 1. Prerequisites
Before you start, ensure you have the following installed:
- **Java 11+**
- **Gradle**
- **MongoDB**
- **Postman** (optional for API testing)

### 2. Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/Jyotipr45/CoderHack-API.git
    cd CoderHack-API
    ```

2. Build the project:
    ```bash
    ./gradlew clean build
    ```

3. Run the application:
    ```bash
    ./gradlew bootRun
    ```

4. MongoDB should be running locally on port `27017` with the database `coderhack_db`.

### 3. Testing
You can run the tests by using:
```bash
./gradlew test
```
## API Documentation

You can access the Postman documentation for the API [here](https://documenter.getpostman.com/view/26482679/2sAXqpA4R1).

## Usage

### Base URL:
The API is available at: `http://localhost:8081/coderhack/api/v1/`

### Available Endpoints:
Here is a summary of the main API calls:

- **Get All Users**: 
  - `GET /coderhack/api/v1/users`
  - Retrieves a list of all users sorted by their scores in descending order.

- **Get User by ID**: 
  - `GET /coderhack/api/v1/users/{userId}`
  - Retrieves a specific user based on their user ID.

- **Add New User**: 
  - `POST /coderhack/api/v1/users`
  - Adds a new user to the system with initial values.

- **Update User's Score**: 
  - `PUT /coderhack/api/v1/users/{userId}`
  - Updates the score of an existing user.

- **Delete User**: 
  - `DELETE /coderhack/api/v1/users/{userId}`
  - Deletes a user by their user ID.

## Technologies Used
- **Java**: Backend language.
- **Spring Boot**: Framework for building the REST API.
- **MongoDB**: NoSQL database for storing user and leaderboard data.
- **Gradle**: Build automation tool.
- **Lombok**: To reduce boilerplate code.
- **Postman**: API testing.

## Try It Out

- **Postman Documentation**: Explore and test the API directly using the [Postman Documentation](https://documenter.getpostman.com/view/26482679/2sAXqpA4R1).
- **GitHub Source Code**: View or clone the full source code on [GitHub](https://github.com/Jyotipr45/CoderHack-API). Feel free to fork the repository and contribute!

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request on [GitHub](https://github.com/Jyotipr45/CoderHack-API).

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT). See the `LICENSE` file for more details.
