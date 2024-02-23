"use client";
import Link from "next/link";
import { useState } from "react";

export const HeaderComponent = ({ admin }: { admin: boolean }) => {
	const [isMenuOpen, setIsMenuOpen] = useState<boolean>(false);

	return (
		<header className="bg-white w-full">
			<nav className="mx-auto flex max-w-7xl items-center justify-between p-6 lg:px-8" aria-label="Global">
				<div className="flex lg:flex-1">
					<Link href={"#"} className="-m-1.5 p-1.5">
						<span className="sr-only">Codelab</span>
						<img className="h-8 w-auto" src="/small_logo.png" alt="" />
					</Link>
				</div>
				<div className="flex lg:hidden">
					<button
						type="button"
						className="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-gray-700"
						onClick={() => setIsMenuOpen(!isMenuOpen)}
					>
						<span className="sr-only">Ouvrir main</span>
						{/* Menu icon */}
						<svg className="h-6 w-6" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" aria-hidden="true">
							<path strokeLinecap="round" strokeLinejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5" />
						</svg>
					</button>
				</div>
				<div className="hidden lg:flex lg:gap-x-12">
					<Link href={"/"} className="text-sm font-semibold leading-6 text-gray-900">Accueil</Link>
					<Link href={"/catalogue"} className="text-sm font-semibold leading-6 text-gray-900">Catalogue</Link>
					{admin && (
						//todo changer id
						<Link href={"/profil/ID"} className="text-sm font-semibold leading-6 text-gray-900">Profil</Link>
					)}
					<Link href={"/exercise/aleatoire"} className="text-sm font-semibold leading-6 text-gray-900">Exercice aléatoire &#128256;</Link>
				</div>
				<div className="hidden lg:flex lg:flex-1 lg:justify-end">
					{admin && (
						<Link href={"#"} className="text-sm font-semibold leading-6 text-gray-900">Menu</Link>
					)}
					{!admin && (
						<Link href={"#"} className="text-sm font-semibold leading-6 text-gray-900">Connexion</Link>
					)}
				</div>
			</nav>
			{/* Mobile menu */}
			{isMenuOpen && (
				<div className="lg:hidden" role="dialog" aria-modal="true">
					<div className="fixed inset-y-0 right-0 z-10 w-full overflow-y-auto bg-white px-6 py-6 sm:max-w-sm sm:ring-1 sm:ring-gray-900/10">
						<div className="flex items-center justify-between">
							<Link href={"#"} className="-m-1.5 p-1.5">
								<span className="sr-only">Codelab</span>
								<img className="h-8 w-auto" src="/small_logo.png" alt="" />
							</Link>
							<button
								type="button"
								className="-m-2.5 rounded-md p-2.5 text-gray-700"
								onClick={() => setIsMenuOpen(false)}
							>
								<span className="sr-only">Fermer menu</span>
								{/* Close icon */}
								<svg className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true">
									<path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
								</svg>
							</button>
						</div>
						<div className="mt-6">
							<Link href={"#"} className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50">Accueil</Link>
							<Link href={"#"} className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50">Catalogue</Link>
							{admin && (
								<Link href={"#"} className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50">Profil</Link>
							)}
							<Link href={"#"} className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50">Exercice aléatoire &#128256;</Link>
							{!admin && (
								<Link href={"#"} className="-mx-3 block rounded-lg px-3 py-2.5 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50">Connexion</Link>
							)}

						</div>
					</div>
				</div>
			)}
		</header>
	);
};
