import NextAuth from "next-auth/next";

declare module "next-auth"{
    interface Session{
        user:{
            id:number;
            userName:string;
            name:string;
            email:string;
            role:string;
            accessToken:string;
            refreshToken:string;
        }
    }
}