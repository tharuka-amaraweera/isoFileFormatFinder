## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or later
- Maven

### Installation

1. Clone the repository:
    - git clone https://github.com/tharuka-amaraweera/isoFileFormatFinder.git
2. Navigate to the project directory:
    - cd   isoFileFormatFinder
3. Build the project with Maven:
    - cd   mvn clean install
4. Run the Spring Boot application
    - The application will start running on `http://localhost:8080`.
### Endpoints
      - GET http://localhost:8080/mp4/analyze?url=<your_URL>

            Example : //localhost:8080/mp4/analyze?url=https://demo.castlabs.com/tmp/text0.mp4
