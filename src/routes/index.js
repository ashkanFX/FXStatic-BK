const express = require('express');
const router = express.Router();
const exampleController = require('../controllers/exampleController');

router.get('/example', exampleController.getExample);

// Resource routes
router.use('/items', require('./items'));

module.exports = router;
