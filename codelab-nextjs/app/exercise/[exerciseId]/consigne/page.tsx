"use client";
import {HeaderComponent} from "@/components/HeaderComponent";
import {useEffect, useState} from "react";
import {useGetExerciseDetails} from "@/utils/hooks/useGetExerciseDetails";
import {GetExerciseDetailsResponse, Languages} from "@/utils/types";
import Link from "next/link";

export default function ConsignePage({ params }: { params: { exerciseId: string } }) {

	const exerciseId = params.exerciseId;

	const [exercice, setExercice] = useState<GetExerciseDetailsResponse>({
		id: "",
		title: "Chargement...",
		description: "Chargement...",
		instructions: "Chargement...",
		tasks: [],
		banner: "https://via.placeholder.com/150x120",
		author: "Codelab",
		testCode: "",
		language: {
			id: "",
			name: Languages.PYTHON,
			abbreviation: ""
		},
		difficulty: {
			id: "",
			name: ""
		},
		nbTests: 0,
		createdAt: ""
	});


	const { exercise : dataExo } = useGetExerciseDetails(exerciseId);

	useEffect(() => {
		if (dataExo) {
			setExercice(dataExo);
		}
	}, [dataExo]);

	return (
		<div className="w-full h-screen bg-lite-quinary">
			<HeaderComponent />
			<main className="flex flex-row w-full h-full justify-center">
				{/* consignes */}
				<div className="flex flex-col max-w-xl m-6 h-5/6 p-8 justify-between bg-white rounded-xl">
					<div>
						<h2 className="text-2xl font-semibold mt-2 mb-4">Consignes</h2>
						<p>{exercice.instructions}</p>
						<ul className="list-inside text-gray-600 dark:text-gray-400 list-none mt-4">
							{exercice.tasks.map((task, index) => (
								<li key={index} className="flex items-center"><span className="flex items-center justify-center border w-7 h-7 rounded-full mr-2 my-1">{index + 1}</span> {task.content}</li>
							))}
						</ul>
						{/* //? tasks = tests unitaire ? */}
					</div>
					<Link legacyBehavior href={`/exercise/${exercice.id}/ide`}>
						<a
							className=" mx-auto w-full h-14 m-6 bg-primary rounded-xl hover:bg-slate-700 font-semibold text-xl text-white">
							Commencer l'exercice
						</a>
					</Link>
				</div>
				{/* conteneur droit */}
				<div className="flex flex-col">
					{/* info + auteur */}
					<div className="flex flex-row mx-auto p-8">
						<div className="flex flex-row items-center mr-5 h-36 p-8 bg-white rounded-xl">
							<img src={`/lg/${exercice.language.abbreviation.valueOf()}.png`} className="w-24 h-24" alt="Langage" />
							<div className="ml-6 flex flex-col justify-center">
								<div>
									<h2 className="text-2xl font-semibold mt-2">Langage</h2>
									<p>{exercice.language.name}</p>
								</div>
								<div>
									<h2 className="text-2xl font-semibold mt-2">Difficulté</h2>
									<p>{exercice.difficulty.name}</p>
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