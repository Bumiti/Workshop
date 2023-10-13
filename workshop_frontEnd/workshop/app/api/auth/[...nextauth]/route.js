import GoogleProvider from "next-auth/providers/google"
import FacebookProvider from "next-auth/providers/facebook"
import GitHubProvider from "next-auth/providers/github"
import CredentialsProvider from "next-auth/providers/credentials"
import NextAuth from "next-auth/next"
import jwt_decode from "jwt-decode"
const handler = NextAuth({
    providers: [
        CredentialsProvider({
            name: 'Credentials',
            credentials: {
              email: { label: "email", type: "text", placeholder: "jsmith" },
              password: { label: "Password", type: "password" }
            },
            async authorize(credentials, req) 
            {
                const res = await fetch("http://localhost:8089/auth/loginWeb", {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    email:credentials?.email,
                    password:credentials?.password,
                }),
              });
              const token = await res.json()
              if (res.ok)
              {

              const dataDecode = jwt_decode(token.data.token);
              console.log("dataDecode ne2 ",dataDecode)
                return { 
                    name: dataDecode.username, 
                    email: dataDecode.sub,
                    id:'credentials',
                    image:dataDecode.image,
                };
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
        })
    ],
    callbacks: {
        async session({ session,token,user}) {
            if(token.sub !=="credentials"){
                const fetchData = async (e) => {
                    const response = await fetch('http://localhost:8089/auth/login0Authen', {
                        method: 'POST',
                        headers: new Headers({
                            'Content-Type': 'application/json',
                        }),
                        body: JSON.stringify({ email: session.user.email }),
                    });
                    if (response.ok) {
                        const data = await response.json();
                        const dataDecode = jwt_decode(data.data.token);
             
                        console.log('token : ',data.data.token);
                        console.log('dataDecode trong session: ',dataDecode);
                        session.user.role = dataDecode.roles;
                        session.user.email = dataDecode.sub;
                        session.user.name = dataDecode.username;
                        console.log("session trong credential",session)
                    }
                };
                await fetchData();
                return session;
            }else{
                session.user.email = token.email;
                session.user.name = token.name;
                session.user.image=token.picture;
                session.user.roles=user;
                console.log("token ngoai credential",token)
                console.log("session ngoai credential",session)
                return session;
            }
        },
    },
    pages:{
        signIn:"/login"
    }
})
export { handler as GET, handler as POST };