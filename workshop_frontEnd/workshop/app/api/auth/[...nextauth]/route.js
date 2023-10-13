import GoogleProvider from "next-auth/providers/google"
import FacebookProvider from "next-auth/providers/facebook"
import GitHubProvider from "next-auth/providers/github"
import NextAuth from "next-auth/next"
const handler = NextAuth({
    providers: [
        FacebookProvider({
            clientId: process.env.FACEBOOK_ID,
            clientSecret: process.env.FACEBOOK_SECRET,
        }),
        GoogleProvider({
            clientId: process.env.GOOGLE_ID,
            clientSecret: process.env.GOOGLE_SECRET,
        }),
        GitHubProvider({
            clientId: process.env.GIT_ID,
            clientSecret: process.env.GIT_SECRET,
        })
    ],
    callbacks: {
        async session({ session}) {
            const fetchData = async (e) => {
                const response = await fetch('http://localhost:8089/auth/login0Authen', {
                    method: 'POST',
                    headers: new Headers({
                        'Content-Type': 'application/json',
                    }),
                    body: JSON.stringify({ email: session.user.email }),
                });
                // console.log('token : ',token);
             
                if (response.ok) {
                    const data = await response.json();
                }
            };

            fetchData();
            return session;
        },
        async jwt({token,user}){
            if(user) token.role = user.role
            return token;
        }
    },
})
export { handler as GET, handler as POST };