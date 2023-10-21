'use client'
import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import 'bootstrap/dist/css/bootstrap.css'
import AuthProvider from '@/app/component/Authprovider'
import Navbar from '@/components/Navbar'

const inter = Inter({ subsets: ['latin'] })

export default function CaLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <AuthProvider>
          <container className={inter.className}>
          {children}
          </container>
      </AuthProvider>
    </html>
  )
}
