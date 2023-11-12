import { Button, Container } from "react-bootstrap";
import dynamic from 'next/dynamic';
import Link from "next/link";
import styles from '../../[id]/courseDemo.module.css';
import { useEffect, useState } from "react";
import ApiService from "@/app/services/ApiService";
import { useSession } from 'next-auth/react';

const ReactPlayer = dynamic(() => import('react-player'), { ssr: false });

export default function SideLeft({ course }) {
    const [isFree, setIsFree] = useState(false);
    const [isUserInCourse, setIsUserInCourse] = useState(false);
    const { data: session } = useSession();
    const apiService = new ApiService(session);
    const email = session?.user.email;

    useEffect(() => {
        const fetchData = async () => {
            if (course) {
                try {
                    const response = await apiService.checkUserInCourse(email, course.id);
                    console.log("response.status", response.status);

                    if (response.status === 'true') {
                        setIsUserInCourse(true);
                        console.log("User is in the course");
                    } else if (response.status === 'false') {
                        setIsUserInCourse(false);
                        console.log("User is not in the course, prompt to buy");
                    }
                } catch (error) {
                    console.error("Error checking user in course:", error);
                }
            }
        };
        const handleRegistration = async () => {
            if (course?.price === 0) {
                setIsFree(true);
                fetchData();
            }
        };
        handleRegistration();

    }, [course]);

    if (!course) {
        return null; // or render a loading state
    }

    return (
        <Container className="p-2 ">
            <div className={styles.videothum}>
                <ReactPlayer
                    url='https://youtu.be/4zlqCu24wr4'
                    controls={true}
                    playing={true}
                    muted={true}
                    width={'100%'}
                    height={333}
                />
            </div>
            <div className="text-center">
                <h1>{isFree ? 'free' : 'premium'}</h1>
                {isFree ? (
                    <Link href={'/courseDemo/register'}>
                        Xem Miễn Phí
                    </Link>
                ) : (
                    isUserInCourse ? (
                        <Link href={'/courseDemo/watch'}>
                            Xem Video
                        </Link>
                    ) : (
                        <Button onClick={() => handleRegistration()}>Mua Ngay</Button>
                    )
                )}
                <h4>Tổng Cộng có 11 bài</h4>
                <h4>Tổng Thời Lượng</h4>
            </div>
        </Container>
    );
}
