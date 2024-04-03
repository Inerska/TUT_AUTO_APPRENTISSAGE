import { useEffect, useState } from "react";
import { getExerciseResults } from "@/service/apiServiceExercise";
import { Exercise } from "@/utils/types";

export function useGetExerciseResults(code: string, submitLoad: boolean, exerciseId?: string) {

	const [results, setResults] = useState<Exercise>();
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState();
	const [pending, setPending] = useState<boolean>(true)

	const [refresh, refreshData] = useState();

	useEffect(() => {
		if (code !== "" && !submitLoad && exerciseId) {
			setLoading(true);
			getExerciseResults(exerciseId)
				.then(data => {
					console.log(data);
					if (data.status != "PENDING") {
						setPending(false);
					}
					setResults(data)
				})
				.catch((err) => {
					setError(err);
				})
				.finally(() => {
					setLoading(false);
				})
		}
	}, [code, exerciseId, refresh, submitLoad])

	return { results, pending, loading, error, refreshData };
}