import React, { useState, useCallback } from 'react';
import {
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  Divider,
} from '@mui/material';
import { ref, uploadBytesResumable, getDownloadURL } from 'firebase/storage';
import { FirebaseDb } from '../../../../utils/FireBase/Config';
import { v4 } from 'uuid';


export const AccountProfile = ({ onVideoUpload }) => {
  const [videoData, setVideoData] = useState({
    mediaInfoList: [
      {
        id: 0,
        urlMedia: null,
        urlImage: null,
        thumbnailSrc: null,
        title: null,
      },
    ],
  });

  const handleVideoUpload = useCallback(async (event) => {
    const files = event.target.files;

    if (files && files[0]) {
      const selectedVideo = files[0];
      const videoRef = ref(FirebaseDb, `videos/${v4()}`);

      try {
        const uploadTask = uploadBytesResumable(videoRef, selectedVideo);

        uploadTask.on(
          'state_changed',
          (snapshot) => {
            const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            console.log('Upload is ' + progress + '% done');
          },
          (error) => {
            console.error('Error uploading:', error);
          },
          async () => {
            try {
              const url = await getDownloadURL(uploadTask.snapshot.ref);

              const newVideoInfo = {
                id: videoData.mediaInfoList.length,
                thumbnailSrc: null,
                title: '',
                urlImage: 'https://example.com/image3',
                urlMedia: url,
              };
              setVideoData((prevState) => ({
                ...prevState,
                mediaInfoList: [newVideoInfo, ...prevState.mediaInfoList.slice(1)],
              }));
      
              onVideoUpload(newVideoInfo); // Gửi thông tin mới đến component cha
              console.log('Video uploaded successfully.');
            } catch (error) {
              console.error('Error getting download URL:', error);
            }
          }
        );
      } catch (error) {
        console.error('Error uploading:', error);
      }
    } else {
      console.error('No video file selected.');
    }
  }, [onVideoUpload]);

  const handleInputChange = (e) => {
    // Update the state with the value from the hidden input
    setVideoData((prevState) => ({
      ...prevState,
      mediaInfoList: [
        {
          ...prevState.mediaInfoList[0],
          urlMedia: e.target.value,
        },
      ],
    }));
  };

  return (
    <Card>
      <CardContent>
        <Box
          sx={{
            alignItems: 'center',
            display: 'flex',
            flexDirection: 'column',
          }}
        >
          {videoData.mediaInfoList[0].urlMedia && (
            <video width="320" height="240" controls>
              <source src={videoData.mediaInfoList[0].urlMedia} type="video/mp4" />
              Your browser does not support the video tag.
            </video>
          )}
        </Box>
      </CardContent>
      <Divider />
      <CardActions>
        <Button fullWidth variant="text">
          <label htmlFor="video-upload">
            Upload video
          </label>
          <input
            type="hidden"
            name="video_url"
            value={videoData.mediaInfoList[0].urlMedia || ''}
            onChange={handleInputChange}
          />
          <input
            id="video-upload"
            type="file"
            style={{ display: 'none' }}
            onChange={handleVideoUpload}
            accept="video/*,image/*"
            multiple // Allow selecting multiple files for uploading video and images
          />
        </Button>
      </CardActions>
    </Card>
  );
};
