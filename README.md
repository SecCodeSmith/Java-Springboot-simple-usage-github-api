# Java Spring Boot GitHub API Integration

This project is a recruitment task developed to demonstrate proficiency in building RESTful APIs using Java and Spring Boot. The application interacts with the GitHub API to retrieve information about a specified user's repositories.

## Features

- **Retrieve User Repositories**: Fetches a list of public repositories for a given GitHub username.
- **Optional Authentication**: Supports the use of a GitHub personal access token to increase API rate limits.

## Technologies Used

- **Java**: Primary programming language.
- **Spring Boot**: Framework for building the application.
- **Gradle**: Build automation tool.

## Running the Application

### Prerequisites

- **Java Development Kit (JDK)**: Ensure JDK 8 or higher is installed.
- **Gradle**: Included in the project; no need for a separate installation.

### Steps to Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/SecCodeSmith/Java-Springboot-simple-usage-github-api.git
   cd Java-Springboot-simple-usage-github-api
   ```
2. **Run the Application**:
   - **On Linux/macOS**:
     ```bash
     ./gradlew bootRun
     ```
   - **On Windows**:
     ```cmd
     gradlew.bat bootRun
     ```

## API Usage

### Endpoint

- **Retrieve User Repositories**:
  - **URL**: `http://127.0.0.1:8080/api/user/{username}`
  - **Method**: GET
  - **Headers**:
    - `Accept: application/json`
    - `Token: {GITHUB_TOKEN}` (Optional)

### Example Request

```bash
curl -H "Accept: application/json" -H "Token: YOUR_GITHUB_TOKEN" http://127.0.0.1:8080/api/user/octocat
```

### Response

The API responds with a JSON array containing the user's public repositories, each with details such as repository name, description, and URL.
