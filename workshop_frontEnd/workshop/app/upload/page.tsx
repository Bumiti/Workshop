'use client'

import styles from './login-form.module.css';

const Upload = () => {
    const token = localStorage.getItem('token');
    if (token) {
        // Token tồn tại trong Local Storage
        console.log('Token tồn tại:', token);
      
        // Tại đây, bạn có thể thực hiện các hành động cần thiết với token
      } else {
        // Token không tồn tại trong Local Storage
        console.log('Token không tồn tại');
      }
    return (
        <div className={styles.login_box + ' p-3'}>
            <h1>upload</h1>
        </div>
    )
}
export default Upload;