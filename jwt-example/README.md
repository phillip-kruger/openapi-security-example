# OpenAPI Security : JWT

## Keycloak 
Download keycloak 12.0.3 and start with:

```
./bin/standalone.sh -Djboss.http.port=8081
```

* Import `jwt-keycloak-setup.json`
** This creates the relevant client (microprofile-jwt-client) and groups and roles.
* Create some users
** Under Manage>Users>Add user
*** dilbert:dilbert and join the employeeGroup
*** boss:boss and join the employeeGroup and the bossGroup

## Getting a token

### Employee (dilbert)
curl -d "realm=OpenAPIJWT&grant_type=password&client_id=microprofile-jwt-client&username=dilbert&password=dilbert" -X POST http://localhost:8081/auth/realms/OpenAPIJWT/protocol/openid-connect/token | json_pp

### Boss (boss)
curl -d "realm=OpenAPIJWT&grant_type=password&client_id=microprofile-jwt-client&username=boss&password=boss" -X POST http://localhost:8081/auth/realms/OpenAPIJWT/protocol/openid-connect/token | json_pp