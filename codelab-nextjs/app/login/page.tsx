"use client";

import React, { useState } from 'react';
import { HeaderComponent } from '@/components/HeaderComponent';
import { FooterComponent } from '@/components/FooterComponent';
import { login } from '@/utils/api';
import { useAuthStore } from '../store/authState';
import Link from 'next/link';
import { useRouter } from 'next/router';


export default function LoginPage() {

	const [isLoading, setIsLoading] = useState(false);
	const [error, setError] = useState('');
	const router = useRouter();
	const { setAccessToken, setRefreshToken, setProfileId } = useAuthStore();

	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		setIsLoading(true);
		setError('');

		try {
			const form = e.currentTarget;
			const mail = form.mail.value;
			const password = form.password.value;
			const response = await login({ mail, password });

			setAccessToken(response.data.accessToken);
			setRefreshToken(response.data.refreshToken);
			setProfileId(response.data.profileId);

			router.push('/menu');
		} catch (error: any) {
			setError('Échec de la connexion. Veuillez réessayer.');
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
					<h1 className="mt-6 text-center text-3xl font-extrabold text-lite-primary dark:text-dark-quaternary">Se connecter</h1>
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
								'Se connecter'
							)}
						</button>
					</form>
					<p className="mt-2 text-center text-sm text-gray-600">
						Vous n'avez pas de compte ? {' '}
						<Link href="/signup" legacyBehavior>
							<a className="font-medium text-blue-600 hover:text-blue-500">
								Créer un compte
							</a>
						</Link>
					</p>
				</div>
			</main>
			<FooterComponent />
		</>
	);
}
