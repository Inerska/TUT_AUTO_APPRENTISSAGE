import { PulseLoader } from 'react-spinners';
export const Spinner = () => {
	return (
		<div className="flex justify-center items-center h-screen">
			<PulseLoader color="#000" loading size={20} />
		</div>

	)
}
