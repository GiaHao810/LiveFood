import * as UIController from './modules/UI_Management/UIController.js'
import * as userService from './modules/service/UserService.js'


$(document).ready(function(){
$('.nav-item li#user-link').click(function(){
    renderUserManagementHandler();
});

$('.nav-item li#product-link').click(function(){
});

$('.nav-item li#invoice-link').click(function(){
});

function handleToolBarBtn(button){
    let actions = {
        'add-user': handleAddUser,
        'del-user': handleDelUser,
        'edit-user': handleEditUser
    };

    let action = actions[$(button).attr('id')];
    if (action) action();
}

globalThis.handleToolBarBtn = handleToolBarBtn;

function renderUserManagementHandler(){
    userService.loadUser();
}
function handleAddUser(){
    UIController.renderFormBackground(UIController.renderAddUserForm());

    $("#add-user-form button[type='submit']").click(function(event){
        event.preventDefault(); 

        const email = $("input#email").val();
        const username = $("input#username").val();
        const password = $("input#password").val();
    
        if (!email || !username || !password || email.trim() === "" || username.trim() === "" || password.trim() === "") {
            UIController.renderNotificationBox("warn", "Please fill in all fields!!!")
            return; 
        }

        userService.addUser(email, username, password);
    });

}

function handleDelUser(){
    if ($('input.manage-checkbox:checked').length <= 0) {
        UIController.renderNotificationBox("warn", "No boxes are checked.")
        return;
    }

    // Attension!!!!!! Khi xóa nhiều User thì bị lặp mã nhiều lần 
    $('input.manage-checkbox:checked').each(function(){
        var parentTr = $(this).closest('tr');

        var id = parentTr.find('td:eq(1)').text();

        userService.deleteUser(id);
    })
}

function handleEditUser(){
    let checkedInput = $('input.manage-checkbox:checked');

    if($(".edit-mode").length == 1 || checkedInput.length > 1){
        UIController.renderNotificationBox("warn", "Edit one user per time.")
        return;
    }

    if(checkedInput.length == 1) {
        let tr = checkedInput.closest('tr');
        
        let id = tr.find('td:eq(1)').text();
        let name = tr.find('td:eq(2)').text();
        let mail = tr.find('td:eq(3)').text();
        let role = tr.find('td:eq(4)').text();
    
        UIController.renderEditUserSection(id, name, mail, role, tr);
    
        $(".edit-mode #submit-btn").click(function(){
            let row = $('tr.edit-mode');

            let updateRequest = {
                username : row.find('td:eq(2) input').val(),
                mail : row.find('td:eq(3) input').val()
            };
            
            userService.updateUserWithID(id, updateRequest);
            
            $(".edit-mode").remove();
        });
    
        $(".edit-mode #cancel-btn").click(function(){
            $(".edit-mode").remove();
        });
        return;
    }

    UIController.renderNotificationBox("warn", "No boxes are  checked.")
}

function handleFindUser(){
    let form = $('#dropdownSearch > .form-search');

    let username = form.find('input[name="search_by_username"').val();
    let id = form.find('input[name="search_by_id"').val();
    let mail = form.find('input[name="search_by_mail"').val();

    
}

globalThis.handleFindUser = handleFindUser;
})

/* 
        .then(response => {
            logger.logInfo("Edit User API called", { 
                message: response.message,
                status: response.status, 
                data: response.data 
            });
        })
        .catch(error => {
            logger.logError("Error Edit User API", { 
                status: error.status,
                message: error.message,
                data: error.data
            });
        })
*/