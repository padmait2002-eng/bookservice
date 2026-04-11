# Book Service Backend - Setup Guide

## Project Overview

This is a Spring Boot REST API backend for the Book Client Angular application. It provides CRUD operations for managing books using an in-memory H2 database.

## Technology Stack

- **Framework**: Spring Boot 3.2.1
- **Java Version**: 17
- **Database**: H2 (In-Memory)
- **Build Tool**: Maven
- **Data Layer**: Spring Data JPA

## Features

### API Endpoints

1. **Get All Books**
   - Endpoint: `GET /findAllBooks`
   - Response: List of all books

2. **Get Single Book**
   - Endpoint: `GET /getBook/{id}`
   - Response: Book details by ID

3. **Add New Book**
   - Endpoint: `POST /addBook`
   - Request Body: `{ "name": "string", "price": number }`
   - Response: Created book with ID

4. **Update Book**
   - Endpoint: `PUT /updateBook/{id}`
   - Request Body: `{ "name": "string", "price": number }`
   - Response: Updated book

5. **Delete Book**
   - Endpoint: `DELETE /delete/{id}`
   - Response: Confirmation message

## Database Configuration

The application uses H2 in-memory database with the following configuration:

- **Database URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (empty)
- **H2 Console**: http://localhost:8080/h2-console

### Key Properties (application.properties)

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
```

The `create-drop` strategy means:
- Database schema is created on startup
- Data is stored in memory
- Database is dropped on shutdown

## Building the Project

```bash
# Navigate to project directory
cd C:\JavaWork\Repository\bookservice

# Build the project
mvn clean package -DskipTests

# Run the application
java -jar target/Bookservice-0.0.1-SNAPSHOT.jar
```

## Running the Application

```bash
java -jar target/Bookservice-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:8080**

## CORS Configuration

The backend is configured to accept requests from the Angular client:
- **CORS Origin**: `http://localhost:4200`

This is configured in the `BookController` class using `@CrossOrigin` annotation.

## Project Structure

```
src/
├── main/
│   ├── java/com/spring/angular/example/Bookservice/
│   │   ├── BookserviceApplication.java       # Main application class
│   │   ├── controller/
│   │   │   └── BookController.java           # REST endpoints
│   │   ├── service/
│   │   │   └── BookService.java              # Business logic
│   │   ├── repository/
│   │   │   └── BookRepository.java           # Data access layer
│   │   ├── dto/
│   │   │   └── Book.java                     # Data transfer object
│   │   └── entity/
│   │       └── Book.java                     # Entity class
│   └── resources/
│       └── application.properties             # Configuration
└── test/
    └── java/com/spring/angular/example/Bookservice/
        ├── BookServiceTest.java
        └── BookControllerTest.java
```

## Entity Structure

The Book entity has the following properties:

```java
{
  "id": 1,
  "name": "Book Title",
  "price": 999
}
```

## Recent Updates

### Changes Made (April 11, 2026)

1. **Added new endpoint**: `GET /getBook/{id}`
   - Allows fetching a single book by ID
   - Returns 404 if book not found

2. **Updated endpoint**: `PUT /updateBook/{id}`
   - Changed from `/update` to `/updateBook/{id}`
   - Now accepts ID as path parameter for better REST compliance
   - Supports partial updates (name and/or price)

3. **Enhanced service layer**:
   - Added `getBookById(int id)` method
   - Updated `updateBook(int id, Book book)` to accept ID as parameter
   - Improved validation and error handling

4. **H2 Database Configuration**:
   - Updated `application.properties` with complete H2 setup
   - Enabled H2 console for database inspection
   - Set `ddl-auto=create-drop` for automatic schema creation

## Testing the API

### Using curl or Postman:

1. **Get all books**:
   ```
   curl http://localhost:8080/findAllBooks
   ```

2. **Add a new book**:
   ```
   curl -X POST http://localhost:8080/addBook \
     -H "Content-Type: application/json" \
     -d '{"name":"Java Programming","price":999}'
   ```

3. **Get a specific book**:
   ```
   curl http://localhost:8080/getBook/1
   ```

4. **Update a book**:
   ```
   curl -X PUT http://localhost:8080/updateBook/1 \
     -H "Content-Type: application/json" \
     -d '{"name":"Advanced Java","price":1200}'
   ```

5. **Delete a book**:
   ```
   curl -X DELETE http://localhost:8080/delete/1
   ```

## Integration with Angular Frontend

The Angular application at `http://localhost:4200` connects to this backend via:

- **Base URL**: `http://localhost:8080`
- **CORS**: Enabled for port 4200
- **HTTP Methods**: GET, POST, PUT, DELETE

### Angular Endpoints Configuration

The Angular app uses the following endpoints:
- `GET /findAllBooks` - Display all books
- `GET /getBook/{id}` - Fetch book details for editing
- `PUT /updateBook/{id}` - Save updated book
- `DELETE /delete/{id}` - Delete a book

## Troubleshooting

### Port Already in Use
If port 8080 is already in use:
```bash
# Edit application.properties and change
server.port=8081
```

### H2 Console Access
Access the H2 console at: http://localhost:8080/h2-console
- Username: `sa`
- Password: (leave empty)
- JDBC URL: `jdbc:h2:mem:testdb`

### CORS Issues
If you encounter CORS errors, verify:
1. Backend is running on port 8080
2. Angular app is running on port 4200
3. `@CrossOrigin("http://localhost:4200")` is present in BookController

## Build and Test Commands

```bash
# Build only
mvn clean package -DskipTests

# Build with tests
mvn clean package

# Run tests only
mvn test

# Run specific test
mvn test -Dtest=BookServiceTest

# Run application with Maven
mvn spring-boot:run
```

## Next Steps

1. Start the Spring Boot application:
   ```bash
   cd C:\JavaWork\Repository\bookservice
   java -jar target/Bookservice-0.0.1-SNAPSHOT.jar
   ```

2. Keep the Angular development server running:
   ```bash
   cd C:\JavaWork\Repository\Angular\book-client
   ng serve
   ```

3. Access the application at http://localhost:4200

4. Test the edit functionality:
   - Navigate to the books list
   - Click "Edit" on any book
   - Update the name and/or price
   - Click "Save Changes"
   - Verify the changes are persisted

