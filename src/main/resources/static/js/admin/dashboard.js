$(document).ready(function(){
    $('.nav-item li#user-link').click(function(){
        loadUserManagement();
    });
})

function loadUserManagement(){
    $.ajax({
        url: '/api/user/',
        type: 'GET',
        success: function(response) {
            console.log(response);
            let user_content = '';
            response.data.forEach(data => {
                user_content += `<tr><td><input type="checkbox"class="user-checkbox"data-id="1"></td><td>${data.id}</td><td>${data.username}</td><td>${data.mail}</td><td>${data.role}</td></tr>`
            });
            $('#body').html('<div class="container"><div class="row"><div class="col">Thanh cong cu</div></div><div class="row"><div class="col-3">Side Bar</div><div class="col-9"><table><thead><tr><th></th><th>ID</th><th>Username</th><th>Mail</th><th>Role</th></tr></thead><tbody>' + user_content + '</tbody></table></div></div></div>');
        },
        error: function(xhr) {
            console.error('Error loading Product management UI: ' + xhr.statusText);
        }
    });
}
function loadProductManagement(){}
function loadInvoiceManagement(){}

function handleToolBarBtn(button){
    if($(button).attr("id") == "add-user") createForm();
}

function createForm(){
    let duration = 1000;
    let background = $("<div>").addClass("background").fadeIn(duration);
    let form = $("<div>").addClass("form border border-2 rounded shadow").fadeIn(duration * 2);

    // Add HTML this

    background.click(function(){
        removeForm(duration);
    });

    $("#body").append(background, form);
}

function removeForm(duration){
    $('.form').fadeOut(duration, function(){
        $(this).remove();
    });
    $('.background').fadeOut(duration, function(){
        $(this).remove();
    });
}

function createFormUser(){
    let content = '';
}