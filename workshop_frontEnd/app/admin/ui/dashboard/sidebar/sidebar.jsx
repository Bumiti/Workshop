'use client'
import Image from "next/image";
import MenuLink from "./menuLink/menuLink";
import styles from "./sidebar.module.css";
import { useSession, signIn, signOut } from "next-auth/react";
import { SiGoogleclassroom } from "react-icons/si";
import * as React from 'react';

import {
  MdDashboard,
  MdSupervisedUserCircle,
  MdShoppingBag,
  MdAttachMoney,
  MdWork,
  MdAnalytics,
  MdPeople,
  MdOutlineSettings,
  MdHelpCenter,
  MdLogout,
  MdRequestQuote
} from "react-icons/md";
import Link from "next/link";

const menuItems = [
  {
    title: "Pages",
    list: [
      {
        title: "Dashboard",
        path: "/admin/dashboard",
        icon: <MdDashboard />,
      },
      {
        title: "Users",
        path: "/admin/dashboard/users",
        icon: <MdSupervisedUserCircle />,
      },
      {
        title: "Workshop",
        path: "/admin/dashboard/workshop",
        icon: <SiGoogleclassroom />,
      },
      {
        title: "Request",
        path: "/admin/dashboard/request",
        icon: <MdRequestQuote />,
      },
      {
        title: "Transaction",
        path: "/admin/dashboard/transaction",
        icon: <MdAttachMoney />,
      },
    ],
  },
  {
    title: "Analytics",
    list: [
      {
        title: "Revenue",
        path: "/admin/dashboard/revenue",
        icon: <MdWork />,
      },
      {
        title: "Reports",
        path: "/admin/dashboard/reports",
        icon: <MdAnalytics />,
      },
      {
        title: "Teams",
        path: "/admin/dashboard/teams",
        icon: <MdPeople />,
      },
    ],
  },
  {
    title: "User",
    list: [
      {
        title: "Settings",
        path: "/admin/dashboard/settings",
        icon: <MdOutlineSettings />,
      },
      {
        title: "Help",
        path: "/dashboard/help",
        icon: <MdHelpCenter />,
      },
    ],
  },
];

const Sidebar = () => {
  const { data: session } = useSession();
  console.log(session?.user);
  const [showProfileDropdown, setShowProfileDropdown] = React.useState(false);

  const handleProfileClick = () => {
    setShowProfileDropdown(!showProfileDropdown);
  };

  let menuRef = React.useRef();

  React.useEffect(() => {
    let handler = (e) => {
      if (!menuRef.current.contains(e.target)) {
        setShowProfileDropdown(false);
      }
    };
    document.addEventListener("mousedown", handler)
  })


  return (
    <div className={styles.container} ref={menuRef}>
      <div className={styles.user} onClick={handleProfileClick}>
        <Image
          className={styles.userImage}
          src={session?.user?.image || session?.user?.picture || "/noavatar.png"}
          alt=""
          width="50"
          height="50"
        />
        <div className={styles.userDetail}>
          <span className={styles.username}>{session?.user?.full_name}</span>
          <span className={styles.userTitle}>Administrator</span>
        </div>
        {showProfileDropdown && (
          <div className={styles.profileDropdown}>
            <Link href={'/admin/profile'}>Profile</Link>
            <br />
            <Link href={'/admin/settings'}>Setting</Link>
            <br />
            <Link href={'/'} onClick={() => session ? signOut() : signIn()}>
              Logout
            </Link>
          </div>
        )}
      </div>
      <ul className={styles.list}>
        {menuItems.map((cat) => (
          <li key={cat.title}>
            <span className={styles.cat}>{cat.title}</span>
            {cat.list.map((item) => (
              <MenuLink item={item} key={item.title} />
            ))}
          </li>
        ))}
      </ul>
      <button className={styles.logout} onClick={() => session ? signOut() : signIn()}>
        <MdLogout />
        Logout
      </button>
    </div>
  );
};

export default Sidebar;
