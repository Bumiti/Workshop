import Image from 'next/image';
import Link from "next/link";
import styles from '../CSS/home.module.css';
import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const PricingCarousel = () => {
    return (
        <div>
            <div className={styles.sectionHeadingPricing}>
                <h4>We Have The Best Pre-Order <em className={styles.emm}>Prices</em> You Can Get</h4>
                <div className="d-flex justify-content-center">
                    <Image src="/heading-line-dec.png" alt="Ảnh" width={45} height={2} />
                
                </div>      
                <div className="d-flex justify-content-center">  
                <p className={styles.pText}>
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna.</p>
                </div>      

            </div>
            <div id="carouselExampleCaptions-2" className="carousel carousel-dark slide" data-bs-ride="false">
                <div className={`${styles.indicartorsCustom} carousel-indicators`}>
                    <button type="button" data-bs-target="#carouselExampleCaptions-2" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions-2" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions-2" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div className="carousel-inner">
                    <div className="carousel-item active">
                        <div className="card-group">
                            <div className={`${styles.cardCustom} card`}>
                                <div className="card-body">
                                    <div className={styles.pricingItemRegular}>
                                        <span className={styles.price}>$12</span>
                                        <h4>Standard Plan App</h4>
                                        <div className="icon">
                                            <Image src="/pricing-table-01.png" width={100} height={100} alt="" />
                                        </div>
                                        <ul>
                                            <li>Lorem Ipsum Dolores</li>
                                            <li>20 TB of Storage</li>
                                            <li className="non-function">Life-time Support</li>
                                            <li className="non-function">Premium Add-Ons</li>
                                            <li className="non-function">Fastest Network</li>
                                            <li className="non-function">More Options</li>
                                        </ul>
                                        <div className={styles.borderButton}>
                                            <a href="#">Purchase This Plan Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className={`${styles.cardCustom} card`}>
                                <div className="card-body">
                                    <div className={styles.pricingItemPro}>
                                        <span className={styles.price}>$25</span>
                                        <h4>Business Plan App</h4>
                                        <div className="icon">
                                            <Image src="/pricing-table-01.png" alt="Pricing Table Icon" width={100} height={100} />
                                        </div>
                                        <ul>
                                            <li>Lorem Ipsum Dolores</li>
                                            <li>50 TB of Storage</li>
                                            <li>Life-time Support</li>
                                            <li>Premium Add-Ons</li>
                                            <li className="non-function">Fastest Network</li>
                                            <li className="non-function">More Options</li>
                                        </ul>
                                        <div className={styles.borderButton}>
                                            <a href="#">Purchase This Plan Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className={`${styles.cardCustom} card`}>
                                <div className="card-body">
                                    <div className={styles.pricingItemRegular}>
                                        <span className={styles.price}>$66</span>
                                        <h4>Premium Plan App</h4>
                                        <div className="icon">
                                            <Image src="/pricing-table-01.png" alt="Pricing Table Icon" width={100} height={100} />
                                        </div>
                                        <ul>
                                            <li>Lorem Ipsum Dolores</li>
                                            <li>120 TB of Storage</li>
                                            <li>Life-time Support</li>
                                            <li>Premium Add-Ons</li>
                                            <li>Fastest Network</li>
                                            <li>More Options</li>
                                        </ul>
                                        <div className={styles.borderButton}>
                                            <a href="#">Purchase This Plan Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            {/* Thêm thẻ card khác tại đây cho Carousel Item 3 */}
                        </div>
                    </div>

                    <div className="carousel-item">
                        <div className="card-group">
                            <div className={`${styles.cardCustom} card`}>
                                <div className="card-body">
                                    <div className={styles.pricingItemRegular}>
                                        <span className={styles.price}>$12</span>
                                        <h4>Standard Plan App</h4>
                                        <div className="icon">
                                            <Image src="/pricing-table-01.png" width={100} height={100} alt="" />
                                        </div>
                                        <ul>
                                            <li>Lorem Ipsum Dolores</li>
                                            <li>20 TB of Storage</li>
                                            <li className="non-function">Life-time Support</li>
                                            <li className="non-function">Premium Add-Ons</li>
                                            <li className="non-function">Fastest Network</li>
                                            <li className="non-function">More Options</li>
                                        </ul>
                                        <div className={styles.borderButton}>
                                            <a href="#">Purchase This Plan Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className={`${styles.cardCustom} card`}>
                                <div className="card-body">
                                    <div className={styles.pricingItemPro}>
                                        <span className={styles.price}>$25</span>
                                        <h4>Business Plan App</h4>
                                        <div className="icon">
                                            <Image src="/pricing-table-01.png" alt="Pricing Table Icon" width={100} height={100} />
                                        </div>
                                        <ul>
                                            <li>Lorem Ipsum Dolores</li>
                                            <li>50 TB of Storage</li>
                                            <li>Life-time Support</li>
                                            <li>Premium Add-Ons</li>
                                            <li className="non-function">Fastest Network</li>
                                            <li className="non-function">More Options</li>
                                        </ul>
                                        <div className={styles.borderButton}>
                                            <a href="#">Purchase This Plan Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className={`${styles.cardCustom} card`}>
                                <div className="card-body">
                                    <div className={styles.pricingItemRegular}>
                                        <span className={styles.price}>$66</span>
                                        <h4>Premium Plan App</h4>
                                        <div className="icon">
                                            <Image src="/pricing-table-01.png" alt="Pricing Table Icon" width={100} height={100} />
                                        </div>
                                        <ul>
                                            <li>Lorem Ipsum Dolores</li>
                                            <li>120 TB of Storage</li>
                                            <li>Life-time Support</li>
                                            <li>Premium Add-Ons</li>
                                            <li>Fastest Network</li>
                                            <li>More Options</li>
                                        </ul>
                                        <div className={styles.borderButton}>
                                            <a href="#">Purchase This Plan Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            {/* Thêm thẻ card khác tại đây cho Carousel Item 3 */}
                        </div>
                    </div>

                    <div className="carousel-item">
                        <div className="card-group">
                            <div className={`${styles.cardCustom} card`}>
                                <div className="card-body">
                                    <div className={styles.pricingItemRegular}>
                                        <span className={styles.price}>$12</span>
                                        <h4>Standard Plan App</h4>
                                        <div className="icon">
                                            <Image src="/pricing-table-01.png" width={100} height={100} alt="" />
                                        </div>
                                        <ul>
                                            <li>Lorem Ipsum Dolores</li>
                                            <li>20 TB of Storage</li>
                                            <li className="non-function">Life-time Support</li>
                                            <li className="non-function">Premium Add-Ons</li>
                                            <li className="non-function">Fastest Network</li>
                                            <li className="non-function">More Options</li>
                                        </ul>
                                        <div className={styles.borderButton}>
                                            <a href="#">Purchase This Plan Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className={`${styles.cardCustom} card`}>
                                <div className="card-body">
                                    <div className={styles.pricingItemPro}>
                                        <span className={styles.price}>$25</span>
                                        <h4>Business Plan App</h4>
                                        <div className="icon">
                                            <Image src="/pricing-table-01.png" alt="Pricing Table Icon" width={100} height={100} />
                                        </div>
                                        <ul>
                                            <li>Lorem Ipsum Dolores</li>
                                            <li>50 TB of Storage</li>
                                            <li>Life-time Support</li>
                                            <li>Premium Add-Ons</li>
                                            <li className="non-function">Fastest Network</li>
                                            <li className="non-function">More Options</li>
                                        </ul>
                                        <div className={styles.borderButton}>
                                            <a href="#">Purchase This Plan Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className={`${styles.cardCustom} card`}>
                                <div className="card-body">
                                    <div className={styles.pricingItemRegular}>
                                        <span className={styles.price}>$66</span>
                                        <h4>Premium Plan App</h4>
                                        <div className="icon">
                                            <Image src="/pricing-table-01.png" alt="Pricing Table Icon" width={100} height={100} />
                                        </div>
                                        <ul>
                                            <li>Lorem Ipsum Dolores</li>
                                            <li>120 TB of Storage</li>
                                            <li>Life-time Support</li>
                                            <li>Premium Add-Ons</li>
                                            <li>Fastest Network</li>
                                            <li>More Options</li>
                                        </ul>
                                        <div className={styles.borderButton}>
                                            <a href="#">Purchase This Plan Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            {/* Thêm thẻ card khác tại đây cho Carousel Item 3 */}
                        </div>
                    </div>
                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions-2" data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions-2" data-bs-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    );
};

export default PricingCarousel;
