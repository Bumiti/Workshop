/** @type {import('next').NextConfig} */
const nextConfig = {
    reactStrictMode: false,
    images: {
        domains: ['firebasestorage.googleapis.com','drive.google.com'],
   
    },
};

module.exports = nextConfig
