import { useEffect, useState } from "react";
import { getAllLanguages } from "@/service/apiServiceExercise";
import { LanguageItemApi } from "@/utils/types";

export function useGetAllLanguages() {

	const [languages, setlanguages] = useState<LanguageItemApi[]>([]);
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState();

	useEffect(() => {
		setLoading(true);
		getAllLanguages()
			.then(data => {
				setlanguages(data)
			})
			.catch((err) => {
				setError(err);
			})
			.finally(() => {
				setLoading(false);
			})
	}, [])

	return { languages, loading, error };
}