import * as productManagement from './modules/API_Management/ProductAPI.js'
import * as customerManagement from './modules/API_Management/CustomerAPI.js'
import * as userManagement from './modules/API_Management/UserAPI.js'
import * as invoiceManagement from './modules/API_Management/InvoiceAPI.js'

import * as UIController from './modules/UI_Management/UIController.js'

import * as systaxChecker from './modules/utilities/SyntaxChecker.js'

import * as logger from './modules/utilities/Logger.js'


$(document).ready(function(){
$('.nav-item li#user-link').click(function(){
    renderUserManagementHandler();
});

$('.nav-item li#product-link').click(function(){
    productManagement.loadProductManagement();
    UIController.renderNotificationBox("success", "Loading Product Infomation");
});

$('.nav-item li#invoice-link').click(function(){
    invoiceManagement.loadInvoiceManagement();
});

function handleToolBarBtn(button){
    let actions = {
        'add-user': handleAddUser,
        'del-user': handleDelUser,
        'edit-user': handleEditUser,
        'add-product': handleAddProduct,
        'del-product': handleDelProduct
    };

    let action = actions[$(button).attr('id')];
    if (action) action();
}

globalThis.handleToolBarBtn = handleToolBarBtn;

function renderUserManagementHandler(){
    userManagement.loadUserManagement()
    .then(response => {

        logger.logInfo("Edit User API called", { 
            message: response.message,
            status: response.status, 
            data: response.data 
        });

        UIController.renderUserManagement(response.data);

        UIController.renderNotificationBox("success", "Loading User Infomation");
    })
    .catch(error => {
        logger.logError("Error Edit User API", { 
            status: error.status,
            message: error.message,
            data: error.data
        });

        UIController.renderNotificationBox("warn", `${error.message}`)
    });
}
function handleAddUser(){
    UIController.renderFormBackground(UIController.createFormAddUser());

    $("#add-user-form button[type='submit']").click(function(event){
        event.preventDefault(); 

        const email = $("input#email").val();
        const username = $("input#username").val();
        const password = $("input#password").val();
    
        if (!email || !username || !password || email.trim() === "" || username.trim() === "" || password.trim() === "") {
            UIController.renderNotificationBox("warn", "Please fill in all fields!!!")
            return; 
        }

        userManagement.addUser(email, username, password)
        .then(response => {
            logger.logInfo("Add User API called", { 
                message: response.message, 
                status: response.status, 
                data: response.data 
            });

            UIController.renderNotificationBox("info", `${response.message}`);

            setTimeout(() => {
                UIController.removeForm(0);
                UIController.renderUserManagement();
            }, 1000);
        })
        .catch(error => {
            logger.logError("Error Add User API", { 
                status: error.status,  
                message: error.message,
                data: error.data 
            });

            UIController.renderNotificationBox("warn", `${error.message}`);
        });
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

        userManagement.deleteUser(id)
        .then(response => {
            UIController.renderUserManagement();

            logger.logInfo("Delete User API called", { 
                message: response.message,
                status: response.status, 
                data: response.data 
            });

            UIController.renderNotificationBox("success", `${response.message}`);
        })
        .catch(error => {
            logger.logError("Error Delete User API", { 
                status: error.status,
                message: error.message,
                data: error.data
            });

            UIController.renderNotificationBox("warn", `${error.message}`);
        })
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
            let updateRequest = {
                username : name,
                mail : mail
            };
            
            userManagement.updateUserWithNameAndMail(id, updateRequest)
            .then(response => {
                UIController.renderUserManagement();
    
                logger.logInfo("Edit User API called", { 
                    message: response.message,
                    status: response.status, 
                    data: response.data 
                });
    
                UIController.renderNotificationBox("succeess", `${response.message}`)
            })
            .catch(error => {
                logger.logError("Error Edit User API", { 
                    status: error.status,
                    message: error.message,
                    data: error.data
                });
    
                UIController.renderNotificationBox("error", `${error.message}`)
            })
    
            $(".edit-mode").remove();
        });
    
        $(".edit-mode #cancel-btn").click(function(){
            $(".edit-mode").remove();
        });
        return;
    }

    UIController.renderNotificationBox("warn", "No boxes are  checked.")
}

function handleAddProduct(){
    UIController.renderFormBackground(UIController.createFormAddProduct());
    $("#add-product-form button[type='submit']").click(function() {
        productManagement.addProduct()
            .then(response => {
                location.reload();
                console.log(`Add User API called!!!`);
                console.log(`Message: ${response.message}`);
                console.log(`Status: ${response.status}`);
                console.log(`Data: ${response.data}`);
            })
            .catch(error => {
                console.error("Error Add User API");
                console.error(`Status: ${error.status}`);
                console.error(`Code: ${error.code}`);
                console.error(`Message: ${error.message}`);
                console.error(`Details: ${error.details}`);
            })
    })
}

function handleDelProduct(){
    productManagement.deleteProduct();
}
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