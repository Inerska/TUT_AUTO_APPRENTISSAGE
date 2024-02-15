package org.arobase.infrastructure.persistence.service;

import org.arobase.domain.model.request.ExerciceSubmitRequest;
import org.arobase.infrastructure.persistence.entity.ExerciceResults;
import org.arobase.infrastructure.persistence.repository.ExerciceRepository;
import org.arobase.infrastructure.persistence.repository.ExerciceResultsRepository;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;
class ExerciceServiceTest {

    @Mock
    private ExerciceResultsRepository exerciceResultsRepository;

    @Mock
    private ExerciceRepository exerciceRepository;

    @Mock
    private Logger logger;

    @Mock
    private Emitter<ExerciceSubmitRequest> exerciceEmitter;

    @InjectMocks
    private ExerciceService exerciceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void submitExercice_ValidExercice_Submitted() {
        // Given
        ExerciceSubmitRequest exerciceSubmitRequest = new ExerciceSubmitRequest("author", "testCode", "someData", "someOtherData");
        ObjectId expectedId = new ObjectId();
        ExerciceResults exerciceResults = new ExerciceResults();
        doReturn(exerciceResults).when(exerciceResultsRepository).persist(any(ExerciceResults.class));

        // When
        ObjectId result = exerciceService.submitExercice(exerciceSubmitRequest);

        // Then
        assertEquals(expectedId, result);
    }

    // TODO Add more tests
}
