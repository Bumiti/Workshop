'use client'
import React, { useEffect, useState } from 'react';
import ApiService from '@/app/services/ApiService';
import { useSession } from 'next-auth/react';
import { Button, Container, Table, Modal, Card } from 'react-bootstrap';
import StickIcon from '@mui/icons-material/EmojiFlags';
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

const AccountPage = () => {
    const { data: session } = useSession();
    const apiService = new ApiService(session);
    const [buttonClicked, setButtonClicked] = useState(false);
    const [users, setUsers] = useState<UserData[]>([]);
    const [selectedUser, setSelectedUser] = useState<UserData | null>(null); // State to track the selected user
    const [showDetails, setShowDetails] = useState(false); // State to show/hide user details modal

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
    const getStatusIcon = (enable: boolean) => {
        return enable ? (
            <StickIcon color="success" /> // Green stick icon for enabled
        ) : (
            <StickIcon color="error" /> // Red stick icon for disabled
        );
    };
    const handleUserDetails = (user: UserData) => {
        setSelectedUser(user); // Set the selected user
        setShowDetails(true); // Show the user details modal
    };

    const handleCloseDetails = () => {
        setSelectedUser(null); // Clear the selected user
        setShowDetails(false); // Hide the user details modal
    };

    return (
        <Container>
            <h1 className='text-center text-white'>Account page</h1>
            {users.length > 0 && (
                <Table striped bordered hover className="border border-warning rounded-circle">
                    <thead>
                        <tr>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Gender</th>
                            <th>Phone Number</th>
                            <th>Roles</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map((user, index) => (
                            <tr key={user.id} onClick={() => handleUserDetails(user)}>
                                <td>{user.full_name}</td>
                                <td>{user.email}</td>
                                <td>{user.gender}</td>
                                <td>{user.phoneNumber}</td>
                                <td>{user.roles.join(', ')}</td>
                                <td>{user.enable ? 'Enabled' : 'Disabled'}</td>
                                <td>
                                    <Button
                                        onClick={(e) => {
                                            e.stopPropagation(); // Stop propagation to prevent the row click event
                                            handleButtonClick(user.id);
                                        }}
                                    >
                                        Toggle Status
                                    </Button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            )}
            {/* User Details Modal */}
            <Modal show={showDetails} onHide={handleCloseDetails}>
                <Modal.Header closeButton>
                    <Modal.Title>User Details</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {selectedUser && (
                        <Card>
                            <Card.Body>
                                <Card.Title>Name : {selectedUser.full_name}</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">Roles :{selectedUser.email}</Card.Subtitle>
                                <Card.Text>Gender: {selectedUser.gender}</Card.Text>
                                <Card.Text>Phone Number: {selectedUser.phoneNumber}</Card.Text>
                                <Card.Text>Roles: {selectedUser.roles.join(', ')}</Card.Text>
                                <Card.Text>Status: {selectedUser.enable ? 'Enabled' : 'Disabled'}</Card.Text>
                                <Card.Title>Addresses</Card.Title>
                                <ul>
                                    {selectedUser.userAddresses.map((address, index) => (
                                        <li key={index}>
                                            {address.address}, {address.city}, {address.state} - {address.postalCode}
                                        </li>
                                    ))}
                                </ul>
                            </Card.Body>
                        </Card>
                    )}
                </Modal.Body>
            </Modal>
        </Container>
    );
};

export default AccountPage;

