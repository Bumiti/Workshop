'use client'
import { Col, Container, Row } from "react-bootstrap"
import styles from '../courseDemo/courseDemo.module.css';
import SideLeft  from './component/SideLeft';
import Link from "next/link";


export default function CourseDemo() {

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
                  <SideLeft/>
                </Col>
            </Row>
        </Container>
    )
}