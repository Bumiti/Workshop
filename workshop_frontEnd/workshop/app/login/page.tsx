'use client'
import { Formik, Field, Form, FormikHelpers } from 'formik';
import styles from './login-form.module.css';
import * as Yup from 'yup';
import { signIn, useSession } from "next-auth/react"
import { Container, Row, Col } from 'react-bootstrap'
import { AiFillGithub } from "react-icons/ai"
import { BsFacebook } from "react-icons/bs"
import { BiLogoGmail } from "react-icons/bi"
import { BsDiscord } from 'react-icons/bs';
import { BsInstagram } from 'react-icons/bs';
import { BsReddit } from 'react-icons/bs';
import { BsLinkedin } from 'react-icons/bs';
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
            const response = await signIn("credentials",{
                email:values.email,
                password:values.password,
                redirect:true,
                callbackUrl:"/"
            })
            console.log("response",response)
            
        } catch (error) {
            console.error('Lỗi khi gọi API đăng nhập:', error);
        } finally {
            setSubmitting(false);
        }
    };
    return (
        <Container className={styles.container_css}>
            <Row className={' p-5 my-5'}>
                <Col col='10' md='6'>
                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg" className="img-fluid" alt="Phone image" />
                </Col>
                <Col col='4' md='6' >
                    <div className={' p-3'}>
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
                                <button className="mb-4 w-100 btn btn-primary" type="submit">Login</button>
                                <h2 className="lead fw-normal mb-0 me-3 text-center">Sign in with</h2>
                                <div className={styles.div_media}>
                                    <button onClick={() => signIn("facebook",{callbackUrl:"/"})} className='btn btn-lg me-2'>
                                        <BsFacebook fab="true" icon='facebook-f' />
                                    </button>
                                    <button onClick={() => signIn("github",{callbackUrl:"/"})} className='btn  btn-lg me-2'>
                                        <AiFillGithub fab="true" icon='github' />
                                    </button>                              
                                    <button onClick={() => signIn("google",{callbackUrl:"/"})} className='btn  btn-lg me-2'>
                                        <BiLogoGmail fab="true" icon='mail' />
                                    </button>
                                    <button onClick={() => signIn("discord",{callbackUrl:"/"})} className='btn  btn-lg me-2'>
                                        <BsDiscord fab="true" icon='discord' />
                                    </button>
                                    {/* <button onClick={() => signIn("instagram",{callbackUrl:"/"})} className='btn  btn-lg me-2'>
                                        <BsInstagram fab="true" icon='instagram' />
                                    </button>
                                    <button onClick={() => signIn("reddit",{callbackUrl:"/"})} className='btn  btn-lg me-2'>
                                        <BsReddit fab="true" icon='reddit' />
                                    </button>
                                    <button onClick={() => signIn("linkedin",{callbackUrl:"/"})} className='btn  btn-lg me-2'>
                                        <BsLinkedin fab="true" icon='linkedin' />
                                    </button> */}

                                </div>
                            </Form>
                        </Formik>
                    </div>
                </Col>
            </Row>
        </Container>
    )
}
export default LoginForm;