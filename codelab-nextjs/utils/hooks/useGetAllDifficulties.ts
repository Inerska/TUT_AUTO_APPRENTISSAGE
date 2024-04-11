import { useEffect, useState } from 'react';
import { getAllDifficulties } from "@/service/apiServiceExercise";

export const useGetAllDifficulties = () => {
  const [difficulties, setDifficulties] = useState<any[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | undefined>(''); // Définir le type de 'error'

  useEffect(() => {
    setLoading(true);
    getAllDifficulties()
      .then(data => {
        setDifficulties(data);
      })
      .catch(error => {
        setError(error);
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  return { difficulties, loading, error };
};