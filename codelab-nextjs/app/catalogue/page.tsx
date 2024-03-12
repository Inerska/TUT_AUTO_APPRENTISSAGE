"use client";
import {FooterComponent} from "@/components/FooterComponent";
import {HeaderComponent} from "@/components/HeaderComponent";
import {Search} from "lucide-react";
import Link from "next/link";
import {useEffect, useState} from "react";
import {useGetAllLanguages} from "@/utils/hooks/useGetAllLanguages";
import {GetExerciseDetailsResponse, LanguageAbbreviations, LanguageItemApi, Languages} from "@/utils/types";
import {useGetAllExercicesForSupportedLanguages} from "@/utils/hooks/useGetAllExercicesForSupportedLanguages";

export default function CataloguePage() {

	const [searchTerm, setSearchTerm] = useState('');
    const [sortDirection, setSortDirection] = useState('desc'); // Nouveau
    const [languageFilter, setLanguageFilter] = useState('');

    const [exercices, setExercises] = useState<GetExerciseDetailsResponse[]>([]); //List exos

    const [languagesList, setLanguagesList] = useState<LanguageItemApi[]>([]);

    const handleSearch = (e: any) => {
        e.preventDefault();
        const filtered = exercices.filter(exercise => {
            return exercise.title.toLowerCase().includes(searchTerm.toLowerCase()) &&
                exercise.language.includes(languageFilter);
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

	const {exercises} = useGetAllExercicesForSupportedLanguages(languagesList)

	useEffect(() => {
		if (exercises) {
			console.log(exercises);
			setExercises(exercises);
		}
	}, [exercises]);

	return (
		<div className="flex flex-col min-h-screen">
			<HeaderComponent />
			<main className="w-full h-screen bg-lite-quinary">
				{/* barre de recherche */}
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
						<button type="submit" className="bg-lite-senary rounded-lg p-2 w-2/12 border flex align-middle justify-center"><Search className='mr-2' />Rechercher</button>
					</form>
				</header>
				{/* conteneur */}
				<div className="mt-10 h-[55%] justify-items-center">
					<div className="flex flex-wrap justify-center gap-4 mt-10">
						{exercices.map((exercise) => (
							<div key={exercise.id} className="flex flex-row justify-between border-2 border-gray-200 p-4 bg-white rounded-xl text-black m-1 w-6/12 md:w-4/12">
								<div className="flex">
									<img src={`/lg/${LanguageAbbreviations[exercise.language]}.png`} alt={`${exercise.language.valueOf()} logo`} className="w-24 h-24" />
									<div className="ml-6 flex flex-col justify-between">
										<h4 className="font-semibold text-xl">{exercise.title}</h4>
										<p>{exercise.description} - <i>Proposé par {exercise.author}</i></p>
										<Link legacyBehavior href={`/exercise/${exercise.id}/consigne`}>
											<a className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-lg mt-4 w-36">
												En savoir plus
											</a>
										</Link>
									</div>
								</div>
							</div>
						))}
					</div>
				</div>

            </main>
            <FooterComponent/>
        </div>
    )
}