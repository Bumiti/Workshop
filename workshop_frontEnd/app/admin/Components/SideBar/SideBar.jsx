import React from 'react'
import Image from 'next/image'
import Link from 'next/link'
import { MdDashboard } from 'react-icons/md';
import { AiFillHome } from 'react-icons/ai';
import { BiSolidUserAccount } from 'react-icons/bi';
import { BsPeopleFill } from 'react-icons/bs';
import { RiLogoutBoxRFill } from 'react-icons/ri';

function Sidebar() {
    return (
        <div className='bg-dark sidebar p-2'>
            <Link href={'/admin'}>
                <div className='m-2 text-center'>
                    <Image src={'/logo.png'} height={50} width={96}></Image>
                </div>
            </Link>
            <hr className='text-white' />
            <div className='list-group'>
                <Link className='list-group-item py-2 my-1 bg-dark text-white rounded-pill' href={'/admin/dashboard'}>
                    <i className='fs-5 me-3'><MdDashboard/></i>
                    <span >Dashboard</span>
                </Link>
                <Link className='list-group-item py-2 my-1 bg-dark text-white rounded-pill' href={'/admin/'}>
                    <i className='bi bi-house fs-5 me-3'><AiFillHome/></i>
                    <span >Home</span>
                </Link>
                <Link className='list-group-item py-2 my-1 bg-dark text-white rounded-pill' href={'/admin/account'}>
                    <i className='bi bi-table fs-5 me-3'><BiSolidUserAccount/></i>
                    <span >Accounts</span>
                </Link>
                <Link className='list-group-item py-2 my-1 bg-dark text-white rounded-pill' href={'/admin/dashboard'}>
                    <i className='bi bi-people fs-5 me-3'><BsPeopleFill/></i>
                    <span >Customers</span>
                </Link>
                <Link className='list-group-item py-2 my-1 bg-dark text-white rounded-pill' href={'/admin/dashboard'}>
                    <i className='bi bi-power fs-5 me-3'><RiLogoutBoxRFill/></i>
                    <span >Logout</span>
                </Link>
            </div>
        </div>
    )
}
export default Sidebar 