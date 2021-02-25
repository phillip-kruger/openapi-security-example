# OpenAPI Security : OAuth

## Keycloak 
Download keycloak 12.0.2 and start with:

```
./bin/standalone.sh -Djboss.http.port=8081
```

* Import `oauth-keycloak-setup.json`
** This creates the relevant client (microprofile-jwt-client) and groups and roles.
* Create some users
** Under Manage>Users>Add user
*** dilbert:dilbert and join the employeeGroup
*** boss:boss and join the employeeGroup and the bossGroup

