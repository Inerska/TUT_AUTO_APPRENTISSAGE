"use client";
import { Blocks, LogOut, BookAIcon, LucideMonitorPlay, UserIcon, BarChart2, CheckCircle2, TestTube2Icon, LucideLanguages, BookImage } from 'lucide-react';
import { SidebarItem, StatsItemProps, Languages } from '@/utils/types';
import { useState } from 'react';
import Link from 'next/link';
import { HomeComponent } from '@/components/menu/HomeComponent';
import { ExerciseComponent } from '@/components/menu/ExerciseComponent';
import { RessourceComponent } from '@/components/menu/RessourceComponent';
import { useAuthStore } from '../store/authState';
export default function MenuPage() {
	// pour la sidebar
	const [selected, setSelected] = useState<string>("Accueil");
	
	const store = useAuthStore();

	const sidebarItems: SidebarItem[] = [
		{
			icon: Blocks,
			label: "Accueil",
			separatorNext: true
		},
		{
			icon: LucideMonitorPlay,
			label: "Exercices"
		},
		{
			icon: BookAIcon,
			label: "Ressources",
			separatorNext: true
		},
		{
			icon: UserIcon,
			label: "Profil",
			link: `/profile/${store.profileId}`
		},
		{
			icon: BookImage,
			label: "Catalogue",
			link: "/catalogue",
			separatorNext: true
		},
		{
			icon: LogOut,
			label: "Déconnexion"
		},
	]

	return (
		<div className="bg-lite-quinary text-dark-quaternary flex overflow-y-hidden overflow-x-hidden">
			{/* Sidebar */}
			<aside className="w-56 min-w-56 bg-lite-secondary min-h-screen flex flex-col items-center">
				<Link href="/" legacyBehavior>
					<a><img src="/codelab.png" alt="Logo" className="w-44 h-auto object-contain mt-4" /></a>
				</Link>
				<nav className="mt-12 w-full">
					<ul>
						{sidebarItems.map((item, index) => (
							<>
								{item.link ? (
									<Link href={item.link} key={index} legacyBehavior>
										<a className={`${selected === item.label ? "border-white border-l-4 bg-gradient-to-r from-blue-800" : ""} m-0 p-0 w-full duration-75 h-12 flex items-center cursor-pointer`}>
											<item.icon size={24} className="ml-6" />
											<span className="ml-4 text-lg font-extralight">{item.label}</span>
										</a>
									</Link>
								) : (
									<li key={index} onClick={() => setSelected(item.label)} className={`${selected === item.label ? "border-white border-l-4 bg-gradient-to-r from-blue-800" : ""} m-0 p-0 w-full duration-75 h-12 flex items-center cursor-pointer`}>
										<item.icon size={24} className="ml-6" />
										<span className="ml-4 text-lg font-extralight">{item.label}</span>
									</li>
								)}
								{item.separatorNext && <hr className="my-2 border-opacity-20 border-gray-50 w-full" />}
							</>
						))}
					</ul>

				</nav>
			</aside>

			{/* Main Content */}
			<div className="flex-grow flex flex-col m-0">
				{/* header */}
				<header className='bg-dark-quaternary h-20 min-h-20 flex flex-row justify-between items-center'>
					<p className='text-gray-400 font-light text-xl ml-6'>Bienvenue, <span className='text-lite-primary font-semibold'>Nom d'utilisateur</span></p>
					<div className='mr-3 rounded-full w-16 h-16 flex justify-center align-middle border-2 border-black overflow-hidden'>
						<img src="/next.svg" alt="Avatar" />
					</div>
				</header>
				{/* contenu */}
				{selected === "Accueil" && <HomeComponent />}
				{selected === "Exercices" && <ExerciseComponent />}
				{selected === "Ressources" && <RessourceComponent />}
			</div>
		</div >
	);
}
