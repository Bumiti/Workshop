
import React from 'react';
import Dashboard from './dashboard/page';

const page = () => {
    return (
        <div>
            <Dashboard/>
        </div>
    );
};

export default page;

// 'use client'
// import React, { useEffect, useState } from 'react';
// import ApiService from '../services/ApiService';
// import { useSession } from 'next-auth/react';
// import { Button, Container, Table, Modal, Card } from 'react-bootstrap';
// import StickIcon from '@mui/icons-material/EmojiFlags';
// interface UserData {
//     id: number;
//     full_name: string;
//     user_name: string;
//     email: string;
//     phoneNumber: string;
//     image_url: string | null;
//     gender: string;
//     roles: string[];
//     enable: boolean;
//     userAddresses: UserAddress[];
// }

// interface UserAddress {
//     id: number | null;
//     state: string;
//     address: string;
//     city: string;
//     postalCode: number;
// }

// const Admin = () => {
//     const { data: session } = useSession();
//     const apiService = new ApiService(session);
//     const [buttonClicked, setButtonClicked] = useState(false);
//     const [users, setUsers] = useState<UserData[]>([]);
//     const [selectedUser, setSelectedUser] = useState<UserData | null>(null); // State to track the selected user
//     const [showDetails, setShowDetails] = useState(false); // State to show/hide user details modal

//     useEffect(() => {
//         if (session) {
//             const fetchData = async () => {
//                 try {
//                     const listAccountResponse = await apiService.listAccountAdmin();
//                     if (listAccountResponse.data) {
//                         setUsers(listAccountResponse.data);
//                     }
//                 } catch (error) {
//                     console.error("Error:", error);
//                 }
//             };
//             fetchData();
//         }
//     }, [session]);

//     const handleButtonClick = (id: number) => {
//         apiService.changeStatusAccount(id).then(() => {
//             setUsers((prevUsers) => {
//                 return prevUsers.map((user) => {
//                     if (user.id === id) {
//                         user.enable = !user.enable;
//                     }
//                     return user;
//                 });
//             });
//         });
//     };
//     const getStatusIcon = (enable: boolean) => {
//         return enable ? (
//             <StickIcon color="success" /> // Green stick icon for enabled
//         ) : (
//             <StickIcon color="error" /> // Red stick icon for disabled
//         );
//     };
//     const handleUserDetails = (user: UserData) => {
//         setSelectedUser(user); // Set the selected user
//         setShowDetails(true); // Show the user details modal
//     };

//     const handleCloseDetails = () => {
//         setSelectedUser(null); // Clear the selected user
//         setShowDetails(false); // Hide the user details modal
//     };
