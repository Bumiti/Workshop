'use client'
import React, { useEffect, useState } from "react";
import { Container, Form, Button } from "react-bootstrap";
import PayPalCheckOutButton from "./component/PaypalButton";
import { PayPalScriptProvider } from "@paypal/react-paypal-js"
import { useSession } from "next-auth/react";
import ApiService from "@/app/services/ApiService";

interface CourseDetail {
    id: number;
    name: string;
    description: string;
    link: string;
    price: number;
  }
const PayMent = ({ params }: { params: { id: any } }) => {
    const id = params;
    console.log("id", id);
    const { data: session } = useSession();
    const apiService = new ApiService(session);
    const email = session?.user.email;
    const [course, setCourse] = useState<CourseDetail>();
    const [paymentMethod, setPaymentMethod] = useState("wallet");
    useEffect(() => {
        const fetchData = async () => {
            if (id !== undefined) {
               
                try {
                    const respone = await apiService.CoursePublicDetail(params.id);
                    if (respone.data) 
                        console.log("course in payment",respone.data);    
                    {                
                        setCourse(respone.data);
                                       
                    }
                 
                } catch (error) {
                    console.error("Error checking user in course:", error);
                }

            }
        }
        fetchData();
    }, []);

const handlePaymentChange = (e: { target: { value: React.SetStateAction<string>; }; }) => {
    setPaymentMethod(e.target.value);
};
const handlePayNow = () => {
    if (paymentMethod === "wallet") {
        // Logic for wallet payment
        console.log("Paying with wallet");
    } else if (paymentMethod === "paypal") {
        // Logic for PayPal payment
        console.log("Paying with PayPal");
        // You can pass product information to the PayPal component here
    }
};

return (
    <PayPalScriptProvider
        options={
            {
                clientId: process.env.NEXT_PUBLIC_PAYPAL_CLIENT_ID!,
                currency: "USD"
            }}>
        <Container>
            <h2>Payment Details</h2>
            <p>Product Description: {course?.description}</p>
            <p>Price: ${course?.price}</p>

            <Form>
                <Form.Group controlId="paymentMethod">
                    <Form.Label>Select Payment Method:</Form.Label>
                    <Form.Control
                        as="select"
                        value={paymentMethod}
                        onChange={handlePaymentChange}
                    >
                        <option value="wallet">Wallet</option>
                        <option value="paypal">PayPal</option>
                    </Form.Control>
                </Form.Group>
            </Form>

            <Button variant="primary" onClick={handlePayNow}>
                Pay Now
            </Button>

            {paymentMethod === "paypal" && (
                <PayPalCheckOutButton Courses={course} />
            )}
        </Container>

    </PayPalScriptProvider>
);
}
export default PayMent;