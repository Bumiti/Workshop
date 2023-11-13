import Link from 'next/link';
import React, { useState } from 'react';

const SliderVideo = ({ videos, onVideoSelect }) => {
    const [selectedVideoUrl, setSelectedVideoUrl] = useState('');

    const handleVionVideoSelectdeoSelect = (videoUrl) => {
        setSelectedVideoUrl(videoUrl);
    };
console.log("videos",videos);
    return (
        <div className="flex flex-col items-center justify-start py-12 overflow-y-auto p-2">
            <h1 className="text-2xl font-bold mb-4 text-center">Video List</h1>
            <hr></hr>
            <div className="space-y-6">
                {videos?.map((video) => (
                     console.log(video.thumbnailSrc),
                    <div key={video.id} className="space-y-2">
                    <Link
                        className="underline text-lg"
                        href="#"
                        onClick={() => onVideoSelect(video.urlMedia)}
                    >
                       
                        <img
                            alt="Video Thumbnail"
                            className="object-cover"
                            height="100px" // Đặt chiều cao thành 100px
                            src={video.thumbnailSrc}
                            style={{
                                padding:2,
                                width: "100%", // Đặt chiều rộng thành 100px
                                aspectRatio: "1/1", // Bảo đảm tỷ lệ khung hình là 1:1
                                objectFit: "cover",
                                borderRadius:10,
                                boxShadow: "0px 4px 4px 0px rgba(0, 0, 0, 0.25)", 
                            }}
                        />
                        <div className="text-center">
                            {video.title}
                        </div>
                    </Link>
                    <hr></hr>
                    </div>
                
                    
                ))}
            </div>
        </div>
    );
};

export default SliderVideo;
