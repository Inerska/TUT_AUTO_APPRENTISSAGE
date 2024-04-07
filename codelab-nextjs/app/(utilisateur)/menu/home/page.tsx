"use client";
import 'react-circular-progressbar/dist/styles.css';
import { BarChart2, CheckCircle2, TestTube2Icon, LucideLanguages } from 'lucide-react';
import { StatsItemProps } from '@/utils/types';
import { ChartComponent } from '@/components/ChartComponent';
import { LanguagesComponent } from '@/components/LanguagesComponent';
import { StatsComponent } from '@/components/StatsComponent';
import { Sidebar } from '@/components/Sidebar';
export default function MenuPage() {

	const stats = [
		{
			icon: BarChart2,
			label: "Exercice(s) démarré",
			value: "16"
		},
		{
			icon: CheckCircle2,
			label: "Exercice(s) terminé",
			value: "10"
		},
		{
			icon: TestTube2Icon,
			label: "Test(s) réussi",
			value: "30"
		},
		{
			icon: LucideLanguages,
			label: "Langage(s) exercé",
			value: "2"
		},
	] as StatsItemProps[];
	const exercices = [
		{
			title: "Titre exercice 1",
			description: "Description de l'exercice 1",
			finished: false,
			lang: 'js'
		},
		{
			title: "Titre exercice 2",
			description: "Description de l'exercice 2",
			finished: true,
			lang: 'java'
		},
		{
			title: "Titre exercice 3",
			description: "Description de l'exercice 3",
			finished: false,
			lang: 'cs'
		},
		{
			title: "Titre exercice 4",
			description: "Description de l'exercice 4",
			finished: false,
			lang: 'cpp'
		},
		{
			title: "Titre exercice 5",
			description: "Description de l'exercice 5",
			finished: false,
			lang: 'ruby'
		},
		{
			title: "Titre exercice 6",
			description: "Description de l'exercice 6",
			finished: false,
			lang: 'python'
		},
	]
	const langages = [
		{
			placeholder: "Javascript",
			icon: "/lg/js.png",
			nbExercices: 6
		},
		{
			placeholder: "Python",
			icon: "/lg/py.png",
			nbExercices: 3
		},
		{
			placeholder: "Java",
			icon: "/lg/java.png",
			nbExercices: 2
		},
		{
			placeholder: "C#",
			icon: "/lg/cs.png",
			nbExercices: 5
		},
		{
			placeholder: "C++",
			icon: "/lg/cpp.png",
			nbExercices: 2
		},
		{
			placeholder: "Ruby",
			icon: "/lg/rb.png",
			nbExercices: 3
		},
		{
			placeholder: "Go",
			icon: "/lg/go.png",
			nbExercices: 8
		},
		{
			placeholder: "Typescript",
			icon: "/lg/ts.png",
			nbExercices: 1
		},
	]

	return (
		<div className="bg-lite-quinary text-dark-quaternary flex overflow-y-hidden overflow-x-hidden">
			{/* Sidebar */}
			<Sidebar selected="Accueil" admin/>

			{/* Main Content */}
			<div className="flex-grow flex flex-col m-0">
				{/* header */}
				<header className='bg-dark-quaternary h-20 min-h-20 flex flex-row justify-between items-center'>
					<p className='text-gray-400 font-light text-xl ml-6'>Bienvenue, <span className='text-lite-primary font-semibold'>Nom d'utilisateur</span></p>
					<div className='mr-3 rounded-full w-16 h-16 flex justify-center align-middle border-2 border-black overflow-hidden'>
						<img src="/next.svg" alt="Avatar" />
					</div>
				</header>
				<main className='grid grid-cols-3 w-full h-full'>
					{/* conteneur de gauche */}
					<div className='col-span-2 h-full w-full ml-6'>
						{/* statistiques */}
						<StatsComponent stats={stats} profile={false} />
						<div className='grid grid-cols-2 my-9'>
							{/* graphique */}
							<ChartComponent langages={langages} />
							{/* partie rapide */}
							<div className='flex flex-col items-center bg-white rounded-xl h-5/12 ml-5 w-auto h-full no-user-select'>
								<img src="/illustration.avif" alt="Image d'illustration" className='w-96 object-contain' />
							</div>
						</div>
					</div>

					{/* conteneur de droite */}
					<div>
						{/* voir les cours */}
						<div className='flex flex-col items-center bg-dark-quaternary h-7/12 text-black w-10/12 m-auto mt-8 rounded-xl p-1 pb-4' >
							{/* header */}
							<header className='flex flex-row justify-between items-center w-full my-4'>
								<div className='ml-6'>
									<h3 className='text-xl font-semibold'>Exercices</h3>
									<p className='text-sm'>Vos deniers exercices</p> {/* max 6 */}
								</div>
								<button className='bg-lite-senary rounded-lg p-2 mr-6'>Voir plus</button>
							</header>
							{/* liste des cours */}
							<div className='flex flex-col items-center w-11/12 overflow-y-auto overflow-x-hidden'>
								{!exercices && <p>Vous n'avez pas encore lancé d'exercices</p>}
								{exercices && exercices.map((exercice, index) => (
									<div key={index} className='flex flex-row items-center w-full justify-between even:bg-slate-100 p-2 border border-gray-200'>
										<div className='flex flex-row items-center'>
											<img src={`/lg/${exercice.lang}.png`} alt="LG logo" className='w-10 h-10' />
											<div className='ml-2'>
												<h4>{exercice.title}</h4>
												<p>{exercice.description}</p>
											</div>
										</div>
										{exercice.finished ? <span>Terminé</span> : <span>En cours</span>}
									</div>
								))}
							</div>
						</div>
						<LanguagesComponent langages={langages} />
					</div>
				</main>
			</div>
		</div >
	);
}
