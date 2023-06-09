# Java-Springboot-simple-usage-github-api

Run aplication: 
Linux
```bash
./gradlew bootRun
```
Windows:
```shell
gradlew.cmd bootRun
```
Request build method GET:
```

GET:
    http://127.0.0.1:8080/api/user/{USER}
HEADER:
    Accept: application/json
    Token: {GITHUB TOKEN OPTIONAL}
```