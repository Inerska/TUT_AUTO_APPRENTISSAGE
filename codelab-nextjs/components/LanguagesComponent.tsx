import { StatsItemProps } from '@/utils/types';

export const LanguagesComponent = ({ langages, profile }: { langages: any[], profile?: boolean }) => {
	return (
		<div className={`flex flex-col items-center bg-dark-quaternary h-4/12 text-black w-10/12 ${profile ? "" : "mx-auto"} mt-6 rounded-xl max-w-[500px]`}>
			<div className='flex flex-col justify-between w-full my-4'>
				{/* header */}
				<header className='ml-6'>
					<h3 className='text-xl font-semibold'>Langages exercés</h3>
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
	)
}