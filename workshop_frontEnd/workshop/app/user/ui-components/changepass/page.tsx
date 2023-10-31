'use client'
import React, { useState, ChangeEvent, useEffect } from "react"
import { Grid, TextField, Button} from '@mui/material';
import { useSession } from 'next-auth/react';
import styles from '../forms/form.module.css';
import { Dialog, DialogTitle, DialogContent, DialogActions } from '@mui/material';
import{ createTheme, ThemeProvider }from "@mui/material/styles";
const lightTheme = createTheme({ palette: { mode: 'light' } });

const ChangePassword = () => {
  const [oldPassword, setOldPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const [isAlertOpen, setIsAlertOpen] = useState(false);
  const [alertMessage, setAlertMessage] = useState('');
  const [alertTitle, setAlertTitle] = useState('');
  const { data: session } = useSession();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    if (name === 'oldPassword') {
      setOldPassword(value);
    } else if (name === 'newPassword') {
      setNewPassword(value);
    }
  };

  const handleCloseAlert = () => {
    setIsAlertOpen(false);
    setAlertMessage('');
    setAlertTitle('');
  };
  // interface AlertProps {
  //   message: string;
  //   title?: string;
  //   onClose: () => void;
  // }
  
  const showCustomAlert = (message: string, title: string = 'Alert') => {
    setAlertMessage(message);
    setAlertTitle(title);
    setIsAlertOpen(true);
  };
  // const CustomAlert: React.FC<AlertProps> = ({ message, title, onClose }) => {
  //   return (
  //     <div className="alert">
  //       <div className="alert-title">{title}</div>
  //       <div className="alert-message">{message}</div>
  //       <button onClick={onClose}>OK</button>
  //     </div>
  //   );
  // };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      console.log(session);
      if (session) {
        const url = new URL('http://localhost:8089/user/changePassword');
        url.searchParams.append('oldPassword', oldPassword);
        url.searchParams.append('newPassword', newPassword);

        const response = await fetch(url.href, {
          method: 'PUT',
          headers: {
            'Authorization': `Bearer ${session?.user.accessToken}`,
            'Content-Type': 'application/json',
            'accept': '*/*',
          }
        });
        if (response.status === 204) {
          console.error('The old password is incorrect.');
          setErrorMessage('The old password is incorrect. Please check again.');
        } else {
          console.log('The password has been changed successfully.');
          setSuccessMessage('');
          showCustomAlert('The password has been changed successfully.');
          setIsAlertOpen(true);
        }
        // if (response.ok) {
        //   // console.log('Mật khẩu đã được thay đổi.');
        // } else {
        //   if (response.status === 204) {
        //     console.error('Mật khẩu cũ không đúng.');
        //     setErrorMessage('Mật khẩu cũ không đúng. Vui lòng kiểm tra lại.');
        //   } else {
        //     console.error('Lỗi khi thay đổi mật khẩu:', response.status);
        //     setErrorMessage('Lỗi khi thay đổi mật khẩu. Vui lòng thử lại sau.');
        //   }
        // }
      } else {
        console.error('Session is null. User is not authenticated.');
      }
    } catch (error) {
      console.error('Lỗi:', error);
    }
  };




  return (
    <ThemeProvider theme={lightTheme}>
      <Grid container spacing={3}>
        <Grid item xs={12} lg={12}>
        {/* <Dialog className={styles.customAlert} open={isAlertOpen} onClose={handleCloseAlert}>
            <DialogTitle>SUCCESS</DialogTitle>
            <DialogContent >
              <p>{alertMessage}</p>
            </DialogContent>
            <DialogActions>
              <Button onClick={handleCloseAlert}>OK</Button>
            </DialogActions>
          </Dialog> */}
          <div className={`${styles.formCustom} text-center`}>
            <h1 className={styles.h1Custom}>Change Password</h1>
            <form onSubmit={handleSubmit}>
              <div>
                <TextField
                  type="password"
                  label="Old Password"
                  name="oldPassword"
                  value={oldPassword}
                  onChange={handleChange}
                  className={styles.inputCustom}
                />
                {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
              </div>
              <div>
                <TextField
                  type="password"
                  label="New Password"
                  name="newPassword"
                  value={newPassword}
                  onChange={handleChange}
                  className={styles.inputCustom}
                />
              </div>
              <Button type="submit" className={styles.borderButton}>
                Change Password
              </Button>
              <Dialog
                classes={{ paper: styles.customAlert }} // Áp dụng lớp CSS vào Dialog
                open={isAlertOpen}
                onClose={handleCloseAlert}
              >
                <DialogTitle>SUCCESS</DialogTitle>
                <DialogContent>
                  <p>{alertMessage}</p>
                </DialogContent>
                <DialogActions>
                  <Button onClick={handleCloseAlert}>OK</Button>
                </DialogActions>
              </Dialog>
            </form>
          </div>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
};

export default ChangePassword;
