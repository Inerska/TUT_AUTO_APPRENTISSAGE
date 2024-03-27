import { FooterComponent } from "@/components/FooterComponent";
import { HeaderComponent } from "@/components/HeaderComponent";
import Link from "next/link";

export default function SiteMap() {
	return (
		<>
			<HeaderComponent />
			<div className="text-dark  bg-white flex overflow-y-hidden overflow-x-hidden min-h-screen justify-center ">
				<div className="container px-5 py-24 mx-auto flex md:items-center lg:items-start md:flex-row md:flex-nowrap flex-wrap flex-col">

					<div className="flex-grow flex flex-wrap md:pl-20 -mb-10 md:mt-0 mt-10 md:text-left text-center">
						<div className="lg:w-1/4 md:w-1/2 w-full px-4">
							<h2 className="title-font font-medium text-gray-900 tracking-widest text-sm mb-3">VISITEUR</h2>
							<nav className="list-none mb-10">
								<li>
									<Link href="/" className="text-gray-600 hover:text-gray-800">Accueil</Link>
								</li>
								<li>
									<Link href="/sitemap" className="text-gray-600 hover:text-gray-800">Site map</Link>
								</li>
								<li>
									<Link href="/contact" className="text-gray-600 hover:text-gray-800">Contact</Link>
								</li>
								<li>
									<Link href="/login" className="text-gray-600 hover:text-gray-800">Connexion</Link>
								</li>
								<li>
									<Link href="/signup" className="text-gray-600 hover:text-gray-800">Inscription</Link>
								</li>
								<li>
									<Link href="/catalogue" className="text-gray-600 hover:text-gray-800">Catalogue</Link>
								</li>
							</nav>
						</div>
						<div className="lg:w-1/4 md:w-1/2 w-full px-4">
							<h2 className="title-font font-medium text-gray-900 tracking-widest text-sm mb-3">UTILISATEUR</h2>
							<nav className="list-none mb-10">
								<li>
									<Link href="/menu/home" className="text-gray-600 hover:text-gray-800">Menu</Link>
								</li>
								<li>
									<Link href="/menu/exercises" className="text-gray-600 hover:text-gray-800">Exercices</Link>
								</li>
								<li>
									<Link href="/menu/ressources" className="text-gray-600 hover:text-gray-800">Ressources</Link>
								</li>
								<li>
									<Link href="/menu/profile" className="text-gray-600 hover:text-gray-800">Modifier profil</Link>
								</li>
							</nav>
						</div>

						<div className="lg:w-1/4 md:w-1/2 w-full px-4">
							<h2 className="title-font font-medium text-gray-900 tracking-widest text-sm mb-3">ADMINISTRATEUR</h2>
							<nav className="list-none mb-10">
								<li>
									<Link href="/panel" className="text-gray-600 hover:text-gray-800">Statistiques</Link>
								</li>
								<li>
									<Link href="/panel/exercise" className="text-gray-600 hover:text-gray-800">Exercices</Link>
								</li>
								<li>
									<Link href="/panel/exercise" className="text-gray-600 hover:text-gray-800">Utilisateurs</Link>
								</li>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<FooterComponent />
		</>
	)
}