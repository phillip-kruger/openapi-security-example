# OpenAPI Security : OIDC

## Keycloak 
Download keycloak 12.0.2 and start with:

```
./bin/standalone.sh -Djboss.http.port=8081
```

* Import `oidc-keycloak-setup.json`
** This creates the relevant client (quarkus-oidc-client) and groups and roles.
* Create some users
** Under Manage>Users>Add user
*** dilbert:dilbert and join the employeeGroup
*** boss:boss and join the employeeGroup and the bossGroup

## In Swagger UI

* flow = password
* client id = quarkus-oidc-client&
* client secret = 0ae76fa1-4062-4c84-91c8-a4d635dea618
