"use client";
import React, {useEffect, useRef, useState} from "react";
import Editor, {Monaco} from "@monaco-editor/react";
import {Select, SelectContent, SelectGroup, SelectItem, SelectTrigger, SelectValue,} from "@/components/ui/select";
import {HeaderComponent} from "@/components/HeaderComponent";
import {useGetExerciseDetails} from "@/utils/hooks/useGetExerciseDetails";
import {Exercise, Languages} from "@/utils/types";
import {useSubmitExercise} from "@/utils/hooks/useSubmitExercise";
import {useGetExerciseResults} from "@/utils/hooks/useGetExerciseResults";
import {useAuthStore} from "@/store/authState";

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

	const [exercice, setExercice] = useState<Exercise>(
		{
			id: "",
			title: "Chargement...",
			description: "Chargement...",
			instructions: "Chargement...",
			tasks: [],
			banner: "https://via.placeholder.com/150x120",
			author: "Codelab",
			testCode: "",
			language: {
				id: "",
				name: Languages.PYTHON,
				abbreviation: ""
			},
			difficulty: {
				id: "",
				name: ""
			},
			nbTests: 0,
			createdAt: ""
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
		language: exercice.language.name,
		code: sendCode,
		exerciceId: exercice.id,
		profileId: useAuthStore().profileId
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

		scrollToBottom();
	}

	return (
		<div className="flex flex-col min-h-screen">
			<HeaderComponent />
			<main className="flex-grow flex justify-center bg-lite-quinary p-8">
				<div className="flex flex-col lg:flex-row w-full max-w-7xl gap-8">
					<section className="flex flex-col flex-grow bg-white rounded-xl p-6">
						<h2 className="text-xl font-semibold mb-4">Editeur de code</h2>
						<div className="flex gap-4 mb-4">
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
							onChange={(value: any) => {
								setEditorCode(value);
								setIsDirty(value !== savedCode);
							}}
							language={exercice.language.name}
							className="border border-gray-200 rounded-md flex-grow"
							defaultValue={editorCode}
							theme={currentTheme}
							onMount={handleEditorDidMount}
						/>
						<div className="mt-6">
							<h3 className="text-lg font-semibold mb-2">Terminal</h3>
							<div className="bg-gray-900 text-white p-4 rounded-md h-64 overflow-y-auto">
              <pre ref={consoleRef}>
                {consoleOutput.map((entry, index) => (
					<code key={index} className={entry.isServer ? "text-green-400" : "text-blue-400"}>
						{entry.isServer ? '/home/scruzlara/python> ' : '/home/user> '} {entry.message + "\n"}
					</code>
				))}
              </pre>
							</div>
						</div>
					</section>
					<aside className="lg:w-96 bg-white rounded-xl p-6">
						<h2 className="text-xl font-semibold mb-4">{exercice.title}</h2>
						<p className="text-gray-600 mb-6">{exercice.instructions}</p>
						<h3 className="text-lg font-semibold mb-2">Objectifs</h3>
						<ul className="list-none mb-6">
							{exercice.tasks.map((task, index) => (
								<li key={index} className="flex items-center mb-2">
									<span className="flex items-center justify-center border w-7 h-7 rounded-full mr-2">{index + 1}</span>
									{task.content}
								</li>
							))}
						</ul>
						<p className="text-gray-600 mb-2">L'exercice sera terminé une fois tous les objectifs atteints.</p>
						<p className="text-gray-600 mb-6">Lorsque vous souhaiterez essayer votre code, appuyez sur le bouton "Tester le code".</p>
						<div className="flex flex-col gap-4">
							<button className="bg-lite-primary text-white rounded-md py-2 px-4" onClick={handleSave}>
								Sauvegarder
							</button>
							<button className="bg-dark-secondary text-white rounded-md py-2 px-4" onClick={handleSubmit}>
								Tester le code
							</button>
						</div>
					</aside>
				</div>
			</main>
		</div>
	);
}
