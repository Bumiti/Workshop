'use client';
import ApiService from "@/app/services/ApiService";
import { Grid, Typography, TextField, Button, Card, CardContent, Box } from "@mui/material";import { useSession } from 'next-auth/react';
import React, { useEffect, useState } from "react";
import styles from './withdraw.module.css';


const WithdrawPage = () => {
  const [amount, setAmount] = useState(0);
  const [withdrawalInfo, setWithdrawalInfo] = useState({
    amount: 0,
    type: 'CASH',
    paymentName: 'CASH',
  });
  const [userData, setUserData] = useState({
    full_name: '',
    user_name: '',
    email: '',
    phoneNumber: '',
    image_url: '',
    balance:0,
    userAddresses: [
        {
            id: 0,
            state: '',
            city: '',
            address: '',
            postalCode: 0,
        },
    ],
});

  const { data: session } = useSession();
  const apiService = new ApiService(session);
  const handleWithdraw = async () => {
    try {
      // Kiểm tra nếu số tiền hợp lệ (ví dụ: lớn hơn 0)
      if (withdrawalInfo.amount > 0) {
        // const user = /* Lấy thông tin người dùng (nếu cần) */;
        const result = await apiService.withdraw(withdrawalInfo.amount, withdrawalInfo.type, withdrawalInfo.paymentName);
        console.log('result né',result);
        
        if (result.status === 'Success') {
          console.log('Rút tiền thành công!');
          const updatedUserData = await apiService.getUserDetails();
        if (updatedUserData && updatedUserData.data) {
          setUserData(updatedUserData.data);
          console.log('User data updated successfully:', updatedUserData.data);
        }
        } else {
          console.log('Rút tiền thất bại!');
          // Thực hiện các hành động cần thiết khi rút tiền thất bại
        }
      } else {
        console.log('Số tiền không hợp lệ!');
        // Thực hiện xử lý khi số tiền không hợp lệ
      }
    } catch (error) {
      // Xử lý lỗi nếu cần
      console.error('Lỗi khi thực hiện yêu cầu rút tiền:', error);
    }
  };


  useEffect(() => {
    const fetchUserData = async () => {
      try {
        if (session) {
          const data = await apiService.getUserDetails();
          if (data && data.data) {
            setUserData(data.data);
            console.log('User data fetched successfully:', data.data);
          }
        }
      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    };

    fetchUserData();
  }, [session]);
console.log(userData);

return (
  <Grid container spacing={0} className={styles.container}>
    <Card className={styles.pricingItemRegular}>
      <CardContent>
        <Typography variant="h2">Withdraw Money</Typography>
        <div className={styles.marginTop2}>
          <Typography  variant="h4">Current Balance: {userData.balance}</Typography>
        </div>
        <div className={styles.marginTop2}>
          <TextField
            type="number"
            label="Enter the amount."
            value={withdrawalInfo.amount}
            onChange={(e) => setWithdrawalInfo({ ...withdrawalInfo, amount: parseInt(e.target.value, 10) || 0 })}
          />
        </div>
        <div className={styles.marginTop4}>
          <Button className={styles.gradientbutton}  onClick={handleWithdraw}>
          Confirm withdrawal
          </Button>
        </div>
      </CardContent>
    </Card>
  </Grid>
);
};
export default WithdrawPage;