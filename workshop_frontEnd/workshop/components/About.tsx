import Image from 'next/image';
import Link from "next/link";
import styles from '../CSS/home.module.css';
import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
const About = () => {
    return (
        <div id="about" className={`${styles.aboutUs }section`}>
            <div className="container">
                <div className="row">
                    <div className="col-lg-6 align-self-center">

                        <div className={`${styles.sectionHeadingService} wow fadeInDown`} data-wow-duration="1s" data-wow-delay="0.5s">
                            <h4>
                                About <em className={styles.emm}>What We Do</em> &amp; Who We Are
                            </h4>
                            <Image src="/heading-line-dec.png" alt="" width={45} height={2} />
                            <p className={styles.pText}>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna.
                            </p>
                        </div>
                        <div className="row">
                            <div className="col-lg-6">
                                <div className={`${styles.boxItem} box-item`}>
                                    <h4><a href="#">Maintance Problems</a></h4>
                                    <p>Lorem Ipsum Text</p>
                                </div>
                            </div>
                            <div className="col-lg-6">
                                <div className={`${styles.boxItem} box-item`}>                  <h4><a href="#">24/7 Support &amp; Help</a></h4>
                                    <p>Lorem Ipsum Text</p>
                                </div>
                            </div>
                            <div className="col-lg-6">
                                <div className={`${styles.boxItem} box-item`}>                  <h4><a href="#">Fixing Issues About</a></h4>
                                    <p>Lorem Ipsum Text</p>
                                </div>
                            </div>
                            <div className="col-lg-6">
                                <div className={`${styles.boxItem} box-item`}>                  <h4><a href="#">Co. Development</a></h4>
                                    <p>Lorem Ipsum Text</p>
                                </div>
                            </div>
                            <div className="col-lg-12">
                            <p className={styles.pText}>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna.
                                </p>
                                <div className="gradient-button">
                                    <Link className={styles.gradientbutton} id="modal_trigger" href="#modal">Start 14-Day Free Trial</Link>

                                </div>
                                <span className={styles.pText}>*No Credit Card Required</span>
                            </div>
                        </div>
                    </div>
                    <div className="col-lg-6">
                        <div className="right-image">
                            <Image src="/about-right-dec.png" alt="" width={630} height={782} />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default About;
