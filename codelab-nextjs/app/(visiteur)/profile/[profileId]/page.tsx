"use client"
import { useGetProfile } from "@/utils/hooks/useGetProfile";
import { HeaderComponent } from "@/components/HeaderComponent";
import { FooterComponent } from "@/components/FooterComponent";
import { StatsComponent } from "@/components/StatsComponent";
import { LanguagesComponent } from "@/components/LanguagesComponent";
import { ChartComponent } from "@/components/ChartComponent";
import { BarChart2, CheckCircle2, TestTube2Icon, LucideLanguages } from "lucide-react";
import {useEffect, useState} from "react";

export default function ProfilePage({ params }: { params: { profileId: string } }) {
	const profileId = params.profileId;
	const { profile, loading, error } = useGetProfile(profileId);

	const [stats, setStats] = useState([
		{
			icon: BarChart2,
			label: "Exercice(s) démarré",
			value: 0
		},
		{
			icon: CheckCircle2,
			label: "Exercice(s) terminé",
			value: 0
		},
		{
			icon: TestTube2Icon,
			label: "Test(s) réussi",
			value: 0
		},
		{
			icon: LucideLanguages,
			label: "Langage(s) exercé",
			value: 0
		},
	]);

	const [langages, setLangages] = useState<any[]>([]);

	useEffect(() => {
		console.log("Profile changed:", profile);
		console.log("Calculating stats...");

		if (profile) {
			let newStats = [...stats];
			newStats[0].value = 0;
			newStats[1].value = 0;
			newStats[2].value = 0;
			newStats[3].value = 0;

			let tempLangages = [...langages];

			profile.exercices.forEach(exercice => {
				if (exercice.status !== "COMPLETED") {
					newStats[0].value++;
				} else {
					newStats[1].value++;
					newStats[2].value += exercice.exercice.nbTests;
				}

				// if langage is not in langages, add it
				const langage = exercice.exercice.language;
				const langageIndex = tempLangages.findIndex(l => l.placeholder === langage.name);
				if (langageIndex === -1) {
					tempLangages.push({
						placeholder: langage.name,
						icon: "/lg/"+langage.abbreviation+".png",
						nbExercices: 1
					});
				} else {
					tempLangages[langageIndex].nbExercices++;
				}

			});

			newStats[3].value = new Set(
				profile.exercices.map(exercice => exercice.exercice.language.name)
			).size;

			setStats(newStats);
			setLangages(tempLangages);
		}
	}, [profile]);

	if (loading) {
		return <div>Loading...</div>;
	}

	if (error) {
		return <div>Error: {error.message}</div>;
	}

	if (!profile) {
		return <div>No profile found</div>;
	}
	
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
					<h1 className="text-2xl font-bold">{profile.username}</h1>
				</div>
				{/* Statistiques */}
				<StatsComponent stats={stats} profile />
				<div className="flex justify-between rounded-xl h-5/12 mt-8 w-full max-w-6xl ">
					{/* Langages */}
					<ChartComponent langages={langages} profile />
					<LanguagesComponent langages={langages} profile />
				</div>
			</div>
			<FooterComponent />
		</div>
	);
}
