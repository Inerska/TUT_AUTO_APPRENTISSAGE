"use client";
import { ChartComponent } from "@/components/ChartComponent";
import { FooterComponent } from "@/components/FooterComponent";
import { HeaderComponent } from "@/components/HeaderComponent";
import { LanguagesComponent } from "@/components/LanguagesComponent";
import { StatsComponent } from "@/components/StatsComponent";
import { StatsItemProps } from "@/utils/types";
import { BarChart2, CheckCircle2, TestTube2Icon, LucideLanguages } from "lucide-react";

export default function ProfilePage({ params }: { params: { profileId: string } }) {
	const profileId = params.profileId;
	// @TODO: utiliser le profileId pour afficher les info de l'utilisateur

	const stats = [
		{
			icon: BarChart2,
			label: "Exercice(s) démarré",
			value: "16"
		},
		{
			icon: CheckCircle2,
			label: "Exercice(s) terminé",
			value: "10"
		},
		{
			icon: TestTube2Icon,
			label: "Test(s) réussi",
			value: "30"
		},
		{
			icon: LucideLanguages,
			label: "Langage(s) exercé",
			value: "2"
		},
	] as StatsItemProps[];

	const langages = [
		{
			placeholder: "Javascript",
			icon: "/lg/js.png",
			nbExercices: 6
		},
		{
			placeholder: "Python",
			icon: "/lg/python.png",
			nbExercices: 3
		},
		{
			placeholder: "Java",
			icon: "/lg/java.png",
			nbExercices: 2
		},
		{
			placeholder: "C#",
			icon: "/lg/cs.png",
			nbExercices: 5
		},
		{
			placeholder: "C++",
			icon: "/lg/cpp.png",
			nbExercices: 2
		},
		{
			placeholder: "Ruby",
			icon: "/lg/ruby.png",
			nbExercices: 3
		},
		{
			placeholder: "Go",
			icon: "/lg/go.png",
			nbExercices: 8
		},
		{
			placeholder: "Typescript",
			icon: "/lg/ts.png",
			nbExercices: 1
		},
	]

	return (
		<div className="flex flex-col min-h-screen">
			<HeaderComponent />
			<div className="flex flex-1 flex-col items-center h-screen w-full text-black bg-lite-quinary pb-16">
				{/* banner */}
				<div className="relative w-full h-100">
					<img className="border-4 border-white w-full h-52 object-cover" src="https://via.placeholder.com/100x100" alt="banner" />
					{/* Adjust avatar position */}
					<img className=" bg-white absolute bottom-[-20%] left-1/2 transform -translate-x-1/2 w-40 h-4w-40 rounded-full border-8 border-white object-contain" src="/lg/ts.png" alt="profile" />
				</div>
				{/* user info */}
				<div className="mt-12 flex flex-col items-center w-full">
					<h1 className="text-2xl font-bold">Username</h1>
				</div>
				{/* Statistiques */}
				<StatsComponent stats={stats} profile />
				<div className="flex justify-between rounded-xl h-5/12 mt-8 w-full max-w-6xl ">
					{/* Langages */}
					<ChartComponent langages={langages} profile/>
					<LanguagesComponent langages={langages} profile />

				</div>

			</div>
			<FooterComponent />
		</div>
	);
}
