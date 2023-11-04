import dotenv from 'dotenv';
import axios, { AxiosInstance } from 'axios';
import { useSession } from 'next-auth/react';

dotenv.config();

class ApiService {
    private baseUrls: string;
    private customAxios: AxiosInstance;
    private tokenStr: string;
    constructor() {
        const { data: session } = useSession();
        this.tokenStr = session?.user.accessToken || '',
            this.baseUrls = 'http://localhost:8089/',
            this.customAxios = axios.create({
                baseURL: this.baseUrls,
                timeout: 5000,
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Content-type": "Application/json",
                    "Authorization": `Bearer ${this.tokenStr}`,
                },
            });
        }
    //HomePage API
    async listCoursePublic() {
        try {
            const response = await this.customAxios.get('/web/course/list');
            return response.data;
        } catch (error) {
            throw error;
        }
    }
    //Admin API
    async listRequestAdmin() {
        try {
            if (this.tokenStr != null && this.tokenStr.length > 0) {
                const response = await this.customAxios.get('/admin/request/list');
                return response.data;
            }
        } catch (error) {
            throw error;
        }
    }
    async listCourseAdmin(){
        try {
            if (this.tokenStr != null && this.tokenStr.length > 0) {
                const response = await this.customAxios.get('/admin/course/list');
                return response.data;
            }
        } catch (error) {
            throw error;
        }
    }
    async listAccountAdmin(){
        try {
            if (this.tokenStr != null && this.tokenStr.length > 0) {
                const response = await this.customAxios.get('/admin/user/listUser');
                console.log(response.data.data);
                return response.data;
            }
        } catch (error) {
            throw error;
        }
    }
    async changeStatusAccount(id: number){
        try {
            if (this.tokenStr != null && this.tokenStr.length > 0) {
                console.log(" this.tokenStr :", this.tokenStr)
                const response = await this.customAxios.post(`/admin/user/changeStatus?id=${id}`);
                console.log(response.data);
                return response.data;
            }
        } catch (error) {
            throw error;
        }
    }
}

export default ApiService;
