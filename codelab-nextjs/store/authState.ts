import { AuthStore } from '@/utils/types';
import {create} from 'zustand';
import { persist } from 'zustand/middleware';

export const useAuthStore = create(
	persist<AuthStore>(
		(set) => ({
			// État initial
			profileId: null,
			accessToken: "",
			refreshToken: "",

			// Actions pour modifier l'état
			setProfileId: (profileId) => set({ profileId }),
			setAccessToken: (accessToken) => set({ accessToken }),
			setRefreshToken: (refreshToken) => set({ refreshToken }),
			logout: () => set({ profileId: null, accessToken: "", refreshToken: "" }),
		}),
		{
			name: "auth", // Nom de la clé sous laquelle l'état persistant est stocké dans le localStorage
		}
	)
);
