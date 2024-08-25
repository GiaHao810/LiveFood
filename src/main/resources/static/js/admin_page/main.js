import * as productManagement from './modules/API_Management/ProductAPI.js';
import * as customerManagement from './modules/API_Management/CustomerAPI.js';
import * as userManagement from './modules/API_Management/UserAPI.js';
import * as invoiceManagement from './modules/API_Management/InvoiceAPI.js';

import * as UIController from './modules/UI_Management/UIController.js';

$(document).ready(function(){
    $('.nav-item li#user-link').click(function(){
        userManagement.loadUserManagement();
        UIController.renderNotificationBox("success", "Loading User Infomation");
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

    function handleAddUser(){
        UIController.renderFormBackground(UIController.createFormAddUser());
        $("#add-user-form button[type='submit']").click(userManagement.addUser());
    }

    function handleDelUser(){
        userManagement.deleteUser();
    }

    function handleAddProduct(){
        UIController.renderFormBackground(UIController.createFormAddProduct());
        $("#add-product-form button[type='submit']").click(function() {
            productManagement.addProduct();
        })
    }

    function handleDelProduct(){
        productManagement.deleteProduct();
    }

    function handleEditUser(){
        if(!$(".edit-mode").length) {
            let tr = $('input.manage-checkbox:checked').closest('tr');

            let id = tr.find('td:eq(1)').text();
            let name = tr.find('td:eq(2)').text();
            let mail = tr.find('td:eq(3)').text();
            let role = tr.find('td:eq(4)').text();
            
            UIController.renderEditUserSection(
                UIController.createUIEditUser(id, name, mail, role),
                tr
            );
        }
    }
})

