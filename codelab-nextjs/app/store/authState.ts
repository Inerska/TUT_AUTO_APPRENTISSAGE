import { create } from "zustand";
import { persist } from "zustand/middleware";

type AuthState = {
  profileId: string | null;
  accessToken: string;
  refreshToken: string;
};

export const useAuthStore = create(
  persist(
    (set) => ({
      profileId: null,
      accessToken: "",
      refreshToken: "",

      setProfileId: (profileId: string) => set({ profileId }),
      setAccessToken: (accessToken: string) => set({ accessToken }),
      setRefreshToken: (refreshToken: string) => set({ refreshToken }),

      logout: () =>
        set({
          profileId: null,
          accessToken: "",
          refreshToken: "",
        }),
    }),
    {
      name: "auth",
    }
  )
);
