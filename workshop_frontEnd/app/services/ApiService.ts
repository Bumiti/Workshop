import dotenv from 'dotenv';
import axios, { AxiosInstance } from 'axios';
import UserInfoResponse from '@/types/UserInfoResponse';
import CourseData from '../teacher/ui-components/add/courseData';

dotenv.config();

class ApiService {
    private baseUrl: string;
    private customAxios: AxiosInstance;
    constructor(private session: any) {
        this.baseUrl = 'http://localhost:8089/';
        this.customAxios = axios.create({
            baseURL: this.baseUrl,
            timeout: 500000,
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${session?.user.accessToken || ''}`,
            },
        });
    }

    async listCoursePublic() {
        try {
            const response = await this.customAxios.get('/web/course/list');
            return response.data;
        } catch (error) {
            throw error;
        }
    }

    async listCoursesFromApi() {
        try {
            const response = await this.customAxios.get('/seller/course/list');
            return response.data;
        } catch (error) {
            throw error;
        }
    }
    async createCourse(courseData: CourseData) { 
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.post('/seller/course/add', JSON.stringify(courseData));
                return response.data;
            }
            return [];
        } catch (error) {
            throw error;
        }
    }
    
    async listRequestAdmin() {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.get('/admin/request/list');
                return response.data;
            }
            return [];
        } catch (error) {
            throw error;
        }
    }
    async listCourseAdmin() {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.get('/admin/course/list');
                return response.data;
            }
            return [];
        } catch (error) {
            throw error;
        }
    }
    async listAccountAdmin() {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.get('/admin/user/listUser');
                return response.data ;
            }
            return [];
        } catch (error) {
            throw error;
        }
    }
    async changeStatusAccount(id: any) {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.post(`/admin/user/changeStatus?id=${id}`);
                return response.data;
            }
            return null;
        } catch (error) {
            throw error;
        }
    }
}

export default ApiService;
