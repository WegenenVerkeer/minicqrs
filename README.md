![build workflow](https://github.com/WegenenVerkeer/minicqrs/actions/workflows/maven.yml/badge.svg)

# minicqrs

MiniCQRS is a minimal CQRS framework for Java on top of Spring, JOOQ and Postgres.


## Generating database classes:

Make sure the database is running, up to date with all flyway scripts and reachable with this:

```xml
<url>jdbc:postgresql://db:5432/minicqrs</url>
<user>minicqrs</user>
<password>minicqrs</password>
```

1. remove everything in core/src/main/java/be/wegenenverkeer/minicqrs/core/db
2. run 
```bash
cd core
mvn org.jooq:jooq-codegen-maven:generate
cd ..
```
3. remove everything in demoapp/src/main/java/be/wegenenverkeer/minicqrs/demoapp/db
4. run 
```bash
cd demoapp
mvn org.jooq:jooq-codegen-maven:generate
cd ..
```


