"use client";
import React, { useState } from 'react';
import { Exercise, Languages } from '@/utils/types';
import { Sidebar } from '@/components/Sidebar';

export default function MenuCreateExercise() {
	const [exercise, setExercise] = useState<Exercise>({
		id: '',
		title: '',
		description: '',
		instructions: '',
		tasks: [],
		banner: '',
		author: '',
		testCode: '',
		language: {
			id: '',
			name: Languages.PYTHON,
			abbreviation: '',
		},
		difficulty: {
			id: '',
			name: '',
		},
		nbTests: 0,
		createdAt: '',
	});

	const handleInputChange = (e: React.ChangeEvent<any>) => {
		const { name, value } = e.target;

		setExercise((prevExercise) => ({
			...prevExercise,
			[name]: value,
		}));
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
			tasks: [...prevExercise.tasks, { id: '', content: '', order: prevExercise.tasks.length }],
		}));
	};

	const removeTask = (index: number) => {
		setExercise((prevExercise) => ({
			...prevExercise,
			tasks: prevExercise.tasks.filter((_, i) => i !== index),
		}));
	};

	const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		console.log(exercise);

		//TODO : créer l'exercice dans la base de données

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
							<div key={task.id} className="flex items-center mb-2">
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
						<select
							id="language"
							name="language.name"
							value={exercise.language.name}
							onChange={handleInputChange}
							className="w-full border rounded px-3 py-2"
						>
							{Object.values(Languages).map((language) => (
								<option key={language} value={language}>
									{language}
								</option>
							))}
						</select>
					</div>
					<div className="mb-4">
						<label htmlFor="difficulty" className="block font-medium mb-1">
							Difficulté
						</label>
						<input
							type="text"
							id="difficulty"
							name="difficulty.name"
							value={exercise.difficulty.name}
							onChange={handleInputChange}
							className="w-full border rounded px-3 py-2"
						/>
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