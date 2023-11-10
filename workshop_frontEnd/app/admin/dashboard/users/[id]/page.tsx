'use client'
import ApiService from '@/app/services/ApiService';
import styles from "@/app/admin/ui/dashboard/users/singleUser/singleUser.module.css";
import Image from "next/image";
import React, { useEffect, useState } from 'react';
import { useSession } from 'next-auth/react';
import getUserById from '@/utils/helper/helper'
import { useRouter } from 'next/router';

// import {listAccountAdmin} from "@/app/services/ApiService"


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

const SingleUserPage = ({ params }) => {
  const { id } = params;
  const { data: session } = useSession();
  const apiService = new ApiService(session);
  const [user, setUser] = useState<UserData | null>(null);
  const [listdUser, setListdUser] = useState<UserData[] >([] );

  useEffect(() => {
    if (session) {
      const fetchData = async () => {
          try {
            const usersResponse = await apiService.listAccountAdmin(); 
            if(usersResponse.data){
              setListdUser(usersResponse.data);
            }
          } catch (error) {
            console.error("Error:", error);
          }
      };
      const bindingUser = async ()=>{
        const user = getUserById(id,listdUser);
      }
      fetchData();
      bindingUser();
    }
  }, [session, id]);

  if (!user) {
    return <div>Loading...</div>; // Xử lý trạng thái loading hoặc hiển thị thông báo lỗi nếu user không được tìm thấy
  }

  return (

    <div className={styles.container}>
      <div className={styles.infoContainer}>
        <div className={styles.imgContainer}>
          <Image alt='' src={user.image_url || "/noavatar.png"} fill />
        </div>

      </div>
      <div className={styles.formContainer}>
        <form className={styles.form}>
          <input type="hidden" name="id" value={user.id} />
          <label>Username</label>
          <input type="text" name="username" value={user.full_name} />
          <label>Email</label>
          <input type="email" name="email" value={user.email} />
          <label>Password</label>
          <input type="password" name="password" />
          <label>Phone</label>
          <input type="text" name="phone" value={user.phoneNumber} />
          <label>Address</label>
          <textarea name="address" placeholder='address' />
          <label>{user.roles}</label>
          <select name="isAdmin" id="isAdmin">
            <option selected>Yes</option>
            <option selected>No</option>
          </select>
          <label>{user.enable}</label>
          <select name="isActive" id="isActive">
            <option selected>Yes</option>
            <option selected>No</option>
          </select>
          <button>Update</button>
        </form>
      </div>

    </div>
  );
};

export default SingleUserPage;
