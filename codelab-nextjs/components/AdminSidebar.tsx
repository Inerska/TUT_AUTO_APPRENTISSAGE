import { SidebarItem } from "@/utils/types"
import Link from "next/link"
import { Blocks, BookAIcon, BookImage, Home, LogOut, LucideMonitorPlay, User2, UserCog, UserIcon } from "lucide-react"
import { redirect } from "next/navigation"

export const AdminSideBar = ({ selected }: { selected: string }) => {
	const sidebarItems: SidebarItem[] = [
		{
			icon: Blocks,
			label: "Accueil",
			link: "/panel/home"
		},
		{
			icon: LucideMonitorPlay,
			label: "Exercices",
			link: `/panel/exercises`
		},
		{
			icon: BookAIcon,
			label: "Utilisateurs",
			link: `/panel/users`,
			separatorNext: true
		},
		{
			icon: Home,
			label: "Retour menu",
			link: "/menu/home",
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