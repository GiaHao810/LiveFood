export function getDefaultBox(){
    let content = '<div id="notification-box"class="position-fixed top-2 end-2 p-3 bg-white rounded shadow d-flex align-items-center justify-content-between gap-3 end-0"><div class="icon-container d-flex justify-content-center align-items-center rounded-circle ms-1"><i class="fa-solid fa-info"></i></div><div class="message-text-container d-flex flex-column justify-content-center align-items-start flex-grow-1"><p class="message-text">Default Text</p><p class="sub-text">Default Text</p></div><div><i class="fa-solid fa-xmark cross-icon"></i></div></div>';
    return content;
}

export function renderWarningBox(message){
    let content = '<div id="notification-box"class="position-fixed top-2 end-2 p-3 bg-white rounded shadow d-flex align-items-center justify-content-between gap-3 end-0"><div class="icon-container d-flex justify-content-center align-items-center rounded-circle ms-1 bg-warning"><i class="fa-solid fa-exclamation"></i></div><div class="message-text-container d-flex flex-column justify-content-center align-items-start flex-grow-1"><p class="message-text text-warning">Warning</p><p class="sub-text">' + message + '</p></div><div><i class="fa-solid fa-xmark cross-icon"></i></div></div>';

    $("#main").append(content);
}

export function renderInfoBox(message){
    let content = '<div id="notification-box"class="position-fixed top-2 end-2 p-3 bg-white rounded shadow d-flex align-items-center justify-content-between gap-3 end-0"><div class="icon-container d-flex justify-content-center align-items-center rounded-circle ms-1 bg-info"><i class="fa-solid fa-info"></i></div><div class="message-text-container d-flex flex-column justify-content-center align-items-start flex-grow-1"><p class="message-text text-info">Warning</p><p class="sub-text">' + message + '</p></div><div><i class="fa-solid fa-xmark cross-icon"></i></div></div>';

    $("#main").append(content);
}

export function renderErrorBox(message){
    
}

export function renderSuccessBox(message){
    
}