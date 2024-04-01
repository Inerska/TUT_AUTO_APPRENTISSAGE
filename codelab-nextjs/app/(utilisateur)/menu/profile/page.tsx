"use client";
import { Sidebar } from "@/components/Sidebar";
import { useState } from "react";

export default function MenuPage() {
	const ressources = [
		{
			nom: "Microsoft Docs - C#",
			url: "https://docs.microsoft.com/en-us/dotnet/csharp/",
			description: "La documentation officielle de Microsoft pour le C#, avec des tutoriels, des guides de démarrage rapide et une référence de langage.",
			type: "C#",
			category: "cs"
		},
		{
			nom: "Go by Example",
			url: "https://gobyexample.com/",
			description: "Un site qui fournit des exemples pratiques pour apprendre Go, couvrant les bases jusqu'aux concepts avancés.",
			type: "Go",
			category: "go"
		},
		{
			nom: "Java Programming Tutorials by MOOC.fi",
			url: "https://www.mooc.fi/en/",
			description: "Cours en ligne gratuits pour apprendre Java, proposés par l'Université d'Helsinki.",
			type: "Java",
			category: "java"
		},
		{
			nom: "JavaScript.info",
			url: "https://javascript.info/",
			description: "Un site moderne de tutoriels JavaScript pour apprendre et maîtriser JavaScript, du débutant à l'avancé.",
			type: "JavaScript",
			category: "js"
		},
		{
			nom: "Real Python",
			url: "https://realpython.com/",
			description: "Tutoriels, articles, et ressources pour tous les niveaux de compétence en Python.",
			type: "Python",
			category: "python"
		},
		{
			nom: "Ruby on Rails Guides",
			url: "https://guides.rubyonrails.org/",
			description: "Guides complets pour apprendre Ruby on Rails, le framework web écrit en Ruby.",
			type: "Ruby",
			category: "ruby"
		},
		{
			nom: "TypeScript Documentation",
			url: "https://www.typescriptlang.org/docs/",
			description: "La documentation officielle pour TypeScript, offrant des guides pour débutants et avancés.",
			type: "TypeScript",
			category: "ts"
		},
		{
			nom: "CppReference",
			url: "https://en.cppreference.com/",
			description: "Un site de référence pour les programmeurs C++ offrant une documentation complète sur le langage.",
			type: "C++",
			category: "cpp"
		},
		{
			nom: "Microsoft Docs - C#",
			url: "https://docs.microsoft.com/en-us/dotnet/csharp/",
			description: "La documentation officielle de Microsoft pour le C#, avec des tutoriels, des guides de démarrage rapide et une référence de langage.",
			type: "C#",
			category: "cs"
		},
		{
			nom: "Go by Example",
			url: "https://gobyexample.com/",
			description: "Un site qui fournit des exemples pratiques pour apprendre Go, couvrant les bases jusqu'aux concepts avancés.",
			type: "Go",
			category: "go"
		},
		{
			nom: "Java Programming Tutorials by MOOC.fi",
			url: "https://www.mooc.fi/en/",
			description: "Cours en ligne gratuits pour apprendre Java, proposés par l'Université d'Helsinki.",
			type: "Java",
			category: "java"
		},
		{
			nom: "JavaScript.info",
			url: "https://javascript.info/",
			description: "Un site moderne de tutoriels JavaScript pour apprendre et maîtriser JavaScript, du débutant à l'avancé.",
			type: "JavaScript",
			category: "js"
		},
		{
			nom: "Ruby on Rails Guides",
			url: "https://guides.rubyonrails.org/",
			description: "Guides complets pour apprendre Ruby on Rails, le framework web écrit en Ruby.",
			type: "Ruby",
			category: "ruby"
		},
		{
			nom: "TypeScript Documentation",
			url: "https://www.typescriptlang.org/docs/",
			description: "La documentation officielle pour TypeScript, offrant des guides pour débutants et avancés.",
			type: "TypeScript",
			category: "ts"
		},
		{
			nom: "LearnCpp",
			url: "https://www.learncpp.com/",
			description: "Un site web dédié à l'enseignement de C++ aux débutants comme aux programmeurs expérimentés.",
			type: "C++",
			category: "cpp"
		},
		{
			nom: "C# Corner",
			url: "https://www.c-sharpcorner.com/",
			description: "Une communauté de développement logiciel offrant des articles, des forums, et des tutoriels sur C# et les technologies associées.",
			type: "C#",
			category: "cs"
		},
		{
			nom: "Awesome Go",
			url: "https://awesome-go.com/",
			description: "Une liste impressionnante de frameworks, bibliothèques et logiciels écrits en Go.",
			type: "Go",
			category: "go"
		},
		{
			nom: "Apache Maven",
			url: "https://maven.apache.org/",
			description: "Un outil de gestion et de compréhension de projets logiciels Java.",
			type: "Java",
			category: "java"
		},
		{
			nom: "Eloquent JavaScript",
			url: "https://eloquentjavascript.net/",
			description: "Un livre en ligne interactif pour apprendre JavaScript.",
			type: "JavaScript",
			category: "js"
		},
		{
			nom: "PyPI (Python Package Index)",
			url: "https://pypi.org/",
			description: "Le répertoire officiel des logiciels pour le langage de programmation Python.",
			type: "Python",
			category: "python"
		},
		{
			nom: "RubyGems",
			url: "https://rubygems.org/",
			description: "Le système de gestion de packages de Ruby, qui permet aux programmeurs de distribuer et d'installer des packages Ruby.",
			type: "Ruby",
			category: "ruby"
		},
		{
			nom: "DefinitelyTyped",
			url: "https://definitelytyped.org/",
			description: "Un dépôt de fichiers de déclaration TypeScript de haute qualité pour de nombreuses bibliothèques JavaScript et projets Node.js.",
			type: "TypeScript",
			category: "ts"
		},
		{
			nom: "Node.js Official Website",
			url: "https://nodejs.org/",
			description: "Le site officiel de Node.js, offrant des ressources pour apprendre et utiliser Node.js, un environnement d'exécution JavaScript côté serveur.",
			type: "JavaScript",
			category: "js"
		},
		{
			nom: "npm Official Website",
			url: "https://www.npmjs.com/",
			description: "Le site officiel de npm, le gestionnaire de paquets pour JavaScript, permettant de partager et d'utiliser des modules Node.js.",
			type: "JavaScript",
			category: "js"
		},
		{
			nom: "Understanding Git Flow",
			url: "https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow",
			description: "Un guide d'Atlassian expliquant le workflow Git Flow, une méthode de travail avec Git qui aide les équipes à collaborer efficacement.",
			type: "Documentation",
			category: "documentation"
		},
		{
			nom: "Conventional Commits",
			url: "https://www.conventionalcommits.org/",
			description: "Une spécification pour les messages de commit, permettant une gestion facile du versionnement sémantique et une génération de CHANGELOG automatisée.",
			type: "Documentation",
			category: "documentation"
		},
		{
			nom: "Docker",
			url: "https://www.docker.com/",
			description: "Une plateforme de conteneurisation qui permet de simplifier le déploiement d'applications en les empaquetant avec leurs dépendances.",
			type: "Outil",
			category: "outil"
		},
		{
			nom: "GitHub",
			url: "https://github.com/",
			description: "Une plateforme de gestion de code source et de collaboration qui utilise Git. Elle est essentielle pour le travail d'équipe, le versionnement et le CI/CD.",
			type: "Outil",
			category: "outil"
		},
		{
			nom: "Jenkins",
			url: "https://www.jenkins.io/",
			description: "Un outil d'intégration continue et de déploiement continu, permettant aux développeurs de tester et déployer leurs applications automatiquement.",
			type: "Outil",
			category: "outil"
		},
		{
			nom: "Stack Overflow",
			url: "https://stackoverflow.com/",
			description: "Une plateforme de questions et réponses pour les développeurs, offrant une vaste source de solutions à des problèmes de programmation spécifiques.",
			type: "Communauté",
			category: "communaute"
		},
		{
			nom: "freeCodeCamp",
			url: "https://www.freecodecamp.org/",
			description: "Une organisation à but non lucratif qui offre des cours gratuits de codage pour apprendre le développement web et d'autres disciplines de programmation.",
			type: "Éducation",
			category: "education"
		},
		{
			nom: "OWASP",
			url: "https://www.owasp.org/",
			description: "Une fondation travaillant à améliorer la sécurité des logiciels à travers le monde, offrant des ressources et des outils pour sécuriser les applications web.",
			type: "Sécurité",
			category: "securite"
		},
		{
			nom: "Google Lighthouse",
			url: "https://developers.google.com/web/tools/lighthouse",
			description: "Un outil automatisé de Google pour améliorer la qualité des pages web, couvrant des aspects comme la performance, l'accessibilité, et les bonnes pratiques SEO.",
			type: "Web",
			category: "web"
		},
		{
			nom: "Semantic Versioning",
			url: "https://semver.org/",
			description: "Un site web dédié à l'explication et à l'adoption de Semantic Versioning, un système de versionnement visant à donner du sens aux numéros de version des logiciels.",
			type: "Documentation",
			category: "documentation"
		}
	]

	const [searchTerm, setSearchTerm] = useState('');
	const [filteredRessources, setFilteredRessources] = useState(ressources);

	const handleSearch = (e: any) => {
		e.preventDefault();
		const filtered = ressources.filter(ressource => {
			return ressource.nom.toLowerCase().includes(searchTerm.toLowerCase()) ||
				ressource.description.toLowerCase().includes(searchTerm.toLowerCase()) ||
				ressource.type.toLowerCase().includes(searchTerm.toLowerCase());
		});
		setFilteredRessources(filtered);
	};
	return (
		<div className="bg-lite-quinary text-dark-quaternary flex overflow-y-hidden overflow-x-hidden">
			<Sidebar selected="Ressources" />
			<main className="w-full mw-full">
				<header className="bg-white rounded-xl h-24 flex flex-col items-center justify-center mt-8 w-9/12 mx-auto text-black">
					<form onSubmit={handleSearch} className="flex flex-row justify-between items-center w-11/12 mx-auto">
						<input
							type="text"
							placeholder="Rechercher une ressource"
							className="border-2 border-gray-200 p-2 w-full"
							value={searchTerm}
							onChange={(e) => setSearchTerm(e.target.value)}
						/>
						<button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded ml-4">
							Rechercher
						</button>
					</form>
				</header>
				<div className="flex flex-wrap justify-center mt-10 m-auto w-11/12 text-black overflow-y-scroll max-h-[700px]">
					{filteredRessources.map((ressource, index) => (
						<div key={index} className="flex flex-col border-2 justify-between border-gray-200 p-4 bg-white rounded-xl m-2 w-full md:w-5/12 lg:w-3/12 xl:w-1/4 h-[450px] overflow-auto">
							<div>
								<img src={`/lg/${ressource.category}.png`} alt={`${ressource.type}`} className="w-full h-32 object-contain" />
								<h4 className="font-semibold text-xl mt-2">{ressource.nom}</h4>
								<p className="my-2">{ressource.description}</p>
							</div>
							<div className="flex flex-col">
								<span className="bg-gray-200 text-gray-700 text-sm font-semibold mr-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-gray-300">{ressource.type}</span>
								<a href={ressource.url} target="_blank" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mt-2 text-center">Visiter</a>
							</div>
						</div>
					))}
				</div>
			</main>
		</div>
	)

}