import dotenv from 'dotenv';
import axios, { AxiosInstance } from 'axios';
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
    async buyCourseWithStudent(data: {
        type: string;
        status: string;
        item_register_id: number;
        locationId: number;
        amount: number;
        discountAmount: number;
        discountCode: string;
        paymentName: string;
        paymentStatus: string;
       
    }) {
        try {
            const response = await this.customAxios.post('/user/byCourse', data);
            return response.data;
        } catch (error) {
            throw error;
        }
    }
    //-------------------------------------------------User API-------------------------------------------------//
    //-------------------------------------------------Admin API-------------------------------------------------//
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

    async getCourseById(courseId: number) {
        try {
          if (this.session?.user.accessToken) {
            const response = await this.customAxios.get(`/seller/course/list/${courseId}`, {
              params: {
                courseId: courseId,
              },
            });
            return response.data;
          }
          return [];
        } catch (error) {
          throw error;
        }
      }
      
    
    async editCourse(courseId: number, courseData: CourseData) { 
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.put(`/seller/course/update/${courseId}`, JSON.stringify(courseData));
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
                const response = await this.customAxios.put(`/admin/user/changeStatus?id=${id}`);
                return response.data;
            }
            return null;
        } catch (error) {
            throw error;
        }
    }
    async changeStatusCourse(id: number) {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.post(`/admin/course/status?id=${id}`);
                return response.data;
            }
            return null;
        } catch (error) {
            throw error;
        }
    }
    //-------------------------------------------------Admin API-------------------------------------------------//
    //-------------------------------------------------Web API-------------------------------------------------//
    async listCoursePublic() {
        try {
            const response = await this.customAxios.get('/web/course/list');
            return response.data;
        } catch (error) {
            throw error;
        }
    }
    async CoursePublicDetail(id: number) {
        try {
            const response = await this.customAxios.get(`/web/course/detail?id=${id}`);
            return response.data;
        } catch (error) {
            throw error;
        }
    }
    async checkUserInCourse(user_email: number,courseId:number) {
        try {
            const response = await this.customAxios.get(`/web/course/checkedUser?user_email=${user_email}&course_id=${courseId}`);
            return response.data;
        } catch (error) {
            throw error;
        }
    }
    //-------------------------------------------------Web API-------------------------------------------------//
}

export default ApiService;
