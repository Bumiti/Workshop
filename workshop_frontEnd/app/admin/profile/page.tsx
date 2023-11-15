import React, { useEffect, useState } from 'react';
import { useSession } from 'next-auth/react';
import ApiService from '@/app/services/ApiService';

const ProfilePage = () => {
  const { data: session } = useSession();
  const apiService = new ApiService(session);
  const [userData, setUserData] = useState(null);

  useEffect(() => {
    const fetchProfileData = async () => {
      try {
        if (session && session.user.accessToken) {
          const response = await apiService.EditAdmin(session.user.id); // Assuming you want to edit the profile of the currently logged-in user
          setUserData(response);
        }
      } catch (error) {
        console.error("Error fetching profile data:", error);
      }
    };

    fetchProfileData();
  }, [session]);

  if (!session) {
    // Redirect to login page if not logged in
    // You can also display a message or handle it differently based on your application logic
    return <div>Please log in to view your profile.</div>;
  }

  if (!userData) {
    // Display a loading spinner or message while fetching data
    return <div>Loading...</div>;
  }

  // Render the user profile data
  return (
    <div>
      <h1>Profile Page</h1>
      <p>User ID: {userData.id}</p>
      <p>Username: {userData.username}</p>
      {/* Add more fields based on your user data structure */}
    </div>
  );
};

export default ProfilePage;
