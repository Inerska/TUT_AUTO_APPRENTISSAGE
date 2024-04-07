## Installation

### Génération des Clés RSA

1. Ouvrez un terminal dans le dossier des ressources de votre projet.

2. Générez une clé privée RSA de 2048 bits :

```bash
openssl genrsa -out privateKey.pem 2048
```

3. Créez une clé publique à partir de la clé privée générée :
```bash
openssl rsa -pubout -in privateKey.pem -out publicKey.pem
```

4. Convertissez la clé privée au format PKCS#8 :
```bash
openssl pkcs8 -topk8 -nocrypt -inform pem -in privateKey.pem -outform
```

## Authentification API

Cette API gère l'authentification des utilisateurs. Vous pouvez vous enregistrer et vous connecter pour obtenir des jetons d'accès et de rafraîchissement pour accéder à d'autres fonctionnalités de l'application.

### Enregistrement (/register)

- **Méthode :** POST
- **Endpoint :** `/api/auth/register`
- **Description :** Permet à un utilisateur de créer un nouveau compte en fournissant un nom d'utilisateur, une adresse e-mail et un mot de passe.
- **Requête :**
```json
  {
    "username": "samuel",
    "mail": "samuel.pomin@gmail.com",
    "password": "azerty123",
    "confirm-password": "azerty123"
  }
```
- **Réponse :**
 ```json
 {
  "access-token": "eyJ...",
  "refresh-token": "eyJ..."
}
 ```

### Connexion (/login)

- **Méthode :** POST
- **Endpoint :** `/api/auth/register`
- **Description :** Permet à un utilisateur existant de se connecter en fournissant son adresse e-mail et son mot de passe.
- **Requête :**
```json
  {
    "mail": "samuel.pomin@gmail.com",
    "password": "azerty123"
  }
```
- **Réponse :**
 ```json
 {
  "access-token": "eyJ...",
  "refresh-token": "eyJ..."
}
 ```

## Gestion des Tokens

Après l'enregistrement ou la connexion, chaque utilisateur reçoit un jeton d'accès et un jeton de rafraîchissement. Le jeton d'accès est utilisé pour authentifier les requêtes API, tandis que le jeton de rafraîchissement est utilisé pour obtenir un nouveau jeton d'accès une fois que le jeton d'accès précédent expire. Assurez-vous de stocker ces jetons en toute sécurité et de les inclure dans les en-têtes de vos requêtes API pour accéder aux ressources protégées.