# Guide pour l'implémentation d'une gestion d'un autre langage dans la classe DockerTransactionCommandChain

La classe `PythonDockerTransactionCommandChain` fournit une implémentation spécifique pour exécuter des commandes Docker pour le langage Python. Si vous souhaitez étendre cette fonctionnalité pour supporter un autre langage, suivez ces étapes :

## 1. Créer une nouvelle classe d'implémentation

Créez une nouvelle classe qui implémente l'interface `DockerTransactionCommandChain`. Cette classe devrait contenir la logique spécifique au langage que vous souhaitez prendre en charge.

Exemple : 

```java
public class JavaScriptDockerTransactionCommandChain implements DockerTransactionCommandChain {
    // Implémentation spécifique pour JavaScript
}
```

## 2. Définir les dépendances nécessaires

Identifiez les services ou les exécutants de commandes Docker requis pour l'exécution du langage choisi. Injectez-les dans le constructeur de la nouvelle classe.

Exemple : 

```java
public class JavaScriptDockerTransactionCommandChain implements DockerTransactionCommandChain {

    private final ExerciceService exerciceService;
    private final DockerCommandExecutor commandExecutor;

    public JavaScriptDockerTransactionCommandChain(ExerciceService exerciceService, DockerCommandExecutor commandExecutor) {
        this.exerciceService = exerciceService;
        this.commandExecutor = commandExecutor;
    }

    // Autres méthodes et implémentations ici...
}
```

## 3. Implémenter la logique d'exécution

Écrivez la logique d'exécution spécifique au langage choisi dans la méthode `execute`. Cette méthode doit prendre en charge la création et l'exécution des commandes Docker nécessaires pour exécuter le code dans un conteneur.

## 4. Adapter les commandes Docker

Modifiez les commandes Docker dans la méthode `execute` pour refléter la syntaxe et les exigences du langage choisi. Assurez-vous d'utiliser les bonnes commandes pour copier le code source, installer les dépendances et exécuter les tests ou le code lui-même.

## 5. Utiliser des variables d'environnement

Si nécessaire, utilisez des variables d'environnement pour configurer le conteneur Docker afin de prendre en charge les spécificités du langage choisi, comme les chemins d'accès aux interpréteurs ou aux compilateurs.

## 6. Tester et valider l'implémentation

Une fois l'implémentation complétée, testez-la avec différents scénarios pour vous assurer qu'elle fonctionne correctement. Vérifiez également que les performances et la sécurité sont conformes aux exigences.

## 7. Classe DockerQueueImageResolverFactoryImpl

La classe `DockerQueueImageResolverFactoryImpl` est une implémentation de l'interface `DockerQueueImageResolverFactory`. Elle est responsable de résoudre le nom de l'image Docker en fonction du langage de programmation spécifié. Par exemple, elle retourne "openjdk:11" pour le langage "java" et "python:3.9" pour le langage "python".

Vous devez donc rajouter l'image du nouveau langage a implémenter.

```java
@ApplicationScoped
public class DockerQueueImageResolverFactoryImpl implements DockerQueueImageResolverFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> resolve(final String language) {
        return switch (language) {
            case "java" -> Optional.of("openjdk:11");
            case "python" -> Optional.of("python:3.9");
            default -> Optional.empty();
        };
    }
}
```

## Conclusion

En suivant ces étapes, vous pouvez étendre la classe `DockerTransactionCommandChain` pour prendre en charge d'autres langages de programmation. Cette modularité permet une flexibilité et une extensibilité accrues dans l'exécution de code dans des environnements Docker.
