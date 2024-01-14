# TUT_AUTO_APPRENTISSAGE

## Set up
## Production
### Build the API native image
```bash
cd codelab-quarkus
```

```bash
./gradlew build "-Dquarkus.package.type=native"
```

```bash
docker build -f src/main/docker/Dockerfile.native -t quarkus/codelab-quarkus .
```

### Run the docker-compose
```bash
cd ..
```

```bash
docker-compose up -d --build
```

## Development
### Run the docker-compose
```bash
docker-compose -f docker-compose.dev.yml up --build
```