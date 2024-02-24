import { LucideIcon } from "lucide-react";

export type StatsItemProps = {
	icon: LucideIcon;
	label: string;
	value: string;
};

export type SidebarItem = {
	icon: LucideIcon;
	label: string;
	link?: string;
	separatorNext?: boolean;
};

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

export type LanguageItemApi = {
	name: string,
	abbreviation: string
}

export const LanguageAbbreviations: Record<Languages, string> = {
	[Languages.JAVASCRIPT]: "js",
	[Languages.PYTHON]: "py",
	[Languages.JAVA]: "java",
	[Languages.CSHARP]: "cs",
	[Languages.CPP]: "cpp",
	[Languages.RUBY]: "rb",
	[Languages.GO]: "go",
	[Languages.TYPESCRIPT]: "ts",
};

export interface AuthState {
	profileId: string | null;
	accessToken: string;
	refreshToken: string;
}

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

export type GetExerciseDetailsResponse = {
	title: string;
	description: string;
	banner: string;
	author: string;
	language: Languages;
	difficulty: string,
	id: string;
	testCode: string;
	tasks: string[]
	nbTest: number;
	createdAt: Date;
}

export type SubmitExerciseBody = {
	language: Languages;
	code: string;
	exerciceId: string
};

export type SubmitExerciseResponse = {
	id: string;
	feedback: string;
};

export type CreateExerciseBody = {
	language: Languages;
	testCode: string;
	author: string;
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