# SYSTEM REQUIREMENT
- Java 17 or lastest
- Apache Maven 3.8.8
- MySQL ver 8.4.0
> This is my setup

# HOW TO CLONE REPO
- Clone repository from github:
```
git clone https://github.com/GiaHao810/LiveFood.git
```
- Move to DIRECTORY
```
cd LiveFood
```
- Create a new MySQL Database
- Change to your Data URL and fill in username, password.

```
spring.datasource.url=jdbc:HERE
spring.datasource.username=HERE 
spring.datasource.password=HERE
```
> This is my URL JDBC URL: spring.datasource.url=jdbc:mysql://localhost:3306/livefood
- Install dependency throught Maven
```
mvn clean install
```
- Run app or Run *ClientApplication.java* from your IDE
```
mvn spring-boot:run
```
# API ENDPOINTS


# SCREENSHOTS
![Add Product](https://github.com/user-attachments/assets/93fe3131-cc08-40ba-80f9-065c81a2a972)

![Management User Page](https://github.com/user-attachments/assets/591061ca-1b74-4d0d-87f9-53f1e7adc61c)
![Management Product Page](https://github.com/user-attachments/assets/75f5952c-9936-4478-af55-40b915075fa3)

![Search product](https://github.com/user-attachments/assets/cba82446-f3ef-4d3a-b38e-c3155c434818)
![Search user](https://github.com/user-attachments/assets/44a7b6e4-14f8-49ef-88cc-611998408a4c)

Thông tin chi tiết về các phiên bản sẽ ở pom.xml

Jira: https://giahaobui810-1724241620238.atlassian.net/jira/software/projects/KAN/boards/1
