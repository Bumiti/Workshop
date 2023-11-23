import styles from '../CSS/home.module.css';
import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Carousel } from 'react-bootstrap';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { v4 as uuidv4 } from 'uuid';
import ApiService from '@/app/services/ApiService';
import { useSession } from 'next-auth/react';
const Card = () => {
  const [courses, setCourses] = useState<CourseType[]>([]); // Thay thế 'CourseType' bằng kiểu dữ liệu cụ thể bạn sử dụng
  const router = useRouter();
  interface CourseType {
    id: number;
    name: string;
    description: string;
    link: string;
    // Các thuộc tính khác nếu có
  }
  const { data: session } = useSession();
  const apiService = new ApiService(session);
  useEffect(() => {
    const fetchData = async () => {
      try {
        if(session){
        const result = await apiService.listCoursePublic();
        if (Array.isArray(result.data)) {
          setCourses(result.data);
        } else {
          console.error('Data is not an array:', result.data);
        }}
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, [session]);


  const chunkArray = (arr: CourseType[], chunkSize: number) => {
    const groups = [];
    for (let i = 0; i < arr.length; i += chunkSize) {
      groups.push(arr.slice(i, i + chunkSize));
    }
    return groups;
  };
  const randomToken = uuidv4();
  return (
    <Carousel>
      {chunkArray(courses, 4).map((chunk, chunkIndex) => (
        <Carousel.Item key={chunkIndex}>
          <div className="card-group">
            {chunk.map((course, index) => (
              <div key={index} className={`card ${styles.cardCustom}`}>
                <div className="card-body">
                  <div className={`${styles.serviceItem}`}>
                  <h2>{course.id}</h2>
                    <h4>{course.name}</h4>
                    <p>{course.description}</p>
                    <div className={`textButton ${styles.textButton}`}>
                      <Link href={`/courseDemo/[id]`} as={`/courseDemo/${course.id}`}>
                        Đăng kí nhanh
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </Carousel.Item>
      ))}
    </Carousel>

  );
};

export default Card;
