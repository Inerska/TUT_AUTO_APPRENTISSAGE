import { FooterComponent } from "@/components/FooterComponent";
import { HeaderComponent } from "@/components/HeaderComponent";
import { List, Code, Lightbulb, CheckCircle, MessageSquare, Clock } from 'lucide-react';

export default function Home() {
	return (
		<>
			<HeaderComponent />
			<div className="text-dark  bg-white flex overflow-y-hidden h-auto overflow-x-hidden justify-center ">
				<div className="grid grid-cols-2 w-4/6 mt-8 p-4">
					<div className="flex flex-col mt-8 justify-center">
						<h2 className="font-extrabold text-6xl">CODELAB</h2>
						<h4 className="font-bold text-2xl mt-8" >L'apprentissage sans limite</h4>
						<a className="bg-lite-primary w-48 h-12 text-center mt-8 flex justify-center items-center text-white rounded-xl cursor-pointer" href="/catalogue">Voir notre catalogue</a>
					</div>
					<div>
						<img src="/general.png" alt="Illustration" />
					</div>
				</div>
			</div>
			<section className="text-gray-600 body-font">
				<div className="container px-5 py-24 mx-auto">
					<div className="flex flex-wrap -m-4">
						<div className="lg:w-1/3 lg:mb-0 mb-6 p-4">
							<div className="h-full text-center">
								<img alt="testimonial" className="w-20 h-20 mb-8 object-cover object-center rounded-full inline-block border-2 border-gray-200 bg-gray-100" src="/avis.png" />
								<p className="leading-relaxed">Grâce à CodeLab, j'ai pu apprendre les bases de la programmation en JavaScript et je me sens maintenant prêt à relever de nouveaux défis.</p>
								<span className="inline-block h-1 w-10 rounded bg-indigo-500 mt-6 mb-4"></span>
								<h2 className="text-gray-900 font-medium title-font tracking-wider text-sm">HOLDEN CAULFIELD</h2>
								<p className="text-gray-500">Développeur junior</p>
							</div>
						</div>
						<div className="lg:w-1/3 lg:mb-0 mb-6 p-4">
							<div className="h-full text-center">
								<img alt="testimonial" className="w-20 h-20 mb-8 object-cover object-center rounded-full inline-block border-2 border-gray-200 bg-gray-100" src="/avis.png" />
								<p className="leading-relaxed">Je recommande vivement CodeLab à tous ceux qui souhaitent se former aux langages de programmation. De plus, la plateforme est très intuitive et facile à utiliser.</p>
								<span className="inline-block h-1 w-10 rounded bg-indigo-500 mt-6 mb-4"></span>
								<h2 className="text-gray-900 font-medium title-font tracking-wider text-sm">ALPER KAMU</h2>
								<p className="text-gray-500">Développeur full-stack</p>
							</div>
						</div>
						<div className="lg:w-1/3 lg:mb-0 p-4">
							<div className="h-full text-center">
								<img alt="testimonial" className="w-20 h-20 mb-8 object-cover object-center rounded-full inline-block border-2 border-gray-200 bg-gray-100" src="/avis.png" />
								<p className="leading-relaxed">CodeLab m'a permis d'approfondir mes connaissances en Python et de découvrir de nouveaux frameworks. Je suis ravi d'avoir choisi cette plateforme pour me former.</p>
								<span className="inline-block h-1 w-10 rounded bg-indigo-500 mt-6 mb-4"></span>
								<h2 className="text-gray-900 font-medium title-font tracking-wider text-sm">HENRY LETHAM</h2>
								<p className="text-gray-500">Développeur senior</p>
							</div>
						</div>
					</div>
				</div>
			</section>
			<section className="text-gray-600 body-font">
				<div className="container px-5 py-24 mx-auto">
					<div className="flex flex-wrap w-full mb-20 flex-col items-center text-center">
						<h1 className="sm:text-3xl text-2xl font-medium title-font mb-2 text-gray-900">L'outil d'apprentissage parfait</h1>
						<p className="lg:w-1/2 w-full leading-relaxed text-gray-500">Apprendre un nouveau langage n'a jamais été aussi simple.</p>
					</div>
					<div className="flex flex-wrap -m-4">
						<div className="xl:w-1/3 md:w-1/2 p-4">
							<div className="border border-gray-200 p-6 rounded-lg">
								<div className="w-10 h-10 inline-flex items-center justify-center rounded-full bg-indigo-100 text-indigo-500 mb-4">
									<List />
								</div>
								<h2 className="text-lg text-gray-900 font-medium title-font mb-2">Un catalogue complet</h2>
								<p className="leading-relaxed text-base">Avec un catalogue d'exercice complet, vous trouverez l'exercice parfait.</p>
							</div>
						</div>
						<div className="xl:w-1/3 md:w-1/2 p-4">
							<div className="border border-gray-200 p-6 rounded-lg">
								<div className="w-10 h-10 inline-flex items-center justify-center rounded-full bg-indigo-100 text-indigo-500 mb-4">
									<Code />
								</div>
								<h2 className="text-lg text-gray-900 font-medium title-font mb-2">Apprentissage en temps réel</h2>
								<p className="leading-relaxed text-base">Pratiquez et obtenez des commentaires instantanés sur votre code.</p>
							</div>
						</div>
						<div className="xl:w-1/3 md:w-1/2 p-4">
							<div className="border border-gray-200 p-6 rounded-lg">
								<div className="w-10 h-10 inline-flex items-center justify-center rounded-full bg-indigo-100 text-indigo-500 mb-4">
									<Lightbulb />
								</div>
								<h2 className="text-lg text-gray-900 font-medium title-font mb-2">Idées et astuces</h2>
								<p className="leading-relaxed text-base">Découvrez de nouvelles façons de coder avec nos conseils et astuces.</p>
							</div>
						</div>
						<div className="xl:w-1/3 md:w-1/2 p-4">
							<div className="border border-gray-200 p-6 rounded-lg">
								<div className="w-10 h-10 inline-flex items-center justify-center rounded-full bg-indigo-100 text-indigo-500 mb-4">
									<CheckCircle />
								</div>
								<h2 className="text-lg text-gray-900 font-medium title-font mb-2">Validation des compétences</h2>
								<p className="leading-relaxed text-base">Validez vos compétences avec nos défis et projets.</p>
							</div>
						</div>
						<div className="xl:w-1/3 md:w-1/2 p-4">
							<div className="border border-gray-200 p-6 rounded-lg">
								<div className="w-10 h-10 inline-flex items-center justify-center rounded-full bg-indigo-100 text-indigo-500 mb-4">
									<MessageSquare />
								</div>
								<h2 className="text-lg text-gray-900 font-medium title-font mb-2">Feedback constructif</h2>
								<p className="leading-relaxed text-base">Recevez des commentaires constructifs.</p>
							</div>
						</div>
						<div className="xl:w-1/3 md:w-1/2 p-4">
							<div className="border border-gray-200 p-6 rounded-lg">
								<div className="w-10 h-10 inline-flex items-center justify-center rounded-full bg-indigo-100 text-indigo-500 mb-4">
									<Clock />
								</div>
								<h2 className="text-lg text-gray-900 font-medium title-font mb-2">Suivi du progrès</h2>
								<p className="leading-relaxed text-base">Suivez votre progression et restez motivé.</p>
							</div>
						</div>
					</div>
				</div>
			</section>

			<FooterComponent />
		</>

	)
}
