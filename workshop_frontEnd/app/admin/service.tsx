import ApiService from '../services/ApiService';
import styles from './login-form.module.css';
import { Container, Row, Col,Button  } from 'react-bootstrap';
import { useEffect,useState  } from 'react';
const admin = () => {
    const apiService = new ApiService();
    const [buttonClicked, setButtonClicked] = useState(false);

    apiService.listAccountAdmin();
    const handleButtonClick = () => {
        apiService.changeStatusAccount(25);
        setButtonClicked(true); // Đánh dấu rằng nút đã được click
    };
    return (
        <Container>
            <div className={styles.login_box + ' p-3'}>
                <h1>Admin page</h1>
                <Button onClick={handleButtonClick}>Click me to change Status Account</Button> {/* Nút gọi hàm */}
                {buttonClicked && <p>Button was clicked</p>} {/* Hiển thị khi nút đã được click */}
            </div>
        </Container>
    )
}
export default admin;