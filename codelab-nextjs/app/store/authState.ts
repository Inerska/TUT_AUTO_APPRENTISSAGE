import { AuthState, AuthStore } from "@/utils/types";
import { create } from "zustand";
import { persist } from "zustand/middleware";


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
        } as AuthState),
    } as AuthStore),
    {
      name: "auth",
    }
  )
);
