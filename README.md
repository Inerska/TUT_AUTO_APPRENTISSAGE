# TUT_AUTO_APPRENTISSAGE

[Documentation service Exercices](https://github.com/Inerska/TUT_AUTO_APPRENTISSAGE/tree/main/codelab-exercices)
[Documentation service Authentication](https://github.com/Inerska/TUT_AUTO_APPRENTISSAGE/tree/main/codelab-auth)


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

### Install dependencies of front
```bash
cd codelab-nextjs
npm i
```

### Run the docker-compose
```bash
cd ..
```

```bash
docker-compose up -d --build
```

## Development

### Install dependencies of front
```bash
cd codelab-nextjs
npm i
```

### Run the docker-compose
```bash
docker-compose -f docker-compose.dev.yml up --build
```

## Evolution 

Read this : 
https://github.com/Inerska/TUT_AUTO_APPRENTISSAGE/blob/Leonarddoo-patch-1/EVOLUTION.md
