"use client";
import { AdminSideBar } from "@/components/AdminSidebar";
import { Profile } from "@/utils/types";


export default function PanelUsers() {

	const users = [
		{
			id: "1",
			username: "codelab",
			email: "contact@codelab.com",
			createdAt: "2022-01-01",
			admin: true
		},
		{
			id: "2",
			username: "JaneSmith",
			email: "janesmith@example.com",
			createdAt: "2022-02-01",
			admin: false
		},
		{
			id: "3",
			username: "Alice",
			email: "alice@example.com",
			createdAt: "2022-03-01",
			admin: true
		},
		{
			id: "4",
			username: "Bob",
			email: "bob@example.com",
			createdAt: "2022-04-01",
			admin: false
		}
	] as Profile[]

	const handleDelete = (id: string) => {
		console.log("Delete user with id", id)
	}

	const handleSwitchAdmin = (id: string) => {
		console.log("Switch admin with id", id)
	}

	return (
		<div className="bg-lite-quinary text-dark-quaternary flex justify-centeroverflow-y-hidden overflow-x-hidden">
			<AdminSideBar selected="Utilisateurs" />
			<section className="text-gray-600 body-font w-full flex justify-center">
				<div className="relative overflow-x-auto  sm:rounded-lg">
					<table className="w-full text-sm text-left rtl:text-right text-gray-500 mt-4">
						<thead className="text-xs text-gray-700 uppercase bg-gray-50">
							<tr>
								<th scope="col" className="px-6 py-3">
									ID
								</th>
								<th scope="col" className="px-6 py-3">
									Nom d'utilisateur
								</th>
								<th scope="col" className="px-6 py-3">
									Email
								</th>
								<th scope="col" className="px-6 py-3">
									Date de création
								</th>
								<th scope="col" className="px-6 py-3">
									Admin
								</th>
								<th scope="col" className="px-6 py-3">
									<span className="sr-only text-red-600">Action rapide</span>
								</th>
							</tr>
						</thead>
						<tbody>
							{users.map((user) => (
								<tr key={user.id} className="bg-gray-50 border-b odd:bg-white hover:bg-gray-100 ">
									<td className="px-6 py-4">
										{user.id}
									</td>
									<td className="px-6 py-4 font-medium text-gray-900 ">
										{user.username}
									</td>
									<td className="px-6 py-4">
										{user.email}
									</td>
									<td className="px-6 py-4">
										{user.createdAt}
									</td>
									<td className="px-6 py-4 flex justify-center">
										<input type="checkbox" checked={user.admin} onChange={() => handleSwitchAdmin(user.id)} />
									</td>
									<td className="px-6 py-4 text-right">
										<button onClick={() => handleDelete(user.id)} className="font-medium text-red-600  hover:underline">Supprimer</button>
									</td>
								</tr>
							))}
						</tbody>
					</table>
				</div>
			</section>
		</div >
	)
}