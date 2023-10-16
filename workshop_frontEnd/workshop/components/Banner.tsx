import Image from 'next/image';
import Link from "next/link";
import styles from '../CSS/home.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faApple, faGooglePlay } from '@fortawesome/free-brands-svg-icons';
 
export default function Banner() {
  return (
    <div className={`${styles.bannerImage} w-100 vh-100 d-flex justify-content-center align-items-center`}>
      <div className="content text-center">
        <div className="container">
          <div className="row">
            <div className="col-lg-12">
              <div className="row">
                <div className="col-lg-6 align-self-center">
                  <div>
                    <div className="row">
                      <div className="col-lg-12">
                        <h2 className={`${styles.fontroboto} ${styles.fontsize50} ${styles.lineheight70} ${styles.custombold} text-left`}>
                          Get The Latest App From App Stores
                        </h2>
                        <p>
                          Chain App Dev is an app landing page HTML5 template based on Bootstrap v5.1.3 CSS layout
                          provided by TemplateMo, a great website to download free CSS templates.
                        </p>
                      </div>

                      <div className="col-lg-12">
                        <div className={`${styles.whiteButton} first-button scroll-to-section`}>
                          <Link href="#contact" passHref>
                            Free Quote <FontAwesomeIcon icon={faApple} />

                          </Link>
                          <Link href="#contact" passHref>
                            Free Quote <FontAwesomeIcon icon={faGooglePlay} />

                          </Link>
                        </div>
                        {/* <div className={`${styles.whiteButton} scroll-to-section`}>
                          
                        </div> */}
                      </div>
                    </div>
                  </div>
                </div>
                <div className="col-lg-6">
                  <div
                    className="right-image wow fadeInRight"
                    data-wow-duration="1s"
                    data-wow-delay="0.5s"
                  >
                    <Image src="/slider-dec.png" alt="Slider Image" width={710} height={620} />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
