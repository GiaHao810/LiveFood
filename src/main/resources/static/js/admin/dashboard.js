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
            $('#main').html('<div class="container"><div class="top row"><div class="col toolbar d-flex justify-content-end"><button type="button"id="add-user"onclick="handleToolBarBtn(this)"><i class="fa-sharp fa-solid fa-user-plus"></i></button> <button type="button"id="del-user"onclick="handleToolBarBtn(this)"><i class="fa-sharp fa-solid fa-user-minus"></i></button> <button type="button"id="edit-user"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-user-gear"></i></button> <button type="button"id="search-user"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-magnifying-glass"></i></button></div></div><div class="row bot"><div class="col-3">Side Bar</div><div class="col-9"><table><thead><tr><th></th><th>ID</th><th>Username</th><th>Mail</th><th>Role</th></tr></thead><tbody>' + user_content + '</tbody></table></div></div></div>');
        },
        error: function(xhr) {
            console.error('Error loading Product management UI: ' + xhr.statusText);
        }
    });
}
function loadProductManagement(){}
function loadInvoiceManagement(){}

function handleToolBarBtn(button){
    if($(button).attr("id") == "add-user") createForm(createFormUser);
}

function createForm(formType){
    let duration = 1000;
    let background = $("<div>").addClass("background position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center").fadeIn(duration);
    let form = $("<div>").addClass("form border border-2 rounded shadow col position-fixed").append(formType).fadeIn(duration * 2);
    
    $("#main").append(background, form);

    background.click(function(){
        removeForm(duration);
    });

    $(".close-button").click(function(){
        removeForm(duration);
    })
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
    let content = '<span class="close-button align-self-end">&times;</span><h2>Add User</h2><form id="add-user-form"><div class="row m-1"><div class="col"><label for="username">Username:</label></div><div class="col"><input id="username"name="username"required></div></div><div class="row m-1"><div class="col"><label for="email">Email:</label></div><div class="col"><input type="email"id="email"name="email"required></div></div><div class="row m-1"><div class="col"><label for="password">Password:</label></div><div class="col"><input type="password"id="password"name="password"required></div></div><div class="row m-1"><div class="col"><button type="submit">Submit</button></div></div></form>';

    let form = $("<div>").addClass("modal-content p-4 rounded shadow").html(content);

    return form;
}