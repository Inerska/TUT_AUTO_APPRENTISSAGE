import { options } from "@/utils/chart";
import { Doughnut } from "react-chartjs-2";
import { Chart } from 'chart.js';
import 'chart.js/auto';
import 'chartjs-plugin-datalabels';
import ChartDataLabels from 'chartjs-plugin-datalabels';
Chart.register(ChartDataLabels);
export const ChartComponent = ({langages, profile}: {langages: {placeholder: string, nbExercices: number}[], profile?: boolean }) => {
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
	return (
		<div className={`flex flex-col items-center bg-white rounded-xl ${profile ? 'w-full mt-6':''} mr-5 h-full pb-4 max-w-[550px]`}>
			<div>
				<h3 className='text-black text-center text-xl m-4 font-semibold'>Graphique pourcentage langage </h3>
				<Doughnut width={"60px"} height={"60px"} options={options} data={data} />
			</div>
		</div>
	);
}