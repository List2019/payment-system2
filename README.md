# Payment-system 2

Payment-system 2 is a testing project where I trying to use the most modern technologies as test-containers, groovy and etc.

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

## Quckstart

First of all let's up app and keycloak in docker and then generate token for accessing the swagger UI

```bash
docker-compose up -d
```

## Keycloak

For token generation you should make a POST request to this url 
```localhost:8080/realms/payment-system/protocol/openid-connect/token``` with that parameters:

```
client_id:payment-system-app
username:developer
password:developer
grant_type:password
```

For access to keycloak admin console use admin/admin

## Swagger

After getting token you can use it for access the app endpoints
```
localhost:8180/swagger-ui/index.html#/
```
