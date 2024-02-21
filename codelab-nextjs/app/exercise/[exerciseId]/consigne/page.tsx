"use client";
import { HeaderComponent } from "@/components/HeaderComponent";
import { useState } from "react";

export default function ConsignePage({ params }: { params: { exerciseId: string } }) {

	const exerciseId = params.exerciseId;

	const [exercice, setExercice] = useState({
		title: "Exercice 1",
		description: "Dans cet exercice, vous allez apprendre à créer une méthode Hello World, vérifier une condition et afficher un message de bienvenue.",
		tasks: ["Créer une méthode Hello World", "Vérifier une condition", "Afficher un message de bienvenue"],
		nbTest: 3,
		finished: false,
		difficulty: "Facile",
		lg: "js",
		langage: "JavaScript",
		id: "aeza1c8",
		author: "Codelab",
		createdAt: "21/02/2024", //todo: la db va sûrement renvoyer un timestamp, la méthode de conversion est disponible dans le fichier menu/page.tsx ;)
	});

	return (
		<div className="w-full h-screen bg-lite-quinary">
			<HeaderComponent />
			<main className="flex flex-row w-full h-full justify-center">
				{/* consignes */}
				<div className="flex flex-col max-w-xl m-6 h-5/6 p-8 justify-between bg-white rounded-xl">
					<div>
						<h2 className="text-2xl font-semibold mt-2 mb-4">Consignes</h2>
						<p>Vous devez écrire un programme qui affiche "Hello World" dans la console.</p>
						<ul className="list-inside text-gray-600 dark:text-gray-400 list-none mt-4">
							{exercice.tasks.map((task: any, index: number) => (
								<li key={index} className="flex items-center"><span className="flex items-center justify-center border w-7 h-7 rounded-full mr-2 my-1">{index + 1}</span> {task}</li>
							))}
						</ul>
						{/* //? tasks = tests unitaire ? */}
					</div>
					<button className=" mx-auto w-full h-14 m-6 bg-primary rounded-xl hover:bg-slate-700 font-semibold text-xl text-white">Commencer l'exercice</button>
				</div>
				{/* conteneur droit */}
				<div className="flex flex-col">
					{/* ce que vous allez apprendre */}
					<div className="flex flex-col max-w-3xl m-6 h-36 p-8 bg-white rounded-xl">
						<h2 className="text-2xl font-semibold mb-2">Ce que vous allez apprendre</h2>
						<p>Vous allez apprendre à créer une méthode Hello World, vérifier une condition et afficher un message de bienvenue.</p>
					</div>
					{/* info + auteur */}
					<div className="flex flex-row mx-auto">
						<div className="flex flex-row items-center w-[375px] mr-5 h-36 p-8 bg-white rounded-xl">
							<img src={`/lg/${exercice.lg}.png`} className="w-24 h-24" alt="Langage" />
							<div className="ml-6 flex flex-col justify-center">
								<div>
									<h2 className="text-2xl font-semibold mt-2">Langage</h2>
									<p>{exercice.langage}</p>
								</div>
								<div>
									<h2 className="text-2xl font-semibold mt-2">Difficulté</h2>
									<p>{exercice.difficulty}</p>
								</div>
							</div>
						</div>
						<div className="flex flex-row items-center w-[375px] h-36 p-8 bg-white rounded-xl">
							<img src={`/lg/outil.png`} className="w-24 h-24" alt="Langage" />
							<div className="ml-6 flex flex-col justify-center">
								<div>
									<h2 className="text-2xl font-semibold mt-2">Auteur</h2>
									<p>{exercice.author}</p>
								</div>
								<div>
									<h2 className="text-2xl font-semibold mt-2">Date de création</h2>
									<p>{exercice.createdAt}</p>
								</div>
							</div>
						</div>
					</div>
					<div className="h-full w-full mt-10">
						<img src="/programmer.png" width={600} alt="Image illustration" />
					</div>
				</div>
			</main>
		</div>
	);
}