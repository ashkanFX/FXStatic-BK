const exampleService = require('../services/exampleService');

async function getExample(req, res, next) {
  try {
    const data = await exampleService.getExampleData();
    res.json(data);
  } catch (err) {
    next(err);
  }
}

module.exports = { getExample };
