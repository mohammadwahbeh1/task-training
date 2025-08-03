const express = require('express');
const session = require('express-session');
const path = require('path');
const bodyParser = require('body-parser');

const app = express();
const PORT = 3000;

// Middleware
app.use(express.static('public'));
app.use(bodyParser.urlencoded({ extended: true }));
app.use(session({
  secret: 'secret-blog',
  resave: false,
  saveUninitialized: true
}));

app.set('view engine', 'ejs');

// Routes
const blogRoutes = require('./routes/blogRoutes');
const authRoutes = require('./routes/authRoutes');
const adminRoutes = require('./routes/adminRoutes');
app.use(blogRoutes);
app.use(adminRoutes);

app.use(authRoutes);


// Start
app.listen(PORT, () => console.log(`Server running at http://localhost:${PORT}`));
