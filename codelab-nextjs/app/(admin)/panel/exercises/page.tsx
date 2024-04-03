"use client";
import { AdminSideBar } from "@/components/AdminSidebar";
import { FooterComponent } from "@/components/FooterComponent";
import { HeaderComponent } from "@/components/HeaderComponent";
import { Exercise } from "@/utils/types";

export default function PanelExercises() {
	const exercises = [
		{
			id: "1",
			author: "codelab",
			title: "Exercice 1",
			description: "Description de l'exercice 1",
			instructions: "Instructions de l'exercice 1",
			banner: "https://via.placeholder.com/150x120",
			createdAt: "2021-09-01",
			testCode: "Test code de l'exercice 1",
			language: {
				id: "1",
				abbreviation: "py"
			},
			difficulty: {
				id: "1",
				name: "Facile"
			},
			nbTests: 5,
			tasks: [
				{
					id: "1",
					content: "Task 1",
					order: 1
				},
				{
					id: "2",
					content: "Task 2",
					order: 2
				}
			]
		},
		{
			id: "2",
			author: "codelab",
			title: "Exercice 2",
			description: "Description de l'exercice 2",
			instructions: "Instructions de l'exercice 2",
			banner: "https://via.placeholder.com/150x120",
			createdAt: "2021-09-02",
			testCode: "Test code de l'exercice 2",
			language: {
				id: "2",
				abbreviation: "js"
			},
			difficulty: {
				id: "2",
				name: "Moyen"
			},
			nbTests: 5,
			tasks: [
				{
					id: "1",
					content: "Task 1",
					order: 1
				},
				{
					id: "2",
					content: "Task 2",
					order: 2
				}
			],
		},
		{
			id: "3",
			author: "codelab",
			title: "Exercice 3",
			description: "Description de l'exercice 3",
			instructions: "Instructions de l'exercice 3",
			banner: "https://via.placeholder.com/150x120",
			createdAt: "2021-09-03",
			testCode: "Test code de l'exercice 3",
			language: {
				id: "3",
				abbreviation: "java"
			},
			difficulty: {
				id: "3",
				name: "Difficile"
			},
			nbTests: 5,
			tasks: [
				{
					id: "1",
					content: "Task 1",
					order: 1
				},
				{
					id: "2",
					content: "Task 2",
					order: 2
				}
			]
		},
		{
			id: "4",
			author: "codelab",
			title: "Exercice 4",
			description: "Description de l'exercice 4",
			instructions: "Instructions de l'exercice 4",
			banner: "https://via.placeholder.com/150x120",
			createdAt: "2021-09-04",
			testCode: "Test code de l'exercice 4",
			language: {
				id: "4",
				abbreviation: "ts"
			},
			difficulty: {
				id: "1",
				name: "Facile"
			},
			nbTests: 5,
			tasks: [
				{
					id: "1",
					content: "Task 1",
					order: 1
				},
				{
					id: "2",
					content: "Task 2",
					order: 2
				}
			]
		},
		{
			id: "5",
			author: "codelab",
			title: "Exercice 5",
			description: "Description de l'exercice 5",
			instructions: "Instructions de l'exercice 5",
			banner: "https://via.placeholder.com/150x120",
			createdAt: "2021-09-05",
			testCode: "Test code de l'exercice 5",
			language: {
				id: "5",
				abbreviation: "py"
			},
			difficulty: {
				id: "2",
				name: "Moyen"
			},
			nbTests: 5,
			tasks: [
				{
					id: "1",
					content: "Task 1",
					order: 1
				},
				{
					id: "2",
					content: "Task 2",
					order: 2
				}
			]
		}
	] as Exercise[]

	const handleDelete = (id: string) => {
		console.log("Delete user with id", id)
	}

	return (
		<div className="bg-lite-quinary text-dark-quaternary flex justify-centeroverflow-y-hidden overflow-x-hidden">
			<AdminSideBar selected="Exercices" />
			<section className="text-gray-600 body-font w-full flex justify-center">
				<div className="relative overflow-x-auto  sm:rounded-lg">
					<table className="w-full text-sm text-left rtl:text-right text-gray-500 mt-4">
						<thead className="text-xs text-gray-700 uppercase bg-gray-50">
							<tr>
								<th scope="col" className="px-6 py-3">
									ID
								</th>
								<th scope="col" className="px-6 py-3">
									Titre
								</th>
								<th scope="col" className="px-6 py-3">
									Auteur
								</th>
								<th scope="col" className="px-6 py-3">
									Langage
								</th>
								<th scope="col" className="px-6 py-3">
									Difficulté
								</th>
								<th scope="col" className="px-6 py-3">
									Nb. de tâches
								</th>
								<th scope="col" className="px-6 py-3">
									Nb. de tests
								</th>
								<th scope="col" className="px-6 py-3">
									Date de création
								</th>

								<th scope="col" className="px-6 py-3">
									<span className="sr-only text-red-600">Action rapide</span>
								</th>
							</tr>
						</thead>
						<tbody>
							{exercises.map((exercise) => (
								<tr key={exercise.id} className="bg-gray-50 border-b odd:bg-white hover:bg-gray-100 ">
									<td className="px-6 py-4">
										{exercise.id}
									</td>
									<td className="px-6 py-4 font-medium text-gray-900 ">
										{exercise.title}
									</td>
									<td className="px-6 py-4">
										{exercise.author}
									</td>
									<td className="px-6 py-4">
										{exercise.language.abbreviation}
									</td>
									<td className="px-6 py-4">
										{exercise.difficulty.name}
									</td>
									<td className="px-6 py-4">
										{exercise.tasks.length}
									</td>
									<td className="px-6 py-4">
										{exercise.nbTests}
									</td>
									<td className="px-6 py-4">
										{exercise.createdAt}
									</td>
									<td className="px-6 py-4 text-right">
										<a href={`/exercise/${exercise.id}/consigne`} target="_blank" onClick={() => handleDelete(exercise.id)} className="font-medium text-blue-600  hover:underline">Voir</a>
										<button onClick={() => handleDelete(exercise.id)} className="ml-4 font-medium text-red-600  hover:underline">Supprimer</button>
									</td>
								</tr>
							))}
						</tbody>
					</table>
				</div>
			</section>
		</div>
	)
}