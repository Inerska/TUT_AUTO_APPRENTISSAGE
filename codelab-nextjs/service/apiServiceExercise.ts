import axios from 'axios';
import {LanguageItemApi, Languages, SubmitExerciseBody} from "@/utils/types";

const baseUrl = 'http://exercices.codelab.local:81/api/v1';
const API_URL = `${baseUrl}/exercices`;

export const getExerciseDetails = async (exerciseId: string) => {
    //65d9232feb032c39fafe6217
    const reponse = { //type a modifier quand api dispo
        data : {
            title: "Titre python",
            description: "Description js",
            banner: "https://via.placeholder.com/150x120",
            author: "Alexis Gridel",
            language: Languages.PYTHON,
            id: exerciseId,
            tasks: ["Créer une méthode Hello World", "Vérifier une condition", "Afficher un message de bienvenue", "Tartenpion"],
            testCode: "import unittest\n\nfrom main import hello\n\nclass TestHelloFunction(unittest.TestCase):\n\n    def test_hello_returns_correct_message(self):\n        self.assertEqual(hello(), \"Hello world\", \"Should be 'Hello world'\")\n\n    def test_hello_returns_correct_message_case(self):\n        self.assertEqual(hello(), \"Hello world\", \"Should be 'Hello world'\")\n\n    def test_hello_returns_correct_message_type(self):\n        self.assertEqual(type(hello()), str, \"Should be a string\")\n\n    def test_hello_returns_correct_message_length(self):\n        self.assertEqual(len(hello()), 11, \"Should be 11 characters\")\n\n\nif __name__ == \"__main__\":\n    unittest.main()",
            nbTest: 5,
            difficulty: "Facile",
            createdAt: new Date()
        }
    };
    return reponse;
};

export const submitExercise = async (submission: SubmitExerciseBody) => {
    try {
        const response = await axios.post(API_URL, submission);
        return response.data;
    } catch (error) {
        console.error(error);
    }
};

export const getExerciseResults = async (id: string) => {
    try {
        const response = await axios.get(`${API_URL}/${id}/results`);
        return response.data;
    } catch (error) {
        console.error(error);
    }
};

export const getAllLanguages = async () => {
    try {
        const response = await axios.get(`${baseUrl}/languages`);
        return response.data.map(
            (language: any) => {
                return {
                    name : language.item1,
                    abbreviation: language.item2
                }
            }
        );
    } catch (error) {
        console.error(error);
    }
};

export const getAllExercicesForSupportedLanguages = async (languages: LanguageItemApi[]) => {
    const exercices = [];
    try {
        for (let i = 0; i < languages.length; i++) {
            const response = await axios.get(`${baseUrl}/languages/exercices?language=${languages[i].name}`);
            exercices.push(...response.data);
        }
    } catch (error) {
        console.error(error);
    }
    return exercices.map(
        (exercise) => {
            return {
                title: "Titre python",
                description: "Description js",
                banner: "https://via.placeholder.com/150x120",
                author: exercise.author,
                language: exercise.language,
                difficulty: "Facile",
                createdAt: new Date(),
                id: exercise.id,
                tasks: ["Créer une méthode Hello World", "Vérifier une condition", "Afficher un message de bienvenue"],
                testCode: exercise.testCode,
                nbTest: 5
            };
        }
    );
}