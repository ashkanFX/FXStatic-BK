const express = require('express');
const app = express();
const port = process.env.PORT || 5000;

app.listen(port, () => `Server running on port ${port} 🔥`);
const router = express.Router();

router.get('/', (req, res) => {
  res.json({ message: 'Example route', timestamp: Date.now() });
});

module.exports = router;
