import { Blocks, LogOut, BookAIcon, LucideMonitorPlay, UserIcon, BarChart2, CheckCircle2, TestTube2Icon, LucideLanguages, BookImage } from 'lucide-react';
import { Doughnut } from 'react-chartjs-2';
import { Chart } from 'chart.js';
import 'chart.js/auto';
import 'chartjs-plugin-datalabels';
import ChartDataLabels from 'chartjs-plugin-datalabels';
import { StatsItemProps } from '@/utils/types';
Chart.register(ChartDataLabels);
export const HomeComponent = () => {
	const stats = [
		{
			icon: BarChart2,
			label: "Exercice(s) démarré",
			value: "16"
		},
		{
			icon: CheckCircle2,
			label: "Exercice(s) terminé",
			value: "10"
		},
		{
			icon: TestTube2Icon,
			label: "Test(s) réussi",
			value: "30"
		},
		{
			icon: LucideLanguages,
			label: "Langage(s) exercé",
			value: "2"
		},
	] as StatsItemProps[];
	const exercices = [
		{
			title: "Titre exercice 1",
			description: "Description de l'exercice 1",
			finished: false,
			lang: 'js'
		},
		{
			title: "Titre exercice 2",
			description: "Description de l'exercice 2",
			finished: true,
			lang: 'java'
		},
		{
			title: "Titre exercice 3",
			description: "Description de l'exercice 3",
			finished: false,
			lang: 'cs'
		},
		{
			title: "Titre exercice 4",
			description: "Description de l'exercice 4",
			finished: false,
			lang: 'cpp'
		},
		{
			title: "Titre exercice 5",
			description: "Description de l'exercice 5",
			finished: false,
			lang: 'ruby'
		},
		{
			title: "Titre exercice 6",
			description: "Description de l'exercice 6",
			finished: false,
			lang: 'python'
		},
	]
	const langages = [
		{
			placeholder: "Javascript",
			icon: "/lg/js.png",
			nbExercices: 6
		},
		{
			placeholder: "Python",
			icon: "/lg/python.png",
			nbExercices: 3
		},
		{
			placeholder: "Java",
			icon: "/lg/java.png",
			nbExercices: 2
		},
		{
			placeholder: "C#",
			icon: "/lg/cs.png",
			nbExercices: 5
		},
		{
			placeholder: "C++",
			icon: "/lg/cpp.png",
			nbExercices: 2
		},
		{
			placeholder: "Ruby",
			icon: "/lg/ruby.png",
			nbExercices: 3
		},
		{
			placeholder: "Go",
			icon: "/lg/go.png",
			nbExercices: 8
		},
		{
			placeholder: "Typescript",
			icon: "/lg/ts.png",
			nbExercices: 1
		},
	]

	const data = {
		labels: Object.values(langages).map(lang => lang.placeholder),
		datasets: [
			{
				label: "Exercice(s) lancé avec ce langage",
				data: Object.values(langages).map(lang => lang.nbExercices),
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)',
				],
				borderColor: [
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)',
				],
				borderWidth: 1,
				anchor: 'end',

			},
		],
	};



	var options = {
		tooltips: {
			enabled: false
		},
		plugins: {
			datalabels: {
				formatter: (value: number, ctx: any) => {

					let sum = 0;
					let dataArr = ctx.chart.data.datasets[0].data;
					dataArr.map((data: any) => {
						sum += data;
					});
					let percentage = (value * 100 / sum).toFixed(0) + "%";
					return percentage;
				},
				color: '#000000',
			}
		}
	};
	return (
		<main className='grid grid-cols-3 w-full h-full'>
		{/* conteneur de gauche */}
		<div className='col-span-2 h-full w-full ml-6'>
			{/* statistiques */}
			<div className='flex justify-between bg-white rounded-xl h-5/12 mt-8 m-auto'>
				<div className='flex flex-col m-4 bg-lite-tertiary w-5/12 pl-2 pt-5 justify-between rounded-lg' >
					<div>
						<h3 className='ml-6 font-normal text-3xl drop-shadow-2xl'>Statistiques</h3>
						<p className='ml-6 mt-5 font-light text-xl drop-shadow-2xl'>Ton compte en quelques chiffres</p>
					</div>
					<div className='flex justify-end'>
						<img src="/stats.png" alt="Image illustration" className='w-52 object-contain' />
					</div>

				</div>
				<div className='grid grid-cols-2 h-5/12 m-3'>
					{stats.map((stat, index) => (
						<div className='w-56 h-36 bg-lite-quinary m-3 rounded-xl'>
							<div className="bg-white rounded-md w-10 h-10 mt-3 ml-4 flex justify-center items-center">
								<stat.icon size={20} color="#485fd1" />
							</div>
							<p className="mt-3 ml-4 text-4xl font-bold text-black">{stat.value}</p>
							<span className="ml-4 text-lg font-medium text-gray-500">{stat.label}</span>
						</div>
					))}
				</div>
			</div>
			<div className='grid grid-cols-2 my-9'>
				{/* graphique */}
				<div className='flex flex-col items-center bg-white rounded-xl h-4/12 w-auto mr-5 h-full pb-4'>
					<div>
						<h3 className='text-black text-center text-xl m-4 font-semibold'>Graphique pourcentage langage </h3>
						<Doughnut width={"60px"} height={"60px"} options={options} data={data} />
					</div>
				</div>
				{/* partie rapide */}
				<div className='flex flex-col items-center bg-white rounded-xl h-5/12 ml-5 w-auto h-full no-user-select'>
					<img src="/illustration.avif" alt="Image d'illustration" className='w-96 object-contain' />
				</div>
			</div>
		</div>

		{/* conteneur de droite */}
		<div>
			{/* voir les cours */}
			<div className='flex flex-col items-center bg-dark-quaternary h-7/12 text-black w-10/12 m-auto mt-8 rounded-xl p-1 pb-4' >
				{/* header */}
				<header className='flex flex-row justify-between items-center w-full my-4'>
					<div className='ml-6'>
						<h3 className='text-xl font-semibold'>Exercices</h3>
						<p className='text-sm'>Vos deniers exercices</p> {/* max 6 */}
					</div>
					<button className='bg-lite-senary rounded-lg p-2 mr-6'>Voir plus</button>
				</header>
				{/* liste des cours */}
				<div className='flex flex-col items-center w-11/12 overflow-y-auto overflow-x-hidden'>
					{!exercices && <p>Vous n'avez pas encore lancé d'exercices</p>}
					{exercices && exercices.map((exercice, index) => (
						<div key={index} className='flex flex-row items-center w-full justify-between even:bg-slate-100 p-2 border border-gray-200'>
							<div className='flex flex-row items-center'>
								<img src={`/lg/${exercice.lang}.png`} alt="LG logo" className='w-10 h-10' />
								<div className='ml-2'>
									<h4>{exercice.title}</h4>
									<p>{exercice.description}</p>
								</div>
							</div>
							{exercice.finished ? <span>Terminé</span> : <span>En cours</span>}
						</div>
					))}
				</div>
			</div>
			<div className='flex flex-col items-center bg-dark-quaternary h-4/12 text-black w-10/12 m-auto mt-6 rounded-xl'>
				<div className='flex flex-col justify-between w-full my-4'>
					{/* header */}
					<header className='ml-6'>
						<h3 className='text-xl font-semibold'>Vos langages exercés</h3>
						<p className='text-sm'>Tous les langages exercés à travers les exercices</p>
					</header>
					{/* les langages */}
					<div className='grid grid-cols-4 mx-10 mt-5'>
						{langages.map((langage, index) => (
							<div key={index} className='flex flex-col items-center my-2'>
								<img src={langage.icon} alt="" className='w-16 h-16  object-contain' />
								<p>{langage.placeholder}</p>
							</div>
						))}
					</div>
				</div>
			</div>
		</div>
	</main>
	);
}