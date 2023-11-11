'use client';
import {
    Box,
    Button,
    Container,
    Pagination,
    Stack,
    SvgIcon,
    Typography,
    Unstable_Grid2 as Grid
} from '@mui/material';
import PageContainer from '@/app/user/components/container/PageContainer';
import React, { useEffect, useState } from "react";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import { CompanyCard } from './company-card.js';
import ApiService from '@/app/services/ApiService';
import { useSession } from 'next-auth/react';
import Link from 'next/link';

interface Course {
    id: number;
}

const Buttons: React.FC = () => {
    const [courses, setCourses] = useState<Course[]>([]);
    const [loading, setLoading] = useState(true);
    const { data: session } = useSession();
  
    useEffect(() => {
      const fetchData = async () => {
        try {
          const apiService = new ApiService(session);
          const response = await apiService.listCoursesFromApi();
          if (response.status === "success") {
            setCourses(response.data);
          }
        } catch (error) {
          console.error('Lỗi khi tải dữ liệu:', error);
        } finally {
          setLoading(false);
        }
      };
  
      if (session) {
        fetchData();
      }
    }, [session]);
  


    return (
        <PageContainer title="button" description="this is button">
            <Box
                component="main"
                sx={{
                    flexGrow: 1,
                    py: 8
                }}
            >
                <Container maxWidth="xl">
                    <Stack spacing={3}>
                        <Stack
                            direction="row"
                            justifyContent="space-between"
                            spacing={4}
                        >
                            <Stack spacing={1}>
                                <Typography variant="h4">
                                    Courses
                                </Typography>
                                <Stack
                                    alignItems="center"
                                    direction="row"
                                    spacing={1}
                                >
                                    <Button
                                        color="inherit"
                                        startIcon={(
                                            <SvgIcon fontSize="small">
                                                {/* <ArrowUpOnSquareIcon /> */}
                                            </SvgIcon>
                                        )}
                                    >
                                        Import
                                    </Button>
                                    <Button
                                        color="inherit"
                                        startIcon={(
                                            <SvgIcon fontSize="small">
                                                {/* <ArrowDownOnSquareIcon /> */}
                                            </SvgIcon>
                                        )}
                                    >
                                        Export
                                    </Button>
                                </Stack>
                            </Stack>

                            <Link href="./add">
                                <Button
                                    startIcon={<SvgIcon fontSize="small">...</SvgIcon>}
                                    variant="contained"
                                >
                                    Add
                                </Button>
                            </Link>
                        </Stack>
                        {/* <CompaniesSearch /> */}
                        {loading ? (
                            <p>Đang tải...</p>
                        ) : courses.length > 0 ? (
                            <Grid container spacing={3}>
                                {courses.map((course, index) => (
                                    <Grid xs={12} md={6} lg={4} key={index}>
                                         {/* <div onClick={() => router.push(`./edit/${course.id}`)} > */}
                                          {/* <Link style={{ textDecoration: 'none', color: 'inherit' }} href={`./edit/${course.id}`} passHref> */}
                                          <CompanyCard  courses={courses} courseId={course.id} />
                                        {/* </Link> */}
                                        {/* </div> */}
                                    </Grid>

                                ))}
                            </Grid>
                        ) : (
                            <p>Không có khóa học nào</p>
                        )}

                        <Box
                            sx={{
                                display: 'flex',
                                justifyContent: 'center'
                            }}
                        >
                            <Pagination
                                count={3}
                                size="small"
                            />
                        </Box>
                    </Stack>
                </Container>
            </Box>
        </PageContainer>
    );
};
export default Buttons;
