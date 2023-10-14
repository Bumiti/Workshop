
import { withAuth, NextRequestWithAuth } from "next-auth/middleware"
import { NextResponse } from "next/server"
export default withAuth(
    function middleware(request: NextRequestWithAuth) {
  
        if (request.nextUrl.pathname.startsWith("/teacher")) {
            if (request.nextauth.token?.roles && request.nextauth.token.roles.includes("SELLER")) {

            } else {
                return NextResponse.rewrite(new URL("/login", request.url));
            }
        }
        if (request.nextUrl.pathname.startsWith("/user/")) 
        {
            if (request.nextauth.token?.roles && (request.nextauth.token.roles.includes("USER") || request.nextauth.token.roles.includes("SELLER"))) {

            } else {
                return NextResponse.rewrite(new URL("/login", request.url));
            }
        }
    },
    {
        callbacks: {
            authorized: ({ token }) => !!token
        },
        secret: process.env.NEXTAUTH_SECRET
    }
)

export const config = { matcher: ["/teacher", "/user", "/dashboard"] }