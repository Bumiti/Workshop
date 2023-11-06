'use client'

import { Container, Row, Col } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

import VideoPublic from './component/VideoPlayer';
import SliderVideo from "./component/SliderVideo";
import { useState } from "react";
const videos = [
    { 
        id: 1,
         thumbnailSrc: 'https://firebasestorage.googleapis.com/v0/b/workshopprojec04.appspot.com/o/course%2Fimage%2Fimages.jpg?alt=media&token=fb62f939-f797-44a7-839c-5c7757c078e2&_gl=1*tlzsam*_ga*Nzk1MDE5ODk4LjE2OTc4MTA0Nzg.*_ga_CW55HF8NVT*MTY5OTIwNjQ3MS4xOS4xLjE2OTkyMDcxODQuNDIuMC4w', 
         title: 'Full Video Title 1s',
         url:'https://youtu.be/wuArhMaD5Hc',
         },
    { 
        id: 2, thumbnailSrc: 'https://firebasestorage.googleapis.com/v0/b/workshopprojec04.appspot.com/o/course%2Fimage%2Fimages.jpg?alt=media&token=fb62f939-f797-44a7-839c-5c7757c078e2&_gl=1*tlzsam*_ga*Nzk1MDE5ODk4LjE2OTc4MTA0Nzg.*_ga_CW55HF8NVT*MTY5OTIwNjQ3MS4xOS4xLjE2OTkyMDcxODQuNDIuMC4w', 
    title: 'Full Video Title 2',
url:'https://youtu.be/xioxrWXR1Q0', },
    // Thêm video khác nếu cần
];
const teacher = () => {
    const [selectedVideoUrl, setSelectedVideoUrl] = useState('');
    return (
        <Container fluid className="w-screen h-screen">
            <Row className="bg-slate-400">
                <Col sm={2}>
                    <Container>
                       <SliderVideo videos={videos} onVideoSelect={setSelectedVideoUrl}/>
                    </Container>
                </Col>
                <Col sm={10}>
                    <VideoPublic selectedVideoUrl={selectedVideoUrl} />
                </Col>
            </Row>
        </Container>
    )
}

export default teacher;