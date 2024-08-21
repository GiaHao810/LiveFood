import * as productManagement from './modules/productManagement.js';
import * as customerManagement from './modules/customerManagement.js';
import * as userManagement from './modules/userManagement.js';
import * as invoiceManagement from './modules/invoiceManagement.js';
import * as formController from './modules/formController.js';

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
})

function handleToolBarBtn(button){
    if($(button).attr("id") == "add-user") formController.createFormBackground(formController.createFormAddUser());
    if($(button).attr("id") == "del-user") userManagement.deleteUser();
    if($(button).attr("id") == "add-product") formController.createFormBackground(productManagement.createFormAddProduct());
    if($(button).attr("id") == "del-product") productManagement.deleteProduct();
}

globalThis.handleToolBarBtn = handleToolBarBtn;