"use client";

import {useAuthStore} from "@/store/authState";
import {redirect} from "next/navigation";

export default function Page() {
    const authStore = useAuthStore();
    authStore.logout();

    redirect("/")

    return <div>Logging out...</div>
}