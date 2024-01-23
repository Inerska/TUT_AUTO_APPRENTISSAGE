# Service Exercice

Ce service est conçu pour gérer les exercices de programmation, y compris leur soumission, création, et la récupération des résultats. Il offre une API REST pour interagir avec l'application.

## Configuration

Avant de lancer le service, assurez-vous de configurer correctement le fichier `application.properties` :

```
quarkus.mongodb.connection-string = mongodb://(user):(pass)@(host):27017
quarkus.mongodb.database = (db)
```

Remplacez `(user)`, `(pass)`, `(host)` et `(db)` par vos informations de connexion MongoDB.

## Points de terminaison de l'API

### Soumettre un Exercice

- **URL** : `/api/v1/exercices`
- **Méthode** : `POST`
- **Description** : Soumet l'exercice pour l'évaluation.
- **Corps de la Requête** :
  ```json
  {
    "language": "python",
    "code": "def hello() -> str:\n    return \"Hello world\"",
    "exerciceId": "65a855d58f3b59165d67b7f3"
  }
  ```
- **Réponse** :
  ```json
  {
    "id": "65aa55334516d71f4b905ab7",
    "feedback": "Exercice submitted successfully."
  }
  ```

### Créer un Exercice

- **URL** : `/api/v1/exercices/create`
- **Méthode** : `POST`
- **Description** : Crée un nouvel exercice.
- **Corps de la Requête** :
  ```json
  {
    "language": "python",
    "testCode": "import unittest\n\nfrom main import hello\n\nclass TestHelloFunction(unittest.TestCase):\n\n    def test_hello_returns_correct_message(self):\n        self.assertEqual(hello(), \"Hello world\", \"Should be 'Hello world'\")\n\n    def test_hello_returns_correct_message_case(self):\n        self.assertEqual(hello(), \"Hello world\", \"Should be 'Hello world'\")\n\n    def test_hello_returns_correct_message_type(self):\n        self.assertEqual(type(hello()), str, \"Should be a string\")\n\n    def test_hello_returns_correct_message_length(self):\n        self.assertEqual(len(hello()), 11, \"Should be 11 characters\")\n\n\nif __name__ == \"__main__\":\n    unittest.main()",
    "author": "Alexis Gridel"
  }
  ```

### Récupérer les Résultats de l'Exercice

- **URL** : `/api/v1/exercices/:id/results`
- **Méthode** : `GET`
- **Description** : Récupère les résultats de l'exercice spécifié.
- **Réponse** :
  ```json
  {
    "id": "65aa55334516d71f4b905ab7",
    "status": "COMPLETED",
    "result": "FF..\n...[details des tests]...\n",
    "timestamp": "2024-01-19T10:55:47.642",
    "errorDetails": null,
    "additionalInfo": null
  }
  ```
