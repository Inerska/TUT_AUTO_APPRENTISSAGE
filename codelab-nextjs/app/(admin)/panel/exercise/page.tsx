import { FooterComponent } from "@/components/FooterComponent";
import { HeaderComponent } from "@/components/HeaderComponent";

export default function PanelExercises() {
	return (
		<>
			<HeaderComponent />
			<div className="text-dark  bg-white flex overflow-y-hidden overflow-x-hidden min-h-screen justify-center ">
				<div className="grid grid-cols-2 h-96 w-5/6 mt-8 p-4">
					<div className="flex flex-col mt-8 ">
						<h2 className="font-extrabold text-6xl">CODELAB</h2>
						<h4 className="font-bold text-2xl mt-8" >L'apprentissage sans limite</h4>
						<a className="bg-lite-primary w-48 h-12 text-center mt-8 flex justify-center items-center text-white rounded-xl cursor-pointer" href="/catalogue">Voir notre catalogue</a>
					</div>
					<div>
						<img src="/general.png" alt="Illustration" />
					</div>
				</div>
			</div>
			<FooterComponent />
		</>
	)
}