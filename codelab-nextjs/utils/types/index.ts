

export enum Languages {
  JAVASCRIPT = "javascript",
  PYTHON = "python",
  JAVA = "java",
  CSHARP = "csharp",
  CPP = "cpp",
  RUBY = "ruby",
  GO = "go",
  TYPESCRIPT = "typescript",
}

export interface AuthState {
	profileId: string | null;
	accessToken: string;
	refreshToken: string;
};
export interface AuthActions {
	setProfileId: (profileId: string | null) => void;
	setAccessToken: (accessToken: string) => void;
	setRefreshToken: (refreshToken: string) => void;
	logout: () => void;
  }

export type AuthStore = AuthState & AuthActions;


export type LoginBody = {
	mail: string;
	password: string;
};
export type LoginResponse = {
	profileId: string;
	"access-token": string;
	"refresh-token": string;
};
export type RegisterBody = {
	mail: string;
	username: string;
	password: string;
	"confirm-password": string;
};
export type RegisterResponse = {
	profileId: string;
	"access-token": string;
	"refresh-token": string;
};

export type SubmitExerciseBody = {
	language: "javascript" | "python" | "java" | "csharp"; 
	testCode: string;
	author: string;
};
export type SubmitExerciseResponse = {
	id: string;
	feedback: string;
};

export type CreateExerciseBody = {
	language: "javascript" | "python" | "java" | "csharp";
	testCode: string;
};
export type CreateExerciseResponse = {
	language: string;
	testCode: string;
	author: string;
};

export type GetResultsExerciceResponse = {
	id: string;
	status: "PENDING"|"ERROR"|"COMPLETED";
	result: string;
	timestamp: string;
	errorDetails: string|null;
	additionalInfo: string|null;
};