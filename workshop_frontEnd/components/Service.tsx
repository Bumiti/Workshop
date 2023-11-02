import Image from 'next/image';
import Link from "next/link";
import styles from '../CSS/home.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';

function Services() {
  return (
    <div id="services" className="services section">
      <div className="container">
        <div className="row d-flex justify-content-center align-items-center">
          <div className="col-lg-8 offset-lg-2">
            <Image src="/services-right-dec.png"className={styles.customimage} alt="Custom Image" width={1056} height={226} />

            <div className={`${styles.sectionHeadingService} wow fadeInDown`} data-wow-duration="1s" data-wow-delay="0.5s">
              <h4 className="text-center custom-spacing">
                Amazing <em className={styles.emm}>Services &amp; Features</em> for you
              </h4>
              <div className="d-flex justify-content-center">
                <Image src="/heading-line-dec.png" alt="Ảnh" width={45} height={2} />
              </div>
              <p className={styles.pText}>
                If you need the greatest collection of HTML templates for your business, please visit{' '}
                <a rel="nofollow" href="https://www.toocss.com/" target="_blank">
                  TooCSS
                </a>{' '}
                Blog. If you need to have a contact form PHP script, go to{' '}
                <a href="https://templatemo.com/contact" target="_parent">
                  our contact page
                </a>{' '}
                for more information.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Services;
