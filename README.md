# Backend dev technical test

Spring Boot application that exposes a reactive REST API on port 5000 to get similar products using Spring Webflux.

## Project Structure

The application is organized into the following packages:
- controller: Contains the controllers and exception handlers.
- service: Contains the service interfaces, implementations, and DTOs.
- config: Contains app configurations

## Project Prerequisites

- Java Development Kit (JDK) 17 or later
- Maven

## Installation

To install and set up the application, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the project root directory.
3. Run the following command to build the application:

```
mvn clean install
```

## Running the Application
To run the application, use the following command:

```
mvn spring-boot:run
```

- This command will start the Spring Boot application and make it accessible at http://localhost:5000.
- Send a GET request to http://localhost:5000/product/{productId}/similar to get the similar products.

## Tests

The application includes unit tests for all services and the controller. Additionally, there is an integration test to validate the request.

- SimilarProductsControllerIT: Tests the request in the 'VisibilityController' to ensure its correctness.

You can run the tests using the following command:
``` 
mvn test
``` 

## Docker

The application is containerized, and the 'Dockerfile' is located in the project's root directory. To deploy the application using Docker, execute the following commands in a terminal from the project's root directory:
```    
docker build -t spring-boot-docker .
docker run -p 5000:5000 spring-boot-docker --server.port=5000
```

## Documentation with OpenAPI 3.0

The application provides API documentation. Once the application is running, you can access the Swagger UI at http://localhost:5000/swagger-ui.html and OpenAPI descriptions at http://localhost:5000/api-docs.
The API documentation provides detailed information about the available endpoints, request/response formats, and example requests.

## Logger

The application utilizes logging to record important events and provide debugging information.
The default log level is DEBUG.
