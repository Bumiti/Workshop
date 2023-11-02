import Image from 'next/image';
import Link from "next/link";
import styles from '../CSS/home.module.css';
import React, { useState } from 'react'; import 'bootstrap/dist/css/bootstrap.min.css';

const Testimonials = () => {
    const testimonials = [  // Định nghĩa danh sách đánh giá sao
        {
            name: 'David Martino Co',
            date: '30 November 2021',
            category: 'Financial Apps',
        },
        {
            name: 'Jake Harris Nyo',
            date: '29 November 2021',
            category: 'Digital Business',
        },
        {
            name: 'Random User',
            date: '24 November 2021',
            category: 'New App Ecosystem',
        },
        {
            name: 'Mark Amber Do',
            date: '21 November 2021',
            category: 'Web Development'
        },
        // Thêm các đánh giá khác vào đây
    ];
    const [ratings, setRatings] = useState<number[]>([]);  // State để lưu trạng thái số sao đã chọn
    const handleRatingChange = (index: number, value: number) => {
        const updatedRatings: number[] = [...ratings]; // Xác định kiểu dữ liệu cho updatedRatings
        updatedRatings[index] = value;
        setRatings(updatedRatings);
    };
    return (
        <div id="clients" className={`the-clients ${styles.theClients}`}>
            <div className="container">
                <div className="row">
                    <div className="col-lg-8 offset-lg-2">
                        <div className={`section-heading ${styles.sectionHeadingService}`}>
                            <div className="d-flex justify-content-center">
                                <h4>Check What <em className={styles.emm}>The Clients Say</em>  About Our App Dev</h4>
                            </div>
                            <div className="d-flex justify-content-center">
                                <Image src="/heading-line-dec.png" alt="Ảnh" width={45} height={2} />
                            </div>
                            <p className={styles.pText}>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna.
                            </p>
                        </div>
                    </div>
                    <div className="col-lg-12">
                        <div className="naccs">
                            <div className="grid">
                                <div className="row">
                                    <div className="col-lg-7 align-self-center">
                                        <div className="menu">
                                            {/* Bắt đầu phần map */}
                                            {testimonials.map((testimonial, index) => (
                                                <div key={index}>
                                                    <div className="thumb">
                                                        <div className="row">
                                                            <div className="col-lg-4 col-sm-4 col-12">
                                                                <h4>{testimonial.name}</h4>
                                                                <span className="date">{testimonial.date}</span>
                                                            </div>
                                                            <div className="col-lg-4 col-sm-4 d-none d-sm-block">
                                                                <span className="category">{testimonial.category}</span>
                                                            </div>
                                                            <div className="col-lg-4 col-sm-4 col-12">
                                                                <div className={styles.rating}>
                                                                    {Array.from({ length: 5 }).map((_, i) => (
                                                                        <div key={i}>
                                                                            <input
                                                                                type="radio"
                                                                                id={`star${5 - i}_${index}`}
                                                                                name={`rating_${index}`}
                                                                                value={5 - i}
                                                                                onChange={() => handleRatingChange(index, 5 - i)}
                                                                                checked={ratings[index] === 5 - i} // Kiểm tra xem sao đã được chọn hay chưa
                                                                            />
                                                                            <label htmlFor={`star${5 - i}_${index}`}></label>
                                                                        </div>
                                                                    ))}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            ))}

                                            {/* Kết thúc phần map */}
                                            {/* Thêm các phần tử khác tại đây */}
                                        </div>
                                    </div>
                                    <div className="col-lg-5">
                                        <div className={styles.clientContent}>
                                            <Image src="/quote.png" alt="" width={50} height={50} />
                                            <p>
                                                “Lorem ipsum dolor sit amet, consectetur adpiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua darwin kengan lorem ipsum dolor sit amet, consectetur picing elit massive big blasta.”
                                            </p>
                                        </div>
                                        <div className="down-content">
                                            <Image src="/client-image.jpg" alt="" width={100} height={100} className={styles.downContent} />
                                            <div className="right-content">
                                                <h4>David Martino</h4>
                                                <span>CEO of David Company</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Testimonials;
