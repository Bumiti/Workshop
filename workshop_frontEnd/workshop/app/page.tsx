'use client'

import Link from 'next/link'
import { signIn, useSession } from "next-auth/react"
import jwtDecode from 'jwt-decode';

export default function Home() {


  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <Link href="/api/auth/signin">login</Link>
    </main>
  )
}
