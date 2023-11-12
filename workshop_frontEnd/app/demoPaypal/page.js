'use client'
import { PayPalScriptProvider, PayPalButtons } from '@paypal/react-paypal-js'
import { useState } from 'react';
export default function demopaypal() {
  const [dynamicData, setDynamicData] = useState({
    amount: '100.00',
    description: 'Demo sản phẩm',
    itemName: 'Demo sản phẩm 1',
    itemDescription: 'Demo sản phẩm 1',
    quantity: '1',
    unitAmount: '50.00',
  });
  const handlePayment = async () => {
    console.log(JSON.stringify({ data: dynamicData }))
    const response = await fetch('/api/checkout', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ data: dynamicData }),
    });
    
    // Process the response as needed
    const order = await response.json();
    console.log("order",order);
    return order.id
  };
  return (

    <PayPalScriptProvider
      options={{
        clientId: process.env.NEXT_PUBLIC_PAYPAL_CLIENT_ID
      }}
    >
      <PayPalButtons
        style={{
          color: "blue",
          layout: "horizontal",
        }}
        createOrder={async () => {
          // const res = await fetch('/api/checkout', {
          //   method: "POST",
          // })
          // const order = await res.json()
          // console.log("order", order)
          // return order.id
          return handlePayment();
        }}
        onApprove={(data, actions) => {
          console.log("data onApprove", data)

        }}
        onCancel={(data) => {
          console.log("Cancel", data);

        }}
        onInit={(data, actions) => {
          console.log("data onInit", data)
        }}
      />
    </PayPalScriptProvider>

  )
}