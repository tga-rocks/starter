# starter
Java microservices starter

Steps:
1. create new codespace from repository
2. install Extension Pack for Java
3. install Gradle for Java extension
4. install Spring Boot Extension Pack
5. try to run tests, allow codespaces to import required projects (i.e. from Debugger for Java extension)... had to kill / restart codespaces for this step
6. looks like most of the issues were b/c it doesn't support Spring Boot v3.0.2... works with 2.7.8
7. moved over to desktop VS Code to explicitly be able to support the container config via devcontainer.json
8. need java 17+ to run java extensions pack... in configuring the container via devcontainer.json, it won't allow changes due to permissions... hmmmm
9. def permissions related as indicated in the creation.log... something to do with vs code as a user versus the image "owner"
10. had to change user in devcontainer.json to root 
11. got everything working... Spring issues were due to remoteUser and failure to get Java installed on image as part of the devcontainer.json overrides
