import { useEffect, useState } from "react";
import { getProfil } from "@/service/apiServiceExercise";
import {GetUserProfileResponse, Profile} from "@/utils/types";

export function useGetProfile(profilId: string) {
    const [profile, setProfile] = useState<GetUserProfileResponse>();
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<any>(null);

    useEffect(() => {
        setLoading(true);
        getProfil(profilId)
            .then(data => {
                setProfile(data);
            })
            .catch((err) => {
                setError(err);
            })
            .finally(() => {
                setLoading(false);
            });
    }, [profilId]);

    return { profile, loading, error };
}
