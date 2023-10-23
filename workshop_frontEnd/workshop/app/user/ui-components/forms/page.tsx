'use client'
import React, { useState, useEffect } from 'react';
import {
  Paper,
  Grid,
  TextField,
  Button,
  createTheme,
  ThemeProvider,
} from '@mui/material';
import { useSession } from 'next-auth/react';

const lightTheme = createTheme({ palette: { mode: 'light' } });

const EditProfile = () => {
  const { data: session } = useSession();

  const [updatedUser, setUpdatedUser] = useState({
    full_name: '',
    user_name: '',
    email: '',
    phoneNumber: '',
    image_url: '',
    roles: '',
    userAddresses: [
      {
        state: '',
        address: '',
        city: '',
        postalCode: 0,
      },
    ],

  });

  useEffect(() => {
    if (session) {
      console.log(session);
      const { user } = session;
      setUpdatedUser({
        full_name: user.full_name || '',
        user_name: user.name || '',
        email: user.email || '',
        phoneNumber: user.phoneNumber || '',
        image_url: user.image || '',
        roles: user.roles || '',
        userAddresses: user.userAddresses || updatedUser.userAddresses,
      });
    }
  }, [session]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUpdatedUser((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleAddUserAddress = () => {
    setUpdatedUser((prevState) => ({
      ...prevState,
      userAddresses: [
        ...prevState.userAddresses,
        {
          state: '',
          address: '',
          postalCode: 0,
          city: '',
        },
      ],
    }));
  };

  const handleEditUser = async () => {
    try {
      console.log("updatedUser", updatedUser)
      const response = await fetch('http://localhost:8089/auth/user/edit', {
        method: 'PUT',
        headers: {
          Authorization: `Bearer ${session?.user.accessToken}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ ...updatedUser, id: session?.user.id }), // Thêm id người dùng vào dữ liệu cần cập nhật
      });

      if (response.ok) {

      } else {

      }
    } catch (error) {
      console.error('Đã xảy ra lỗi:', error);
    }
  };

  return (
    <ThemeProvider theme={lightTheme}>
      <Grid container spacing={3}>
        <Grid item xs={12} lg={12}>
          <div>
            <h1>Edit Profile</h1>
            <form>
              <TextField
                type="text"
                name="full_name"
                label="Full Name"
                variant="outlined"
                value={updatedUser.full_name}
                onChange={handleInputChange}
              />
              <TextField
                type="text"
                name="user_name"
                label="User Name"
                variant="outlined"
                value={updatedUser.user_name}
                onChange={handleInputChange}
              />
              <TextField
                name="email"
                label="Email"
                variant="outlined"
                value={updatedUser.email}
                onChange={handleInputChange}
              />
              <TextField
                name="phoneNumber"
                label="Phone Number"
                variant="outlined"
                value={updatedUser.phoneNumber}
                onChange={handleInputChange}
              />
              {updatedUser.userAddresses.map((address, index) => (
                <div key={index}>
                  <TextField
                    name={`userAddresses[${index}].state`}
                    label="State"
                    variant="outlined"
                    value={address.state}
                    onChange={(e) => handleUserAddressChange(e, index, 'state')}
                  />
                  <TextField
                    name={`userAddresses[${index}].address`}
                    label="Address"
                    variant="outlined"
                    value={address.address}
                    onChange={(e) => handleUserAddressChange(e, index, 'address')}
                  />
                  <TextField
                    name={`userAddresses[${index}].city`}
                    label="City"
                    variant="outlined"
                    value={address.city}
                    onChange={(e) => handleUserAddressChange(e, index, 'city')}
                  />
                  <TextField
                    name={`userAddresses[${index}].postalCode`}
                    label="Postal Code"
                    variant="outlined"
                    value={address.postalCode}
                    onChange={(e) => handleUserAddressChange(e, index, 'postalCode')}
                  />
                </div>
              ))}

              <Button variant="contained" onClick={handleAddUserAddress}>
                Add User Address
              </Button>
              <Button variant="contained" onClick={handleEditUser}>
                Submit
              </Button>
            </form>
          </div>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
};

export default EditProfile;
