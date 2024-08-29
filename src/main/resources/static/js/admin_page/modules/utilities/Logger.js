const log = window.log.getLogger('Log');

log.setLevel('info'); // Cài đặt mức độ log (trace, debug, info, warn, error)

export function logInfo(message, data = null) {
    log.info(message, data);
}

export function logError(message, details = null) {
    log.error(message, details);
}