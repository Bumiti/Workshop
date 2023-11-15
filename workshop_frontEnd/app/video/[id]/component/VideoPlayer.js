import { TextField } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import dynamic from 'next/dynamic';
const ReactPlayer = dynamic(() => import('react-player'), { ssr: false });

const VideoPublic = ({ selectedVideoUrl }) => {

    const [currentVideoUrl, setCurrentVideoUrl] = useState(selectedVideoUrl);
    useEffect(() => {
        setCurrentVideoUrl(selectedVideoUrl);
    }, [selectedVideoUrl]);
    
    return (
        <Row>
            <Col sm={12} md={12} lg={12}>
                <div className="mx-auto w-full space-y-6 p-2">
                    <Row>
                        <div className="player-container">
                            <ReactPlayer
                                url={currentVideoUrl}
                                controls={true}
                                playing={true}
                                muted={true}
                                width={'100%'}
                                height={666}
                            />
                        </div>
                    </Row>
                    <Row>
                        <div className="space-y-2">
                            <h2 className="text-2xl font-bold">Comments</h2>
                            <div className="space-y-2">
                                <label htmlFor="comment" className="font-bold text-lg">Leave a comment</label>
                                <TextField
                                    id="comment"
                                    placeholder="Write your comment here..."
                                    required
                                    type="text"
                                    className="border border-gray-300 rounded p-2 w-full"
                                />
                            </div>
                            <Button
                                className="bg-blue-500 text-white rounded p-2 w-24 hover:bg-blue-700"
                                type="submit"
                            >
                                Post Comment
                            </Button>
                        </div>
                    </Row>
                </div>
            </Col>
        </Row>
    );
}

export default VideoPublic;
