# Getting Started
Gateway network gives ability to manage gateways - master devices that control multiple peripheral devices. 

### Database
Create database and user using console:
```
mysql -u root

CREATE DATABASE network;

CREATE USER network@localhost IDENTIFIED BY 'secret';

GRANT ALL ON network.* TO network@localhost;
```

### Properties
Check properties if you need to change something: 
```
gateway_network/src/main/resources/application.yml
```

### Installing
```
mvn clean install
```

### Run
Run *GatewaysNetworkApplication.java*

### Swagger
Will be availavle after running the application on *http://localhost:8080/swagger-ui*
* [Swagger UI](http://localhost:8080/swagger-ui/)


