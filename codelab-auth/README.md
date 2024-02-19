## Installation

### Génération des Clés RSA

1. Ouvrez un terminal dans le dossier des ressources de votre projet.

2. Générez une clé privée RSA de 2048 bits :
m

```bash
openssl genrsa -out rsaPrivateKey.pem 2048
```

3. Créez une clé publique à partir de la clé privée générée :
```bash
openssl rsa -pubout -in rsaPrivateKey.pem -out publicKey.pem
```

4. Convertissez la clé privée au format PKCS#8 :
```bash
openssl pkcs8 -topk8 -nocrypt -inform pem -in rsaPrivateKey.pem -outform pem -out privateKey.pe
```


## Routes 

### Register
- URL : /api/auth/register
- Method : POST
- RequestBody :
```json
{
  "username": "Leonarddoo",
  "mail": "samuel.pomin@gmail.com",
  "password": "admin",
  "confirm-password": "admin"
}
```

- ResponseBody :
```json
{
  "access-token": "eyJ0eXAiO",
  "refresh-token": "pv23Jcc0ZoAo"
}
```

### Login
- URL : /api/auth/login
- Method : POST
- RequestBody : 
```json
{
  "mail": "samuel.pomin@gmail.com",
  "password": "admin"
}
```

- ResponseBody :
```json
{
  "access-token": "eyJ0eXAiO",
  "refresh-token": "pv23Jcc0ZoAo"
}
```
