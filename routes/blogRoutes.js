const express = require('express');
const fs = require('fs');
const path = require('path');

const router = express.Router();
const ARTICLES_DIR = path.join(__dirname, '../articles');

// Helper
function getAllArticles() {
  const files = fs.readdirSync(ARTICLES_DIR);
  return files.map(file => JSON.parse(fs.readFileSync(path.join(ARTICLES_DIR, file))));
}

// Home page
router.get('/', (req, res) => {
  const articles = getAllArticles().sort((a, b) => new Date(b.date) - new Date(a.date));
  res.render('home', { articles });
});

// View single article
router.get('/article/:id', (req, res) => {
  const filePath = path.join(ARTICLES_DIR, `${req.params.id}.json`);
  if (fs.existsSync(filePath)) {
    const article = JSON.parse(fs.readFileSync(filePath));
    res.render('article', { article });
  } else {
    res.status(404).send('Article not found');
  }
});

module.exports = router;