import * as userManagement from "../API_Management/UserAPI.js";
import * as notiManagement from "../UI_Management/NotificationUI.js"
import * as notiEffect from "../effect/NotificationEffect.js"
import * as userUI from "./UserUI.js"

export function renderFormBackground(formType){
    let duration = 1000;
    let background = $("<div>").addClass("background position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center").fadeIn(duration);
    
    $("#main").append(background, formType);

    $(".form").find("input").first().focus();
    console.log($(".form").find("input").first().val());
    background.click(function(){
        removeForm(duration);
    });

    $(".close-button").click(function(){
        removeForm(duration);
    })
}

export function removeForm(duration){
    $('.form').fadeOut(duration, function(){
        $(this).remove();
    });
    $('.background').fadeOut(duration, function(){
        $(this).remove();
    });
}

export function renderNotificationBox(type, message){
    let types = {
        'success': () => notiManagement.renderSuccessBox(message),
        'warn': () => notiManagement.renderWarningBox(message),
        'error': () => notiManagement.renderErrorBox(message),
        'info': () => notiManagement.renderInfoBox(message) 
    };

    let action = types[type];
    if (action) {
        action();
        notiEffect.fadeInAndOut("#notification-container #notification-box");
    }
}

export function renderAddUserForm(){
    return $("<div>").addClass("form border border-2 rounded shadow col position-fixed").append(
        userUI.createFormAddUser()
    );
}

export function renderEditUserSection(id, username, mail, role, row){
    $(row).after(
        userUI.createEditUserSection(id, username, mail, role)
    );
}

export function renderUserManagement(dataList){
    $("#main").html(userUI.createUserManagement(dataList));

    $('tr').click(function(){
        let checkbox = $(this).find('input.manage-checkbox');
        if (checkbox.length) {
            checkbox.prop('checked', !checkbox.prop('checked'));
        }        
    })

    $('.caret-icon-filter-search').click(function(){
        let dropSearch = $('#dropdownSearch');

        if(dropSearch.css('display') != 'none') {
            dropSearch.fadeOut('slow');
            return;
        } 
        dropSearch.fadeIn('slow');
    })

    $('input.input-filter-search').on('keydown', function(event){
        if(event.key == 'Enter') {
            alert("Finding....")
        }
    })
}