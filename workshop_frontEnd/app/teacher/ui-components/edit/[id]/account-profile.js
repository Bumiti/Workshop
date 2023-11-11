import React, { useState, useCallback, useEffect } from 'react';
import {
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  Divider,
} from '@mui/material';
import {
  ref,
  uploadBytesResumable,
  getDownloadURL,
  getStorage,
} from 'firebase/storage';
import { FirebaseDb } from '@/utils/FireBase/Config';
import { v4 as uuidv4 } from 'uuid';

const storage = getStorage();

export const AccountProfile = ({ onVideoUpload, existingVideoUrl, formData }) => {
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
      const videoRef = ref(storage, `videos/${uuidv4()}`);

      try {
        const uploadTask = uploadBytesResumable(videoRef, selectedVideo);

        uploadTask.on(
          'state_changed',
          (snapshot) => {
            const progress =
              (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
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
                mediaInfoList: [
                  newVideoInfo,
                  ...prevState.mediaInfoList.slice(1),
                ],
              }));

              onVideoUpload(newVideoInfo);
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
  useEffect(() => {
    // Set the existing video URL when available
    if (existingVideoUrl) {
      setVideoData((prevState) => ({
        ...prevState,
        mediaInfoList: [
          {
            ...prevState.mediaInfoList[0],
            urlMedia: existingVideoUrl,
          },
        ],
      }));
    }
  }, [existingVideoUrl]);
  console.log(formData);
  useEffect(() => {
    if (formData && formData.length > 0 && formData[0].courseMediaInfos && formData[0].courseMediaInfos.length > 0) {
      const {
        id,
        urlMedia,
        urlImage,
        thumbnailSrc,
        title,
      } = formData[0].courseMediaInfos[0];
  
      setVideoData({
        mediaInfoList: [
          {
            id: id || 0,
            urlMedia: urlMedia || '',
            urlImage: urlImage || '',
            thumbnailSrc: thumbnailSrc || 0,
            title: title || 0,
          },
        ],
      });
    }
  }, [formData]);
  
  
console.log('videoData nè',videoData);
console.log('Video URL:', videoData?.mediaInfoList[0]?.urlMedia);

  return (
    <Card>
      <CardContent>
        <Box
          sx={{
            alignItems: 'center',
            display: 'flex',
            flexDirection: 'column',
          }}
        > {videoData.mediaInfoList[0].urlMedia && (
            <video width="320" height="240" controls>
              <source
                src={videoData.mediaInfoList[0].urlMedia}
                type="video/mp4"
              />
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
            onChange={() => { }} // A dummy function, as this value is not being used
          />
          <input
            id="video-upload"
            type="file"
            style={{ display: 'none' }}
            onChange={handleVideoUpload}
            accept="video/*,image/*"
            multiple
          />
        </Button>
      </CardActions>
    </Card>
  );
};
