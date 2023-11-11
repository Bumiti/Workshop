import styles from '../CSS/home.module.css';
import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Carousel } from 'react-bootstrap';
import Link from 'next/link';
import SliderVideo from '@/app/video/component/SliderVideo';

const Card = () => {
  const [courses, setCourses] = useState<CourseType[]>([]);
  const [selectedVideoUrl, setSelectedVideoUrl] = useState<string>('');

  interface CourseType {
    id: number;
    name: string;
    description: string;
    link: string;
    // Other properties if any
  }

  const handleVideoSelect = (videoId: number) => {
    const selectedCourse = courses.find(course => course.id === videoId);
    if (selectedCourse) {
      setSelectedVideoUrl(selectedCourse.link);
    }
  };

  useEffect(() => {
    // Fetching course list
    fetch('http://localhost:8089/web/course/list')
      .then(response => response.json())
      .then(result => {
        if (Array.isArray(result.data)) {
          setCourses(result.data);
        } else {
          console.error('Data is not an array:', result.data);
        }
      })
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  const chunkArray = (arr: CourseType[], chunkSize: number) => {
    const groups: CourseType[][] = [];
    for (let i = 0; i < arr.length; i += chunkSize) {
      groups.push(arr.slice(i, i + chunkSize));
    }
    return groups;
  };

  return (
    <Carousel>
      {chunkArray(courses, 3).map((chunk, chunkIndex) => (
        <Carousel.Item key={chunkIndex}>
          <div className="card-group">
            {chunk.map((course, index) => (
              <div key={index} className={`card ${styles.cardCustom}`}>
                <div className="card-body">
                  <div className={`${styles.serviceItem}`}>
                    <h4>{course.name}</h4>
                    <p>{course.description}</p>
                    <Link href={`/video/${course.id}`}>
                    <span>Read More</span>
                    </Link>
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
