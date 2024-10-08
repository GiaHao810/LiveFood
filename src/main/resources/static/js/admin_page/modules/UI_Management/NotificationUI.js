export function createDefaultBox(){
    return`
    <div id="notification-box" class="mb-3 p-3 bg-white rounded shadow d-flex align-items-center justify-content-between gap-3">
        <div class="icon-container d-flex justify-content-center align-items-center rounded-circle ms-1"><i class="fa-solid fa-info"></i></div>
        <div class="message-text-container d-flex flex-column justify-content-center align-items-start flex-grow-1">
            <p class="message-text">Default Text</p>
            <p class="sub-text">Default Text</p>
        </div>
        <div><i class="fa-solid fa-xmark cross-icon"></i></div>
    </div>`;
}

export function createWarningBox(message){
    return`
    <div id="notification-box" class="mb-3 p-3 bg-white rounded shadow d-flex align-items-center justify-content-between gap-3">
        <div class="icon-container d-flex justify-content-center align-items-center rounded-circle ms-1 bg-warning"><i class="fa-solid fa-exclamation"></i></div>
        <div class="message-text-container d-flex flex-column justify-content-center align-items-start flex-grow-1">
            <p class="message-text text-warning">Warning</p>
            <p class="sub-text">${message}</p>
        </div>
        <div><i class="fa-solid fa-xmark cross-icon"></i></div>
    </div>`;
}

export function createInfoBox(message){
    return`
    <div id="notification-box" class="mb-3 p-3 bg-white rounded shadow d-flex align-items-center justify-content-between gap-3">
        <div class="icon-container d-flex justify-content-center align-items-center rounded-circle ms-1 bg-info"><i class="fa-solid fa-info"></i></div>
        <div class="message-text-container d-flex flex-column justify-content-center align-items-start flex-grow-1">
            <p class="message-text text-info">Info</p>
            <p class="sub-text">${message}</p>
        </div>
        <div><i class="fa-solid fa-xmark cross-icon"></i></div>
    </div>`;
}

export function createErrorBox(message){
    return`
    <div id="notification-box" class="mb-3 p-3 bg-white rounded shadow d-flex align-items-center justify-content-between gap-3">
        <div class="icon-container d-flex justify-content-center align-items-center rounded-circle ms-1 bg-danger"><i class="fa-solid fa-exclamation"></i></div>
        <div class="message-text-container d-flex flex-column justify-content-center align-items-start flex-grow-1">
            <p class="message-text text-danger">Error</p>
            <p class="sub-text">${message}</p>
        </div>
        <div><i class="fa-solid fa-xmark cross-icon"></i></div>
    </div>`;
}

export function createSuccessBox(message){
    return`
    <div id="notification-box" class="mb-3 p-3 bg-white rounded shadow d-flex align-items-center justify-content-between gap-3">
        <div class="icon-container d-flex justify-content-center align-items-center rounded-circle ms-1 bg-success"><i class="fa-solid fa-thumbs-up"></i></div>
        <div class="message-text-container d-flex flex-column justify-content-center align-items-start flex-grow-1">
            <p class="message-text text-success">Sucess</p>
            <p class="sub-text">${message}</p>
        </div>
        <div><i class="fa-solid fa-xmark cross-icon"></i></div>
    </div>`;
}