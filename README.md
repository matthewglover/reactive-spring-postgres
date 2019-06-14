# Spring - Reactive Postgres Sample

## What

A simple demo application running Spring with Reactive Postgres

### Pre-requisites

- Docker
- Java 8
- Kotlin 1.2.71

### Setup Postgres

To run Postgres as a docker container, from the root of the project:

```
docker-compose -f docker/docker-compose.yaml up -d
```

The file will save data outside of the container at `./docker/postgres-data` It accepts an optional ENV parameter, which 
will save postgres data at a sub-directory. For example, the following command will save data to 
`./docker/postgres-data/test/`

```
ENV=test docker-compose -f docker-compose -f docker/docker-compose.yaml up
```

### Run the application

Run:

```
./gradlew bootRun
```
