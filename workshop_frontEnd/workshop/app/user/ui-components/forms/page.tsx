// 'use client'
// import React, { useState, useEffect } from 'react';
// import {
//   Paper,
//   Grid,
//   TextField,
//   Button,
//   createTheme,
//   ThemeProvider,
// } from '@mui/material';
// import { useSession } from 'next-auth/react';

// const lightTheme = createTheme({ palette: { mode: 'light' } });

// const EditProfile = () => {
//   const { data: session } = useSession();
//   const [updatedUser, setUpdatedUser] = useState({
//     full_name: '',
//     user_name: '',
//     email: '',
//     phoneNumber: '',
//   });

//   const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
//     const { name, value } = e.target;
//     setUpdatedUser((prevState) => ({
//       ...prevState,
//       [name]: value,
//     }));
//   };

//   const handleEditUser = async () => {
//     try {
//       if (session) {
//         const response = await fetch('http://localhost:8089/auth/user/edit', {
//           method: 'PUT',
//           headers: {
//             'Content-Type': 'application/json',
//           },
//           body: JSON.stringify({ ...updatedUser, id: session.user.id }),
//         });

//         if (response.ok) {
//           // Xử lý thành công (ví dụ: hiển thị thông báo thành công)
//         } else {
//           // Xử lý lỗi (ví dụ: hiển thị thông báo lỗi)
//         }
//       } else {
//         // Xử lý trường hợp khi session là null (ví dụ: hiển thị thông báo lỗi)
//       }
//     } catch (error) {
//       console.error('Lỗi:', error);
//     }
//   };


//   return (
//     <ThemeProvider theme={lightTheme}>
//       <Grid container spacing={3}>
//         <Grid item xs={12} lg={12}>
//           <div>
//             <h1>Edit Profile</h1>
//             <form>
//               <TextField
//                 type="text"
//                 name="full_name"
//                 label="Full Name"
//                 variant="outlined"
//                 value={updatedUser.full_name}
//                 onChange={handleInputChange}
//               />
//               <TextField
//                 type="text"
//                 name="user_name"
//                 label="User Name"
//                 variant="outlined"
//                 value={updatedUser.user_name}
//                 onChange={handleInputChange}
//               />
//               <TextField
//                 name="email"
//                 label="Email"
//                 variant="outlined"
//                 value={updatedUser.email}
//                 onChange={handleInputChange}
//               />
//               <TextField
//                 name="phoneNumber"
//                 label="Phone Number"
//                 variant="outlined"
//                 value={updatedUser.phoneNumber}
//                 onChange={handleInputChange}
//               />
//               <Button variant="contained" onClick={handleEditUser}>
//                 Submit
//               </Button>
//             </form>
//           </div>
//         </Grid>
//       </Grid>
//     </ThemeProvider>
//   );
// };

// export default EditProfile;
// 'use client'
// import React, { useState, useEffect } from 'react';
// import {
//   Grid,
//   TextField,
//   Button,
//   createTheme,
//   ThemeProvider,
// } from '@mui/material';
// import { useSession } from 'next-auth/react';

// const lightTheme = createTheme({ palette: { mode: 'light' } });

// const EditProfile = () => {
//   const [userData, setUserData] = useState({
//     full_name: '',
//     user_name: '',
//     email: '',
//     phoneNumber: '',
//     userAddresses: [
//       {
//         state: '',
//         city: '',
//         address: '',
//         postalCode: 0,
//       },
//     ],
//   });

//   const { data: session } = useSession();

//   useEffect(() => {
//     if (session) {
//       // Lấy thông tin người dùng từ API và cập nhật userData
//       fetch('http://localhost:8089/user/detail', {
//         headers: {
//           Authorization: `Bearer ${session?.user.accessToken}`,
//         },
//       })
//         .then((response) => response.json())
//         .then((data) => {
//           if (data && data.data) {
//             setUserData(data.data);
//           }
//         })
//         .catch((error) => {
//           console.error('Error fetching user data:', error);
//         });
//     }
//   }, [session]);
//   const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
//     const { name, value } = e.target;
//     setUserData({
//       ...userData,
//       [name]: value,
//     });
//   };

//   const handleAddressChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, addressIndex: number | undefined) => {
//     if (addressIndex !== undefined) {
//       const { name, value } = e.target;
//       setUserData((prevUserData) => {
//         const updatedAddresses = [...prevUserData.userAddresses];
//         updatedAddresses[addressIndex] = {
//           ...updatedAddresses[addressIndex],
//           [name]: value,
//         };
//         return {
//           ...prevUserData,
//           userAddresses: updatedAddresses,

//         };

//       });
//     }
//   };
//   console.log(userData.userAddresses);


//   const handleSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
//     e.preventDefault();
//     try {
//       const response = await fetch('http://localhost:8089/auth/user/edit', {
//         method: 'PUT',
//         headers: {
//           // Không cần gắn Bearer Token
//           // Authorization: `Bearer ${session?.user.accessToken}`,
//           'Content-Type': 'multipart/form-data',
//         },
//         body: new FormData()
//       });
//       // console.log(userData);

//       if (response.ok) {
//         console.log('User data updated successfully.');
//       } else {
//         console.error('Error updating user data:', response.status);
//       }
//     } catch (error) {
//       console.error('Error:', error);
//     }
//   };

//   return (
//     <ThemeProvider theme={lightTheme}>
//       <Grid container spacing={3}>
//         <Grid item xs={12} lg={12}>
//           <div>
//             <h1>Edit Profile</h1>
//             <form onSubmit={handleSubmit}>
//               <TextField
//                 type="text"
//                 label="Full Name"
//                 name="full_name"
//                 value={userData.full_name}
//                 onChange={handleInputChange}
//               />
//               <TextField
//                 type="text"
//                 label="User Name"
//                 name="user_name"
//                 value={userData.user_name}
//                 onChange={handleInputChange}
//               />
//               <TextField
//                 type="text"
//                 label="phone Number"
//                 name="phoneNumber"
//                 value={userData.phoneNumber}
//                 onChange={handleInputChange}
//               />
//               <TextField
//                 type="text"
//                 label="Email"
//                 name="email"
//                 value={userData.email}
//                 onChange={handleInputChange}
//               />
//               {userData.userAddresses.map((address, index) => (
//                 <div key={index}>
//                   <TextField
//                     type="text"
//                     label="State"
//                     name="state"
//                     value={address.state}
//                     onChange={(e) => handleAddressChange(e, index)}
//                   />
//                   <TextField
//                     type="text"
//                     label="City"
//                     name="city"
//                     value={address.city}
//                     onChange={(e) => handleAddressChange(e, index)}
//                   />
//                   <TextField
//                     type="text"
//                     label="Address"
//                     name="address"
//                     value={address.address}
//                     onChange={(e) => handleAddressChange(e, index)}
//                   />
//                   <TextField
//                     type="number"
//                     label="Postal Code"
//                     name="postalCode"
//                     value={address.postalCode}
//                     onChange={(e) => handleAddressChange(e, index)}
//                   />
//                 </div>
//               ))}
//               <button type="submit">Save</button>
//             </form>
//           </div>
//         </Grid>
//       </Grid>
//     </ThemeProvider>
//   );
// };

// export default EditProfile;

'use client'
import React, { useState, ChangeEvent, useEffect } from "react"
import { FirebaseDb } from ".././../../FireBase/Config"
import { getDownloadURL, ref, uploadBytes, uploadBytesResumable } from "firebase/storage"
import { v4 } from 'uuid';
import { Container } from "react-bootstrap";
import { UploadTaskSnapshot } from 'firebase/storage';
import {
  Paper,
  Grid,
  TextField,
  Button,
  createTheme,
  ThemeProvider,
} from '@mui/material';
import { useSession } from 'next-auth/react';
import styles from '../forms/form.module.css';


const lightTheme = createTheme({ palette: { mode: 'light' } });

const EditProfile = () => {
  const [inforUser, setInforUser] = useState("")
  const [userData, setUserData] = useState({
    full_name: '',
    user_name: '',
    email: '', // Thêm trường email
    phoneNumber: '',
    image_url: '',
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


  useEffect(() => {
    // console.log(session?.user.accessToken)
    if (session) {
      fetch('http://localhost:8089/user/detail', {
        headers: {
          Authorization: `Bearer ${session?.user.accessToken}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          if (data && data.data) {
            setUserData(data.data);
          }
        })
        .catch((error) => {
          console.error('Error fetching user data:', error);
        });
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
    } else if (name === 'image_url') {
      setUserData({ ...userData, image_url: value });
    }
  };

  // console.log(userData);

  // const handleAddressChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  //   const { name, value } = e.target;

  //   // Xác định các trường địa chỉ và cập nhật chúng
  //   if (name === 'state') {
  //     setUserData({
  //       ...userData,
  //       userAddresses: [{ ...userData.userAddresses[0], state: value }],
  //     });
  //   } else if (name === 'city') {
  //     setUserData({
  //       ...userData,
  //       userAddresses: [{ ...userData.userAddresses[0], city: value }],
  //     });
  //   } else if (name === 'address') {
  //     setUserData({
  //       ...userData,
  //       userAddresses: [{ ...userData.userAddresses[0], address: value }],
  //     });
  //   } else if (name === 'postalCode') {
  //     setUserData({
  //       ...userData,
  //       userAddresses: [{ ...userData.userAddresses[0], postalCode: parseInt(value, 10) }],
  //     });
  //   }
  // };
  // const handleAddressChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  //   const { name, value } = e.target;

  //   // Xác định các trường địa chỉ và cập nhật chúng
  //   if (name === 'state') {
  //     const newAddress = { ...userData.userAddresses[0]};

  //     // Kiểm tra xem userAddresses đã có thông tin hay chưa
  //     if (userData.userAddresses[0].id === 0) {
  //       // Chưa có thông tin userAddresses, gán id là 0
  //       newAddress.id = 0;
  //       newAddress.state = value;

  //       newAddress.city = value; // Cập nhật giá trị cho trường "city"
  //       newAddress.address = value; // Cập nhật giá trị cho trường "address"
  //       newAddress.postalCode = parseInt(value, 10);        
  //     } else {
  //       // Đã có thông tin userAddresses, tìm "id" và gán lại
  //       // Tìm "id" từ danh sách userAddresses, sử dụng logic thích hợp
  //       // Ví dụ: Tìm "id" từ danh sách userAddresses đã tồn tại
  //       const existingAddress = userData.userAddresses.find((address) => address.id === userData.userAddresses[0].id);
  //       if (existingAddress) {
  //         newAddress.id = existingAddress.id;
  //       }
  //     }


  //     setUserData({
  //       ...userData,
  //       userAddresses: [newAddress],
  //     });
  //   }
  //   }
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


  const handleSubmit = async (e: React.ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      if (session) {
        // const authToken = session.user.accessToken;
        // console.log(authToken);
        // console.log(userData);

        const response = await fetch('http://localhost:8089/auth/user/edit', {
          method: 'PUT',
          headers: {
            //  Authorization: `Bearer ${authToken}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(userData),
        });

        if (response.ok) {
          console.log('User data updated successfully.');
          console.log(userData);

        } else {
          console.error('Error updating user data:', response.status);
        }
      } else {
        console.error('Session is null. User is not authenticated.');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const [img, setImg] = useState<{ file: File | null, extension: string | undefined }>({
    file: null,
    extension: undefined,
  });
  const [imgUrl, setImgUrl] = useState<(string | { url: string, fileName: string })[]>([]);

  const handleImageUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    if (files) {
      const selectedImage = files[0];
      if (selectedImage) {
        const fileExtension = selectedImage.name.split('.').pop();
        console.log("File Extension:", fileExtension);
        setImg({ file: selectedImage, extension: fileExtension });
      } else {
        console.error("Không có tệp hình ảnh được chọn.");
      }
    } else {
      console.error("Không có tệp hình ảnh được chọn.");
    }
  }

  const handleClick = () => {
    if (img && img.file) {
      const { file, extension } = img;
      const fileName = `${v4()}.${extension}`;
      const imgRef = ref(FirebaseDb, `/user/${v4()}`);
      uploadBytes(imgRef, file).then((value) => {
        console.log("Tải lên thành công:", value);
        getDownloadURL(value.ref).then(url => {
          setImgUrl((data) => [...data, { url, fileName }]);
          console.log(url);

          // Lưu đường dẫn hình ảnh vào userData
          setUserData({ ...userData, image_url: url });
        });
      }).catch((error) => {
        console.error("Lỗi tải lên:", error);
      });
    }
  };

  return (
    <ThemeProvider theme={lightTheme}>
      <Grid container spacing={3}>
        <Grid item xs={12} lg={12}>
          <div  className={`${styles.formCustom} text-center`}>
            <h1>Edit Profile</h1>
            <form onSubmit={handleSubmit}>
              <div>
                <TextField
                  type="text"
                  label="Full Name"
                  name="full_name"
                  value={userData.full_name}
                  onChange={handleInputChange}
                  className={styles.inputCustom}
                />


                <TextField
                  type="text"
                  label="User Name"
                  name="user_name"
                  value={userData.user_name}
                  onChange={handleInputChange}
                  className={styles.inputCustom}
                />
              </div>
              <div>
                <TextField
                  type="text"
                  label="Email"
                  name="email"
                  value={userData.email}
                  onChange={handleInputChange}
                  className={styles.inputCustom}
                />

                <TextField
                  type="text"
                  label="Phone Number"
                  name="phoneNumber"
                  value={userData.phoneNumber}
                  onChange={handleInputChange}
                  className={styles.inputCustom}
                />
              </div>
              <div>
                <TextField
                  type="text"
                  label="Image URL"
                  name="image_url"
                  value={userData.image_url}
                  onChange={handleInputChange}
                  className={styles.inputCustom2}
                />
                <label className={styles.customFileInput}>
                  Choose File
                  <input
                    id="fileInput" // Gán id cho input
                    type="file"
                    accept="image/*"
                    onChange={handleImageUpload}
                  />
                </label>
              </div>
              <div>
                <TextField
                  type="text"
                  label="State"
                  name="state"
                  value={userData?.userAddresses[0]?.state}
                  onChange={handleAddressChange}
                  className={styles.inputCustom}
                />

                <TextField
                  type="text"
                  label="City"
                  name="city"
                  value={userData?.userAddresses[0]?.city}
                  onChange={handleAddressChange}
                  className={styles.inputCustom}
                />
              </div>
              <div>
                <TextField
                  type="text"
                  label="Address"
                  name="address"
                  value={userData?.userAddresses[0]?.address}
                  onChange={handleAddressChange}
                  className={styles.inputCustom}
                />

                <TextField
                  type="number"
                  label="Postal Code"
                  name="postalCode"
                  value={userData?.userAddresses[0]?.postalCode}
                  onChange={handleAddressChange}
                  className={styles.inputCustom}
                />
              </div>

              {/* <div>
                <button onClick={handleClick}>Upload Image</button>
                <button type="submit">Save</button>
              </div> */}
              <Button onClick={handleClick}
                className={styles.borderButton}>
                Upload Image
              </Button>
              <Button type="submit"
                className={styles.borderButton}>
                Save Profile
              </Button>
            </form>
          </div>
        </Grid>
      </Grid>
    </ThemeProvider>
  );

};
export default EditProfile;