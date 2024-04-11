import {useEffect, useState} from 'react';
import {CreateExerciseBody, CreateExerciseResponse, Exercise} from '@/utils/types';
import { createExercise } from '@/service/apiServiceExercise';

export const useCreateExercise = (exerciseData: CreateExerciseBody) => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [response, setResponse] = useState<CreateExerciseResponse | null>(null);

    const [isExerciseSubmitted, setIsExerciseSubmitted] = useState(false);


    useEffect(() => {
        if (exerciseData.title !== "" && !isExerciseSubmitted) {
            setLoading(true);
            setIsExerciseSubmitted(true);
            createExercise(exerciseData)
                .then(data => {
                    setResponse(data);
                })
                .catch((err) => {
                    setError(err);
                })
                .finally(() => {
                    setLoading(false);
                });
        }
    }, [exerciseData, isExerciseSubmitted]);

    return {loading, error, response, setIsExerciseSubmitted};
};
