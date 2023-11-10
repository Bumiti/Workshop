"use client"
import ApiService from '@/app/services/ApiService';
import Pagination from "@/app/admin/ui/dashboard/pagination/pagination";
import Search from "@/app/admin/ui/dashboard/search/search";
import styles from "@/app/admin/ui/dashboard/users/users.module.css";
import Image from "next/image";
import Link from "next/link";
import { Button, Container, Table, Modal, Card } from 'react-bootstrap';
import React, { useEffect, useState } from 'react';
import { useSession } from 'next-auth/react';


interface UserData {
  id: number;
  full_name: string;
  user_name: string;
  email: string;
  phoneNumber: string;
  image_url: string | null;
  gender: string;
  roles: string[];
  enable: boolean;
  userAddresses: UserAddress[];
}

interface UserAddress {
  id: number | null;
  state: string;
  address: string;
  city: string;
  postalCode: number;
}



const UsersPage = () => {
  const { data: session } = useSession();
  const apiService = new ApiService(session);
  const [users, setUsers] = useState<UserData[]>([]);

  useEffect(() => {
    if (session) {
      const fetchData = async () => {
        try {
          const listAccountResponse = await apiService.listAccountAdmin();
          if (listAccountResponse.data) {
            setUsers(listAccountResponse.data);
          }
        } catch (error) {
          console.error("Error:", error);
        }
      };
      fetchData();
    }
  }, [session]);

  const handleButtonClick = (id: number) => {
    apiService.changeStatusAccount(id).then(() => {
      setUsers((prevUsers) => {
        return prevUsers.map((user) => {
          if (user.id === id) {
            user.enable = !user.enable;
          }
          return user;
        });
      });
    });
  };



  return (
    <div className={styles.container}>
      <div className={styles.top}>
        <Search placeholder="Search for a user..." />
        <Link href="/admin/dashboard/users/add">
          <button className={styles.addButton}>Add New</button>
        </Link>
      </div>
      {users.length > 0 && (
        <table className={styles.table}>
          <thead>
            <tr>
              <td>Name</td>
              <td>Email</td>
              <td>Phone Number</td>
              <td>Role</td>
              <td>Status</td>
              <td>Action</td>
            </tr>
          </thead>
          <tbody>
            {users.map((user, index) => (
              <tr key={user.id}>
                <td>
                  <div className={styles.user}>
                    <Image
                      src={"/noavatar.png"}
                      alt=""
                      width={40}
                      height={40}
                      className={styles.userImage}
                    />
                    {user.full_name}
                  </div>
                </td>
                <td>{user.email}</td>
                <td>{user.phoneNumber}</td>
                <td>{user.roles}</td>
                <td>{user.enable ? 'Actived' : 'Passived'}</td>
                <td>
                  <div className={styles.buttons}>
                    {/*${user.id} bỏ vô link dưới*/}
                    <Link href={`/admin/dashboard/users/${user.id}`}>
                      <button className={`${styles.button} ${styles.view}`}>
                        View
                      </button>
                    </Link>
                    {/*action={deleteUser} bỏ vào form*/}
                    <form>
                      {/*value={(user.id)}*/}
                      <input type="hidden" name="id" />
                      <button className={`${styles.button} ${styles.delete}`} onClick={(e) => {
                        e.stopPropagation(); // Stop propagation to prevent the row click event
                        handleButtonClick(user.id);
                      }}>
                        Change Status
                      </button>
                    </form>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default UsersPage;
