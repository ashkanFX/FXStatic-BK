export function info(msg: any, ...args: any[]) {
  console.log('[INFO]', msg, ...args);
}

export function error(msg: any, ...args: any[]) {
  console.error('[ERROR]', msg, ...args);
}
