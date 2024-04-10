import axios from 'axios';
import { Exercise, LanguageItemApi, Languages, SubmitExerciseBody } from "@/utils/types";

const baseUrl = 'http://exercices.codelab.local:81/api/v1';
const API_URL = `${baseUrl}/exercices`;

export const getExerciseDetails = async (exerciseId: string) => {
	try {
		const response = await axios.get(`${API_URL}/${exerciseId}`);
		return response.data;
	} catch (error) {
		console.error(error);
	}
};

export const submitExercise = async (submission: SubmitExerciseBody) => {
	try {
		const response = await axios.post(API_URL, submission);
		return response.data;
	} catch (error) {
		console.error(error);
	}
};

export const getExerciseResults = async (id: string) => {
	try {
		const response = await axios.get(`${API_URL}/${id}/results`);
		return response.data;
	} catch (error) {
		console.error(error);
	}
};

export const getAllLanguages = async () => {
	try {
		const response = await axios.get(`${baseUrl}/languages`);
		return response.data;
	} catch (error) {
		console.error(error);
	}
};

export const getAllExercicesForSupportedLanguages = async (languages: LanguageItemApi[]): Promise<Exercise[]> => {
	const exercices = [];
	try {
		for (let i = 0; i < languages.length; i++) {
			const response = await axios.get<Exercise[]>(`${baseUrl}/languages/exercices?language=${languages[i].name}`);
			exercices.push(...response.data);
		}
	} catch (error) {
		console.error(error);
	}

	return exercices;
}

export const getProfil = async (profilId: string) => {
	try {
		const response = await axios.get(`${baseUrl}/profil/${profilId}`);
		return response.data;
	} catch (error) {
		console.error(error);
	}
}