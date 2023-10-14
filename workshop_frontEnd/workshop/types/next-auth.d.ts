import NextAuth from "next-auth/next";

declare module "next-auth"{
    interface Session{
        user:{
            id:number;
            userName:string;
            name:string;
            email:string;
            roles:string;
            accessToken:string;
            refreshToken:string;
            image:string;
        }
    }
}
declare module "next-auth/jwt" {
    interface JWT extends DefaultJWT {
        role: string,
    }
}