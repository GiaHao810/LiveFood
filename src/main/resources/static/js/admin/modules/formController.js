export function createFormBackground(formType){
    let duration = 1000;
    let background = $("<div>").addClass("background position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center").fadeIn(duration);
    let form = $("<div>").addClass("form border border-2 rounded shadow col position-fixed").append(formType).fadeIn(duration * 2);
    
    $("#main").append(background, form);

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

export function createFormAddUser(){
    let content = '<span class="close-button align-self-end">&times;</span><h2>Add User</h2><div id="add-user-form"><div class="row m-1"><div class="col"><label for="username">Username:</label></div><div class="col"><input id="username"name="username" required autofocus></div></div><div class="row m-1"><div class="col"><label for="email">Email:</label></div><div class="col"><input type="email"id="email"name="email"required></div></div><div class="row m-1"><div class="col"><label for="password">Password:</label></div><div class="col"><input type="password"id="password"name="password"required></div></div><div class="row m-1"><div class="col"><button type="submit" onclick="addUser()">Submit</button></div></div></div>';

    let form = $("<div>").addClass("modal-content p-4 rounded shadow").html(content);
    return form;
}