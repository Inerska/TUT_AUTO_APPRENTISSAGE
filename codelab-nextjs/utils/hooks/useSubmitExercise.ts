import {useEffect, useState} from "react";
import { submitExercise} from "@/service/apiServiceExercise";
import {SubmitExerciseBody, SubmitExerciseResponse} from "@/utils/types";

export function useSubmitExercise(exercise : SubmitExerciseBody) {

    const [response, setResponse] = useState<SubmitExerciseResponse>();
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState();

    const [isExerciseSubmitted, setIsExerciseSubmitted] = useState(false);

    useEffect(() => {
            if(exercise.code !== "" && !isExerciseSubmitted) {
                setLoading(true);
                setIsExerciseSubmitted(true);
                submitExercise(exercise)
                    .then(data => {
                        console.log(data);
                        setResponse(data)
                    })
                    .catch((err) => {
                        setError(err);
                    })
                    .finally(() => {
                        setLoading(false);
                    })
            }
    }, [exercise, isExerciseSubmitted], )

    return { response, loading, error, setIsExerciseSubmitted };
}