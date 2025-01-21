$(document).ready(function() {
    console.log('Hello from login.js');
    $.ajax({
        url:`/authentication/authenticate`,
        type: 'POST',
        data: {
            username: $('#username').val(),
            password: $('#password').val()
        },
        success: function(response){
        console.log(response);
        },
        error: function(xhr) {
        console.error(xhr);
        }
    });
})