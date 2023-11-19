'use client'
import React, { useState, useEffect, ChangeEvent } from 'react';
import ApiService from '@/app/services/ApiService'; // Thay đổi đường dẫn tới ApiService của bạn
import { useSession } from 'next-auth/react';

interface AdminData {
  id: number;
  full_name: string;
  user_name: string;
  email: string;
  phoneNumber: string;
  image_url: string | null;
  balance: number | null;
  gender: string;
  roles: string[];
  enable: boolean;
  userAddresses: any[]; // Thay thế any bằng kiểu dữ liệu thích hợp
  userBank: any[]; // Thay thế any bằng kiểu dữ liệu thích hợp
}

const UpdateAdminPage: React.FC = () => {
  const [adminId, setAdminId] = useState<number>(0);
  const { data: session } = useSession();
  const apiService = new ApiService(session);
  const [adminData, setAdminData] = useState<AdminData | null>(null);

  useEffect(() => {
    // Gọi API để lấy thông tin admin khi component được mount
    const fetchAdminData = async () => {
      try {
        const result = await apiService.getUserbyIdAdmin(adminId);
        setAdminData(result);
      } catch (error) {
        console.error('Error fetching admin data:', error);
      }
    };

    fetchAdminData();
  }, [adminId]); // Chạy lại effect khi adminId thay đổi


  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setAdminData((prevData) => ({
      ...(prevData as AdminData),
      [name]: value,
    }));
  };

  const handleUpdateAdmin = async () => {
    try {
      if (adminData) {
        // Gọi phương thức cập nhật admin từ ApiService
        const result = await apiService.updateAdminDetails(adminData.id, adminData);
        console.log('Admin updated:', result);
      }
    } catch (error) {
      console.error('Error updating admin:', error);
    }
  };

  if (!adminData) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h2>Update Admin</h2>
      <label>
        Admin ID:
        <input type="text" value={adminId} onChange={(e) => setAdminId(Number(e.target.value))} />
      </label>
      <br />
      <label>
        Name:
        <input type="text" name="name" value={adminData.full_name} onChange={handleInputChange} />
      </label>
      <br />
      <label>
        Email:
        <input type="text" name="email" value={adminData.email} onChange={handleInputChange} />
      </label>
      <br />
      {/* Thêm các trường khác cần cập nhật */}
      <button onClick={handleUpdateAdmin}>Update Admin</button>
    </div>
  );
};

export default UpdateAdminPage;
