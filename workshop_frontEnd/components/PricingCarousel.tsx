// import Image from 'next/image';
// import Link from "next/link";
// import styles from '../CSS/home.module.css';
// import React, { useEffect, useState } from 'react';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import ApiService from '@/app/services/ApiService';
// import { useSession } from 'next-auth/react';
// import * as bootstrap from 'bootstrap';
// interface Course {
//     id: number;
//     name: string;
//     description: string;
//     price: number;
//     startDate: string;
//     endDate: string;
//     student_count: number;
//     type: string;
//     public: boolean;
//     teacher: string;
// }

// const PricingCarousel = () => {
//     const { data: session } = useSession();
//     const apiService = new ApiService(session);
//     const [listCourse, setlistCourse] = useState<Course[]>([]); 

//     useEffect(() => {
//         const fetchData = () => {
//             try {
//                 apiService.listCoursePublic()
//                 .then((listCourse)=>{
//                     setlistCourse(listCourse.data);
//                     console.log("listCourse",listCourse.data);
//                 }) .catch((error) => {
//                     console.error(error);
//                 });
//             }
//             catch (error) 
//             {
//                 console.log(error);
//             }

//         }
//         fetchData();
//     },[])
//     useEffect(() => {
//         const carouselElement = document.getElementById('carouselExampleCaptions-2');
//         if (carouselElement) {
//             // Initialize the carousel
//             const carousel = new bootstrap.Carousel(carouselElement, {
//                 interval: 2000, // Set the interval time in milliseconds (adjust as needed)
//                 // other options if needed
//             });

//             // Optional: Pause the carousel when the mouse is over
//             carouselElement.addEventListener('mouseover', () => {
//                 carousel.pause();
//             });

//             // Optional: Resume the carousel when the mouse leaves
//             carouselElement.addEventListener('mouseleave', () => {
//                 carousel.cycle();
//             });
//         }
//     }, []);

//     return (
//         <div>
//             <div className={styles.sectionHeadingPricing}>
//                 <h4>We Offer Exclusive <em className={styles.emm}>Pre-Order</em> Prices !</h4>
//                 <div className="d-flex justify-content-center">
//                     <Image src="/heading-line-dec.png" alt="Ảnh" width={45} height={2} />

//                 </div>
//                 <div className="d-flex justify-content-center">
//                     <p className={styles.pText}>
//                     Check out our amazing pre-order deals – the best prices you can find anywhere. Get ready for a fantastic experience!</p>
//                 </div>

//             </div>

//             <div id="carouselExampleCaptions-2" className="carousel carousel-dark slide" data-bs-ride="true">
//                 <div className={`${styles.indicartorsCustom} carousel-indicators`}>
//                     <button type="button" data-bs-target="#carouselExampleCaptions-2" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
//                     <button type="button" data-bs-target="#carouselExampleCaptions-2" data-bs-slide-to="1" aria-label="Slide 2"></button>
//                     <button type="button" data-bs-target="#carouselExampleCaptions-2" data-bs-slide-to="2" aria-label="Slide 3"></button>
//                 </div>
//                 <div className="carousel-inner">
//                 {listCourse.reduce((chunks, course, index) => {
//       const chunkIndex = Math.floor(index / 3);
//       if (!chunks[chunkIndex]) {
//         chunks[chunkIndex] = [];
//       }
//       chunks[chunkIndex].push(course);
//       return chunks;
//     }, []).map((chunk, chunkIndex) => (
//       <div key={chunkIndex} className={`carousel-item ${chunkIndex === 0 ? 'active' : ''}`}>
//         <div className="card-group">
//           {chunk.map((course, index) => (
//             <div key={index} className={`${styles.cardCustom} card`}>
//               <div className="card-body">
//                 <div className={styles.pricingItemRegular}>
//                   <span className={styles.price}>{course.price}</span>
//                   <h4>{course.name}</h4>
//                   <div className="icon">
//                     <Image src="/pricing-table-01.png" width={100} height={100} alt="" />
//                   </div>
//                   <ul>
//                     <li>{course.description}</li>
//                     {/* Add other fields as needed */}
//                   </ul>
//                   <div className={styles.borderButton}>
//                     <Link href={`/course/${course.id}`}>
//                       {/* Your Link content goes here */}
//                     </Link>
//                   </div>
//                 </div>
//               </div>
//             </div>
//           ))}
//         </div>
//       </div>
//     ))}

//                     <div className="carousel-item">
//                         <div className="card-group">
//                             <div className={`${styles.cardCustom} card`}>
//                                 <div className="card-body">
//                                     <div className={styles.pricingItemRegular}>
//                                         <span className={styles.price}>$12</span>
//                                         <h4>Standard Plan App</h4>
//                                         <div className="icon">
//                                             <Image src="/pricing-table-01.png" width={100} height={100} alt="" />
//                                         </div>
//                                         <ul>
//                                             <li>Lorem Ipsum Dolores</li>
//                                             <li>20 TB of Storage</li>
//                                             <li className="non-function">Life-time Support</li>
//                                             <li className="non-function">Premium Add-Ons</li>
//                                             <li className="non-function">Fastest Network</li>
//                                             <li className="non-function">More Options</li>
//                                         </ul>
//                                         <div className={styles.borderButton}>
//                                             <a href="#">Purchase This Workshop Now</a>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                             <div className={`${styles.cardCustom} card`}>
//                                 <div className="card-body">
//                                     <div className={styles.pricingItemPro}>
//                                         <span className={styles.price}>$25</span>
//                                         <h4>Business Plan App</h4>
//                                         <div className="icon">
//                                             <Image src="/pricing-table-01.png" alt="Pricing Table Icon" width={100} height={100} />
//                                         </div>
//                                         <ul>
//                                             <li>Lorem Ipsum Dolores</li>
//                                             <li>50 TB of Storage</li>
//                                             <li>Life-time Support</li>
//                                             <li>Premium Add-Ons</li>
//                                             <li className="non-function">Fastest Network</li>
//                                             <li className="non-function">More Options</li>
//                                         </ul>
//                                         <div className={styles.borderButton}>
//                                             <a href="#">Purchase This Workshop Now</a>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                             <div className={`${styles.cardCustom} card`}>
//                                 <div className="card-body">
//                                     <div className={styles.pricingItemRegular}>
//                                         <span className={styles.price}>$66</span>
//                                         <h4>Premium Plan App</h4>
//                                         <div className="icon">
//                                             <Image src="/pricing-table-01.png" alt="Pricing Table Icon" width={100} height={100} />
//                                         </div>
//                                         <ul>
//                                             <li>Lorem Ipsum Dolores</li>
//                                             <li>120 TB of Storage</li>
//                                             <li>Life-time Support</li>
//                                             <li>Premium Add-Ons</li>
//                                             <li>Fastest Network</li>
//                                             <li>More Options</li>
//                                         </ul>
//                                         <div className={styles.borderButton}>
//                                             <a href="#">Purchase This Workshop Now</a>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                             {/* Thêm thẻ card khác tại đây cho Carousel Item 3 */}
//                         </div>
//                     </div>

//                     <div className="carousel-item">
//                         <div className="card-group">
//                             <div className={`${styles.cardCustom} card`}>
//                                 <div className="card-body">
//                                     <div className={styles.pricingItemRegular}>
//                                         <span className={styles.price}>$12</span>
//                                         <h4>Standard Plan App</h4>
//                                         <div className="icon">
//                                             <Image src="/pricing-table-01.png" width={100} height={100} alt="" />
//                                         </div>
//                                         <ul>
//                                             <li>Lorem Ipsum Dolores</li>
//                                             <li>20 TB of Storage</li>
//                                             <li className="non-function">Life-time Support</li>
//                                             <li className="non-function">Premium Add-Ons</li>
//                                             <li className="non-function">Fastest Network</li>
//                                             <li className="non-function">More Options</li>
//                                         </ul>
//                                         <div className={styles.borderButton}>
//                                             <a href="#">Purchase This Workshop Now</a>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                             <div className={`${styles.cardCustom} card`}>
//                                 <div className="card-body">
//                                     <div className={styles.pricingItemPro}>
//                                         <span className={styles.price}>$25</span>
//                                         <h4>Business Plan App</h4>
//                                         <div className="icon">
//                                             <Image src="/pricing-table-01.png" alt="Pricing Table Icon" width={100} height={100} />
//                                         </div>
//                                         <ul>
//                                             <li>Lorem Ipsum Dolores</li>
//                                             <li>50 TB of Storage</li>
//                                             <li>Life-time Support</li>
//                                             <li>Premium Add-Ons</li>
//                                             <li className="non-function">Fastest Network</li>
//                                             <li className="non-function">More Options</li>
//                                         </ul>
//                                         <div className={styles.borderButton}>
//                                             <a href="#">Purchase This Workshop Now</a>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                             <div className={`${styles.cardCustom} card`}>
//                                 <div className="card-body">
//                                     <div className={styles.pricingItemRegular}>
//                                         <span className={styles.price}>$66</span>
//                                         <h4>Premium Plan App</h4>
//                                         <div className="icon">
//                                             <Image src="/pricing-table-01.png" alt="Pricing Table Icon" width={100} height={100} />
//                                         </div>
//                                         <ul>
//                                             <li>Lorem Ipsum Dolores</li>
//                                             <li>120 TB of Storage</li>
//                                             <li>Life-time Support</li>
//                                             <li>Premium Add-Ons</li>
//                                             <li>Fastest Network</li>
//                                             <li>More Options</li>
//                                         </ul>
//                                         <div className={styles.borderButton}>
//                                             <a href="#">Purchase This Workshop Now</a>
//                                         </div>
//                                     </div>
//                                 </div>
//                             </div>
//                             {/* Thêm thẻ card khác tại đây cho Carousel Item 3 */}
//                         </div>
//                     </div>
//                 </div>
//                 <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions-2" data-bs-slide="prev">
//                     <span className="carousel-control-prev-icon" aria-hidden="true"></span>
//                     <span className="visually-hidden">Previous</span>
//                 </button>
//                 <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions-2" data-bs-slide="next">
//                     <span className="carousel-control-next-icon" aria-hidden="true"></span>
//                     <span className="visually-hidden">Next</span>
//                 </button>
//             </div>
//         </div>
//     );
// };

// export default PricingCarousel;
// <>
//     <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
//     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
//     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
// </>

import Image from 'next/image';
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
        price: 0;
        startDate: string,
        courseMediaInfos: {
            urlImage: string; 
        };
        // Các thuộc tính khác nếu có
    }
    const { data: session } = useSession();
    const apiService = new ApiService(session);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await apiService.listCoursePublic();
                if (Array.isArray(result.data)) {
                    const sortedCourses = result.data.sort((a: { startDate: string | number | Date; }, b: { startDate: string | number | Date; }) => new Date(b.startDate).getTime() - new Date(a.startDate).getTime());

                    setCourses(sortedCourses);

                } else {
                    console.error('Data is not an array:', result.data);
                }
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
        <div>
            <div className={styles.sectionHeadingPricing}>
                <h4>We Offer Exclusive <em className={styles.emm}>Pre-Order</em> Prices !</h4>
                <div className="d-flex justify-content-center">
                    <Image src="/heading-line-dec.png" alt="Ảnh" width={45} height={2} />

                </div>
                <div className="d-flex justify-content-center">
                    <p className={styles.pText}>
                        Check out our amazing pre-order deals – the best prices you can find anywhere. Get ready for a fantastic experience!</p>
                </div>

            </div>
            <Carousel>
                {chunkArray(courses, 3).map((chunk, chunkIndex) => (
                    <Carousel.Item key={chunkIndex}>
                        <div className="card-group">
                            {chunk.map((course, index) => (
                                <div key={index} className={`card ${styles.cardCustom}`}>
                                    <div className="card-body">
                                        <div key={index} className={`card ${index === Math.floor(chunk.length / 2) ? styles.pricingItemPro : styles.pricingItemRegular}`}>
                                            <span className={styles.price}>${course.price}</span>
                                            <h4>{course.name}</h4>
                                            <div className="icon">
                                                <Image src={course.courseMediaInfos.urlImage} width={100} height={100} alt="" />
                                            </div>

                                            <ul>
                                                <li>{course.description}</li>
                                                <li>{course.description}</li>
                                                <li>{course.description}</li>
                                            </ul>
                                            <div className={styles.borderButton}>
                                                <a href="#">Purchase This Workshop Now</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </Carousel.Item>
                ))}
            </Carousel>
        </div>
    );
};

export default Card;