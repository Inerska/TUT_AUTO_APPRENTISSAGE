import {useEffect, useState} from "react";
import {getAllExercicesForSupportedLanguages, getAllLanguages} from "@/service/apiServiceExercise";
import {Exercise, LanguageItemApi} from "@/utils/types";

export function useGetAllExercicesForSupportedLanguages(languages: LanguageItemApi[]) {

	const [exercises, setExercises] = useState<Exercise[]>();
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState();
useEffect(() => {
	setLoading(true);
	getAllExercicesForSupportedLanguages(languages)
		.then((data) => {
			if (typeof data !== "undefined" && data.length > 0) {
				setExercises(data);
			}
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