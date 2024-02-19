import axios, { AxiosRequestConfig } from "axios";
import {LoginBody, LoginResponse, RegisterBody, RegisterResponse} from "./types";
const CONFIG: AxiosRequestConfig = { withCredentials: false }
const BASE_URL = "http://auth.codelab.local:81";

// #region Auth
export const login = (data: LoginBody) => {
	return axios.post<LoginResponse>(`${BASE_URL}/auth/login`, data, CONFIG).
		then(response => {
			console.log(response.data);
			return response;
		});
}

export const register = (data: RegisterBody) => {
	return axios.post<RegisterResponse>(`${BASE_URL}/auth/register`, data, CONFIG)
		.then(response => {
			console.log(response.data);
			return response;
		});
}