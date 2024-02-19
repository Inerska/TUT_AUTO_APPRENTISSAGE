import axios, { AxiosRequestConfig } from "axios";
import { CreateExerciseBody, CreateExerciseResponse, GetResultsExerciceResponse, LoginBody, LoginResponse, RegisterBody, RegisterResponse, SubmitExerciseBody, SubmitExerciseResponse } from "./types";
const CONFIG: AxiosRequestConfig = { withCredentials: true }
const BASE_URL = "http://localhost:3000/api/v1";

// #region Auth
export const login = (data: LoginBody) => 
	axios.post<LoginResponse>(`${BASE_URL}/auth/login`, data, CONFIG);
export const register = (data: RegisterBody) => 
	axios.post<RegisterResponse>(`${BASE_URL}/auth/login`, data, CONFIG);
// #endregion

// #region Exercise
export const submitExercise = (data: SubmitExerciseBody) => 
	axios.post<SubmitExerciseResponse>(`${BASE_URL}/exercise`, data, CONFIG);

export const createExercise = (data: CreateExerciseBody) =>
	axios.post<CreateExerciseResponse>(`${BASE_URL}/exercise/create`, data, CONFIG);

export const getResultsExercise = (id: string) =>
	axios.get<GetResultsExerciceResponse>(`${BASE_URL}/exercise/${id}/results`, CONFIG);
// #endregion
