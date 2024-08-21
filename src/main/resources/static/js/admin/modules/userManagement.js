export function loadUserManagement(){
    $.ajax({
        url: '/api/user/',
        type: 'GET',
        success: function(response) {
            console.log(response);
            let content = '';
            let counter = 1;
            response.data.forEach(data => {
                content += `<tr><td><input type="checkbox"class="manage-checkbox"data-id="${counter}"></td><td>${data.id}</td><td>${data.username}</td><td>${data.mail}</td><td>${data.role}</td></tr>`
                counter++;
            });
            $('#main').html('<div class="container"><div class="top row"><div class="col toolbar d-flex justify-content-end"><button type="button"id="add-user"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-user-plus"></i></button> <button type="button"id="del-user"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-user-minus"></i></button> <button type="button"id="edit-user"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-user-gear"></i></button> <button type="button"id="search-user"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-magnifying-glass"></i></button></div></div><div class="row bot"><div class="col-3">Side Bar</div><div class="col-9"><table><thead><tr><th></th><th>ID</th><th>Username</th><th>Mail</th><th>Role</th></tr></thead><tbody>' + content + '</tbody></table></div></div></div>');
        },
        error: function(xhr) {
            console.error('Error loading Product management UI: ' + xhr.statusText);
        }
    });
}

export function deleteUser(){
    $('input.manage-checkbox:checked').each(function() {
        var parentTr = $(this).closest('tr');

        var id = parentTr.find('td:eq(1)').text();

        $.ajax({
            url: `/api/user/${id}`,
            type: 'DELETE',
            success: function(response){
                console.log(response)
                loadUserManagement();
            },
            error: function(xhr){
                console.log(xhr.statusText)
            }
        })
    });
}

export function addUser(){
    $.ajax({
        url: `/api/user/register`,
        data: JSON.stringify({
            mail: $("input#email").val(),
            username: $("input#username").val(),
            password: $("input#password").val()
        }),
        contentType: 'application/json; charset=UTF-8; ',
        type: 'POST',
        success: function(response){
            location.reload();
            console.log(response);
        },
        error: function(xhr) {
            console.error(xhr.statusText)
        }
    })
}