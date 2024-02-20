"use client";
import React, { useState } from 'react';
import { CircularProgressbar } from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';
import Link from 'next/link';
import { Search } from 'lucide-react';

export const ExerciseComponent = () => {
	const exercices = [
		{
			title: "Titre js",
			description: "Description js",
			finished: false,
			language: 'js',
			id: "65a855d58f3b59165d67b7f3",
			banner: "https://via.placeholder.com/150x120",
			status: "PENDING",
			lastModified: "2021-10-12T12:00:00Z",
			nbTestTotal: 5,
			nbTestPassed: 3
		},
		{
			title: "Titre java",
			description: "Description java",
			finished: false,
			language: 'java',
			id: "65a855d58f3b59165d67b7f3",
			banner: "https://via.placeholder.com/150x120",
			status: "PENDING",
			lastModified: "2021-10-12T12:00:00Z",
			nbTestTotal: 5,
			nbTestPassed: 3
		},
		{
			title: "Titre python",
			description: "Description python",
			finished: false,
			language: 'python',
			id: "65a855d58f3b59165d67b7f3",
			banner: "https://via.placeholder.com/150x120",
			status: "PENDING",
			lastModified: "2021-10-12T12:00:00Z",
			nbTestTotal: 5,
			nbTestPassed: 3
		},
		{
			title: "Titre ruby",
			description: "Description ruby",
			finished: false,
			language: 'ruby',
			id: "65a855d58f3b59165d67b7f3",
			banner: "https://via.placeholder.com/150x120",
			status: "PENDING",
			lastModified: "2021-10-12T12:00:00Z",
			nbTestTotal: 5,
			nbTestPassed: 3
		},
		{
			title: "Titre ts",
			description: "Description ts",
			finished: false,
			language: 'ts',
			id: "65a855d58f3b59165d67b7f3",
			banner: "https://via.placeholder.com/150x120",
			status: "PENDING",
			lastModified: "2021-10-12T12:00:00Z",
			nbTestTotal: 5,
			nbTestPassed: 3
		},
	]

	const [searchTerm, setSearchTerm] = useState('');
	const [languageFilter, setLanguageFilter] = useState('');
	const [sortCriteria, setSortCriteria] = useState('date'); // Nouveau
	const [sortDirection, setSortDirection] = useState('desc'); // Nouveau
	const [filteredExercises, setFilteredExercises] = useState(exercices);

	const handleSearch = (e: any) => {
		e.preventDefault();
		const filtered = exercices.filter(exercise => {
			return exercise.title.toLowerCase().includes(searchTerm.toLowerCase()) &&
				exercise.language.includes(languageFilter);
		}).sort((a: any, b: any) => {
			switch (sortCriteria) {
				case 'title':
					return sortDirection === 'asc' ? a.title.localeCompare(b.title) : b.title.localeCompare(a.title);
				case 'language':
					return sortDirection === 'asc' ? a.language.localeCompare(b.language) : b.language.localeCompare(a.language);
				case 'date':
				default:
					return sortDirection === 'asc' ? (new Date(a.lastModified) as any) - (new Date(b.lastModified) as any)  : new Date(b.lastModified) as any  - (new Date(a.lastModified) as any);
			}
		});
		setFilteredExercises(filtered);
	};

	return (
		<main className="w-full mw-full">
			<header className='bg-white rounded-xl h-24 flex flex-row items-center justify-center mt-8 w-9/12 mx-auto text-black'>
				<form onSubmit={handleSearch} className="flex flex-row justify-between items-center w-11/12 mx-auto">
					<input type="text" placeholder="Rechercher un exercice" className="border-2 border-gray-200 p-2 w-6/12" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} />
					<select name="language" id="language" className="border-2 border-gray-200 p-2 w-3/12" value={languageFilter} onChange={(e) => setLanguageFilter(e.target.value)}>
						<option value="">Tous les langages</option>
						<option value="js">Javascript</option>
						<option value="java">Java</option>
						<option value="cs">C#</option>
						<option value="cpp">C++</option>
						<option value="ruby">Ruby</option>
					</select>
					{/* Sélecteurs pour critère de tri et direction */}
					<select value={sortCriteria} onChange={(e) => setSortCriteria(e.target.value)} className="border-2 border-gray-200 p-2">
						<option value="date">Date</option>
						<option value="title">Titre</option>
						<option value="language">Langage</option>
					</select>
					<select value={sortDirection} onChange={(e) => setSortDirection(e.target.value)} className="border-2 border-gray-200 p-2">
						<option value="asc">Croissant</option>
						<option value="desc">Décroissant</option>
					</select>
					<button type="submit" className="bg-lite-senary rounded-lg p-2 w-2/12 border flex align-middle justify-center"><Search className='mr-2' />Rechercher</button>
				</form>
			</header>
			<div className='flex flex-col items-center mt-10 h-[55%] m-auto overflow-y-scroll overflow-x-hidden w-9/12'>
				{filteredExercises.map((exercice, index) => (
					<div key={index} className="flex flex-row justify-between border-2 border-gray-200 p-4 bg-white rounded-xl w-full text-black m-1">
						<div className='flex'>
							<img src={exercice.banner} alt="" className='w-56 my-auto h-36 border-2 border-dotted border-black' />
							<div className='ml-6 flex flex-col items-start mt-2'>
								<h4 className='font-semibold text-xl'>{exercice.title}</h4>
								<p>{exercice.description}</p>
								<p><i>Dernière date de modification : {convertISOToDate(exercice.lastModified)}</i></p>
								<div className='flex my-auto'>
									<Link legacyBehavior href={`/exercise/${exercice.id}`}><a className='bg-lite-senary rounded-lg p-2 mr-6 border'>Continuer</a></Link>
									<Link legacyBehavior href={`/exercise/${exercice.id}/consigne`}><a className='bg-lite-senary rounded-lg p-2 mr-6 border'>Voir consignes</a></Link>
								</div>
							</div>
						</div>
						<div className='flex flex-row'>
							<div className='w-40 h-40 text-xs'>
								<CircularProgressbar styles={{ text: { fontSize: "10px" } }} value={exercice.nbTestPassed} maxValue={exercice.nbTestTotal} text={`${exercice.nbTestPassed}/${exercice.nbTestTotal} tests réussi`} />
							</div>
							<img src={`/lg/${exercice.language}.png`} alt="LG logo" className="ml-4 w-10 h-10" />
						</div>
					</div>
				))}
			</div>
		</main>
	);
}

function convertISOToDate(isoString: string) {
	const date = new Date(isoString);
	const day = date.getDate().toString().padStart(2, '0');
	const month = (date.getMonth() + 1).toString().padStart(2, '0');
	const year = date.getFullYear();
	return `${day}/${month}/${year}`;
}
