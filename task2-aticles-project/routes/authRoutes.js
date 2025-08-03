   const express = require('express');
    const router = express.Router();

    // Show login form
    router.get('/login', (req, res) => {
    res.render('login');
    });

    // Handle login submission
    router.post('/login', (req, res) => {
    const { username, password } = req.body;

    // Replace with real authentication logic
    if (username === 'admin' && password === '1234') {
        req.session.authenticated = true;
        res.redirect('/admin');
    } else {
        res.render('login', { error: 'Invalid credentials' });
    }
    });

    // Logout route
    router.get('/logout', (req, res) => {
    req.session.destroy(() => {
        res.redirect('/login');
    });
    });

    module.exports = router;
