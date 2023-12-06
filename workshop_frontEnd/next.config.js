/** @type {import('next').NextConfig} */
const nextConfig = {
    reactStrictMode: false,
    images: {
        domains: ['firebasestorage.googleapis.com','drive.google.com','example.com'],
   
    },
};

module.exports = nextConfig
