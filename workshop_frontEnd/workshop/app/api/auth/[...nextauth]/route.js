import GoogleProvider from "next-auth/providers/google"
import FacebookProvider from "next-auth/providers/facebook"
import GitHubProvider from "next-auth/providers/github"
import CredentialsProvider from "next-auth/providers/credentials"
import DiscordProvider from "next-auth/providers/discord"
import Instagram from "next-auth/providers/instagram"
import Reddit from "next-auth/providers/reddit"
import LinkedIn from "next-auth/providers/linkedin"
import NextAuth from "next-auth/next"


const handler = NextAuth({
 
    providers: [
        CredentialsProvider({
            name: 'Credentials',
            credentials: {
              email: { label: "email", type: "text", placeholder: "jsmith" },
              password: { label: "Password", type: "password" }
            },
            async authorize(credentials) 
            {
                const res = await fetch("http://localhost:8089/auth/loginWeb", {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    email:credentials?.email,
                    password:credentials?.password,
                }),
              });
              const user = await res.json()
              
              if (user)
              {
                console.log("user nè :",user.data.user);
                user.data.user.sub = 'credentials',
                user.data.user.id= 'credentials'
                return user.data.user;
              }
              return null
            }
          }),
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
        }),
        DiscordProvider({
            clientId: process.env.DISCORD_ID,
            clientSecret: process.env.DISCORD_SECRET,
        }),
    ],
    callbacks: {
        async jwt({token,user,session,trigger}){
            if(trigger === "update"&& session ?.user){
                token.user = session.user;
            }
            return{ ...token, ...user}
        },
        async session({ session,token}) {
            session.user =token;
            if(session.user.id !=='credentials')
            {
                const fetchData = async (e) => {
                    const response = await fetch('http://localhost:8089/auth/loginOAuthentication', {
                        method: 'POST',
                        headers: new Headers({
                            'Content-Type': 'application/json',
                        }),
                        body: JSON.stringify({ email: token.email }),
                    });
                    if (response.ok) {
                        const data = await response.json();
                        console.log("User Data :", data.data.user);
                        session.user.roles = data.data.user.roles
                        session.user.accessToken = data.data.user.accessToken
                        session.user.refreshToken = data.data.user.refreshToken
                        session.user.gender == data.data.user.gender
                        session.user.phoneNumber == data.data.user.phoneNumber
                        session.user.userAddresses == data.data.user.userAddresses
                    }
                };
                await fetchData(); 
                return session;
            }else{
                return session;
            }
        },
    },
    pages:{
        signIn:"/login",
        error:"/login",
    },
    secret: process.env.NEXTAUTH_SECRET
})
export { handler as GET, handler as POST };