"use client";

import React, { useRef, useState } from "react";
import Editor, {Monaco} from "@monaco-editor/react";
import {Select, SelectContent, SelectGroup, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";

export default function Page() {
    const editorRef = useRef(null);
    const [currentLanguage, setCurrentLanguage] = useState("javascript");
    const [currentTheme, setCurrentTheme] = useState("light");

    const languages = ["javascript", "python", "java", "csharp", "cpp", "ruby", "go", "typescript"];

    const handleEditorDidMount = (editor: any, monaco: Monaco) => {
        editorRef.current = editor;
        editor.focus();
    };

    return (
        <div className="min-h-screen bg-gray-100">
            <header className="bg-blue-500 text-white text-lg p-4">
                <h1>CodeLab</h1>
            </header>

            <div className="flex flex-col md:flex-row">
                <div className="md:w-1/2 p-4">
                    <h2 className="text-xl font-bold mb-3">Énoncé de l'Exercice</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. A asperiores aspernatur deserunt dolores doloribus earum eius est fugit incidunt libero maiores nisi nulla recusandae repellat sequi, similique ullam unde voluptate!</p>
                </div>

                <div className="md:w-1/2 p-4 flex flex-col gap-5">
                    <div className="flex gap-5">
                        <Select onValueChange={(value) => setCurrentLanguage(value)}>
                            <SelectTrigger>
                                <SelectValue placeholder={currentLanguage}/>
                            </SelectTrigger>
                            <SelectContent>
                                <SelectGroup>
                                    {languages.map((language) => (
                                        <SelectItem value={language} key={language}>
                                            {language}
                                        </SelectItem>
                                    ))}
                                </SelectGroup>
                            </SelectContent>
                        </Select>

                        <Select onValueChange={(value) => setCurrentTheme(value)}>
                            <SelectTrigger>
                                <SelectValue placeholder={currentTheme}/>
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
                        height="60vh"
                        language={currentLanguage}
                        defaultValue="// Écrivez votre code ici"
                        theme={currentTheme}
                        onMount={handleEditorDidMount}
                    />
                </div>
            </div>

            <div className="bg-gray-800 text-white p-4">
                <h2 className="text-xl font-bold mb-3">Console</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab, assumenda corporis deleniti distinctio enim fugit ipsum nisi praesentium, rem sit totam voluptate. Eveniet molestias possimus sint. A est quos tempora.</p>
            </div>
        </div>
    );
}
