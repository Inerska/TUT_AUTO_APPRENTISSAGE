export const options = {
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