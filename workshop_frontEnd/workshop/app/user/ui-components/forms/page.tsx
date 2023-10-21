// 'use client';
// import {
//     Paper,
//     Grid,
//     Stack,
//     TextField,
//     Checkbox,
//     FormGroup,
//     FormControlLabel,
//     RadioGroup,
//     Radio,
//     FormLabel,
//     FormControl,
//     Button,
// } from '@mui/material'
// import BaseCard from'@/app/user/shared/BaseCard';
// import { createTheme, ThemeProvider, styled } from '@mui/material/styles';
// const Item = styled(Paper)(({ theme }) => ({
//     ...theme.typography.body1,
//     textAlign: 'center',
//     color: theme.palette.text.secondary,
//     height: 60,
//     lineHeight: '60px',
//   }));

// const darkTheme = createTheme({ palette: { mode: 'dark' } });
// const lightTheme = createTheme({ palette: { mode: 'light' } });

// const Forms = () => {
//     return (
//       <Grid container spacing={3}>
//         <Grid item xs={12} lg={12}>
//           <BaseCard title="Edit Profile">
//             <>
//             <Stack spacing={3}>
//               <TextField
//                 id="name-basic"
//                 label="Name"
//                 variant="outlined"
//                 defaultValue="Nirav Joshi"
//               />
//               <TextField id="email-basic" label="Email" variant="outlined" />
//               <TextField
//                 id="pass-basic"
//                 label="Password"
//                 type="password"
//                 variant="outlined"
//               />
//               <TextField
//                 id="outlined-multiline-static"
//                 label="Text Area"
//                 multiline
//                 rows={4}
//                 defaultValue="Default Value"
//               />
//               <TextField
//                 error
//                 id="er-basic"
//                 label="Error"
//                 defaultValue="ad1avi"
//                 variant="outlined"
//               />
//               <FormGroup>
//                 <FormControlLabel
//                   control={<Checkbox defaultChecked />}
//                   label="Terms & Condition"
//                 />
//                 <FormControlLabel
//                   disabled
//                   control={<Checkbox />}
//                   label="Disabled"
//                 />
//               </FormGroup>
//               <FormControl>
//                 <FormLabel id="demo-radio-buttons-group-label">Gender</FormLabel>
//                 <RadioGroup
//                   aria-labelledby="demo-radio-buttons-group-label"
//                   defaultValue="female"
//                   name="radio-buttons-group"
//                 >
//                   <FormControlLabel
//                     value="female"
//                     control={<Radio />}
//                     label="Female"
//                   />
//                   <FormControlLabel
//                     value="male"
//                     control={<Radio />}
//                     label="Male"
//                   />
//                   <FormControlLabel
//                     value="other"
//                     control={<Radio />}
//                     label="Other"
//                   />
//                 </RadioGroup>
//               </FormControl>
//             </Stack>
//             <br />
//             <Button>
//               Submit
//             </Button>
//             </>
//           </BaseCard>
//         </Grid>

//         {/* <Grid item xs={12} lg={12}>
//           <BaseCard title="Form Design Type">
//             <Stack spacing={3} direction="row">
//               <TextField
//                 id="outlined-basic"
//                 label="Outlined"
//                 variant="outlined"
//               />
//               <TextField id="filled-basic" label="Filled" variant="filled" />
//               <TextField
//                 id="standard-basic"
//                 label="Standard"
//                 variant="standard"
//               />
//             </Stack>
//           </BaseCard>
//         </Grid> */}
//       </Grid>
//     );
//   };
// export default Forms;

// 'use client';
// import React from "react";
// import { Formik, Field, Form } from "formik";
// import * as Yup from "yup";
// import {
//   Paper,
//   Grid,
//   Stack,
//   TextField,
//   Checkbox,
//   FormGroup,
//   FormControlLabel,
//   RadioGroup,
//   Radio,
//   FormLabel,
//   FormControl,
//   Button,
// } from "@mui/material";
// import BaseCard from "@/app/user/shared/BaseCard";
// interface User {
//   full_name: string;
//   email: string;
//   // Thêm các thuộc tính khác của đối tượng user
// }

// interface FormsProps {
//   user: User;
//   onSave: (values: any) => void; // Điều này phụ thuộc vào cách bạn xử lý lưu thông tin người dùng
// }
// const Forms: React.FC<FormsProps> = ({ user, onSave }) => {
//   return (
//     <Grid container spacing={3}>
//       <Grid item xs={12} lg={12}>
//         <BaseCard title="Edit Profile">
//           <Formik
//             initialValues={{
//               name: user?.full_name || '', // Sử dụng Optional chaining và mặc định giá trị là chuỗi trống
//               email: user?.email || '',
//               password: "",
//               textArea: "Default Value",
//               errorField: "ad1avi",
//               terms: true,
//               gender: "female",
//             }}
//             validationSchema={Yup.object({
//               name: Yup.string().required("Vui lòng nhập họ và tên"),
//               email: Yup.string().email("Email không hợp lệ").required("Vui lòng nhập email"),
//               password: Yup.string().min(6, "Mật khẩu phải có ít nhất 6 ký tự"),
//             })}
//             onSubmit={(values) => {
//               // Xử lý việc cập nhật thông tin người dùng tại đây
//               onSave(values);
//             }}
//           >
//             <Form>
//               <div>
//                 <label htmlFor="name">Fullname</label>
//                 <Field type="text" name="name" as={TextField} variant="outlined" fullWidth />
//               </div>


//               <div>
//                 <label htmlFor="email">Email</label>
//                 <Field type="text" name="email" as={TextField} variant="outlined" fullWidth />
//               </div>
//               <div>
//                 <label htmlFor="password">Mật khẩu</label>
//                 <Field type="password" name="password" as={TextField} variant="outlined" fullWidth />
//               </div>


//               <div>
//                 <label htmlFor="phone">Phone</label>
//                 <Field type="number" name="phone" as={TextField} variant="outlined" fullWidth />
//               </div>
//               <div>
//                 <label htmlFor="address">Address</label>
//                 <Field type="text" name="address" as={TextField} variant="outlined" fullWidth />
//               </div>
//               <div>
//                 <label htmlFor="textArea">Text Area</label>
//                 <Field
//                   name="textArea"
//                   as={TextField}
//                   multiline
//                   rows={4}
//                   variant="outlined"
//                   fullWidth
//                 />
//               </div>
//               <div>
//                 <label htmlFor="errorField">Error Field</label>
//                 <Field type="text" name="errorField" as={TextField} variant="outlined" fullWidth />
//               </div>
//               <FormGroup>
//                 <FormControlLabel
//                   control={<Field type="checkbox" name="terms" as={Checkbox} />}
//                   label="Terms & Condition"
//                 />
//               </FormGroup>
//               <FormControl>
//                 <FormLabel id="gender-label">Gender</FormLabel>
//                 <Field
//                   name="gender"
//                   as={RadioGroup}
//                   aria-labelledby="gender-label"
//                   row
//                 >
//                   <FormControlLabel
//                     value="female"
//                     control={<Field type="radio" as={Radio} />}
//                     label="Female"
//                   />
//                   <FormControlLabel
//                     value="male"
//                     control={<Field type="radio" as={Radio} />}
//                     label="Male"
//                   />
//                   <FormControlLabel
//                     value="other"
//                     control={<Field type="radio" as={Radio} />}
//                     label="Other"
//                   />
//                 </Field>
//               </FormControl>
//               <Button variant="contained" color="primary" type="submit">
//                 Submit
//               </Button>
//             </Form>
//           </Formik>
//         </BaseCard>
//       </Grid>
//     </Grid>
//   );
// };

// export default Forms;

'use client';
import { Session } from "next-auth";
import { useSession } from 'next-auth/react';
import styles from '../../css/user.module.css';
import React, { useState, useEffect } from 'react';
import {
  Paper,
  Grid,
  Stack,
  TextField,
  Checkbox,
  FormGroup,
  FormControlLabel,
  RadioGroup,
  Radio,
  FormLabel,
  FormControl,
  Button,
  createTheme,
  ThemeProvider,
  styled,
} from '@mui/material';
import BaseCard from '@/app/user/shared/BaseCard';

const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body1,
  textAlign: 'center',
  color: theme.palette.text.secondary,
  height: 60,
  lineHeight: '60px',
}));
// const fetchDataFromAPI = async () => {
//   try {
//     const response = await fetch('http://localhost:8089/v3/api-docs'); // Replace with your API endpoint
//     if (!response.ok) {
//       throw Error('Request failed');
//     }
//     const data = await response.json();
//     return data;
//   } catch (error) {
//     console.error('Error fetching data: ', error);
//     return null;
//   }
// };


const darkTheme = createTheme({ palette: { mode: 'dark' } });
const lightTheme = createTheme({ palette: { mode: 'light' } });

// const EditProfile = () => {
//   const [formData, setFormData] = useState({
//     name: '',
//     email: '',
//     password: '',
//     phone:'',
//     textArea: '',
//     error: '',
//     gender: '',
//   });

// const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
//   const { name, value } = e.target;
//   console.log(formData);
//   setFormData({
//     ...formData,
//     [name]: value,
//   });
// };

// useEffect(() => {
//   // Tải dữ liệu cũ từ nguồn dữ liệu (API hoặc cơ sở dữ liệu)
//   const fetchData = async () => {
//     const oldData = await fetchDataFromAPI();
//     if (oldData) {
//       // Gán dữ liệu cũ vào trạng thái formData
//       setFormData(oldData);
//     }
//   };

//   fetchData();
// }, []);

// const handleSubmit = () => {
//   // Handle form submission here
//   console.log(formData);
// };







const EditProfile = () => {
  const { data: session } = useSession();
  const [updatedUser, setUpdatedUser] = useState({
    name: '',
    email: '',
    phone: '',
    // gender: '', // Giới tính mặc định
    image: null as string | null,
      });

  useEffect(() => {
    if (session) {
      const { user } = session;
      setUpdatedUser({
        name: user.name,
        email: user.email,
        phone: user.phoneNumber,
        // gender: user.gender || '',
        image: null,
      });
    }
  }, [session]);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setUpdatedUser({ ...updatedUser, [name]: value });
  };

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const files = e.target.files;
    if (files && files.length > 0) {
      const imageUrl = URL.createObjectURL(files[0]);
      setUpdatedUser({ ...updatedUser, image: imageUrl });
    } else {
      setUpdatedUser({ ...updatedUser, image: null }); // Set image to null if no file is selected
    }
  };


  const handleEditUser = async () => {
    try {
      const accessToken = session?.user.accessToken;
      console.log(`Bearer ${session?.user.accessToken}`);
      console.log(accessToken)
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
                name="name"
                label="Full name"
                variant="outlined"
                value={updatedUser.name}
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
                name="phone"
                label="Phone"
                variant="outlined"
                value={updatedUser.phone}
                onChange={handleInputChange}
              />
              {/* <FormControl>
                <FormLabel>Gender</FormLabel>
                <RadioGroup
                  name="gender"
                  value={updatedUser.gender}
                  onChange={handleInputChange}
                >
                  <FormControlLabel value="female" control={<Radio />} label="Female" />
                  <FormControlLabel value="male" control={<Radio />} label="Male" />
                  <FormControlLabel value="other" control={<Radio />} label="Other" />
                </RadioGroup>
              </FormControl> */}
              <input
                type="file"
                accept="image/*"
                onChange={handleImageChange}
              />
              {updatedUser.image && (
                <img src={updatedUser.image} alt="User" style={{ width: '100px' }} />
              )}
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
