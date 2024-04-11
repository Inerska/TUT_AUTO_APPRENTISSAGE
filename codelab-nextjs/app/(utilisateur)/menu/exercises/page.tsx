"use client";
import { useEffect, useState } from 'react';
import Link from 'next/link';
import { CircularProgressbar } from 'react-circular-progressbar';
import { Search } from 'lucide-react';
import { Sidebar } from '@/components/Sidebar';
import { useGetAllLanguages } from "@/utils/hooks/useGetAllLanguages";
import { useGetAllExercicesForSupportedLanguages } from "@/utils/hooks/useGetAllExercicesForSupportedLanguages";
import { Exercise, LanguageItemApi } from "@/utils/types";

export default function MenuExercisesPage() {
	const [searchTerm, setSearchTerm] = useState('');
	const [sortDirection, setSortDirection] = useState('desc');
	const [languageFilter, setLanguageFilter] = useState('');

	const [exercices, setExercises] = useState<Exercise[]>([]); //List exos

	const [languagesList, setLanguagesList] = useState<LanguageItemApi[]>([]);

	const handleSearch = (e: any) => {
		e.preventDefault();
		const filtered = exercices.filter(exercise => {
			return exercise.title.toLowerCase().includes(searchTerm.toLowerCase()) &&
				exercise.language.name.includes(languageFilter);
		}).sort((a: any, b: any) => {
			return sortDirection === 'asc' ? a.language.localeCompare(b.language) : b.language.localeCompare(a.language);
		});
		setExercises(filtered);
	};

	const { languages } = useGetAllLanguages();

	useEffect(() => {
		if (languages) {
			console.log(languages);
			setLanguagesList(languages);
		}
	}, [languages]);

	const { exercises } = useGetAllExercicesForSupportedLanguages(languagesList)

	useEffect(() => {
		if (exercises) {
			console.log(exercises);
			setExercises(exercises);
		}
	}, [exercises]);

	return (
		<div className="bg-lite-quinary text-dark-quaternary flex overflow-y-hidden overflow-x-hidden">
			<Sidebar selected="Exercices" />
			<main className="w-full">
				<header className='bg-white rounded-xl h-24 flex flex-row items-center justify-center mt-8 w-9/12 mx-auto text-black'>
					<form onSubmit={handleSearch} className="flex flex-row justify-between items-center w-11/12 mx-auto">
						<input type="text" placeholder="Rechercher un exercice" className="border-2 border-gray-200 p-2 w-6/12" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} />
						<select name="language" id="language" className="border-2 border-gray-200 p-2 w-3/12" value={languageFilter} onChange={(e) => setLanguageFilter(e.target.value)}>
							<option value="">Tous les langages</option>
							{languagesList.map((language: any) => (
								<option key={language.abbreviation}
										value={language.abbreviation}
								>
									{language.name}
								</option>
							))}
						</select>
						{/* Sélecteurs pour critère de tri et direction */}
						<select value={sortDirection} onChange={(e) => setSortDirection(e.target.value)} className="border-2 border-gray-200 p-2">
							<option value="asc">Croissant</option>
							<option value="desc">Décroissant</option>
						</select>
						<button type="submit" className="bg-lite-senary rounded-lg p-2 w-2/12 border flex align-middle justify-center"><Search className='mr-2' />Rechercher
                        </button>
                    </form>
                </header>
                <section
                    className='h-12 flex flex-row items-center justify-center mt-4 w-9/12 mx-auto text-white gap-4'>
                    <Link href={"/menu/exercises/create"}
                          className='bg-lite-secondary rounded-lg p-2 w-2/12 border text-center'>Créer un
                        exercice</Link>
                    <Link href={"/menu/exercises/owns"}
                          className='bg-lite-secondary rounded-lg p-2 w-2/12 border text-center'>Gérez mes
                        exercices</Link>
                </section>
                <div
                    className='flex flex-col items-center mt-10 h-[55%] m-auto overflow-y-scroll overflow-x-hidden w-9/12'>
                    {exercices.map((exercise, index) => (<div key={index}
                                                                      className="flex flex-row justify-between border-2 border-gray-200 p-4 bg-white rounded-xl w-full text-black m-1">
                            <div className='flex'>
                                <img src={exercise.banner} alt=""
                                     className='w-56 my-auto h-36 border-2 border-dotted border-black'/>
                                <div className='ml-6 flex flex-col items-start mt-2'>
                                    <h4 className='font-semibold text-xl'>{exercise.title}</h4>
                                    <p>{exercise.description}</p>
                                    <p><i>Dernière date de modification : {convertISOToDate(exercise.lastModified)}</i>
                                    </p>
                                    <div className='flex my-auto'>
                                        <Link legacyBehavior href={`/exercise/${exercise.id}/ide`}
                                              className='bg-lite-senary rounded-lg p-2 mr-6 border text-center'>Continuer</Link>
                                        <Link legacyBehavior href={`/exercise/${exercise.id}/consigne`}
                                              className='bg-lite-senary rounded-lg p-2 mr-6 border'>Voir
                                            consignes</Link>
                                    </div>
                                </div>
                            </div>
                            <div className='flex flex-row'>
                                <div className='w-40 h-40 text-xs'>
                                    <CircularProgressbar styles={{text: {fontSize: "10px"}}}
                                                         value={exercise.nbTestPassed} maxValue={exercise.nbTestTotal}
                                                         text={`${exercise.nbTestPassed}/${exercise.nbTestTotal} tests réussi`}/>
                                </div>
                                <img src={`/lg/${exercise.language}.png`} alt="LG logo" className="ml-4 w-10 h-10"/>
                            </div>
                        </div>))}
                </div>
            </main>
        </div>);
}

function convertISOToDate(isoString: string) {
    const date = new Date(isoString);
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}