'use client'
import Banner from '@/components/Banner';
import Services from '@/components/Service';
import Card from '@/components/CardSlide';
import About from '@/components/About';
import Testimonials from '@/components/Rate';
import PricingCarousel from '@/components/PricingCarousel';
import Footer from '@/components/Footer';
import Navbar from '@/components/Navbar';
import ApiService from './services/ApiService';

export default function Home() {
  const apiService = new ApiService();
   apiService.listCoursePublic();
  return (
    <div>
      <Navbar/>
      <Banner />
      <Services />
      <Card />
      <About />
      <Testimonials />
      <PricingCarousel />
      <Footer />
    </div>
  )
}