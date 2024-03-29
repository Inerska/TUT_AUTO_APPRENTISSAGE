import { AdminSideBar } from "@/components/AdminSidebar";
import { FooterComponent } from "@/components/FooterComponent";
import { HeaderComponent } from "@/components/HeaderComponent";
import { Users, Code, CheckCircle, FileText } from 'lucide-react';

export default function PanelExercises() {
	return (
		<div className="bg-lite-quinary text-dark-quaternary flex overflow-y-hidden overflow-x-hidden">
			<AdminSideBar selected="Accueil" />
			<section className="text-gray-600 body-font w-full">
				<div className="container px-5 py-24 mx-auto">
					<div className="flex flex-col text-center w-full mb-20">
						<h1 className="sm:text-3xl text-2xl font-medium title-font mb-4 text-gray-900">Statistiques CodeLab</h1>
					</div>
					<div className="flex flex-wrap -m-4 text-center">
						<div className="p-4 md:w-1/4 sm:w-1/2 w-full">
							<div className="border-2 border-gray-200 px-4 py-6 rounded-lg">
								<Users className="text-indigo-500 w-12 h-12 mb-3 inline-block" />
								<h2 className="title-font font-medium text-3xl text-gray-900">1.3K</h2>
								<p className="leading-relaxed">Utilisateurs</p>
							</div>
						</div>
						<div className="p-4 md:w-1/4 sm:w-1/2 w-full">
							<div className="border-2 border-gray-200 px-4 py-6 rounded-lg">
								<Code className="text-indigo-500 w-12 h-12 mb-3 inline-block" />
								<h2 className="title-font font-medium text-3xl text-gray-900">2.7K</h2>
								<p className="leading-relaxed">Exercices créés</p>
							</div>
						</div>
						<div className="p-4 md:w-1/4 sm:w-1/2 w-full">
							<div className="border-2 border-gray-200 px-4 py-6 rounded-lg">
								<CheckCircle className="text-indigo-500 w-12 h-12 mb-3 inline-block" />
								<h2 className="title-font font-medium text-3xl text-gray-900">74</h2>
								<p className="leading-relaxed">Exercices terminés</p>
							</div>
						</div>
						<div className="p-4 md:w-1/4 sm:w-1/2 w-full">
							<div className="border-2 border-gray-200 px-4 py-6 rounded-lg">
								<FileText className="text-indigo-500 w-12 h-12 mb-3 inline-block" />
								<h2 className="title-font font-medium text-3xl text-gray-900">254</h2>
								<p className="leading-relaxed">Tests passés</p>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	)
}