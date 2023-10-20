
import Link from "next/link";
import styles from '../CSS/home.module.css';

import 'bootstrap/dist/css/bootstrap.min.css'; // Import CSS của Bootstrap
import Image from 'next/image';
import React, { useEffect, useState } from 'react';
import { useSession, signIn, signOut } from "next-auth/react";

export default function Navbar() {
  const { data: session } = useSession();

  const NavLink = ({ text }: { text: string }) => (
    <li className="nav-item mr-20">
      <Link href={`/${text.toLowerCase()}`} className="nav-link text-dark font-roboto custom-bold">
        {text}
      </Link>
    </li>
  );
  const [dropdownVisible, setDropdownVisible] = useState(false);

  const toggleDropdown = () => {
    setDropdownVisible(!dropdownVisible);
  };
  // console.log("dropdownVisible:", dropdownVisible);
  // console.log("session:", session);
  const handleSignout = () => {
    signOut();
  };
  const renderUserMenu = () => {
    if (!session) {
      return (
        <Link href="/login" className={`${styles.gradientbutton} nav-link text-dark font-roboto custom-bold`} onClick={() => signIn("your-auth-provider")}>
          Sign In</Link>
      );
    } else {
      return (
        <div className={`${styles.gradientbutton} user-menu`}>
          <img 
            src={session.user.image}
            alt={session.user.name}
            onClick={() => {
              console.log("Image clicked");
              toggleDropdown();
            }}
          />
          {dropdownVisible && (
            <div className={`dropdownMenu ${dropdownVisible ? 'visible' : ''}`}>
              <div className={styles.dropDown}>
                <label tabIndex={0} className="btn btn-active text-white btn-circle">
                  <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h7" /></svg>
                </label>
                <ul tabIndex={0} className={`${styles.dropdownCustom} menu menu-compact dropdown-content p-2 shadow text-black bg-gray-50 rounded-box w-52`}>
                  <li  >
                    <Link className={styles.LinkDropDown} href="/user/profile">Profile</Link>
                  </li>
                  <li><Link href="" className={styles.LinkDropDown} onClick={handleSignout}>Log Out</Link></li>
                  <li><Link className={styles.LinkDropDown} href={"/order/view-orders"}>My Orders</Link></li>
                  <li><Link className={styles.LinkDropDown} href={"/Dashboard"}>Dashboard</Link></li>
                </ul>
              </div>

            </div>
          )}
        </div>
      );
    }
  };

  useEffect(() => {
    const nav = document.querySelector('nav');
    if (nav) {
      window.addEventListener('scroll', function () {
        if (window.pageYOffset > 100) {
          nav.style.backgroundColor = 'rgba(255, 255, 255, 0.8)';
          nav.classList.add('shadow');
        } else {
          nav.style.backgroundColor = 'transparent';
          nav.classList.remove('shadow');
        }
      });
    }
  }, []);

  return (
    <nav className={styles.navbar}>
      <nav  className="navbar fixed-top navbar-expand-lg navbar-dark p-md-3 transparent-navbar">
        <div className={`${styles.logoContainer} container`} >
          <Link href="/" className="navbar-brand mx-auto">
            <Image src="/logo.png" alt="Chain App Dev" width={96} height={45}  />
          </Link>
        </div>
        <div className="collapse navbar-collapse" id="navbarNav">
          <div className="container-fluid ml-auto mr-0">
            <ul className="navbar-nav ">
              <NavLink text="Home" />
              <NavLink text="About" />
              <NavLink text="Blog" />
              <NavLink text="Pricing" />
              <NavLink text="Contact" />
              {/* <li>
                <div className="nav-item gradient-button">
                  {session ? (
                    <UserLoggedIn session={session} signOut={signOut} />
                  ) : (
                    <UserNotLoggedIn signIn={signIn} />
                  )}
                </div>
              </li> */}
              <div className="user-section">{renderUserMenu()}</div>

            </ul>
          </div>
        </div>
      </nav>
    </nav>
  );
}
