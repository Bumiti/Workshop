'use client'
import React, { useEffect, useState } from "react";
import { Container, Form, Button, Row, Col, ButtonToolbar } from "react-bootstrap";
import PayPalCheckOutButton from "./component/PaypalButton";
import { PayPalScriptProvider } from "@paypal/react-paypal-js"
import { useSession } from "next-auth/react";
import { useRouter } from 'next/navigation';
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
    const router = useRouter();
    const { data: session } = useSession();
    const apiService = new ApiService(session);
    const email = session?.user.email;
    const [course, setCourse] = useState<CourseDetail>();
    const [paymentMethod, setPaymentMethod] = useState("#");
    const [userData, setUserData] = useState({
        full_name: '',
        user_name: '',
        email: '',
        phoneNumber: '',
        image_url: '',
        userAddresses: [
            {
                id: 0,
                state: '',
                city: '',
                address: '',
                postalCode: 0,
            },
        ],
    });

    useEffect(() => {
        if (session) {
            apiService.getUserDetails().then((data) => {
                if (data && data.data) {
                    setUserData(data.data);
                }
            }).catch((error) => {
                console.error('Error fetching user data:', error);
            });
        }
        const fetchData = async () => {
            if (id !== undefined) {
                try {
                    const respone = await apiService.CoursePublicDetail(params.id);
                    if (respone.data)
                        console.log("course in payment", respone.data);
                    {
                        setCourse(respone.data);

                    }

                } catch (error) {
                    console.error("Error checking user in course:", error);
                }

            }
        }
        fetchData();
    }, [session]);

    const handlePaymentChange = (e: { target: { value: React.SetStateAction<string>; }; }) => {
        setPaymentMethod(e.target.value);
    };
    const handlePayNow = async () => {
        if (paymentMethod === "wallet") {
            try {
                const data = {
                    type: "string",
                    status: "Amount",
                    item_register_id: course.id,
                    locationId: 0,
                    amount: course.price,
                    discountAmount: 0,
                    discountCode: "string",
                    paymentName: "Amount",
                    paymentStatus: "string",
                          
                };
                const buyCourseResponse = await apiService.buyCourseWithStudent(data);
                console.log(buyCourseResponse)
             if(buyCourseResponse.status === "Success"){
                router.back();
                router.refresh();
             }else{
                router.refresh();
             }
                  
            } catch (error) {
                console.error("Error buying course with student:", error);
            }
            console.log("Paying with wallet");
        } else if (paymentMethod === "paypal") {
            // Logic for PayPal payment
            console.log("Paying with PayPal");
            // You can pass product information to the PayPal component here
        }
    };
    const styles = {
        disabled: {
            pointerEvents: 'none',
            backgroundColor: '#CCCCCC',
             // Màu tối khi vô hiệu hóa
        },
        enabled: {
            pointerEvents: 'auto',
            backgroundColor: '#FFFFFF', // Màu sáng khi có thể tương tác
        },
        enabledHover: {
            backgroundColor: '#efefef', // Màu khi di chuột qua trong trạng thái enabled
        },
    };
    const remainingBalance = userData?.balance - course?.price;
    console.log("remainingBalance", remainingBalance);

    return (
        <PayPalScriptProvider
            options={
                {
                    clientId: process.env.NEXT_PUBLIC_PAYPAL_CLIENT_ID!,
                    currency: "USD"
                }}>
            <Container>
                <Row>
                    <Col sm={6}>
                        <Container>
                            <h2>Payment Details</h2>
                            <p>Product Description: {course?.description}</p>
                            <p>Price: ${course?.price}</p>
                            <img
                                alt="Video Thumbnail"
                                className="object-cover"
                                height="100px" // Đặt chiều cao thành 100px
                                src={course?.courseMediaInfos[0].thumbnailSrc}
                            />
                        </Container>
                    </Col>
                    <Col sm={6}>
                        <Container>
                            <Form>
                                <Form.Group controlId="paymentMethod">
                                    <Form.Label>Select Payment Method:</Form.Label>
                                    <Form.Control
                                        as="select"
                                        value={paymentMethod}
                                        onChange={handlePaymentChange}
                                    >
                                        <option value="#">Choses</option>
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
                            <div style={remainingBalance < 5 ? styles.disabled : styles.enabled}
                                onClick={handlePayNow}
                                onMouseOver={remainingBalance >= 5 ? () => styles.enabledHover : null}
                                onMouseOut={remainingBalance >= 5 ? () => styles.enabled : null}
                            >
                                {paymentMethod === "wallet" && (
                                    <div>
                                        <p>Wallet Balance: ${userData?.balance}</p>
                                        <p>Remaining Balance: ${remainingBalance}</p>
                                    </div>
                                )}
                            </div>
                        </Container>
                    </Col>
                </Row>
            </Container>


        </PayPalScriptProvider>
    );
}
export default PayMent;