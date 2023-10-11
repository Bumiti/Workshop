'use client'
import { Formik, Field, Form, FormikHelpers } from 'formik';
import styles from './login-form.module.css';
import * as Yup from 'yup';
import { headers } from 'next/headers'

type Values = {
    email: string;
    password: string;
};
const LoginForm = () => {

    const handleSubmit = async (values: Values, { setSubmitting }: FormikHelpers<Values>) => {
        const requestHeaders: HeadersInit = new Headers();
        requestHeaders.set('Content-Type', 'application/json');
        try {
            console.log(values);
            // Gọi API đăng nhập ở đây, thay thế URL_API_LOGIN bằng URL thực tế
            const response = await fetch('http://localhost:8089/auth',
                {
                    method: 'POST',
                    headers: requestHeaders,
                    body: JSON.stringify(values), // Gửi dữ liệu đăng nhập
                });

            if (response.ok) {
                console.log(response);
                // Trích xuất dữ liệu JSON từ response
                const data = await response.json();
                if (data.data.token) {
                    // Lưu token vào Local Storage
                    localStorage.setItem('token', data.data.token);
                }
                console.log("data",data);
                console.log("data token",data.data.token);
                alert('Đăng nhập thành công!');
            } else {
                // Xử lý khi đăng nhập thất bại, ví dụ hiển thị thông báo lỗi
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
                </Form>
            </Formik>
        </div>
    )
}
export default LoginForm;