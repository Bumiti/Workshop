import dotenv from 'dotenv';
import axios, { AxiosInstance } from 'axios';
import CourseData from '../teacher/ui-components/add/courseData';

dotenv.config();

class ApiService {
    private baseUrl: string;
    private customAxios: AxiosInstance;
    customAxiosWithoutAuthorization: AxiosInstance;
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
        // Tạo customAxios mà không chứa 'Authorization'
        this.customAxiosWithoutAuthorization = axios.create({
            baseURL: this.baseUrl,
            timeout: 500000,
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                // Không bao gồm 'Authorization'
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

    async getUserDetails() {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.get('/user/detail', {
                    headers: {
                        Authorization: `Bearer ${this.session?.user.accessToken}`,
                    },
                });
                return response.data;
            }
            return null;
        } catch (error) {
            throw error;
        }
    }

    async editUserProfile(userData: { full_name: string; user_name: string; email: string; phoneNumber: string; image_url: string; userAddresses: { id: number; state: string; city: string; address: string; postalCode: number; }[]; }) {
        try {
            const response = await this.customAxiosWithoutAuthorization.put('/auth/user/edit', JSON.stringify(userData));
            return response.data;
        } catch (error) {
            throw error;
        }
    }
    async checkUserDiscount(code: any) {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.get(`/user/checkDiscount?code=${code}`);
                return response.data;
            }
            return [];
        } catch (error) {
            throw error;
        }
    }
    async changePassword(oldPassword: string, newPassword: string) {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.put(
                    '/user/changePassword',
                    JSON.stringify({ oldPassword, newPassword }),
                    {
                        headers: {
                            Authorization: `Bearer ${this.session?.user.accessToken}`,
                            'Content-Type': 'application/json',
                        },
                    }
                );
                return response;
            }
            return null;
        } catch (error) {
            throw error;
        }
    }
    //-------------------------------------------------User API-------------------------------------------------//
    //-------------------------------------------------Admin API-------------------------------------------------//
    async getUserbyIdAdmin(id: any) {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.get(`/admin/user/findById?id=${id}`);
                return response.data;
            }
            return [];
        } catch (error) {
            throw error;
        }
    }
    async dashboard() {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.get('admin/DashBoard');
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
                return response.data;
            }
            return [];
        } catch (error) {
            throw error;
        }
    }
    async changeStatusAccount(id: number) {
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

    async updateAdminDetails(id: number, newData: any): Promise<any> {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.put(`/admin/edit?id=${id}`, newData);
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
    async checkUserInCourse(user_email: string, courseId: number) {
        try {
            const response = await this.customAxios.get(`/web/course/checkedUser?user_email=${user_email}&course_id=${courseId}`);
            return response.data;
        } catch (error) {
            throw error;
        }
    }
    //-------------------------------------------------Web API-------------------------------------------------//


    //-------------------------------------------------Teacher API-------------------------------------------------//

    async changePasswordTeacher(oldPassword: string, newPassword: string) {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.put(
                    '/teacher/changePassword',
                    JSON.stringify({ oldPassword, newPassword }),
                    {
                        headers: {
                            Authorization: `Bearer ${this.session?.user.accessToken}`,
                            'Content-Type': 'application/json',
                        },
                    }
                );
                return response;
            }
            return null;
        } catch (error) {
            throw error;
        }
    }
    async withdraw(amount: number, type: string, paymentName: string) {
        try {
            if (this.session?.user.accessToken) {
                const response = await this.customAxios.post('/seller/deposit', {
                    amount: amount,
                    type: type,
                    paymentName: paymentName,
                });
                return response.data;
            }
            return [];
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

    //-------------------------------------------------Teacher API-------------------------------------------------//

}

export default ApiService;
