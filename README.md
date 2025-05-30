# Cloudnovas-task-management-api
A simple **Task Management REST API** that provide a comprehensive way of Creating , updating, retriving and deleting task.
This API when integrated will allow clients to create, delete, update and view task.
It also provides a **Statistical Summary** of the task that is in the system

*Setup and Usage Instructions*

### Prerequisites
- Java 21 or higher
- Docker
- Maven 4.0+ (or use included Maven wrapper)
- Git

### Quick Start

**Clone the repository:**
```bash
git clone https://github.com/Tabari-Linus/task-management-api.git
cd task-management-api
```

**Run with Maven:**
```bash
./mvnw spring-boot:run
```

**Or run with Docker:**
```bash
docker build -t cloudnova-task-management-api .
docker run -p 8080:8080 cloudnova-task-management-api
```
**Access the API:**
- Base URL: `http://localhost:8080/api/v1/tasks`
- Swagger UI: `http://localhost:8080/swagger-ui.html`


### API Endpoints

| Method | Endpoint                    | Description                        |
|--------|-----------------------------|------------------------------------|
| GET | `/api/v1/tasks`             | Get all tasks (supports filtering) |
| GET | `/api/v1/tasks/{id}`        | Get task by ID                     |
| POST | `/api/v1/tasks`             | Create new task                    |
| PUT | `/api/v1/tasks/{id}`        | Update task                        |
| PATCH | `/api/v1/tasks/{id}/status` | Update task status                 |
| DELETE | `/api/v1/tasks/{id}`        | Delete task                        |
| GET | `/api/v1/tasks/stats`       | Get task statistical count of task |
| GET | `/api/v1/tasks/search`      | search task by keyword             |
