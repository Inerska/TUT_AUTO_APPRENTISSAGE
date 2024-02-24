"use client";
import React, {useEffect, useRef, useState} from "react";
import Editor, {Monaco} from "@monaco-editor/react";
import {Select, SelectContent, SelectGroup, SelectItem, SelectTrigger, SelectValue,} from "@/components/ui/select";
import {HeaderComponent} from "@/components/HeaderComponent";
import {useGetExerciseDetails} from "@/utils/hooks/useGetExerciseDetails";
import {GetExerciseDetailsResponse, Languages} from "@/utils/types";
import {useSubmitExercise} from "@/utils/hooks/useSubmitExercise";
import {useGetExerciseResults} from "@/utils/hooks/useGetExerciseResults";

interface ConsoleEntry {
	message: string;
	isServer: boolean; // true pour les sorties serveur, false pour les commandes utilisateur
}
export default function IdePage({ params }: { params: { exerciseId: string } }) {

	const exerciseId = params.exerciseId;

	const editorRef = useRef(null);
	const consoleRef = useRef(null);

	const [currentTheme, setCurrentTheme] = useState("Light");

	//TODO modifier editorCode pour qu'il soit récupéré depuis l'api
	const [editorCode, setEditorCode] = useState("//TODO: Write your code here");
	const [consoleOutput, setConsoleOutput] = useState<ConsoleEntry[]>([{ message: "En attente..", isServer: true}]);

	const [savedCode, setSavedCode] = useState(editorCode);
	const [isDirty, setIsDirty] = useState(false);

	const [loading, setLoading] = useState(false);

	const [exercice, setExercice] = useState<GetExerciseDetailsResponse>(
		{
			title: "Chargement...",
			description: "Chargement...",
			banner: "https://via.placeholder.com/150x120",
			author: "Chargement...",
			language: Languages.JAVASCRIPT,
			id: exerciseId,
			nbTest: 0,
			tasks: [],
			difficulty: "Chargement...",
			testCode: "",
			createdAt: new Date()
		}
	);
	const { exercise : dataExo } = useGetExerciseDetails(exerciseId);

	useEffect(() => {
		if (dataExo) {
			setExercice(dataExo);
		}
	}, [dataExo]);

	const handleEditorDidMount = (editor: any, monaco: Monaco) => {
		editorRef.current = editor;
		editor.focus();
	};

	const beforeUnloadListener = (event: any) => {
		if (isDirty) {
			event.preventDefault();
			event.returnValue = "You have unsaved changes! Are you sure you want to leave?";
		}
	};

	useEffect(() => {
		if (isDirty) {
			console.log("Adding listener");
			window.addEventListener("beforeunload", beforeUnloadListener);
		} else {
			console.log("Removing listener");
			window.removeEventListener("beforeunload", beforeUnloadListener);
		}
		return () => {
			window.removeEventListener("beforeunload", beforeUnloadListener);
		};
	}, [ isDirty]);

	useEffect(() => {
		scrollToBottom(); 
	}, [consoleOutput]);

	const appendToConsole = (newOutput: string, isServer: boolean) => {
		const newEntry: ConsoleEntry = { message: newOutput, isServer };
		setConsoleOutput((prevOutput: ConsoleEntry[] | undefined) => [...(prevOutput || []), newEntry]);
	};

	const scrollToBottom = () => {
		if (consoleRef.current) {
			(consoleRef.current as any).scrollTop = (consoleRef.current as any).scrollHeight;
		}
	};

	const handleSave = () => {
		setSavedCode(editorCode);
		setIsDirty(false);
		console.log("Code saved!");
	};

	const [sendCode, setSendCode] = useState("");

	const {
		response: responseSubmit,
		loading: loadSubmit,
		error: erorSubmit,
		setIsExerciseSubmitted
	} = useSubmitExercise({
		language: exercice.language,
		code: sendCode,
		exerciceId: exercice.id,
	});

	const {
		results,
		pending,
		error: errorResults,
		refreshData
	} = useGetExerciseResults(sendCode, loadSubmit, responseSubmit?.id);

	//print errors if set
	useEffect(() => {
		if (erorSubmit) {
			appendToConsole(erorSubmit, true);
		}
	} , [erorSubmit]);

	useEffect(() => {
		if (errorResults) {
			appendToConsole(errorResults, true);
		}
	} , [errorResults]);


	// If results gets updated, append to console and stop loading
	useEffect(() => {
		if (results) {
			const interval = setInterval(() => {
				if (!pending && sendCode !="" && !loadSubmit) {
					appendToConsole(results.result, true);
					setSendCode("");
					clearInterval(interval);
				}
				refreshData;

			} , 5000);

		}
	}, [results]);

	const handleSubmit = () => {
		setSendCode(editorCode);
		setIsExerciseSubmitted(false);
		setLoading(true);
		appendToConsole("Envoi du code...", false);

	}

	return (
		<div className="">
			<HeaderComponent />
			<main className="flex  justify-center overflow-hidden h-screen bg-lite-quinary ">
				<section className="flex flex-col  w-4/6 h-5/6 m-8 bg-white rounded-xl">
					<div className="px-6">
						<h2 className="text-xl font-semibold mb-2 mt-8">Editeur de code</h2>
						<div className="flex gap-5 m-auto mb-2">
							<Select onValueChange={(value) => setCurrentTheme(value)}>
								<SelectTrigger className="w-64">
									<SelectValue placeholder={currentTheme} />
								</SelectTrigger>
								<SelectContent>
									<SelectGroup>
										<SelectItem value="vs">Light</SelectItem>
										<SelectItem value="vs-dark">Dark</SelectItem>
									</SelectGroup>
								</SelectContent>
							</Select>
						</div>
						<Editor
							onChange={
								(value: any) => {
									setEditorCode(value)
									if (value !== savedCode && !isDirty) setIsDirty(true)
									else if (value === savedCode) setIsDirty(false)
								}
							}
							language={exercice.language}
							className="border border-black border-opacity-20 py-1 h-4/5 w-auto overflow-hidden"
							defaultValue={editorCode}
							theme={currentTheme}
							onMount={handleEditorDidMount}
						/>
					</div>
					<div className="px-6 h-auto">
						<div className="bg-white p-4 text-black border border-black border-opacity-20">
							<pre className="mb-2">
								<code className="border-b border-black font-semibold">
									Terminal
								</code>
							</pre>
							<pre ref={consoleRef} className="flex flex-col overflow-y-scroll h-36">
								{consoleOutput && consoleOutput.map((entry, index) => (
									<code key={index} className={entry.isServer ? "text-green-700" : "text-blue-700"}>
										{entry.isServer ? 'S:\\Serveur> ' : 'C:\\Users\\username> '}{entry.message}
									</code>
								))}
							</pre>
						</div>
					</div>
				</section>
				<aside className="flex flex-col justify-between w-96 h-5/6 p-6 overflow-y-auto m-8 bg-white rounded-xl">
					<div>
						<h2 className="text-xl font-semibold mb-4">{exercice.title}</h2>
						<p className="text-gray-600 dark:text-gray-400">{exercice.description}</p>
						<h3 className="text-lg font-semibold mt-6 mb-2">Objectifs</h3>
						<ul className="list-inside text-gray-600 dark:text-gray-400 list-none">
							{exercice.tasks.map((task: any, index: number) => (
								<li key={index} className="flex items-center"><span className="flex items-center justify-center border w-7 h-7 rounded-full mr-2 my-1">{index + 1}</span> {task}</li>
							))}
						</ul>
						<h3 className="text-lg font-semibold mt-6 mb-2">L'exercice sera terminé une fois tous les objectifs terminés</h3>
						<p className="text-gray-600 dark:text-gray-400">Lorsque vous souhaiterez essayer votre code, appuyez sur le bouton "Tester le code".</p>
						<p className="text-gray-600 dark:text-gray-400 mt-2">N'hésitez pas à sauvegarder régulièrement ! </p>
					</div>

					<div className="flex flex-col items-center py-4">
						<button className="w-full bg-lite-primary py-2 my-2 rounded-xl text-white text-xl" onClick={handleSave}>Sauvegarder</button>
						<button className="w-full bg-dark-secondary py-2 rounded-xl text-white text-xl" onClick={handleSubmit}>Tester le code</button>
					</div>
				</aside>
			</main>
		</div>
	);
}
