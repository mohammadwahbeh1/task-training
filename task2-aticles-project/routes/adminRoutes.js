const express = require('express');
const fs = require('fs');
const path = require('path');

const router = express.Router();
const ARTICLES_DIR = path.join(__dirname, '../articles');

// Auth middleware
function checkAuth(req, res, next) {
  if (req.session && req.session.authenticated) return next();
  res.redirect('/login');
}

// Helper
function getAllArticles() {
  const files = fs.readdirSync(ARTICLES_DIR);
  return files.map(file => JSON.parse(fs.readFileSync(path.join(ARTICLES_DIR, file))));
}

// Admin dashboard
router.get('/admin', checkAuth, (req, res) => {
  const articles = getAllArticles();
  res.render('admin', { articles });
});

// New article form
router.get('/new', checkAuth, (req, res) => res.render('new'));

router.post('/new', checkAuth, (req, res) => {
  const { title, date, content } = req.body;
  const id = Date.now().toString();
  const article = { id, title, date, content };
  fs.writeFileSync(path.join(ARTICLES_DIR, `${id}.json`), JSON.stringify(article, null, 2));
  res.redirect('/admin');
});

// Edit form
router.get('/edit/:id', checkAuth, (req, res) => {
  const filePath = path.join(ARTICLES_DIR, `${req.params.id}.json`);
  if (!fs.existsSync(filePath)) return res.status(404).send('Not found');
  const article = JSON.parse(fs.readFileSync(filePath));
  res.render('edit', { article });
});

router.post('/edit/:id', checkAuth, (req, res) => {
  const { title, date, content } = req.body;
  const id = req.params.id;
  const article = { id, title, date, content };
  fs.writeFileSync(path.join(ARTICLES_DIR, `${id}.json`), JSON.stringify(article, null, 2));
  res.redirect('/admin');
});

// Delete article
router.get('/delete/:id', checkAuth, (req, res) => {
  const filePath = path.join(ARTICLES_DIR, `${req.params.id}.json`);
  if (fs.existsSync(filePath)) fs.unlinkSync(filePath);
  res.redirect('/admin');
});

module.exports = router;
