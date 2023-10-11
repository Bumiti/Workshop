// import type { GetServerSidePropsContext, NextApiRequest, NextApiResponse } from "next"
// import type { NextAuthOptions as NextAuthConfig } from "next-auth"
// import { getServerSession } from "next-auth"

// import Apple from "next-auth/providers/apple"
// import Discord from "next-auth/providers/discord"
// import Facebook from "next-auth/providers/facebook"
// import GitHub from "next-auth/providers/github"
// import Google from "next-auth/providers/google"


// // Read more at: https://next-auth.js.org/getting-started/typescript#module-augmentation
// declare module "next-auth/jwt" {
//   interface JWT {
//     /** The user's role. */
//     userRole?: "admin"
//   }
// }

// export const config = {
//   providers: [
//     Apple({ clientId: process.env.AUTH_APPLE_ID, clientSecret: process.env.AUTH_APPLE_SECRET }),
//     Discord({ clientId: process.env.AUTH_DISCORD_ID, clientSecret: process.env.AUTH_DISCORD_SECRET }),
//     Facebook({ clientId: process.env.AUTH_FACEBOOK_ID, clientSecret: process.env.AUTH_FACEBOOK_SECRET }),
//     GitHub({ clientId: process.env.AUTH_GITHUB_ID, clientSecret: process.env.AUTH_GITHUB_SECRET }),
//     Google({ clientId: process.env.AUTH_GOOGLE_ID, clientSecret: process.env.AUTH_GOOGLE_SECRET }),
//   ],
//   callbacks: {
//     async jwt({ token }) {
//       token.userRole = "admin"
//       return token
//     },
//   },
// } satisfies NextAuthConfig

// // Helper function to get session without passing config every time
// // https://next-auth.js.org/configuration/nextjs#getserversession
// export function auth(...args: [GetServerSidePropsContext["req"], GetServerSidePropsContext["res"]] | [NextApiRequest, NextApiResponse] | []) {
//   return getServerSession(...args, config)
// }

// // We recommend doing your own environment variable validation
// declare global {
//   namespace NodeJS {
//     export interface ProcessEnv {
//       NEXTAUTH_SECRET: string
//       AUTH_APPLE_ID: string
//       AUTH_APPLE_SECRET: string
//       AUTH_DISCORD_ID: string
//       AUTH_DISCORD_SECRET: string
//       AUTH_FACEBOOK_ID: string
//       AUTH_FACEBOOK_SECRET: string
//       AUTH_GITHUB_ID: string
//       AUTH_GITHUB_SECRET: string
//       AUTH_GOOGLE_ID: string
//       AUTH_GOOGLE_SECRET: string
//     }
//   }
// }
