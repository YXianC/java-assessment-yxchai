## Java Assessment

```markdown
# Customer API


## Requirements
- Java 17
- Maven
- Local MSSQL server


## Setup
1. Adjust DB credentials in `src/main/resources/application.yml` (username/password).
    -   Default username : sa, password: Password123!


```bash
mvn clean package
mvn spring-boot:run
```


## Endpoints
- `POST /customer/add` create
- `GET /customer/list` list (page size = 10)
- `PUT /customers/update/{id}` update
- `GET /api/external/call` calls a 3rd party API


## Notes
- All requests/responses are logged (see `./logs/javaassessmmentAppLog.log`).
- `@Transactional` is used in service methods for insert/update/get.
```


---


## Tips & Next steps
- If your MSSQL uses different port or credentials, update `application.yml`.