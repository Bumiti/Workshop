'use client'
import React, { useState, ChangeEvent ,useEffect} from "react"
import { FirebaseDb } from "./Config"
import { getDownloadURL,  ref, uploadBytes } from "firebase/storage"
import { v4  } from 'uuid';
import { Container } from "react-bootstrap";

export default function DemoUpload() {
    const [img, setImg] = useState('');
    const [imgUrl, setImgUrl ]= useState('');

    const handleImageUpload = (event) => {
        const files = event.target.files;
    if (files) {
        const selectedImage = files[0];
        if (selectedImage) {
            const fileExtension = selectedImage.name.split('.').pop();
            console.log("File Extension:", fileExtension);
            setImg({ file: selectedImage, extension: fileExtension });
        } else {
            console.error("Không có tệp hình ảnh được chọn.");
        }
    } else {
        console.error("Không có tệp hình ảnh được chọn.");
    }
    }
    const handleClick = () => {
        if (img && img.file) {
            const { file, extension } = img;
            const fileName = `${v4()}.${extension}`;
            const imgRef = ref(FirebaseDb,`/user/${v4()}`); 
            uploadBytes(imgRef, file).then((value) => {
                console.log("Tải lên thành công :", value);
                getDownloadURL(value.ref).then(url => {
                    setImgUrl(data => [...data, { url, fileName }]);
                });
            }).catch((error) => {
                console.error("Lỗi tải lên:", error);
            });
        }
    };
    console.log("Url :",imgUrl);
  
    return (
        <Container className="">
            <input type="file" onChange={handleImageUpload}/>
            <button onClick={handleClick}>Upload</button>
        </Container>
    )
}