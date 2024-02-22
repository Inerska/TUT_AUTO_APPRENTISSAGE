"use client";
import {FooterComponent} from "@/components/FooterComponent";
import {HeaderComponent} from "@/components/HeaderComponent";
import {Search} from "lucide-react";
import Link from "next/link";
import {useState} from "react";
import {getAllExercicesForSupportedLanguages, getAllLanguages} from "@/service/apiServiceExercise";

export default function CataloguePage() {
	const exercices = [
		{
			title: "Titre ts",
			description: "Description js",
			language: 'ts',
			id: "65a855d5e8f3b59165d67b7f3",
			banner: "https://via.placeholder.com/150x120",
			nbTestTotal: 5,
			author: "Codelab"
		},
		{
			title: "Titre python",
			description: "Description js",
			language: 'python',
			id: "65a855d58f3b59165d67erb7f3",
			banner: "https://via.placeholder.com/150x120",
			nbTestTotal: 5,
			author: "Codelab"
		},
		{
			title: "Titre java",
			description: "Description js",
			language: 'java',
			id: "65a855d58f3b59165d67zrb7f3",
			banner: "https://via.placeholder.com/150x120",
			nbTestTotal: 5,
			author: "Codelab"
		},
		{
			title: "Titre go",
			description: "Description js",
			language: 'go',
			id: "65a855d58f3b59165d6a7b7f3",
			banner: "https://via.placeholder.com/150x120",
			nbTestTotal: 5,
			author: "Codelab"
		},
		{
			title: "Titre C++",
			description: "Description js",
			language: 'cpp',
			id: "65a855d58f3b59165d6ee7b7f3",
			banner: "https://via.placeholder.com/150x120",
			nbTestTotal: 5,
			author: "Codelab"
		},
		{
			title: "Titre C#",
			description: "Description js",
			language: 'cs',
			id: "65a855da58f3b59165d6ee7b7f3",
			banner: "https://via.placeholder.com/150x120",
			nbTestTotal: 5,
			author: "Codelab"
		},
	]

    const [searchTerm, setSearchTerm] = useState('');
    const [sortDirection, setSortDirection] = useState('desc'); // Nouveau
    const [languageFilter, setLanguageFilter] = useState('');

    const [filteredExercises, setFilteredExercises] = useState(exercices); //List exos

    const [languagesList, setLanguagesList] = useState([]);

    const handleSearch = (e: any) => {
        e.preventDefault();
        const filtered = exercices.filter(exercise => {
            return exercise.title.toLowerCase().includes(searchTerm.toLowerCase()) &&
                exercise.language.includes(languageFilter);
        }).sort((a: any, b: any) => {
            return sortDirection === 'asc' ? a.language.localeCompare(b.language) : b.language.localeCompare(a.language);
        });
        setFilteredExercises(filtered);
    };

    if(languagesList.length === 0) { //One time set to prevent api call spam | can be changed to update once every x seconds
        getAllLanguages().then(languages => {
            console.log(languages);
            setLanguagesList(languages);
            getAllExercicesForSupportedLanguages(languages).then(exercices => {
                console.log(exercices);
            }).catch(err => {
                console.error(err);
            });
        }).catch(err => {
            console.error(err);
        });
    }

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
						{filteredExercises.map((exercise) => (
							<div key={exercise.id} className="flex flex-row justify-between border-2 border-gray-200 p-4 bg-white rounded-xl text-black m-1 w-6/12 md:w-4/12">
								<div className="flex">
									<img src={`/lg/${exercise.language}.png`} alt={`${exercise.language} logo`} className="w-24 h-24" />
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