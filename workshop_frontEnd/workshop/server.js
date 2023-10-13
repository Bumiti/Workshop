// server.js (hoặc tệp tương tự)
const express = require('express');
const next = require('next');
const expressSession = require('express-session');

const dev = process.env.NODE_ENV !== 'production';
const app = next({ dev });
const handle = app.getRequestHandler();

app.prepare().then(() => {
  const server = express();

  // Sử dụng express-session middleware
  server.use(
    expressSession({
      secret: 'your-secret-key', // Thay thế bằng chuỗi bí mật thực tế
      resave: false,
      saveUninitialized: true,
      cookie: {
        secure: process.env.NODE_ENV === 'production',
      },
    })
  );

  server.get('*', (req, res) => {
    return handle(req, res);
  });

  server.listen(3000, (err) => {
    if (err) throw err;
    console.log('Ready on http://localhost:3000');
  });
});
