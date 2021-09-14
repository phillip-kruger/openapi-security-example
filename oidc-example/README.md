# OpenAPI Security : OIDC

## Keycloak 
Download keycloak 12.0.3 and start with:

```
./bin/standalone.sh -Djboss.http.port=8081
```

* Import `oidc-keycloak-setup.json`
    * This creates the relevant client (quarkus-oidc-client) and groups and roles.
* Create some users
    * Under Manage>Users>Add user
        * dilbert:dilbert and join the employeeGroup
        * boss:boss and join the employeeGroup and the bossGroup

## In Swagger UI

* flow = password
* client id = quarkus-oidc-client
* client secret = 883adc2a-0d3d-4014-8d94-577ca25f7791

![Screenshot](screenshot_oidc.gif)
