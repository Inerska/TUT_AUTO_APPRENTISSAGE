import { useEffect, useState } from 'react';
import { getAllDifficulties } from "@/service/apiServiceExercise";

const useGetAllDifficulties = () => {
  const [difficulties, setDifficulties] = useState<any[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | undefined>(''); // Définir le type de 'error'

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getAllDifficulties();
        setDifficulties(data);
      } catch (error: any) {
        setError(error.message || 'Une erreur est survenue lors de la récupération des difficultés.');
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  return { difficulties, loading, error };
};

export default useGetAllDifficulties;
