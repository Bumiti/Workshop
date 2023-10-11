import GoogleProvider from "next-auth/providers/google"
import FacebookProvider from "next-auth/providers/facebook"
import GitHubProvider from "next-auth/providers/github"
import NextAuth from "next-auth/next"

const handler = NextAuth({
    providers:[
        FacebookProvider({
            clientId : process.env.FACEBOOK_ID,
            clientSecret : process.env.FACEBOOK_SECRET,
        }),
        GoogleProvider({
            clientId : process.env.GOOGLE_ID,
            clientSecret : process.env.GOOGLE_SECRET,
        }),
        GitHubProvider({
            clientId : process.env.GIT_ID,
            clientSecret : process.env.GIT_SECRET,
        })
    ],
    callbacks:{
        async session ({session}){
            return session
        },
    }
})
export { handler as GET,handler as POST };