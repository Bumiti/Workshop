'use client'
import React from 'react'
import Image from 'next/image'
import { MdDashboard } from 'react-icons/md';
import { AiFillHome } from 'react-icons/ai';
import { BiSolidUserAccount } from 'react-icons/bi';
import { SiGoogleclassroom } from 'react-icons/si';
import { MdOutlineRequestQuote } from 'react-icons/md';
import { BsPersonWorkspace } from 'react-icons/bs';

import Link from 'next/link';
import { useRouter } from 'next/router';

function Sidebar () {
    const navLink = [
        {
            name: "Home",
            link: "/admin",
            icon: <AiFillHome />,
        },
        {
            name: "Accounts",
            link: "/admin/account",
            icon: <BiSolidUserAccount />,
        },
        {
            name: "Courses",
            link: "/admin/courses",
            icon: <SiGoogleclassroom />,
        },{
            name: "Request",
            link: "/admin/request",
            icon: <MdOutlineRequestQuote />,
        },
        {
            name: "WorkShop",
            link: "/admin",
            icon: <BsPersonWorkspace />,
        },
    ]
    return (
        <div className='bg-dark sidebar p-2'>
            <Link href={'/admin'}>
                <div className='m-2 text-center'>
                    <Image alt='' src={'/logo.png'} height={50} width={96}></Image>
                </div>
            </Link>
            <hr className='text-white' />
            <div className='list-group'>
                {navLink.map(({ name, link, icon }) => (
                    <Link key={name} href={link} className='list-group-item py-2 my-1 bg-dark text-white rounded-pill'>
                        <div >
                            <i className='bi bi-house fs-5 me-3'>{icon}</i>
                            <span>{name}</span>
                        </div>
                    </Link>
                ))}
            </div>
        </div>
    )
}
export default Sidebar 