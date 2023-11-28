
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
import { Button, Container, Table, Modal, Card } from 'react-bootstrap';

interface CoursesData {
  id: number;
  name: string;
  public: boolean;
  price: number;
  student_count: number;
  description: string;
  startDate: number;
  endDate: number;
  courseLocations: courseLocations[];
  studentEnrollments: studentEnrollments[];
}

interface courseLocations {
  id: number | null;
  area: string;
  address: string;
  city: string;
  postalCode: number;
}

interface studentEnrollments {
  id: number | null;
  name: string;
  address: string;
  city: string;
  postalCode: number;
}

const CoursesPage = () => {
  const { data: session } = useSession();
  const apiService = new ApiService(session);
  const [courses, setCourses] = useState<CoursesData[]>([]);
  const [showDetails, setShowDetails] = useState(false);
  const [selectedUser, setSelectedUser] = useState<CoursesData | null>(null);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [coursesPerPage] = useState<number>(5);


  useEffect(() => {
    if (session) {
      const fetchData = async () => {
        try {
          const listCourseResponse = await apiService.listCourseAdmin();
          if (listCourseResponse.data) {
            setCourses(listCourseResponse.data);
          }
        } catch (error) {
          console.error("Error fetching courses:", error);
        }
      };
      fetchData();
    }
  }, [session]);

  const handleTogglePublicStatus = (id: number) => {
    apiService.changeStatusCourse(id).then(() => {
      setCourses((prevUsers) => {
        return prevUsers.map((course) => {
          if (course.id === id) {
            course.public = !course.public;
          }
          return course;
        });
      });
    });
  };

  const handleUserDetails = (user: CoursesData) => {
    setSelectedUser(user); // Set the selected user
    setShowDetails(true); // Show the user details modal
  };

  const handleCloseDetails = () => {
    setSelectedUser(null); // Clear the selected user
    setShowDetails(false); // Hide the user details modal
  };

  // Pagination logic
  const indexOfLastCourse = currentPage * coursesPerPage;
  const indexOfFirstCourse = indexOfLastCourse - coursesPerPage;
  const currentCourse = courses.slice(indexOfFirstCourse, indexOfLastCourse);

  const paginate = (pageNumber: number) => setCurrentPage(pageNumber);
  console.log('dâta', courses);
  
  return (
    <div className={styles.container}>
      <div className={styles.top}>
        <Search placeholder="Search for a course..." />
      </div>
      {currentCourse.length > 0 && (
        <>
          <table className={styles.table}>
            <thead className="text-center">
              <tr>
                <th>Course Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>Total students</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody className="text-center">
              {currentCourse.map((course) => (
                <tr key={course.id}>
                  <td>{course.name}</td>
                  <td>{course.price}$</td>
                  <td>{course.description}</td>
                  <td>{course.startDate}</td>
                  <td>{course.endDate}</td>
                  <td onClick={() => handleTogglePublicStatus(course.id)}>{course.public ?
                    (
                      <i>Enabled<BiCheckCircle color="green" size={30} /></i>
                    ) : (
                      <i>Disabled<BiXCircle color="red" size={30} /></i>
                    )}
                  </td>
                  <td>{course.student_count}</td>
                  <td>
                    <div className={styles.buttons}>
                        <button className={`${styles.button} ${styles.view}`} onClick={() => handleUserDetails(course)}>
                          View
                        </button>
                      {/* <form action={deleteProduct}>
                    <input type="hidden" name="id" value={product.id} />
                    <button className={`${styles.button} ${styles.delete}`}>
                      Delete
                    </button>
                  </form> */}
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <Pagination
            itemsPerPage={coursesPerPage}
            totalItems={courses.length}
            paginate={paginate}
            currentPage={currentPage}
          />
        </>

      )}

      {/* User Details Modal */}
      <Modal className="Details" show={showDetails} onHide={handleCloseDetails}>
        <Modal.Header closeButton>
          <Modal.Title>User Details</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {selectedUser && (
            <Card>
              <Card.Body>
                <Card.Title>Course Name : {selectedUser.name}</Card.Title>
                <Card.Title>Price :{selectedUser.price}$</Card.Title>
                <Card.Text>Description: {selectedUser.description}</Card.Text>
                <Card.Text>Start Date: {selectedUser.startDate}</Card.Text>
                <Card.Text>End Date: {selectedUser.endDate}</Card.Text>
                <Card.Text>Status: {selectedUser.public ?
                  (
                    <i>Enabled<BiCheckCircle color="green" size={30} /></i>
                  ) : (
                    <i>Disabled<BiXCircle color="red" size={30} /></i>
                  )}</Card.Text>
                <Card.Title>Locations</Card.Title>
                <ul>
                  {selectedUser.courseLocations.map((location, index) => (
                    <li key={index}>
                      {location.area},
                    </li>
                  ))}
                </ul>
                <Card.Title>Student Enrollments</Card.Title>
                <ul>
                  {selectedUser.studentEnrollments.map((teacher, index) => (
                    <li key={index}>
                      {teacher.name},
                    </li>
                  ))}
                </ul>
              </Card.Body>
            </Card>
          )}
        </Modal.Body>
      </Modal>
    </div>
  );
};

export default CoursesPage;
