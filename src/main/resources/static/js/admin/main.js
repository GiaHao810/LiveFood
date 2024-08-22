import * as productManagement from './modules/productManagement.js';
import * as customerManagement from './modules/customerManagement.js';
import * as userManagement from './modules/userManagement.js';
import * as invoiceManagement from './modules/invoiceManagement.js';
import * as UIController from './modules/UIController.js';

$(document).ready(function(){
    $('.nav-item li#user-link').click(function(){
        userManagement.loadUserManagement();
    });

    $('.nav-item li#product-link').click(function(){
        productManagement.loadProductManagement();
    });

    $('.nav-item li#invoice-link').click(function(){
        invoiceManagement.loadInvoiceManagement();
    });

    function handleToolBarBtn(button){
        if($(button).attr("id") == "add-user") {
            UIController.renderFormBackground(UIController.createFormAddUser());
            $("#add-user-form button[type='submit']").click(function() {
                userManagement.addUser();
            })
        }
        if($(button).attr("id") == "del-user") userManagement.deleteUser();

        if($(button).attr("id") == "add-product") {
            UIController.renderFormBackground(UIController.createFormAddProduct());
            $("#add-product-form button[type='submit']").click(function() {
                productManagement.addProduct();
            })
        }
        if($(button).attr("id") == "del-product") productManagement.deleteProduct();

        if($(button).attr("id") == "edit-user") {
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
    
    globalThis.handleToolBarBtn = handleToolBarBtn;
})

