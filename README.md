# Payment-system 2

Payment-system 2 is a testing project where I'm trying to use the most modern technologies as test-containers, groovy etc.

## Technology stack

<ul>
  <li>Java 17</li>
  <li>Spring</li>
  <li>Gradle</li>
  <li>Test containers</li>
  <li>Groovy</li>
  <li>Keycloak</li>
  <li>Swagger</li>
</ul>

## Quickstart

First of all we need to build project to create jar file and then run docker compose command.
</br>
It will run app and keycloak.

```bash
docker-compose up -d
```

## Keycloak

Usually keycloak is using for sing-in, but this project doesn't have UI, so we will use it only to create and validate bearer token
</br>
For token generation you should make a POST request to this url 
```localhost:8080/realms/payment-system/protocol/openid-connect/token``` with that parameters:
```
client_id:payment-system-app
username:developer
password:developer
grant_type:password
```

For access to keycloak admin console use admin/admin
```localhost:8080```

## Swagger

After getting token you can use it for access the app endpoints
```
localhost:8180/swagger-ui/index.html#/
```
