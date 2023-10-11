// import './globals.css'
import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import 'bootstrap/dist/css/bootstrap.css'
import Authprovider from '@/app/component/Authprovider'
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
      <body className={inter.className}>
      <Authprovider>
      {children}
      </Authprovider>

        </body>
    </html>
  )
}
