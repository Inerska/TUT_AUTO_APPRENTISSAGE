"use client";

import React, { useState } from 'react';
import { HeaderComponent } from '@/components/HeaderComponent';
import { FooterComponent } from '@/components/FooterComponent';
import { register } from '@/utils/api';
import { useAuthStore } from '../store/authState';
import { useRouter } from 'next/navigation';


export default function SignupPage() {

	const [isLoading, setIsLoading] = useState(false);
	const [error, setError] = useState('');
	const { setAccessToken, setRefreshToken, setProfileId } = useAuthStore();
	const router = useRouter();

	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		setIsLoading(true);
		setError('');

		try {
			const form = e.currentTarget;
			const mail = form.mail.value;
			const username = form.username.value;
			const password = form.password.value;
			const confirmPassword = form.confirmPassword.value;
			if (password !== confirmPassword) {
				setError('Les mots de passe ne correspondent pas.');
				setIsLoading(false);
				return;
			}
			if (password.length < 8) {
				setError('Le mot de passe doit contenir au moins 8 caractères.');
				setIsLoading(false);
				return;
			}

			const response = await register({ mail, username, password, confirmPassword});

			setAccessToken(response.data.accessToken);
			setRefreshToken(response.data.refreshToken);
			setProfileId(response.data.profileId);

			router.push('/menu');
		} catch (error: any) {
			setError("Échec de l'inscrition. Veuillez réessayer.");
			console.error(error);
		} finally {
			setIsLoading(false);
		}
	}

	return (
		<>
			<HeaderComponent />
			<main className="min-h-screen bg-lite-quinary dark:bg-dark-primary flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
				<div className="max-w-md w-full space-y-8 bg-white p-6 rounded-lg shadow-md">
					<h1 className="mt-6 text-center text-3xl font-extrabold text-lite-primary dark:text-dark-quaternary">S'inscrire</h1>
					{error && <div className="text-red-500 text-center">{error}</div>} {/* Affiche l'erreur si présente */}
					<form className="mt-8 space-y-6" onSubmit={handleSubmit}>
						<div className="relative">
							<input
								type="email"
								id="mail"
								name="mail"
								required
								className="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
								placeholder=" "
							/>
							<label
								htmlFor="mail"
								className="absolute left-2 text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
							>
								Email
							</label>
						</div>
						<div className="relative">
							<input
								type="text"
								id="username"
								name="username"
								required
								className="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
								placeholder=" "
							/>
							<label
								htmlFor="username"
								className="absolute left-2 text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
							>
								Nom d'utilisateur
							</label>
						</div>
						<div className="relative">
							<input
								type="password"
								id="password"
								name="password"
								required
								className="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
								placeholder=" "
							/>
							<label
								htmlFor="password"
								className="absolute left-2 text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
							>
								Mot de passe
							</label>
						</div>
						<div className="relative">
							<input
								type="password"
								id="confirmPassword"
								name="confirmPassword"
								required
								className="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
								placeholder=" "
							/>
							<label
								htmlFor="confirmPassword"
								className="absolute left-2 text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4"
							>
								Confirmer le mot de passe
							</label>
						</div>
						<button
							type="submit"
							disabled={isLoading}
							className="duration-300 group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-lite-primary hover:bg-lite-secondary focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-lite-primary dark:bg-dark-secondary dark:hover:bg-dark-primary"
						>
							{isLoading ? (
								<div className="spinner-border animate-spin inline-block w-4 h-4 border-2 rounded-full" role="status">
									<span className="sr-only">Chargement...</span>
								</div>
							) : (
								"S'inscrire"
							)}
						</button>
					</form>
				</div>
			</main>
			<FooterComponent />
		</>
	);
}
