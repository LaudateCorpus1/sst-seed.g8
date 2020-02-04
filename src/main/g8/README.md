# Scala Server Toolkit Seed

This is an example that shows how Scala Server Toolkit can be used to properly
initialize a server application.

Some of the "business logic" parts are simplified so please do not take them as
the best example of how server application should be structured.

You need to run Docker Compose to start up the environment for the application
(database, ...):

```bash
docker-compose -f docker-compose.yml up
```

Then you can just run the [Main](src/main/scala/com/avast/sst/example/Main.scala) class
by 'sbt~> reStart' command and you could stop it after testing by 'sbt~> reStop').
(see[sbt-revolver](https://github.com/spray/sbt-revolver) docs about that commands)

To test example app edpoints do following requests:

```
curl -v http://localhost:8080/hello
```

```
curl -v http://localhost:8080/random
```

```
curl -v http://localhost:8080/circuit-breaker
```

Also see note about [Mdoc](./docs/mdoc/index.md)
