import { StatsItemProps } from '@/utils/types';

export const StatsComponent = ({ stats, profile }: { stats: StatsItemProps[], profile: boolean }) => {
	return (
		<div className='flex justify-between bg-white rounded-xl h-5/12 mt-8 m-auto w-full max-w-6xl'>
			<div className='flex flex-col m-4 bg-lite-tertiary w-5/12 pl-2 pt-5 justify-between rounded-lg text-white' >
				<div>
					<h3 className='ml-6 font-normal text-3xl drop-shadow-2xl'>Statistiques</h3>
					<p className='ml-6 mt-5 font-light text-xl drop-shadow-2xl'>{profile ? "Ce" : "Ton"} compte en quelques chiffres</p>
				</div>
				<div className='flex justify-end'>
					<img src="/stats.png" alt="Image illustration" className='w-52 object-contain' />
				</div>
			</div>
			<div className='grid grid-cols-2 h-5/12 m-3 bg-white'>
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
	);
}