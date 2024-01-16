"use client";

import React, { useRef, useState } from "react";
import Editor, { Monaco } from "@monaco-editor/react";
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Button } from "@/components/ui/button";
import { Edit } from "lucide-react";

export default function Page() {
  const editorRef = useRef(null);
  const [currentLanguage, setCurrentLanguage] = useState("javascript");
  const [currentTheme, setCurrentTheme] = useState("light");

  const languages = [
    "javascript",
    "python",
    "java",
    "csharp",
    "cpp",
    "ruby",
    "go",
    "typescript",
  ];

  const handleEditorDidMount = (editor: any, monaco: Monaco) => {
    editorRef.current = editor;
    editor.focus();
  };

  return (
    <div className="flex flex-col h-screen overflow-hidden">
      <header className="flex items-center justify-between px-6 py-4 border-b">
        <h1 className="text-2xl font-semibold">Coding Game</h1>
        <div className="flex items-center space-x-2">
          <Button variant="outline">Save</Button>
          <Button>Submit</Button>
        </div>
      </header>
      <main className="flex flex-1 overflow-hidden">
        <aside className="w-64 border-r p-6 overflow-y-auto">
          <h2 className="text-xl font-semibold mb-4">Challenge Instructions</h2>
          <p className="text-gray-600 dark:text-gray-400">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit
            amet facilisis ipsum. In hac habitasse platea dictumst. Integer
            aliquet purus vitae vestibulum cursus.
          </p>
          <h3 className="text-lg font-semibold mt-6 mb-2">Tasks:</h3>
          <ul className="list-disc list-inside text-gray-600 dark:text-gray-400">
            <li>Lorem ipsum dolor sit amet.</li>
            <li>Consectetur adipiscing elit.</li>
            <li>Sed sit amet facilisis ipsum.</li>
          </ul>
        </aside>
        <section className="flex flex-col flex-1">
          <div className="flex-1 p-6 border-b">
            <h2 className="text-xl font-semibold mb-2">Code Editor</h2>
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
                height="calc(100vh - 200px)"
                language={currentLanguage}
                defaultValue="//TODO: Write your code here"
                theme={currentTheme}
                onMount={handleEditorDidMount}
            />
          </div>
          <div className="p-6">
            <h2 className="text-xl font-semibold mb-2">Console Output</h2>
            <div className="rounded-lg bg-white p-4 text-black border">
              <pre>
                <code>Hello, world!</code>
              </pre>
            </div>
          </div>
        </section>
      </main>
    </div>
  );
}
