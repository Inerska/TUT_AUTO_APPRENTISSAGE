import { useState } from 'react';
import { CreateExerciseBody, CreateExerciseResponse } from '@/utils/types';
import { createExercise } from '@/service/apiServiceExercise';

export const useCreateExercise = () => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [createdExercise, setCreatedExercise] = useState<CreateExerciseResponse | null>(null);

    const createNewExercise = async (exerciseData: CreateExerciseBody) => {
        setLoading(true);
        setError(null);
        try {
            const response = await createExercise(exerciseData);
            if (response) {
                setCreatedExercise(response);
            } else {
                setError("Failed to create exercise");
            }
        } catch (error) {
            console.error(error);
            setError("An error occurred while creating the exercise");
        } finally {
            setLoading(false);
        }
    };

    return { createNewExercise, loading, error, createdExercise };
};
