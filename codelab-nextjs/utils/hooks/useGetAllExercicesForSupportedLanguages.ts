import {useEffect, useState} from "react";
import {getAllExercicesForSupportedLanguages, getAllLanguages} from "@/service/apiServiceExercise";
import {GetExerciseDetailsResponse, LanguageItemApi} from "@/utils/types";

export function useGetAllExercicesForSupportedLanguages(languages: LanguageItemApi[]) {

    const [exercises, setExercises] = useState<GetExerciseDetailsResponse[]>();
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState();

    useEffect(() => {
        setLoading(true);
        getAllExercicesForSupportedLanguages(languages)
            .then( data  => {
                setExercises(data)
            })
            .catch((err)=> {
                setError(err);
            })
            .finally(() => {
                setLoading(false);
            })
    }, [languages])

    return { exercises, loading, error };
}