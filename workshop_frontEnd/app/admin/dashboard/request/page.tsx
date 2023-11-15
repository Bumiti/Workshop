
'use client'
import Image from "next/image";
import Link from "next/link";
import styles from "@/app/admin/ui/dashboard/products/products.module.css";
import Search from "@/app/admin/ui/dashboard/search/search";
import Pagination from "@/app/admin/ui/dashboard/pagination/pagination";
import React, { useEffect, useState } from 'react';
import ApiService from '@/app/services/ApiService';
import { useSession } from 'next-auth/react';
import { BiCheckCircle, BiXCircle } from 'react-icons/bi';

interface RequestData {
  id: number;
  type: string;
  status: boolean;
  createDate: string;
  userName: string;
  registrationDateTime: string;
  nameCourse: CoursesData[];
}

interface UserData {
  id: number | null;
  full_name: string;
}

interface CoursesData {
  id: number | null;
  name: string;
}

const RequestPage = () => {
  const { data: session } = useSession();
  const apiService = new ApiService(session);
  const [requests, setRequests] = useState<RequestData[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [requestsPerPage] = useState<number>(5);

  useEffect(() => {
    if (session) {
      const fetchData = async () => {
        try {
          const listRequestResponse = await apiService.listRequestAdmin(); // Adjust to the actual method in your ApiService
          if (listRequestResponse.data) {
            console.log('data', listRequestResponse);
            setRequests(listRequestResponse.data);
          }
        } catch (error) {
          console.error("Error fetching courses:", error);
        }
      };
      fetchData();
    }
  }, [session]);

  // Pagination logic
  const indexOfLastRequest = currentPage * requestsPerPage;
  const indexOfFirstRequest = indexOfLastRequest - requestsPerPage;
  const currentRequest = requests.slice(indexOfFirstRequest, indexOfLastRequest);

  const paginate = (pageNumber: number) => setCurrentPage(pageNumber);

  return (
    <div className={styles.container}>
      <div className={styles.top}>
        <Search placeholder="Search for a request..." />
      </div>
      {currentRequest.length > 0 && (
        <>
          <table className={styles.table}>
            <thead className="text-center">
              <tr>
                <th>Request Name</th>
                <th>Create Date</th>
                <th>status</th>
                <th>User Name</th>
                <th>Course Name</th>
              </tr>
            </thead>
            <tbody className="text-center">
              {currentRequest.map((request) => (
                <tr key={request.id}>
                  <td>{request.type}</td>
                  <td>{request.registrationDateTime}</td>
                  <td>{request.status}</td>
                  <td>{request.userName}</td>
                  {/* Course Name */}
                  {/* <td>
                    <div className={styles.buttons}>
                      {request.nameCourse.map((course, index) => (
                        <div key={index}>
                          {course.name}
                        </div>
                      ))}
                    </div>
                  </td> */}
                  <td>
                    <div className={styles.buttons}>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <Pagination
            itemsPerPage={requestsPerPage}
            totalItems={requests.length}
            paginate={paginate}
            currentPage={currentPage}
          />
        </>

      )}
    </div>
  );
};

export default RequestPage;
