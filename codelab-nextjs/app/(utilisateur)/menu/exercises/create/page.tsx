"use client";
import React, { useState } from 'react';
import {CreateExerciseBody, Languages} from '@/utils/types';
import { Sidebar } from '@/components/Sidebar';
import {useCreateExercise} from "@/utils/hooks/useCreateExercise";
import {useGetAllDifficulties} from "@/utils/hooks/useGetAllDifficulties";
import {useGetAllLanguages} from "@/utils/hooks/useGetAllLanguages";

export default function MenuCreateExercise() {
	const [exercise, setExercise] = useState<CreateExerciseBody>
	({
		title: '',
		description: '',
		instructions: '',
		tasks: [],
		banner: '',
		author: '',
		testCode: '',
		language: {
			name: Languages.PYTHON,
			abbreviation: '',
		},
		difficulty: {
			name: '',
		},
		nbTests: 0,
		createdAt: '',
	});

	//const for difficutlies and languages

	const { difficulties, loading: loadingDifficulties, error: errorDifficulties } = useGetAllDifficulties()

	const { languages, loading: loadingLanguages, error: errorLanguages } = useGetAllLanguages()

	const handleInputChange = (e: React.ChangeEvent<any>) => {
		const { name, value } = e.target;

		if (name === 'language') {
			setExercise((prevExercise) => ({
				...prevExercise,
				language: {
					...prevExercise.language,
					name: value,
				},
			}));
		} else if (name === 'difficulty') {
			setExercise((prevExercise) => ({
				...prevExercise,
				difficulty: {
					...prevExercise.difficulty,
					name: value,
				},
			}));
		} else {
			setExercise((prevExercise) => ({
				...prevExercise,
				[name]: value,
			}));
		}
	};

	const handleTaskChange = (index: number, e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		const newTasks = [...exercise.tasks];
		newTasks[index] = { ...newTasks[index], [name]: value };

		setExercise((prevExercise) => ({
			...prevExercise,
			tasks: newTasks,
		}));
	};

	const addTask = () => {
		setExercise((prevExercise) => ({
			...prevExercise,
			tasks: [...prevExercise.tasks, {content: '', order: prevExercise.tasks.length }],
		}));
	};

	const removeTask = (index: number) => {
		setExercise((prevExercise) => ({
			...prevExercise,
			tasks: prevExercise.tasks.filter((_, i) => i !== index),
		}));
	};

	const {
		response: responseCreateExercise,
		loading: loadingCreateExercise,
		error: errorCreateExercise,
		setIsExerciseSubmitted
	} = useCreateExercise(exercise);


	const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();

		exercise.createdAt = new Date().toISOString();

		const foundLanguage = Object.values(languages).find(language => language.name == exercise.language.name);

		if (foundLanguage) {
			exercise.language.abbreviation = foundLanguage.abbreviation;
		} else {
			console.error('Language not found');
			return;
		}

		console.log(exercise);
		setIsExerciseSubmitted(false);

	};

	return (
		<div className="bg-lite-quinary text-black flex overflow-y-hidden overflow-x-hidden">
			<Sidebar selected="Exercices" />
			<main className="w-full m-8 bg-white rounded-xl p-8">
				<h1 className="text-3xl font-bold mb-4">Créer un nouvel exercice</h1>
				<form onSubmit={handleSubmit}>
					<div className="mb-4">
						<label htmlFor="title" className="block font-medium mb-1">
							Titre
						</label>
						<input
							type="text"
							id="title"
							name="title"
							value={exercise.title}
							onChange={handleInputChange}
							className="w-full border rounded px-3 py-2"
						/>
					</div>
					<div className="mb-4">
						<label htmlFor="description" className="block font-medium mb-1">
							Description
						</label>
						<textarea
							id="description"
							name="description"
							value={exercise.description}
							onChange={handleInputChange}
							className="w-full border rounded px-3 py-2"
						/>
					</div>
					<div className="mb-4">
						<label htmlFor="instructions" className="block font-medium mb-1">
							Instructions
						</label>
						<textarea
							id="instructions"
							name="instructions"
							value={exercise.instructions}
							onChange={handleInputChange}
							className="w-full border rounded px-3 py-2"
						/>
					</div>
					<div>
						<label className="block font-medium mb-1">Tâches</label>
						{exercise.tasks.map((task, index) => (
							<div key={task.order} className="flex items-center mb-2">
								<input
									type="text"
									name="content"
									value={task.content}
									onChange={(e) => handleTaskChange(index, e)}
									className="w-full border rounded px-3 py-2 mr-2"
								/>
								<button
									type="button"
									onClick={() => removeTask(index)}
									className="bg-red-500 text-white px-3 py-1 rounded"
								>
									Supprimer
								</button>
							</div>
						))}
						<button
							type="button"
							onClick={addTask}
							className="bg-blue-500 text-white px-3 py-1 rounded mb-4"
						>
							Ajouter une tâche
						</button>
					</div>
					<div className="mb-4">
						<label htmlFor="banner" className="block font-medium mb-1">
							Bannière
						</label>
						<input
							type="text"
							id="banner"
							name="banner"
							value={exercise.banner}
							onChange={handleInputChange}
							className="w-full border rounded px-3 py-2"
						/>
					</div>
					<div className="mb-4">
						<label htmlFor="author" className="block font-medium mb-1">
							Auteur
						</label>
						<input
							type="text"
							id="author"
							name="author"
							value={exercise.author}
							onChange={handleInputChange}
							className="w-full border rounded px-3 py-2"
						/>
					</div>
					<div className="mb-4">
						<label htmlFor="testCode" className="block font-medium mb-1">
							Code de test
						</label>
						<textarea
							id="testCode"
							name="testCode"
							value={exercise.testCode}
							onChange={handleInputChange}
							className="w-full border rounded px-3 py-2"
						/>
					</div>
					<div className="mb-4">
						<label htmlFor="language" className="block font-medium mb-1">
							Langage
						</label>
						<input id="language" name="language" type="text" value={exercise.language.name} onChange={handleInputChange} list="languages" className="w-full border rounded px-3 py-2" />
						<datalist
							id="languages"
							className="w-full border rounded px-3 py-2"
						>
							{Object.values(languages).map((language) => (
								<option key={language.name} value={language.name}>
									{language.name}
								</option>
							))}
						</datalist>
					</div>
					<div className="mb-4">
						<label htmlFor="difficulty" className="block font-medium mb-1">
							Difficulté
						</label>
						<input id="difficulty" name="difficulty" type="text" value={exercise.difficulty.name} onChange={handleInputChange} list="difficulties" className="w-full border rounded px-3 py-2" />
						<datalist
							id="difficulties"
							className="w-full border rounded px-3 py-2"
						>
							{difficulties?.map((difficulty) => (
								<option key={difficulty.id} value={difficulty.name}>
									{difficulty.name}
								</option>
							))}
						</datalist>
					</div>
					<div className="mb-4">
						<label htmlFor="nbTests" className="block font-medium mb-1">
							Nombre de tests
						</label>
						<input
							type="number"
							id="nbTests"
							name="nbTests"
							value={exercise.nbTests}
							onChange={handleInputChange}
							className="w-full border rounded px-3 py-2"
						/>
					</div>
					<button
						type="submit"
						className="bg-dark-primary text-white px-3 py-2 rounded mt-4"
					>
						Créer exercice
					</button>
				</form>
			</main>
		</div>
	);
};