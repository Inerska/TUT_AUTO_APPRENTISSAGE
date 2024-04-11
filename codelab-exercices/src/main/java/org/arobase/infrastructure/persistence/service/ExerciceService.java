package org.arobase.infrastructure.persistence.service;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.arobase.domain.model.request.ExerciceCreateRequest;
import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.persistence.entity.Exercice;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;
import org.arobase.infrastructure.persistence.repository.*;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public final class ExerciceService {
    private final ExerciceResultsRepository exerciceResultsRepository;
    private final ExerciceRepository exerciceRepository;

    private final LanguageRepository languageRepository;

    private final ProfileRepository profileRepository;

    private final DifficultyRepository difficultyRepository;

    private final TaskRepository taskRepository;

    private final Logger logger;

    @Channel("exercice-submitted")
    Emitter<ExerciceSubmitRequest> exerciceEmitter;

    public ExerciceService(ExerciceResultsRepository exerciceResultsRepository, ExerciceRepository exerciceRepository, LanguageRepository languageRepository, ProfileRepository profileRepository, DifficultyRepository difficultyRepository, TaskRepository taskRepository, Logger logger) {
        this.exerciceResultsRepository = exerciceResultsRepository;
        this.exerciceRepository = exerciceRepository;
        this.languageRepository = languageRepository;
        this.profileRepository = profileRepository;
        this.difficultyRepository = difficultyRepository;
        this.taskRepository = taskRepository;
        this.logger = logger;
    }

    /**
     * Submit an exercice and return the exercice result guid.
     *
     * @param exercice the exercice to submit
     * @return the exercice result object
     */
    public ObjectId submitExercice(ExerciceSubmitRequest exercice) {

        final var exerciceResult = new ExerciceResults();
        exerciceResult.status = "SUBMITTED";
        exerciceResult.exercice = exerciceRepository
                .findByIdOptional(new ObjectId(exercice.getExerciceId()))
                .orElseThrow(() -> new NotFoundException("Exercice not found."));

        try {
            exerciceResult.persist();
        } catch (final Exception e) {
            logger.error("Error while persisting exercice result.", e);
            throw new RuntimeException("Error while persisting exercice result.");
        }

        exercice.setExerciceResultObjectId(exerciceResult.id.toString());

        //add exerciseResult to profile
        final var profile = profileRepository.findByIdOptional(
                new ObjectId(exercice.getProfileId())).orElseThrow(() -> new NotFoundException("Profile not found.")
        );

        //delete previous exercice result if exists in profile
        profile.exercices.removeIf(exerciceResults -> exerciceResults.exercice.id.toString().equals(exercice.getExerciceId()));

        profile.exercices.add(exerciceResult);

        profile.update();

        exerciceEmitter.send(exercice);

        logger.info("Profile username: " + profile.username + " has submitted exercice " + exercice.getExerciceId());

        return exerciceResult.id;
    }


    /**
     * Get exercice results by object id.
     *
     * @param id the exercice result object id
     * @return the exercice result as an optional
     */
    public Optional<ExerciceResults> getExerciceResultById(final String id) {
        return exerciceResultsRepository.findByIdOptional(new ObjectId(id));
    }

    /**
     * Process exercice result by object id.
     *
     * @param id the exercice result object id
     */
    public void processExerciceResultById(final String id) {
        logger.info("Processing exercice result " + id + "...");
        final var exerciceResult = exerciceResultsRepository.findByIdOptional(new ObjectId(id)).orElseThrow(() -> new NotFoundException("Exercice result not found."));

        exerciceResult.status = "PROCESSING";
        exerciceResult.persistOrUpdate();
    }

    /**
     * Update exercice result by object id.
     *
     * @param id     the exercice result object id
     * @param status the exercice result status
     * @param result the exercice result
     */

    public void updateExerciceResult(final String id, final String status, final String result) {
        final var exerciceResult = exerciceResultsRepository.findByIdOptional(new ObjectId(id)).orElseThrow(() -> new NotFoundException("Exercice result not found."));

        exerciceResult.status = status;
        exerciceResult.result = result;
        exerciceResult.persistOrUpdate();

        //get profile
        final var profile = profileRepository.list("exercices._id", new ObjectId(id)).getFirst();

        //update exercice result in profile
        profile.exercices.stream()
                .filter(exerciceResults -> exerciceResults.id.equals(new ObjectId(id)))
                .findFirst()
                .ifPresent(exerciceResults -> {
                    exerciceResults.status = status;
                    exerciceResults.result = result;
                });

        profile.update();

    }

    /**
     * Create an exercice.
     *
     * @param exerciceCreateRequest the exercice create request
     * @return the response
     */
    public Response createExercice(final ExerciceCreateRequest exerciceCreateRequest) {
        final var exercice = new Exercice();
        exercice.title = exerciceCreateRequest.getTitle();
        exercice.description = exerciceCreateRequest.getDescription();
        exercice.instructions = exerciceCreateRequest.getInstructions();
        exercice.tasks = exerciceCreateRequest.getTasks();
        exercice.banner = exerciceCreateRequest.getBanner();
        exercice.author = exerciceCreateRequest.getAuthor();
        exercice.testCode = exerciceCreateRequest.getTestCode();
        exercice.language = exerciceCreateRequest.getLanguage();
        exercice.difficulty = exerciceCreateRequest.getDifficulty();
        exercice.nbTests = exerciceCreateRequest.getNbTests();
        exercice.createdAt = exerciceCreateRequest.getCreatedAt();

        try {
            exercice.tasks.forEach(task -> task.persist());

            //if language used in exercice does not exist, create it otherwise update it to use existing one
            languageRepository.find("name", exercice.language.name)
            .firstResultOptional().ifPresentOrElse(
                    value -> exercice.language = value,
                    () -> exercice.language.persist()
            );

            //if difficulty used in exercice does not exist, create it otherwise update it to use existing one
            difficultyRepository.find("name", exercice.difficulty.name).firstResultOptional()
            .ifPresentOrElse(
                    value -> exercice.difficulty = value,
                    () -> exercice.difficulty.persist()
            );

            exercice.persist();


            return Response.ok(exercice.id).build();
        } catch (final Exception e) {
            logger.error("Error while creating exercice.", e);

            return Response.serverError().entity("Error while creating exercice: " + e.getMessage()).build();
        }
    }

    /**
     * Get test code by exercice id.
     *
     * @param id the exercice id
     * @return the test code
     */
    public String getTestCodeByExerciceId(final String id) {
        final var exercice = exerciceRepository.findByIdOptional(new ObjectId(id)).orElseThrow(() -> new NotFoundException("Exercice not found."));

        return exercice.testCode;
    }

    /**
     * Get exercices by language.
     * @param language the language
     * @return the list of exercices
     */
    public Uni<List<Exercice>> listExercicesByLanguage(final String language) {
        return Uni.createFrom().item(() -> exerciceRepository.list("language.name", language));
    }

    /**
     * Get exercices by id.
     * @param id Exercise Id
     * @return the exercice
     */
    public Optional<Exercice> getExerciseById(final String id) {
        return exerciceRepository.findByIdOptional(new ObjectId(id));
    }

    /**
     * Delete an exercice by id.
     * @param id the exercice id
     */
    public void deleteExerciceById(final String id) {
        final var exercice = exerciceRepository.findByIdOptional(new ObjectId(id)).orElseThrow(() -> new NotFoundException("Exercice not found."));
        exercice.delete();
    }

    /**
     * Modify an exercice by id.
     * @param id the exercice id
     * @param exerciceCreateRequest the exercice create request
     */
    public void modifyExercice(final String id, final ExerciceCreateRequest exerciceCreateRequest) {
        final var exercice = exerciceRepository.findByIdOptional(new ObjectId(id)).orElseThrow(() -> new NotFoundException("Exercice not found."));

        exercice.title = exerciceCreateRequest.getTitle();
        exercice.description = exerciceCreateRequest.getDescription();
        exercice.instructions = exerciceCreateRequest.getInstructions();
        exercice.banner = exerciceCreateRequest.getBanner();
        exercice.author = exerciceCreateRequest.getAuthor();
        exercice.testCode = exerciceCreateRequest.getTestCode();
        exercice.language = exerciceCreateRequest.getLanguage();
        exercice.difficulty = exerciceCreateRequest.getDifficulty();
        exercice.nbTests = exerciceCreateRequest.getNbTests();
        exercice.createdAt = exerciceCreateRequest.getCreatedAt();

        try {
            //chage tasks only if they have been modified to avoid database spam
                //for each task in exerciceCreateRequest
                    //if id is present means it should be updated
                        //find task, update content and order
                    //if id is not present means it should be created
                        //persist task
                //delete all and add all tasks from exerciceCreateRequest
                    //we should only have now the updated tasks

            exerciceCreateRequest.getTasks().forEach(task -> {
                if (task.id != null) {
                    final var taskToUpdate = taskRepository.findByIdOptional(task.id).orElseThrow(() -> new NotFoundException("Task not found."));
                    taskToUpdate.content = task.content;
                    taskToUpdate.order = task.order;
                    taskToUpdate.update();
                } else {
                    task.persist();
                }
            });

            exercice.tasks.clear();

            exercice.tasks.addAll(exerciceCreateRequest.getTasks());

            //if language used in exercice does not exist, create it otherwise update it to use existing one
            languageRepository.find("name", exercice.language.name)
            .firstResultOptional().ifPresentOrElse(
                    value -> exercice.language = value,
                    () -> exercice.language.persist()
            );

            //if difficulty used in exercice does not exist, create it otherwise update it to use existing one
            difficultyRepository.find("name", exercice.difficulty.name).firstResultOptional()
            .ifPresentOrElse(
                    value -> exercice.difficulty = value,
                    () -> exercice.difficulty.persist()
            );

            exercice.update();
        } catch (final Exception e) {
            logger.error("Error while modifying exercice.", e);
            throw new RuntimeException("Error while modifying exercice.");
        }
    }

    /**
     * Get exercices by profile id.
     * @param profileId the profile id
     * @return the list of exercices
     */
    public Uni<List<Exercice>> listExercicesByProfileId(final String profileId) {
        //get user from profileId
        //get all exercises where author = username in exercices
        return Uni.createFrom().item(
                () -> exerciceRepository.list("author",
                    profileRepository.findByIdOptional(
                            new ObjectId(profileId)
                    ).orElseThrow(() -> new NotFoundException("Profile not found.")).username
                )
        );
    }
}

