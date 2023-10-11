'use client'
import { Formik, Field, Form, FormikHelpers } from 'formik';
import styles from './login-form.module.css';
import * as Yup from 'yup';
import { signIn, useSession } from "next-auth/react"
import { useEffect } from 'react';
type Values = {
    email: string;
    password: string;
};

const LoginForm = () => {
    const session = useSession();


    useEffect(() => {
        console.log(session);
        if (session.status === "authenticated")
           
        {
            // console.log("session.data :", session.data)
            console.log("session.data.user :", session.data.user)
            console.log("session.data.user.email :", session.data.user?.email)
            console.log("Xử lý đang kí qua mail và tự động đăng nhập ở đây");
        }
        if (session.status === "loading") {
            console.log("trả thông báo vấn đề");
        }
        if (session.status === "unauthenticated") {
            console.log("trả thông báo không thành công");
        }
    }, [session.status]);


    const handleSubmit = async (values: Values, { setSubmitting }: FormikHelpers<Values>) => {
        const requestHeaders: HeadersInit = new Headers();
        requestHeaders.set('Content-Type', 'application/json');
        try {
            console.log(values);
            const response = await fetch('http://localhost:8089/auth',
                {
                    method: 'POST',
                    headers: requestHeaders,
                    body: JSON.stringify(values),
                });

            if (response.ok) {
                console.log(response);
                const data = await response.json();
                if (data.data.token) {
                    localStorage.setItem('token', data.data.token);
                    const tokenusung = localStorage.getItem('token');
                    console.log("token trong local store",tokenusung)
                }
          
                alert('Đăng nhập thành công!');
            } else {
                alert('Đăng nhập thất bại. Vui lòng kiểm tra thông tin đăng nhập.');
            }
        } catch (error) {
            console.error('Lỗi khi gọi API đăng nhập:', error);
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <div className={styles.login_box + ' p-3'}>
            <h1 className="display-6 mb-3">Login</h1>
            <Formik
                initialValues={{
                    email: '',
                    password: '',
                }}
                validationSchema={Yup.object({
                    email: Yup.string().required('Vui lòng nhập tên đăng nhập'),
                    password: Yup.string().required('Vui lòng nhập mật khẩu'),
                })}
                onSubmit={handleSubmit}
            >
                <Form>
                    <div className="mb-3">
                        <Field className="form-control" id="email" name="email" placeholder="email" aria-describedby="usernameHelp" />
                    </div>

                    <div className="mb-3">
                        <Field className="form-control" id="password" name="password" placeholder="Password" type="password" />
                    </div>

                    <button type="submit" className="btn btn-primary">Login</button>
                    <button onClick={() => signIn("google")} >Login With Google</button>
                    <button onClick={() => signIn("facebook")} className="btn btn-primary">Login With FaceBook</button>
                    <button onClick={() => signIn("github")} className="btn btn-primary">Login With GitHub</button>
                </Form>
            </Formik>
        </div>
    )
}
export default LoginForm;