
import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import 'bootstrap/dist/css/bootstrap.css'
import AuthProvider from '@/app/component/Authprovider'
import NavBar from '@/NavBar'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Workshop',
  description: 'Workshop Project 04',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <AuthProvider>
        <body className={inter.className}>
          <NavBar></NavBar>
          {children}
          </body>
      </AuthProvider>
    </html>
  )
}
