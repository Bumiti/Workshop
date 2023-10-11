'use client'

import Link from 'next/link'
import { signIn, useSession } from "next-auth/react"
import jwtDecode from 'jwt-decode';

export default function Home() {
  const session = useSession();
  const email = session.data?.user?.email;
  const tokenusung = localStorage.getItem('token');
  if(tokenusung!=null){
    const decode = jwtDecode(tokenusung)
    console.log("decode", (decode as { sub: string }).sub);

  }
  console.log("token trong local store",tokenusung)

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <Link href="/api/auth/signin">login</Link>
    </main>
  )
}
