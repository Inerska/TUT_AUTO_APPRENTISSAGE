import Link from "next/link";

export const HeaderComponent = () => {
	return (
		<header className="flex items-center justify-between w-full h-16 px-8">
			<div className="flex items-center space-x-4">
				<Link href="/" legacyBehavior>
					<a className="font-bold text-xl">Codelab</a>
				</Link>
			</div>
			<div className="flex justify-between m-6 space-x-10">
				<Link href="/" legacyBehavior>
					<a>Accueil</a>
				</Link>
				<Link href="/menu" legacyBehavior>
					<a>Menu</a>
				</Link>
				<Link href="/catalogue" legacyBehavior>
					<a>Catalogue</a>
				</Link>
				<Link href="/login" legacyBehavior>
					<a>Connexion</a>
				</Link>
			</div>
		</header>
	);
}