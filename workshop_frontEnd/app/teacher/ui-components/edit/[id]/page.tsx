'use client'
import { ref, uploadBytesResumable, getDownloadURL } from 'firebase/storage';
import { v4 } from 'uuid';
import { useState, useEffect, ChangeEvent } from 'react';
import { useRouter } from 'next/navigation';
import ApiService from '@/app/services/ApiService';
import { useSession } from 'next-auth/react';
import PageContainer from '@/app/teacher/components/container/PageContainer';
import Head from 'next/head';
import { Box, Grid, Stack, TextField, Typography, Container, Card, CardContent, CardHeader, FormControlLabel, Switch, Divider, CardActions, Button, CircularProgress } from '@mui/material';
import { FirebaseDb } from '../../../../../utils/FireBase/Config';
import { format } from 'date-fns';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import styles from './edit.module.css';
interface EditProps {
  params: {
    id: number;
  };
}

const EditCoursePage = ({ params }: EditProps) => {
  const router = useRouter();
  const [loading, setLoading] = useState(true);
  const initialCourseData = {
    id: 0,
    name: '',
    description: '',
    price: 0,
    startDate: new Date(),
    endDate: new Date(),
    student_count: 0,
    type: 'offline',
    courseMediaInfos: [
      {
        id: 0,
        urlMedia: '',
        urlImage: '',
        thumbnailSrc: '',
        title: '',
      },
    ],
    discountDTOS: [
      {
        id: 0,
        quantity: 0,
        redemptionDate: new Date(),
        valueDiscount: 0,
        name: '',
        description: '',
        remainingUses: 0,
      },
    ],
    courseLocations: [
      {
        id: 0,
        schedule_Date: new Date(),
        area: '',
      },
    ],
  };

  const [courseData, setCourseData] = useState(initialCourseData);
  const { id } = params;
  const { data: session } = useSession();
  const apiService = new ApiService(session);
  useEffect(() => {
    const fetchData = async () => {
      if (id) {
        try {
          const response = await apiService.getCourseById(id);
          if (response.status === 'success') {

            setCourseData(response.data[0]);
          } else {
            console.error('Error fetching course data:', response.error);
          }
        } catch (error) {
          console.error('Error fetching course data:', error);
        }
      }
    };

    fetchData();
  }, [session, id]);

  const handleInputChange = (e: { target: { name: any; value: any; }; }) => {
    const { name, value } = e.target;
    setCourseData((prevData) => ({
      ...prevData,
      [name]: value,

    }));
  };


  const handleLocationInputChange = (event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, index: number) => {
    const { name, value } = event.target;

    setCourseData((prevData) => ({
      ...prevData,
      courseLocations: prevData.courseLocations && prevData.courseLocations.map((location, i) =>
        i === index && location
          ? { ...location, [name]: value }
          : location || {} // Ensure location is an object
      ),
    }));
  };



  const handleSubmit = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    console.log('Course data before update:', courseData);

    try {
      await apiService.editCourse(id, courseData);
      // Redirect to the course detail page or any other page after successful update
      router.push(`/teacher/ui-components/buttons`);
    } catch (error) {
      console.error('Error updating course:', error);
    }
  };

  const handleImageUpload = async (event: ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;

    if (files && files[0]) {
      const selectedImage = files[0];
      const imageRef = ref(FirebaseDb, `images/${v4()}`);

      try {
        const uploadTask = uploadBytesResumable(imageRef, selectedImage);

        uploadTask.on(
          'state_changed',
          (snapshot) => {
            const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            console.log('Upload is ' + progress + '% done');
          },
          (error) => {
            console.error('Error uploading image:', error);
          },
          async () => {
            try {
              const url = await getDownloadURL(uploadTask.snapshot.ref);

              const newImageInfo = {
                id: courseData.courseMediaInfos.length,
                urlImage: url,
              };

              setCourseData((prevState) => ({
                ...prevState,
                courseMediaInfos: [
                  {
                    ...prevState.courseMediaInfos[0],
                    urlImage: url,
                  },
                ],
              }));
              console.log('Image uploaded successfully.');
            } catch (error) {
              console.error('Error getting download URL:', error);
            }
          }
        );
      } catch (error) {
        console.error('Error uploading image:', error);
      }
    } else {
      console.error('No image file selected.');
    }
  };


  const handleImageInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    // Update the state with the value from the hidden input
    setCourseData((prevState) => ({
      ...prevState,
      courseMediaInfos: [
        {
          ...prevState.courseMediaInfos[0],
          urlImage: e.target.value,
        },
      ],
    }));
  };

  const handleVideoUpload = async (event: ChangeEvent<HTMLInputElement>) => {
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
            console.error('Error uploading video:', error);
          },
          async () => {
            try {
              const url = await getDownloadURL(uploadTask.snapshot.ref);

              const newVideoInfo = {
                id: courseData.courseMediaInfos.length,
                thumbnailSrc: null,
                title: '',
                urlImage: courseData.courseMediaInfos.length > 0 ? courseData.courseMediaInfos[0].urlImage : '',
                urlMedia: url,
              };

              setCourseData((prevState) => ({
                ...prevState,
                courseMediaInfos: [newVideoInfo, ...prevState.courseMediaInfos.slice(1)],
              }));

              console.log('Video uploaded successfully.');
            } catch (error) {
              console.error('Error getting download URL:', error);
            }
          }
        );
      } catch (error) {
        console.error('Error uploading video:', error);
      }
    } else {
      console.error('No video file selected.');
    }
  };


  const handleInputImageChange = (e: ChangeEvent<HTMLInputElement>) => {

    setCourseData((prevState) => ({
      ...prevState,
      courseMediaInfos: [
        {
          ...prevState.courseMediaInfos[0],
          urlMedia: e.target.value,
        },
      ],

    }
    ));
  };
  useEffect(() => {
    console.log('Updated courseData:', courseData);
  }, [courseData]); // Chạy useEffect mỗi khi courseData thay đổi

  console.log(courseData);
  const handleDiscountInputChange = (
    e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>,
    field: string
  ) => {
    const { value } = e.target;
    setCourseData((prevData) => ({
      ...prevData,
      discountDTOS: prevData.discountDTOS && prevData.discountDTOS.map((discount, i) =>
        i === 0 && discount
          ? { ...discount, [field]: value }
          : discount || {}
      ),
    }));
  };


  return (
    <div>
      <PageContainer>
        <Head>
          <title>Edit Course</title>
        </Head>
        <Box component="main" sx={{ flexGrow: 1, py: 8 }}>
          <Container maxWidth="lg">
            {/* {loading && <CircularProgress />} {/* Display loading spinner */}
            {/* {!loading && ( */}
            <form onSubmit={handleSubmit}>
              <Stack spacing={3}>
                <Typography variant="h4">Edit Course</Typography>
                <Grid container spacing={3}>
                  <Grid item xs={12} md={6} lg={4}>
                    <Card>
                      <CardContent>
                        <Box
                          sx={{
                            alignItems: 'center',
                            display: 'flex',
                            flexDirection: 'column',
                          }}
                        >
                          {courseData?.courseMediaInfos?.[0]?.urlMedia && (
                            <video width="320" height="240" controls>
                              <source
                                src={courseData.courseMediaInfos[0].urlMedia} type="video/mp4" />
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
                            value={courseData.courseMediaInfos?.[0]?.urlMedia}
                            onChange={handleInputImageChange}
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

                      <Box
                        sx={{
                          alignItems: 'center',
                          display: 'flex',
                          flexDirection: 'column',
                        }}
                      >
                        {courseData.courseMediaInfos && courseData.courseMediaInfos.length > 0 && (
                          <img
                            src={courseData.courseMediaInfos[0].urlImage}
                            alt="Selected Image"
                            style={{ width: '50%', height: 'auto', maxWidth: '320px' }}
                          />
                        )}

                      </Box>
                      <CardActions>
                        <Button fullWidth variant="text">
                          <label htmlFor="image-upload">
                            Upload image
                          </label>
                          {courseData.courseMediaInfos && courseData.courseMediaInfos.length > 0 && (
                            <input
                              type="hidden"
                              name="image_url"
                              value={courseData.courseMediaInfos[0].urlImage || ''}
                              onChange={handleImageInputChange}
                            />
                          )}

                          <input
                            id="image-upload"
                            type="file"
                            style={{ display: 'none' }}
                            onChange={handleImageUpload}
                            accept="image/*"
                          />
                        </Button>
                      </CardActions>
                    </Card>
                  </Grid>
                  <Grid item xs={12} md={6} lg={8}>
                    AccountProfileDetails
                    <Card>
                      <CardHeader subheader="The information can be edited" title="Profile" />
                      <CardContent sx={{ pt: 0 }}>
                        <Box sx={{ m: -1.5 }}>
                          <Grid container spacing={3}>
                            <Grid item xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="Course name"
                                name="name"
                                onChange={handleInputChange}
                                required
                                value={courseData.name}
                                InputLabelProps={{ shrink: true }}
                              />
                            </Grid>
                            <Grid item xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="Description"
                                name="description"
                                onChange={handleInputChange}
                                required
                                value={courseData.description}
                                InputLabelProps={{ shrink: true }}
                              />
                            </Grid>
                            <Grid item xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="Price"
                                name="price"
                                onChange={handleInputChange}
                                required
                                type="number"
                                value={courseData.price}
                                InputLabelProps={{ shrink: true }}
                              />
                            </Grid>
                            <Grid item xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="Start Date"
                                name="startDate"
                                onChange={handleInputChange}
                                type="date"
                                value={format(new Date(courseData.startDate), 'yyyy-MM-dd')}
                                InputLabelProps={{ shrink: true }}
                              />
                            </Grid>
                            <Grid item xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="End Date"
                                name="endDate"
                                onChange={handleInputChange}
                                type="date"
                                value={format(new Date(courseData.endDate), 'yyyy-MM-dd')}
                                InputLabelProps={{ shrink: true }}
                              />
                            </Grid>
                          </Grid>
                        </Box>
                      </CardContent>
                      <Divider />
                      <CardContent sx={{ pt: 0 }}>
                        <CardHeader subheader="Your location for workshop" />
                        <Box sx={{ m: -1.5 }}>
                          <Grid container spacing={3} >
                            <Grid item xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="Schedule Date"
                                name="schedule_Date"
                                onChange={(event) => handleLocationInputChange(event, 0)}
                                type="date"
                                disabled
                                value={format(new Date(courseData.courseLocations && courseData.courseLocations[0]?.schedule_Date || ''), 'yyyy-MM-dd')} InputLabelProps={{ shrink: true }}
                              />
                            </Grid>
                            <Grid item xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="Area"
                                name="area"
                                onChange={(event) => handleLocationInputChange(event, 0)}
                                disabled
                                value={courseData.courseLocations && courseData.courseLocations[0]?.area || ''}
                              />
                            </Grid>
                            <Grid item xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="District"
                                name="District"
                                onChange={(event) => handleLocationInputChange(event, 0)}
                                disabled
                                value={courseData?.courseLocations?.[0]?.locationDTO?.address || ''}
                              />
                            </Grid><Grid item xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="Center"
                                name="Center"
                                onChange={(event) => handleLocationInputChange(event, 0)}
                                disabled
                                value={courseData?.courseLocations?.[0]?.locationDTO?.name || ''}
                              />
                            </Grid>
                            <Grid item xs={12} md={6}>
                              <TextField
                                fullWidth
                                label="Branch "
                                name="branch "
                                onChange={(event) => handleLocationInputChange(event, 0)}
                                disabled
                                value={courseData?.courseLocations?.[0]?.locationDTO?.description || ''}
                              />
                            </Grid>
                          </Grid>
                          <Grid item xs={12} md={6}>
                            {courseData?.courseLocations?.[0]?.locationDTO?.statusAvailable === "available" && (
                             <div >
                             <svg className="star" height="19" viewBox="0 0 19 19" width="19" xmlns="http://www.w3.org/2000/svg">
                                 <path d="M8.296.747c.532-.972 1.393-.973 1.925 0l2.665 4.872 4.876 2.66c.974.532.975 1.393 0 1.926l-4.875 2.666-2.664 4.876c-.53.972-1.39.973-1.924 0l-2.664-4.876L.76 10.206c-.972-.532-.973-1.393 0-1.925l4.872-2.66L8.296.746z" >
                                 </path></svg>
                             <svg className="star" height="19" viewBox="0 0 19 19" width="19" xmlns="http://www.w3.org/2000/svg">
                                 <path d="M8.296.747c.532-.972 1.393-.973 1.925 0l2.665 4.872 4.876 2.66c.974.532.975 1.393 0 1.926l-4.875 2.666-2.664 4.876c-.53.972-1.39.973-1.924 0l-2.664-4.876L.76 10.206c-.972-.532-.973-1.393 0-1.925l4.872-2.66L8.296.746z" >
                                 </path></svg>
                             <svg className="star" height="19" viewBox="0 0 19 19" width="19" xmlns="http://www.w3.org/2000/svg">
                                 <path d="M8.296.747c.532-.972 1.393-.973 1.925 0l2.665 4.872 4.876 2.66c.974.532.975 1.393 0 1.926l-4.875 2.666-2.664 4.876c-.53.972-1.39.973-1.924 0l-2.664-4.876L.76 10.206c-.972-.532-.973-1.393 0-1.925l4.872-2.66L8.296.746z" >
                                 </path></svg>
                             <svg className="star" height="19" viewBox="0 0 19 19" width="19" xmlns="http://www.w3.org/2000/svg">
                                 <path d="M8.296.747c.532-.972 1.393-.973 1.925 0l2.665 4.872 4.876 2.66c.974.532.975 1.393 0 1.926l-4.875 2.666-2.664 4.876c-.53.972-1.39.973-1.924 0l-2.664-4.876L.76 10.206c-.972-.532-.973-1.393 0-1.925l4.872-2.66L8.296.746z" >
                                 </path></svg>
                             <svg className="star" height="19" viewBox="0 0 19 19" width="19" xmlns="http://www.w3.org/2000/svg">
                                 <path d="M8.296.747c.532-.972 1.393-.973 1.925 0l2.665 4.872 4.876 2.66c.974.532.975 1.393 0 1.926l-4.875 2.666-2.664 4.876c-.53.972-1.39.973-1.924 0l-2.664-4.876L.76 10.206c-.972-.532-.973-1.393 0-1.925l4.872-2.66L8.296.746z" >
                                 </path></svg>
                             <svg className="star" height="19" viewBox="0 0 19 19" width="19" xmlns="http://www.w3.org/2000/svg">
                                 <path d="M8.296.747c.532-.972 1.393-.973 1.925 0l2.665 4.872 4.876 2.66c.974.532.975 1.393 0 1.926l-4.875 2.666-2.664 4.876c-.53.972-1.39.973-1.924 0l-2.664-4.876L.76 10.206c-.972-.532-.973-1.393 0-1.925l4.872-2.66L8.296.746z" >
                                 </path></svg>
                             <svg className="checkmark__check" height="36" viewBox="0 0 48 36" width="48" xmlns="http://www.w3.org/2000/svg">
                                 <path d="M47.248 3.9L43.906.667a2.428 2.428 0 0 0-3.344 0l-23.63 23.09-9.554-9.338a2.432 2.432 0 0 0-3.345 0L.692 17.654a2.236 2.236 0 0 0 .002 3.233l14.567 14.175c.926.894 2.42.894 3.342.01L47.248 7.128c.922-.89.922-2.34 0-3.23">
                                 </path></svg>
                             <svg className="checkmark__background" height="115" viewBox="0 0 120 115" width="120" xmlns="http://www.w3.org/2000/svg">
                                 <path d="M107.332 72.938c-1.798 5.557 4.564 15.334 1.21 19.96-3.387 4.674-14.646 1.605-19.298 5.003-4.61 3.368-5.163 15.074-10.695 16.878-5.344 1.743-12.628-7.35-18.545-7.35-5.922 0-13.206 9.088-18.543 7.345-5.538-1.804-6.09-13.515-10.696-16.877-4.657-3.398-15.91-.334-19.297-5.002-3.356-4.627 3.006-14.404 1.208-19.962C10.93 67.576 0 63.442 0 57.5c0-5.943 10.93-10.076 12.668-15.438 1.798-5.557-4.564-15.334-1.21-19.96 3.387-4.674 14.646-1.605 19.298-5.003C35.366 13.73 35.92 2.025 41.45.22c5.344-1.743 12.628 7.35 18.545 7.35 5.922 0 13.206-9.088 18.543-7.345 5.538 1.804 6.09 13.515 10.696 16.877 4.657 3.398 15.91.334 19.297 5.002 3.356 4.627-3.006 14.404-1.208 19.962C109.07 47.424 120 51.562 120 57.5c0 5.943-10.93 10.076-12.668 15.438z" >
                                 </path></svg>
                         </div>
                            )}
                          </Grid>

                        </Box>

                      </CardContent>
                    </Card>
                  </Grid>
                </Grid>
                <Stack spacing={3}>
                  <Card>
                    <CardHeader
                      subheader="Manage Discounts"
                      title="Discount Information"
                    />
                    <Divider />
                    <CardContent>
                      <Grid container spacing={2}>
                        <Grid item xs={12}>
                          <TextField
                            fullWidth
                            label="Discount Name"
                            name="name"
                            onChange={handleInputChange}
                            value={courseData.discountDTOS[0].name}
                            variant="outlined"
                            disabled

                          />
                        </Grid>
                        <Grid item xs={12}>
                          <TextField
                            fullWidth
                            label="Description"
                            name="description"
                            onChange={(e) => handleDiscountInputChange(e, 'description')}
                            value={courseData.discountDTOS[0].description}
                            variant="outlined"
                            disabled

                          />
                        </Grid>
                        <Grid item xs={6}>
                          <TextField
                            fullWidth
                            label="Quantity"
                            name="quantity"
                            onChange={(e) => handleDiscountInputChange(e, 'quantity')}
                            value={courseData.discountDTOS[0].quantity}
                            type="number"
                            variant="outlined"
                            disabled

                          />
                        </Grid>
                        <Grid item xs={6}>
                          <TextField
                            fullWidth
                            label="Value Discount"
                            name="valueDiscount"
                            onChange={(e) => handleDiscountInputChange(e, 'valueDiscount')}
                            value={courseData.discountDTOS[0].valueDiscount}
                            type="number"
                            variant="outlined"
                            disabled

                          />
                        </Grid>
                        <Grid item xs={6}>
                          <TextField
                            fullWidth
                            label="Remaining Uses"
                            name="remainingUses"
                            onChange={(e) => handleDiscountInputChange(e, 'remainingUses')}
                            value={courseData.discountDTOS[0].remainingUses}
                            type="number"
                            variant="outlined"
                            disabled

                          />
                        </Grid>
                        <Grid item xs={6}>
                          <TextField
                            fullWidth
                            label="Redemption Date"
                            name="redemptionDate"
                            type="datetime-local"
                            onChange={(e) => handleDiscountInputChange(e, 'redemptionDate')}
                            value={format(new Date(courseData.discountDTOS[0].redemptionDate), 'yyyy-MM-dd\'T\'HH:mm')} InputLabelProps={{ shrink: true }}
                            disabled
                            variant="outlined"
                          />
                        </Grid>
                      </Grid>
                    </CardContent>
                    <Divider />

                  </Card>
                </Stack>
              </Stack>

              <CardActions sx={{ justifyContent: 'flex-end' }}>
                <Button type="submit" variant="contained">
                  Save Changes
                </Button>
              </CardActions>
            </form>
            {/* )} */}
          </Container>
        </Box>
      </PageContainer>
    </div>
  );
};

export default EditCoursePage;
