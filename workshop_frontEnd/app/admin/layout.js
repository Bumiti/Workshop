'use client'
import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'

import Sidebar from './Components/SideBar/SideBar'
import Header from './Components/Header/Header'
import Home from './Home'
import './styles.css'

import { useState } from 'react'

export default function RootLayout({ children }) {

    const [toggle, setToggle] = useState(true)
    const Toggle = () => { setToggle(!toggle) }
    return (
            <div className='container-fluid bg-secondary min-vh-100 '>
                <div className='row '>
                    {toggle && <div className='col-4 col-md-2 bg-dark vh-100 position-fixed'>
                        <Sidebar/>
                    </div>}
                    {toggle && <div className='col-4 col-md-2'></div>}
                    <div className='col'>
                        <Header className='Header' Toggle={Toggle} />
                        {/* <Home className='main'/> */}
                        {children}
                    </div>
                </div>
            </div>
            )
}