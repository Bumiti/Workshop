
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

interface CoursesData {
  id: number;
  name: string;
  public: boolean;
  price: number;
  student_count: number;
  description: string;
  startDate: number;
  endDate: number;
}

const CoursesPage = () => {
  const { data: session } = useSession();
  const apiService = new ApiService(session);
  const [courses, setCourses] = useState<CoursesData[]>([]);

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

  return (
    <div className={styles.container}>
      <div className={styles.top}>
        <Search placeholder="Search for a course..." />
      </div>
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
          </tr>
        </thead>
        <tbody className="text-center">
          {courses.map((course) => (
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
                  {/* <Link href={`/dashboard/products/${courses.id}`}>
                    <button className={`${styles.button} ${styles.view}`}>
                      View
                    </button>
                  </Link> */}
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
    </div>
  );
};

export default CoursesPage;
