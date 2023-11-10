import { Container } from "react-bootstrap";
import dynamic from 'next/dynamic';
import Link from "next/link";
import styles from '../../courseDemo/courseDemo.module.css';

const ReactPlayer = dynamic(() => import('react-player'), { ssr: false });
export default function SideLeft(){
    return(
        <Container className="p-2 ">
            <div className={styles.videothum}>
                <ReactPlayer
                url='https://youtu.be/4zlqCu24wr4'
                controls={true}
                playing={true}
                muted={true}
                width={'100%'}
                height={333}
                   
            />
        </div>
        <div className="text-center">
            <h1>free</h1>
            <Link href={'#'}>
                Đăng kí nhanh
            </Link>
            <h4>Tổng Cộng có 11 bài</h4>
            <h4>Tổng Thời Lượng</h4>
        </div>
    </Container>
    )
}