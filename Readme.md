# Keycloak Kafka Event Listener

A Keycloak event listener that publishes user registration events to Apache Kafka for downstream processing in Spring Boot applications.

## Overview

This project implements a producer-consumer pattern using Keycloak's EventListenerProvider interface and Apache Kafka to synchronize user data between Keycloak and your application database.

### Why Use This Approach?

When using Keycloak as an authentication system for your REST API, you often need access to registered user information in your own application database. This solution provides:

- **Real-time synchronization**: User data is immediately available in your application when users register
- **Decoupled architecture**: Your application doesn't need direct database access to Keycloak
- **Event-driven processing**: Handle user registration and other Keycloak events asynchronously
- **Scalability**: Kafka enables reliable message processing across multiple consumers

### Architecture

1. **Keycloak Event Listener**: Captures user registration events using EventListenerProvider interface
2. **Kafka Producer**: Publishes user information to Kafka topics
3. **Spring Boot Consumer**: Consumes messages and persists user data to application database

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Docker and Docker Compose

## Setup Instructions

### 1. Build the Project

Navigate to the project directory and build the JAR file:

```bash
mvn clean install
```

### 2. Start Docker Services

Launch Kafka, Zookeeper, and Keycloak using Docker Compose:

```bash
docker-compose up -d
```

Wait for all containers to be ready. The JAR file will be automatically deployed to the Keycloak server.

### 3. Configure Keycloak

1. Access the Keycloak Admin Console:
   - URL: http://localhost:8084
   - Username: `admin`
   - Password: `secret`

2. Enable the custom event listener:
   - Click **Events** in the left sidebar
   - Select **Config** tab
   - In the **Event Listeners** field, add `custom-event-listener`
   - Click **Save**

### 4. Test the Integration

When a new user registers through Keycloak, their user ID and information will be automatically published to Kafka and can be consumed by your Spring Boot application.

## Configuration

### Kafka Topics

The default topic configuration publishes user events to:
- Topic: `keycloak-user-events`
- Key: User ID
- Value: User information (JSON format)

### Event Types Supported

- User registration
- User profile updates
- User deletion
- Custom events (configurable)

## Development

### Customizing Event Handling

To modify which events are captured or how data is structured, edit the EventListenerProvider implementation in:

```
src/main/java/your/package/CustomEventListenerProvider.java
```

### Kafka Configuration

Kafka settings can be modified in the Docker Compose file or through environment variables.

## Troubleshooting

### Common Issues

- **JAR not deploying**: Ensure Maven build completed successfully and Docker containers are fully started
- **Events not firing**: Verify the event listener is properly configured in Keycloak Admin Console
- **Kafka connection issues**: Check that all Docker services are running and accessible

### Logs

Monitor application logs through Docker:

```bash
docker-compose logs -f keycloak
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

[Add your license information here]