'use client'
import { Container } from "react-bootstrap";
import Card from "../ui/dashboard/card/card";
import Chart from "../ui/dashboard/chart/chart";
import styles from "../ui/dashboard/dashboard.module.css";
import Transactions from "../ui/dashboard/transactions/transactions";
import ApiService from '@/app/services/ApiService';
import { useSession } from 'next-auth/react';
import React, { useEffect, useState } from 'react';

// export const cards = [
//   {
//     id: 1,
//     title: "New Student in Mount",
//     number: 10.928,
//     change: 12,
//   },
//   {
//     id: 2,
//     title: "New Teacher in Mount",
//     number: 8.236,
//     change: -2,
//   },
//   {
//     id: 3,
//     title: "Deposit",
//     number: 6.642,
//     change: 18,
//   },
//   {
//     id: 4,
//     title: "Revenue",
//     number: 6.642,
//     change: 18,
//   },
// ];

const Dashboard = () => {
  const [cards, setCard] = useState( [
    {
      id: '',
      title: '',
      subtitle: '',
      total_numbe: '',
      week_number: '',
      change: '',
    },
    {
      id: '',
      title: '',
      subtitle: '',
      total_numbe: '',
      week_number: '',
      change: '',
    },
    {
      id: '',
      title: '',
      subtitle: '',
      total_numbe: '',
      week_number: '',
      change: '',
    },
    {
      id: '',
      title: '',
      subtitle: '',
      total_number: '',
      week_number: '',
      change: '',
    },
  ]);
  const { data: session } = useSession();
 
  const apiService = new ApiService(session);
  useEffect(() => {
    const fetchData = async () => {
      
      if (apiService != null) {
        try {
          const result = await apiService.dashboard();
          console.log(result.data);
          if (result.status === "success") {
            setCard((prevCard) => [
              {
                ...prevCard[0],
                id: '0' ?? prevCards[0].id,
                title: 'Total User' ??  prevCards[0].title,
                subtitle: 'User on Month' ??  prevCards[0].subtitle,
                total_number: result.data.totalAccount ?? prevCard[0].total_number,
                week_number: result.data.newStudentThisMonth + result.data.newTeacherThisMonth ?? prevCard[0].total_number,
                change: '' ?? prevCards[0].change,
              },
              {
                ...prevCard[1],
                id: '1' ?? prevCards[1].id,
                title: 'Total Workshop' ??  prevCards[1].title,
                subtitle: 'Workshop on Month' ??  prevCards[1].subtitle,
                total_number: result.data.totalCourses ?? prevCard[1].total_number,
                week_number: result.data.newCoursesThisMonth  ?? prevCard[1].total_number,
                change: '' ?? prevCards[1].change,
              },
              {
                ...prevCard[2],
                id: '2' ?? prevCards[2].id,
                title: 'Total Workshop Pricing' ??  prevCards[2].title,
                subtitle: 'Pricing on Month' ??  prevCards[2].subtitle,
                total_number: result.data.totalCoursesPricing + "$" ?? prevCard[2].total_number,
                week_number: result.data.coursesPricingThisMonth + "$"  ?? prevCard[2].total_number,
                change: '' ?? prevCards[2].change,
              }
              ,
              {
                ...prevCard[3],
                id: '2' ?? prevCards[3].id,
                title: 'Total Revenue' ??  prevCards[3].title,
                subtitle: 'Revenue on Month' ??  prevCards[3].subtitle,
                total_number: result.data.totalRevenue + "$" ?? prevCard[3].total_number,
                week_number: result.data.revenueThisMonth + "$"  ?? prevCard[3].total_number,
                change: '' ?? prevCards[3].change,
              }
            ]);
          } else {
            console.error('Data is not an object:', result.data);
          }
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }
    };

    if (apiService != null) {
      fetchData();
    }
  }, [session]);

  return (
 <Container>
     <div className={styles.wrapper}>
      <div className={styles.main}>
        <div className={styles.cards}>
        {cards.map((item, index) => (
            <Card item={item} key={index} />
          ))}
        </div>
        <Transactions />
        <Chart />
       
      
      </div>
    </div>
 </Container>
  );
};

export default Dashboard;

