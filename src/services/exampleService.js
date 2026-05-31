const exampleModel = require('../models/exampleModel');

async function getExampleData() {
  // placeholder logic — later replace with DB calls
  return { message: 'service example', fromModel: exampleModel.sample() };
}

module.exports = { getExampleData };
