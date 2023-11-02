import Image from 'next/image';
import Link from "next/link";
import styles from '../CSS/home.module.css';
import React, { useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
const Card = () => {

  return (
    
    <div id="carouselExampleCaptions" className="carousel carousel-dark slide"data-bs-ride="carousel">
    <div className={`${styles.indicartorsCustom} carousel-indicators`}>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" className="active"
          aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
          aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
          aria-label="Slide 3"></button>
      </div>
      <div className="carousel-inner">
        <div className="carousel-item active">
          <div className="card-group">
          <div className={`${styles.cardCustom} card`}>
              <div className="card-body">
              <div className={`${styles.serviceItem} firstService`}>
              <div className={styles.icon}></div>
                  {/* <Image src="/service-icon-01.png" width={57} height={57}  className={`${styles.cardImg}`} alt="..." /> */}
                  <h4>App Maintenance</h4>
                  <p>You are not allowed to redistribute this template ZIP file ZIP file ZIP file on any other website.</p>
                  <div className={styles.textButton}>
                    <a  className={styles.textButtonA} href="#">Read More <i className="fa fa-arrow-right"></i></a>
                  </div>
                </div>
              </div>
            </div>
            <div className={`${styles.cardCustom} card`}>
              <div className="card-body">
              <div className={`${styles.serviceItem} second-service`}>
              <div className={styles.icon}></div>
                  {/* <Image src="/service-icon-02.png"  width={57} height={57}  className={`${styles.cardImg}`} alt="..." /> */}
                  <h4>Rocket Speed of App</h4>
                  <p>You are allowed to use the Chain App Dev HTML template. Feel free to modify or edit this layout.</p>
                  <div className={styles.textButton}>
                    <a  className={styles.textButtonA} href="#">Read More <i className="fa fa-arrow-right"></i></a>
                  </div>
                </div>
              </div>
            </div>
            <div className={`${styles.cardCustom} card`}>
              <div className="card-body">
              <div className={`${styles.serviceItem} third-service`}>
              <div className={styles.icon}></div>
                  {/* <Image src="/service-icon-01.png"  width={57} height={57}className={`${styles.cardImg}`} alt="..." /> */}
                  <h4>Multi Workflow Idea</h4>
                  <p>If this template is beneficial for your work, please support us <a rel="nofollow"
                      href="https://paypal.me/templatemo" target="_blank">a little via PayPal</a>. Thank you.</p>
                  <div className={styles.textButton}>
                    <a  className={styles.textButtonA} href="#">Read More <i className="fa fa-arrow-right"></i></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="carousel-item">
          <div className="card-group">
          <div className={`${styles.cardCustom} card`}>
              <div className="card-body">
              <div className={`${styles.serviceItem} fourth-service`}>
              <div className={styles.icon}></div>


                  {/* <Image src="/service-icon-01.png"  width={57} height={57}className={`${styles.cardImg}`} alt="..." /> */}
                  <h4>24/7 Help & Support</h4>
                  <p>Lorem ipsum dolor consectetur adipiscing elit sedder williamsburg photo booth quinoa and fashion axe.</p>
                  <div className={styles.textButton}>
                    <a  className={styles.textButtonA} href="#">Read More <i className="fa fa-arrow-right"></i></a>
                  </div>
                </div>
              </div>
            </div>
            <div className={`${styles.cardCustom} card`}>
              <div className="card-body">
              <div className={`${styles.serviceItem} fourth-service`}>
              <div className={styles.icon}></div>
                
                  {/* <Image src="/service-icon-01.png"  width={57} height={57}className={`${styles.cardImg}`} alt="..." /> */}
                  <h4>24/7 Help & Support</h4>
                  <p>Lorem ipsum dolor consectetur adipiscing elit sedder williamsburg photo booth quinoa and fashion axe.</p>
                  <div className={styles.textButton}>
                    <a  className={styles.textButtonA} href="#">Read More <i className="fa fa-arrow-right"></i></a>
                  </div>
                </div>
              </div>
            </div>
            <div className={`${styles.cardCustom} card`}>
              <div className="card-body">
              <div className={`${styles.serviceItem} fourth-service`}>
              <div className={styles.icon}></div>

                  {/* <Image src="/service-icon-01.png"  width={57} height={57}className={`${styles.cardImg}`} alt="..." /> */}
                  <h4>24/7 Help & Support</h4>
                  <p>Lorem ipsum dolor consectetur adipiscing elit sedder williamsburg photo booth quinoa and fashion axe.</p>
                  <div className={styles.textButton}>
                    <a  className={styles.textButtonA} href="#">Read More <i className="fa fa-arrow-right"></i></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="carousel-item">
          <div className="card-group">
          <div className={`${styles.cardCustom} card`}>
              <div className="card-body">
              <div className={`${styles.serviceItem} fourth-service`}>
              <div className={styles.icon}></div>

                  {/* <Image src="/service-icon-01.png"  width={57} height={57}className={`${styles.cardImg}`} alt="..." /> */}
                  <h4>24/7 Help & Support</h4>
                  <p>Lorem ipsum dolor consectetur adipiscing elit sedder williamsburg photo booth quinoa and fashion axe.</p>
                  <div className={styles.textButton}>
                    <a  className={styles.textButtonA} href="#">Read More <i className="fa fa-arrow-right"></i></a>
                  </div>
                </div>
              </div>
            </div>
            <div className={`${styles.cardCustom} card`}>
              <div className="card-body">
              <div className={`${styles.serviceItem} fourth-service`}>
              <div className={styles.icon}></div>

                  {/* <Image src="/service-icon-01.png" width={57} height={57}className={`${styles.cardImg}`} alt="..." /> */}
                  <h4>24/7 Help & Support</h4>
                  <p>Lorem ipsum dolor consectetur adipiscing elit sedder williamsburg photo booth quinoa and fashion axe.</p>
                  <div className={styles.textButton}>
                    <a  className={styles.textButtonA} href="#">Read More <i className="fa fa-arrow-right"></i></a>
                  </div>
                </div>
              </div>
            </div>
            <div className={`${styles.cardCustom} card`}>
              <div className="card-body">
              <div className={`${styles.serviceItem} fourth-service`}>
              <div className={styles.icon}></div>

                  {/* <Image src="/service-icon-01.png" width={50} height={57}className={`${styles.cardImg}`} alt="..." /> */}
                  <h4>24/7 Help & Support</h4>
                  <p>Lorem ipsum dolor consectetur adipiscing elit sedder williamsburg photo booth quinoa and fashion axe.</p>
                  <div className={styles.textButton}>
                    <a  className={styles.textButtonA} href="#">Read More <i className="fa fa-arrow-right"></i></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        {/* Và các carousel-item khác ở đây */}
      </div>
      <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
};

export default Card;
