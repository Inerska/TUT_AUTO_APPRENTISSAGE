import { SidebarItem } from "@/utils/types"
import Link from "next/link"
import { Blocks, BookAIcon, BookImage, LogOut, LucideMonitorPlay, User2, UserCog, UserIcon } from "lucide-react"
import { redirect } from "next/navigation"
import { useAuthStore } from "@/store/authState"

export const Sidebar = ({ selected }: { selected: string }) => {
	const store = useAuthStore()
	const sidebarItems: SidebarItem[] = [
		{
			icon: Blocks,
			label: "Accueil",
			separatorNext: true,
			link: "/menu/home"
		},
		{
			icon: LucideMonitorPlay,
			label: "Exercices",
			link: `/menu/exercises`
		},
		{
			icon: BookAIcon,
			label: "Ressources",
			link: `/menu/ressources`,
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
			icon: UserCog,
			label: "Modifier profil",
			link: "/menu/profile",
		},
		{
			icon: LogOut,
			label: "Déconnexion"
		},
	]

	return (
		<aside className="w-56 min-w-56 bg-lite-secondary min-h-screen flex flex-col items-center text-white">
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
								<li key={index} onClick={() => redirect(`/item.label`)} className={`${selected === item.label ? "border-white border-l-4 bg-gradient-to-r from-blue-800" : ""} m-0 p-0 w-full duration-75 h-12 flex items-center cursor-pointer`}>
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
	)
}