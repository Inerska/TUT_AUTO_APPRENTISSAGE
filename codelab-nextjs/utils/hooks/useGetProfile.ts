import { useEffect, useState } from "react";
import { getProfil } from "@/service/apiServiceExercise";
import { Profile } from "@/utils/types";

export function useGetProfile(profilId: string) {
    const [profile, setProfile] = useState<Profile | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<any>(null);

    useEffect(() => {
        const fetchProfile = async () => {
            setLoading(true);
            try {
                const data = await getProfil(profilId);
                setProfile(data);
            } catch (err) {
                setError(err);
            } finally {
                setLoading(false);
            }
        };

        if (profilId !== "") {
            fetchProfile();
        }
    }, [profilId]);

    return { profile, loading, error };
}
