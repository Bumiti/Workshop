'use client'
import React, { useEffect, useState } from 'react';
import ApiService from '@/app/services/ApiService';
import { useSession } from 'next-auth/react';
import { Button, Container, Table } from 'react-bootstrap';
import StickIcon from '@mui/icons-material/EmojiFlags';
import { BiCheckCircle, BiXCircle } from 'react-icons/bi';
interface CoursesData {
    id: number;
    name: string;
    public: boolean;
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
        <Container>
            <h1 className='text-center text-white'>Courses Page</h1>
            {courses.length > 0 && (
                <Table striped bordered hover className="border border-warning rounded-circle">
                    <thead>
                        <tr className='text-center align-middle'>
                            <th>Course Name</th>
                            <th>Description</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th className='pd-2'>Status</th>
                            <th>Total students</th>
                        </tr>
                    </thead>
                    <tbody className='text-center align-middle'>
                        {courses.map((course) => (
                            <tr key={course.id}>
                                <td>{course.name}</td>
                                <td>{course.description}</td>
                                <td>{course.startDate}</td>
                                <td>{course.endDate}</td>
                                <td onClick={() => handleTogglePublicStatus(course.id)}>{course.public ?
                                    (
                                        <i>Completed<BiCheckCircle color="green" size={20} /></i>
                                    ) : (
                                        <i>Prepared<BiXCircle color="red" size={20} /></i>
                                    )}
                                </td>
                                <td>{course.student_count}</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            )}
        </Container>
    );
};

export default CoursesPage;
