'use client'
import React, { useState, useEffect } from "react";
import { FirebaseDb } from "@/utils/FireBase/Config";
import { getDownloadURL, ref, uploadBytes } from "firebase/storage";
import { v4 } from 'uuid';
import { useSession } from "next-auth/react";
import ApiService from "@/app/services/ApiService";
import styles from "./updateprofile.module.css";
import Image from "next/image";
import Swal from 'sweetalert2';

const EditAdminPage = () => {
  const [userData, setUserData] = useState({
    full_name: '',
    user_name: '',
    email: '',
    phoneNumber: '',
    image_url: '',
    userAddresses:
      [
        {
          id: 0,
          state: '',
          city: '',
          address: '',
          postalCode: 0,
        },
      ],
    userBank:
      [
        {
          id: 0,
          bankName: '',
          bankAccount: '',
        },
      ],
  });

  const { data: session } = useSession();
  const apiService = new ApiService(session);

  useEffect(() => {
    if (session) {
      setTimeout(() => {
        apiService.getUserDetails().then((data) => {
          if (data && data.data) {
            setUserData(data.data);
          }
        }).catch((error) => {
          console.error('Error fetching user data:', error);
        });
      }, 3000); // Đợi 1 giây trước khi lấy lại dữ liệu
    }
  }, [session]);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    // Xác định các trường dữ liệu và cập nhật chúng
    if (name === 'email') {
      setUserData({ ...userData, email: value });
    } else if (name === 'full_name') {
      setUserData({ ...userData, full_name: value });
    } else if (name === 'user_name') {
      setUserData({ ...userData, user_name: value });
    } else if (name === 'phoneNumber') {
      setUserData({ ...userData, phoneNumber: value });
    }
  };

  const handleAddressChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    if (userData.userAddresses && userData.userAddresses.length > 0) {
      // Đã có thông tin userAddresses, tìm "id" và gán lại
      let existingAddress = userData.userAddresses[0];
      if (!existingAddress) {
        existingAddress = {
          id: 0,
          state: '',
          city: '',
          address: '',
          postalCode: 0,
        };
      }

      if (name === 'id') {
        existingAddress.id = parseInt(value, 10);
      } else if (name === 'state') {
        existingAddress.state = value || ''; // Sử dụng giá trị của `value`, nếu nó là `undefined`, thì gán bằng chuỗi rỗng
      } else if (name === 'city') {
        existingAddress.city = value || ''; // Tương tự cho các trường khác
      } else if (name === 'address') {
        existingAddress.address = value || '';
      } else if (name === 'postalCode') {
        existingAddress.postalCode = value ? parseInt(value, 10) : 0;
      }

      setUserData({
        ...userData,
        userAddresses: [existingAddress],
      });
    } else {
      // Chưa có thông tin userAddresses, tạo một bản ghi mới
      const newAddress: {
        id: number;
        state: string; // Đảm bảo rằng trường state luôn là chuỗi
        city: string;
        address: string;
        postalCode: number;
      } = {
        id: 0,
        state: '',
        city: '',
        address: '',
        postalCode: 0,
      };
      if (name === 'state') {
        newAddress.state = value || ''; // Sử dụng giá trị của `value`, nếu nó là `undefined`, thì gán bằng chuỗi rỗng
      } else if (name === 'city') {
        newAddress.city = value || ''; // Tương tự cho các trường khác
      } else if (name === 'address') {
        newAddress.address = value || '';
      } else if (name === 'postalCode') {
        newAddress.postalCode = value ? parseInt(value, 10) : 0;
      }

      setUserData({
        ...userData,
        userAddresses: [newAddress],
      });
    }
  }

  const handleBankChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    if (userData.userBank && userData.userBank.length > 0) {
      // Đã có thông tin userAddresses, tìm "id" và gán lại
      let existingBank = userData.userBank[0];
      if (!existingBank) {
        existingBank = {
          id: 0,
          bankName: '',
          bankAccount: '',
        };
      }

      if (name === 'id') {
        existingBank.id = parseInt(value, 10);
      } else if (name === 'bankName') {
        existingBank.bankName = value || ''; // Sử dụng giá trị của `value`, nếu nó là `undefined`, thì gán bằng chuỗi rỗng
      } else if (name === 'bankAccount') {
        existingBank.bankAccount = value || ''; // Tương tự cho các trường khác
      }

      setUserData({
        ...userData,
        userBank: [existingBank],
      });
    } else {
      // Chưa có thông tin userAddresses, tạo một bản ghi mới
      const newBank: {
        id: number;
        bankName: string,
        bankAccount: string,
      } = {
        id: 0,
        bankName: '',
        bankAccount: '',
      };
      if (name === 'bankName') {
        newBank.bankName = value || ''; // Sử dụng giá trị của `value`, nếu nó là `undefined`, thì gán bằng chuỗi rỗng
      } else if (name === 'bankAccount') {
        newBank.bankAccount = value || ''; // Tương tự cho các trường khác
      }

      setUserData({
        ...userData,
        userBank: [newBank],
      });
    }
  }

  const handleSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      console.log("userData trước khi gửi form:", userData);
      if (session) {
        const response = await apiService.editUserProfile(userData);
        console.log("Phản hồi từ API:", response);

        if (response) {
          console.log("Dữ liệu người dùng đã được cập nhật thành công.");
          await Swal.fire({
            title: "Success!",
            text: "Profile updated successfully!",
            icon: "success",
          });
        } else {
          console.error("Lỗi khi cập nhật dữ liệu người dùng.");
        }
      } else {
        console.error("Phiên là null. Người dùng không được xác thực.");
      }
    } catch (error) {
      console.error("Lỗi:", error);
    }
  };



  const handleImageUpload = async (event: React.ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    if (files) {
      const selectedImage = files[0];
      if (selectedImage) {
        const imgRef = ref(FirebaseDb, `/user/${v4()}`);
        try {
          const uploadTask = await uploadBytes(imgRef, selectedImage);
          const url = await getDownloadURL(uploadTask.ref);
          setUserData({ ...userData, image_url: url });
          console.log(url);
        } catch (error) {
          console.error("Lỗi tải lên:", error);
        }
      } else {
        console.error("Không có tệp hình ảnh được chọn.");
      }
    } else {
      console.error("Không có tệp hình ảnh được chọn.");
    }
  };

  console.log("userData nè:", userData);
  console.log("userData.image_url", userData.image_url);

  return (
    <div>
      <h1>Edit Admin Profile</h1>
      <div className={styles.container}>
        <div className={styles.infoContainer}>
          <div className={styles.imgContainer}>
            <Image src={userData.image_url || "/noavatar.png"} alt="" fill />
          </div>
          <label>Choose File: </label>
          <input
            type="file"
            accept="image/*"
            onChange={handleImageUpload}
          />
        </div>
        <div className={styles.formContainer}>
          <form className={styles.form} onSubmit={handleSubmit}>
            <label>Full Name: </label>
            <input
              type="text"
              name="full_name"
              value={userData.full_name}
              onChange={handleInputChange}
            />
            <label>User Name: </label>
            <input
              type="text"
              name="user_name"
              value={userData.user_name}
              onChange={handleInputChange}
            />

            <label>Email:</label>
            <input
              type="text"
              name="email"
              value={userData.email}
              onChange={handleInputChange}
              disabled
            />

            <label>Phone Number:</label>
            <input
              type="text"
              name="phoneNumber"
              value={userData.phoneNumber}
              onChange={handleInputChange}
            />

            <label>State:</label>
            <input
              type="text"
              name="state"
              value={userData?.userAddresses[0]?.state}
              onChange={handleAddressChange}
            />

            <label>City:</label>
            <input
              type="text"
              name="city"
              value={userData?.userAddresses[0]?.city}
              onChange={handleAddressChange}
            />


            <label>Address:</label>
            <input
              type="text"
              name="address"
              value={userData?.userAddresses[0]?.address}
              onChange={handleAddressChange}
            />

            <label>Postal Code:</label>
            <input
              type="number"
              name="postalCode"
              value={userData?.userAddresses[0]?.postalCode}
              onChange={handleAddressChange}
            />

            <label>Bank Name:</label>
            <input
              type="text"
              name="bankName"
              value={userData?.userBank[0]?.bankName}
              onChange={handleBankChange}
            />

            <label>Bank Account:</label>
            <input
              type="text"
              name="bankAccount"
              value={userData?.userBank[0]?.bankAccount}
              onChange={handleBankChange}
            />
            <button type="submit">Save Profile</button>
          </form>
        </div>
      </div>

    </div>
  );
};

export default EditAdminPage;
