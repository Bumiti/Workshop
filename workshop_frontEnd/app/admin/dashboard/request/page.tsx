
  'use client'
  import Image from "next/image";
  import Link from "next/link";
  import styles from "@/app/admin/ui/dashboard/products/products.module.css";
  import Search from "@/app/admin/ui/dashboard/search/search";
  import Pagination from "@/app/admin/ui/dashboard/pagination/pagination";
  import React, { useEffect, useState } from 'react';
  import ApiService from '@/app/services/ApiService';
  import { Modal, Card } from 'react-bootstrap';
  import { useSession } from 'next-auth/react';
  import { BiCheckCircle, BiXCircle } from 'react-icons/bi';
  import Style from "./request.module.css";

  interface RequestData {
    id: number;
    type: string;
    status: boolean;
    createDate: string;
    userId: number;
    userName: string;
    registrationDateTime: string;
    workshopName:string;
    value:number;
  }
  const RequestPage = () => {
    const { data: session } = useSession();
    const apiService = new ApiService(session);
    const [requests, setRequests] = useState<RequestData[]>([]);
    const [selectedUser, setSelectedUser] = useState<RequestData | null>(null);
    const [showDetails, setShowDetails] = useState(false);
    const [currentPage, setCurrentPage] = useState<number>(1);
    const [requestsPerPage] = useState<number>(5);
    const [locationIds, setLocationIds] = useState<{ [key: number]: number }>({});

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

    const handleUserDetails = (request: RequestData) => {
      setSelectedUser(request); // Set the selected user
      setShowDetails(true); // Show the user details modal
    };

    const handleCloseDetails = () => {
      setSelectedUser(null); // Clear the selected user
      setShowDetails(false); // Hide the user details modal
    };

    const updateRequestStatus = async (id: number) => {
      try {
        await apiService.changeStatusRequest(id);
        // Cập nhật local state hoặc thực hiện các hành động cần thiết sau khi cập nhật thành công
        // Ví dụ: Cập nhật local state để hiển thị trạng thái mới
        const updatedRequests = requests.map(request =>
          isRequestData(request) ? request : (request as RequestData)
        );
        setRequests(updatedRequests);
      } catch (error) {
        console.error('Error updating request status:', error);
      }
    };

    function isRequestData(obj: any): obj is RequestData {
      return 'type' in obj && 'id' in obj && 'status' in obj;
    }

    const UpdateRequestWorkshop = async (request_id: number, user_id: number) => {
      const updaterequestResponse = await apiService.UpdateRequest(request_id, user_id);
      console.log('updaterequestResponse', updaterequestResponse);

    }
  console.log('id',requests);

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
                <th>Request ID</th>
                  <th>Request Name</th>
                  <th>Create Date</th>
                  <th>User Name</th>
                  <th>Amount</th>
                  <th>Workshop Name</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody className="text-center">
                {currentRequest.map((request) => (
                  <tr key={request.id}>
                    <td>{request.id}</td>
                    <td>{request.type}</td>
                    <td>{request.registrationDateTime}</td>
                    <td>{request.userName}</td>
                    <td>{request.value}</td>
                    <td>{request.workshopName}</td>
                    <td>{request.status}</td>
                  
                  

                    <td>
                      <div className={styles.buttons}>
                        <button className={`${styles.button} ${styles.view}`} onClick={() => handleUserDetails(request)}>
                          View
                        </button>
                      </div>
                    </td>
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
        {/* User Details Modal */}
        <Modal show={showDetails} onHide={handleCloseDetails}>
          <Modal.Header className={Style.container} closeButton>
            <Modal.Title>User Details</Modal.Title>
          </Modal.Header>
          <Modal.Body className={Style.container}>
            {selectedUser && (
              <Card className={Style.container}>
                <Card.Body className={Style.form}>
                  <Card.Title><text>Request ID : {selectedUser.id}</text></Card.Title>
                  <Card.Title><text>Request Name :{selectedUser.type}</text></Card.Title>
                  <Card.Text><text>Create Date: {selectedUser.registrationDateTime}</text></Card.Text>
                  <Card.Text><text>User ID: {selectedUser.userId}</text></Card.Text>
                  <Card.Text><text>User Name: {selectedUser.userName}</text></Card.Text>
                  <Card.Text><text>Amount: {selectedUser.value}</text></Card.Text>
                  <Card.Text><text>Status: {selectedUser.status ?
                    (
                      <i>Enabled<BiCheckCircle color="green" size={30} /></i>
                    ) : (
                      <i>Disabled<BiXCircle color="red" size={30} /></i>
                    )}</text>
                    </Card.Text>

                    <button className={Style.btn1} onClick={() => {UpdateRequestWorkshop(selectedUser.id!, selectedUser.userId!);updateRequestStatus(selectedUser.id!);}}> submit</button>
                </Card.Body>
              </Card>
            )}
          </Modal.Body>
        </Modal >
      </div>
    );
  };

  export default RequestPage;
