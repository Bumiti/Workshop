'use client'
import { Col, Container, Row } from "react-bootstrap"
import React, { useEffect, useState } from 'react';
import ApiService from "@/app/services/ApiService";
import { useSession } from 'next-auth/react';
import SideLeft from '../[id]/component/SideLeft'
interface CourseType {
    id: number;
    name: string;
    description: string;
    link: string;
    price: number;
    // Các thuộc tính khác nếu có
    courseMediaInfos:courseMediaInfos[];
  }
interface courseMediaInfos {
    id:number;
    thumbnailSrc:string;
    title:string;
    urlImage:string;
    urlMedia:string;
}
const CourseDemo = ({ params }: { params: { id: any } }) => {
    const { data: session } = useSession();
    const apiService = new ApiService(session);
    const [course, setCourse] = useState<CourseType>();

    useEffect(() => {
            const fetchData = async () => {
                try {
                    const respone = await apiService.CoursePublicDetail(params.id);
                    if (respone.data) 
                    {                
                        setCourse(respone.data);                    
                    }
                } catch (error) {
                    console.error("Error fetching request:", error);
                }
            };
            fetchData();
    },[]);
    return (
        <Container fluid>
            <Row className="">
                <hr></hr>
                <br></br>
                <br></br>
                <br></br>
                <br></br>
                <Col sm={8}>
                    <Container className="p-5 pt-2">
                        <h1>Tiêu Đề Video</h1>
                        <h5>Lời Nhắc</h5>
                        <h1>Tiêu Đề Video</h1>
                        {/* List Danh Sach Title */}
                    </Container>
                </Col>
                <Col sm={4}>
                    <SideLeft course={course} />
                </Col>
            </Row>
        </Container>
    )
}
export default CourseDemo;
