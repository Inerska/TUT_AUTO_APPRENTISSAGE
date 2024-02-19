import axios from 'axios';

const API_URL = 'http://exercices.codelab.local:81/api/v1/exercices';

const TestsTemporaires = {
    "language": "python",
    "testCode": "import unittest\n\nfrom main import hello\n\nclass TestHelloFunction(unittest.TestCase):\n\n    def test_hello_returns_correct_message(self):\n        self.assertEqual(hello(), \"Hello world\", \"Should be 'Hello world'\")\n\n    def test_hello_returns_correct_message_case(self):\n        self.assertEqual(hello(), \"Hello world\", \"Should be 'Hello world'\")\n\n    def test_hello_returns_correct_message_type(self):\n        self.assertEqual(type(hello()), str, \"Should be a string\")\n\n    def test_hello_returns_correct_message_length(self):\n        self.assertEqual(len(hello()), 11, \"Should be 11 characters\")\n\n\nif __name__ == \"__main__\":\n    unittest.main()",
    "author": "Alexis Gridel"
}

export const getExercise = async (exercise: any) => {
    try {
        const response = await axios.post(`${API_URL}/create`, exercise); // Pour les besoins du mvp on doit le créer
        return response.data;
    } catch (error) {
        console.error(error);
    }
};

export const submitExercise = async (submission: any) => {
    try {
        const response = await axios.post(API_URL, submission);
        return response.data;
    } catch (error) {
        console.error(error);
    }
};

export const getExerciseResults = async (id: any) => {
    try {
        const response = await axios.get(`${API_URL}/${id}/results`);
        return response.data;
    } catch (error) {
        console.error(error);
    }
};

export const sendExerise = async (data : string|undefined) => {
    //crée un exercice
    const exercise = await getExercise(TestsTemporaires);
    console.log('exercise ' + exercise);
    const submission = await submitExercise(
        {
            "language": "python", // a modif post mvp pour récupérer la langue depuis editeur
            "code": data,
            "exerciceId": exercise
        }
    );
    console.log(submission);
    //récupère les résultats de l'exercice
    const results = await getExerciseResults(submission.id);
    console.log(results);

    return results.result;
}