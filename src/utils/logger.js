function info(msg, ...args) {
  console.log('[INFO]', msg, ...args);
}

function error(msg, ...args) {
  console.error('[ERROR]', msg, ...args);
}

module.exports = { info, error };
