'use client';
import Head from 'next/head';
import React, { useEffect, useState } from 'react';
import {
  Box,
  Container,
  Stack,
  Typography,
  Grid,
  CardActions,
  Button,
} from '@mui/material';
import PageContainer from '@/app/teacher/components/container/PageContainer';
import ApiService from '@/app/services/ApiService';
import { useSession } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import CourseData from './courseData';
import { AccountProfile } from './account-profile';
import { DiscountDTOS } from './discount';
import { useParams } from 'next/navigation';
import { AccountProfileDetails } from './account-profile-details';
interface EditProps {
  params: {
    id: number;
  };
  onSubmit: (data: CourseData) => void;
}

const Edit = ({ params, onSubmit }: EditProps) => {

  const router = useRouter();
  const { data: session } = useSession();
  const [formData, setFormData] = useState<CourseData>({
    id: 0,
    courseName: '',
    description: '',
    price: 0,
    startDate: '',
    endDate: '',
    studentCount: 0,
    type: 'offline',
    MediaInfoList: [
      {
        id: 0,
        thumbnailSrc: '',
        title: 'string',
        urlImage: '',
        urlMedia: '',
      },
    ],
    discountDTOS: [
      {
        courseDiscount_id: 0,
        quantity: 0,
        valueDiscount: 0,
        redemptionDate: new Date(),
        name: '',
        description: '',
        remainingUses: 0,
      },
    ],
    courseLocation: [],
  });

  useEffect(() => {
    const fetchCourseData = async () => {
      try {

        const apiService = new ApiService(session);
        const response = await apiService.getCourseById(params.id);
        if (response.status === 'success') {

          setFormData(response.data);
          console.log('Updated formData:', response.data);

        } else {
          console.error('Error fetching course data:', response.error);
        }

      } catch (error) {
        console.error('Error:', error);
      }
    };
    if (session) {
      fetchCourseData();
    }
  }, [session, params.id]);

  const handleReceivedVideo = (data: any) => {
    setFormData((prevData) => ({
      ...prevData,
      MediaInfoList: [
        {
          ...prevData.MediaInfoList[0],
          ...data,
        },
      ],
    }));
  };

  const handleAccountProfileDetailsSubmit = (changedData: any) => {
    console.log('Dữ liệu từ việc gửi biểu mẫu:', changedData);

    setFormData((prevData) => {
      // Cập nhật dữ liệu mới vào prevData sử dụng spread operator
      const newData = { ...changedData };

      console.log('handleAccountProfileDetailsSubmit đã thay đổi dữ liệu:', newData);
      return newData;
    });
  };


  // const handleDiscountDTOSubmit = (data: any) => {
  //   console.log('Dữ liệu từ việc gửi biểu handleDiscountDTOSubmit:', data);

  //   setFormData((prevData) => {
  //     // Dữ liệu mới từ onDataChanged
  //     const changedData = data.discountDTOS[0];
  // console.log('changedData nè',changedData);

  //     // Kiểm tra xem prevData.discountDTOS có tồn tại không
  //     const prevDiscountDTOS = prevData.discountDTOS || [];

  //     // Cập nhật dữ liệu mới vào formData sử dụng spread operator
  //     const newData = {
  //       ...prevData. discountDTOS: [
  //         {
  //           ...prevDiscountDTOS[0],
  //           ...changedData,
  //         },
  //       ],

  //       // discountDTOS: [
  //       //   {
  //       //     ...prevDiscountDTOS[0],
  //       //     ...changedData,
  //       //   },
  //       // ],
  //     };

  //     // console.log('handleDiscountDTOSubmit thay doi ne', newData);

  //     // Kiểm tra giá trị mới của discountDTOS
  //     // console.log('newData.discountDTOS', newData.discountDTOS);

  //     return newData;
  //   });
  // };


  const handleDiscountDTOSubmit = (data: any) => {
    console.log('Dữ liệu từ việc gửi biểu handleDiscountDTOSubmit:', data);

    setFormData((prevData) => {
      // Dữ liệu mới từ onDataChanged
      const changedData = data.discountDTOS[0];
        console.log('changedData nè', changedData);
        console.log('prevData trước khi cập nhật:', prevData);

      // Kiểm tra xem prevData.discountDTOS có tồn tại không
      const prevDiscountDTOS = prevData?.[0]?.discountDTOS?.[0];

      // const prevDiscountDTOS = (prevData[0] && prevData[0].discountDTOS) || [];


      // Tạo một bản sao của prevData
      const newData = { ...prevData };
        console.log('newData 1', newData);

      // Nếu prevDiscountDTOS có ít nhất một phần tử, thì cập nhật phần tử đầu tiên của mảng
      if (prevDiscountDTOS) {
        newData[0].discountDTOS = [
          {
            ...prevDiscountDTOS,
            ...changedData,
          },
        ];
      }

      return newData[0];
      console.log(newData);
      
    });
  };

  // const handleDiscountDTOSubmit = (data: any) => {
  //   setFormData((prevData) => {
  //     console.log('Previous Data:', prevData); // Log prevData
  //     const newData = { ...prevData.discountDTOS[0], ...data.discountDTOS[0] };
  //      // Log prevData
  //   console.log(newData);

  //  return newData;

  //   });
  // };

  console.log('formData', formData);

  const handleEditDataToServer = async (e: React.FormEvent) => {
    e.preventDefault();
    try {

      const numericId = Number(params.id);
      const apiService = new ApiService(session);
      const response = await apiService.editCourse(numericId, formData);
      console.log('handleEditDataToServer formData', formData);

      if (response.status === 'success') {
        onSubmit(response);

        router.push('/courses');
      } else {
        console.error('Error editing course:', response.error);
      }
      // }
    } catch (error) {
      console.error('Error:', error);
    }
  };
  console.log(handleEditDataToServer);

  return (
    <PageContainer>
      <Head>
        <title>Edit Course</title>
      </Head>
      <Box component="main" sx={{ flexGrow: 1, py: 8 }}>
        <Container maxWidth="lg">
          <form onSubmit={handleEditDataToServer}>
            <Stack spacing={3}>
              <Typography variant="h4">Edit Course</Typography>
              <Grid container spacing={3}>
                <Grid item xs={12} md={6} lg={4}>
                  <AccountProfile onVideoUpload={handleReceivedVideo} formData={formData || {}} existingVideoUrl={   formData &&formData.MediaInfoList && formData.MediaInfoList.length > 0
                    ? formData.MediaInfoList[0].urlMedia
                    : ''} />
                </Grid>
                <Grid item xs={12} md={6} lg={8}>
                  <AccountProfileDetails
                    onDataChanged={handleAccountProfileDetailsSubmit}
                    formData={formData || {}}
                  />
                </Grid>
              </Grid>
              <Stack spacing={3}>
                <DiscountDTOS onDataChanged={handleDiscountDTOSubmit} formData={formData || {}} />
              </Stack>
            </Stack>

            <CardActions sx={{ justifyContent: 'flex-end' }}>
              <Button type="submit" variant="contained">
                Save Changes
              </Button>
            </CardActions>
          </form>
        </Container>
      </Box>
    </PageContainer>
  );
};

export default Edit;
