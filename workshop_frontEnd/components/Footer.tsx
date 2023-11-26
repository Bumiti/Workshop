import Image from 'next/image';
import Link from "next/link";
import styles from '../CSS/home.module.css';
import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
function Footer() {
  return (
  <div id="contact">
    <footer id="newsletter" className={styles.footer}>
      <div className="container">
        <div className="row">
          <div className="col-lg-8 offset-lg-2">
            <div className={styles.sectionHeading}>
              <h4>Join our mailing list to receive the news & latest trends</h4>
            </div>
          </div>
          <div className="col-lg-6 offset-lg-3">
            <form  className={styles.search} id="search" action="#" method="GET">
              <div className="row">
                <div className="col-lg-6 col-sm-6">
                  <fieldset>
                    <input type="email" name="address" className="email" placeholder="Email Address..." autoComplete="on" required />
                  </fieldset>
                </div>
                <div className="col-lg-6 col-sm-6">
                  <fieldset>
                    <button type="submit" className="main-button">Send Us Now<i className="fa fa-angle-right"></i></button>
                  </fieldset>
                </div>
              </div>
            </form>
          </div>
        </div>
        <div className="row">
          <div className="col-lg-3">
            <div className={styles.footerWidget}>
              <h4>Contact Us</h4>
              <p>Rio de Janeiro - RJ, 22795-008, Brazil</p>
              <p><Link className={styles.aCustom} href="tel:010-020-0340">010-020-0340</Link></p>
              <p><Link className={styles.aCustom} href="mailto:info@company.co">info@company.co</Link></p>
            </div>
          </div>
          <div className="col-lg-3">
            <div className={styles.footerWidget}>
              <h4>About Us</h4>
              <ul>
                <li><Link className={styles.aCustom} href="#">Home</Link></li>
                <li><Link className={styles.aCustom} href="#">Services</Link></li>
                <li><Link className={styles.aCustom} href="#">About</Link></li>
                <li><Link className={styles.aCustom} href="#">Testimonials</Link></li>
                <li><Link className={styles.aCustom} href="#">Pricing</Link></li>
              </ul>
              <ul>
                <li><Link className={styles.aCustom} href="#">About</Link></li>
                <li><Link className={styles.aCustom} href="#">Testimonials</Link></li>
                <li><Link className={styles.aCustom} href="#">Pricing</Link></li>
              </ul>
            </div>
          </div>
          <div className="col-lg-3">
            <div className={styles.footerWidget}>
              <h4>Useful Links</h4>
              <ul>
                <li><Link className={styles.aCustom} href="#">Free Apps</Link></li>
                <li><Link className={styles.aCustom} href="#">App Engine</Link></li>
                <li><Link className={styles.aCustom} href="#">Programming</Link></li>
                <li><Link className={styles.aCustom} href="#">Development</Link></li>
                <li><Link className={styles.aCustom} href="#">App News</Link></li>
              </ul>
              <ul>
                <li><Link className={styles.aCustom} href="#">App Dev Team</Link></li>
                <li><Link className={styles.aCustom} href="#">Digital Web</Link></li>
                <li><Link className={styles.aCustom} href="#">Normal Apps</Link></li>
              </ul>
            </div>
          </div>
          <div className="col-lg-3">
            <div className={styles.footerWidget}>
              <h4>About Our Company</h4>
              <div className="logo">
                <Image src="/white-logo.png" alt="Company Logo" width={96} height={45} />
              </div>
              <p>
              Explore the latest trends and gain valuable insights at our interactive workshops. Our experienced instructors are dedicated to providing hands-on learning experiences that will enhance your skills. Whether you're a beginner or an advanced learner, our workshops are tailored to meet your needs</p>
            </div>
          </div>
          <div className="col-lg-12">
            <div className={styles.copyrightText}>
              <p>Copyright © 2022 Chain App Dev Company. All Rights Reserved.
                {/* <br />Design: <Link className={styles.aCustom} href="https://templatemo.com/" target="_blank" title="css templates">TemplateMo</Link> */}
              </p>
            </div>
          </div>
        </div>
      </div>
    </footer>
    </div>

  );
}

export default Footer;
