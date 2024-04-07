import {useEffect, useState} from "react";
import {getExerciseDetails} from "@/service/apiServiceExercise";
import { Exercise } from "@/utils/types";

export function useGetExerciseDetails(exerciseId : string) {

	const [exercise, setExercise] = useState<Exercise>();
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState();

    useEffect(() => {
        setLoading(true);
        getExerciseDetails(exerciseId)
            .then(data => {
                setExercise(data)
            })
            .catch((err)=> {
                setError(err);
            })
            .finally(() => {
                //setTimeout(()=> {},1000) //fake api time
                setLoading(false);
            })
    }, [exerciseId], )

    return { exercise, loading, error };
}