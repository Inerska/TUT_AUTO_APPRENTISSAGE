## Installation

### Génération des Clés RSA

1. Ouvrez un terminal dans le dossier des ressources de votre projet.

2. Générez une clé privée RSA de 2048 bits :

```bash
openssl genrsa -out rsaPrivateKey.pem 2048
```

3. Créez une clé publique à partir de la clé privée générée :
```bash
openssl rsa -pubout -in rsaPrivateKey.pem -out publicKey.pem
```

4. Convertissez la clé privée au format PKCS#8 :
```bash
openssl pkcs8 -topk8 -nocrypt -inform pem -in rsaPrivateKey.pem -outform
```


## Routes 

- POST /api/auth/register
```json
{
  "username": "username",
  "password": "password"
}
```

- POST /api/auth/login
```json
{
  "username": "username",
  "password": "password"
}
```