'use client'
import React, { useState, ChangeEvent ,useEffect} from "react"
import { FirebaseDb } from "./Config"
import { getDownloadURL,  ref, uploadBytes } from "firebase/storage"
import { v4  } from 'uuid';

export default function DemoUpload() {
    const [img, setImg] = useState(null);
    const [imgUrl, setImgUrl ]= useState('')
    const handleImageUpload = (event) => {
        const files = event.target.files;
        if (files) 
        { 
            const selectedImage = files[0];
            setImg(selectedImage);
        } else {
            console.error("Không có tệp hình ảnh được chọn.");
        }
    }
    const handleClick = () => {
        if (img) {
            const imgRef = ref(FirebaseDb,`/user/${v4()}`); // Định vị đến nơi bạn muốn lưu trữ hình ảnh
            uploadBytes(imgRef, "user").then((value) => {
                console.log("Tải lên thành công :",value);
                getDownloadURL(value).then(url=>{
                    setImgUrl(data=>[...data,url])
                })
            }).catch((error) => {
      
                console.error("Lỗi tải lên:", error);
            });
        }
    };
    console.log("Url :",imgUrl);
  
    return (
        <div>
            <input type="file" onChange={handleImageUpload} />
            <button onClick={handleClick}>Upload</button>
        </div>
    )
}