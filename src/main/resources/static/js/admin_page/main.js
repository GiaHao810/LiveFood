import * as UIController from './modules/UI_Management/UIController.js'
import * as productService from './modules/service/ProductService.js';
import * as userService from './modules/service/UserService.js'



$(document).ready(function(){
    $('.nav-item li#user-link').click(function(){
        renderUserManagementHandler();
    });

    $('.nav-item li#product-link').click(function(){
        renderProductManagementHandler();
    });

    $('.nav-item li#invoice-link').click(function(){
    });

    function handleToolBar(button){
        let actions = {
            'add-user': handleAddUser,
            'del-user': handleDelUser,
            'edit-user': handleEditUser,
            'add-product': handleAddProduct,
            'del-product': handleDelProduct,
            'edit-product': handleEditProduct
        };

        let action = actions[$(button).attr('id')];
        if (action) action();
    }

    globalThis.handleToolBar = handleToolBar;

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
        if ($('table tr.selected-value').length <= 0) {
            UIController.renderNotificationBox("warn", "No boxes are checked.")
            return;
        }

        // Attension!!!!!! Khi xóa nhiều User thì bị lặp mã nhiều lần 
        $('table tr.selected-value').each(function(){
            var parentTr = $(this).closest('tr');

            var id = parentTr.find('td:eq(0)').text();

            userService.deleteUser(id);
        })
    }

    function handleEditUser(){
        let selectedValue = $('table tr.selected-value');

        if($(".edit-mode").length == 1 || selectedValue.length > 1){
            UIController.renderNotificationBox("warn", "Edit one row per time.")
            return;
        }

        if(selectedValue.length == 1) {
            let tr = selectedValue.closest('tr');
            
            let id = tr.find('td:eq(0)').text();
            let name = tr.find('td:eq(1)').text();
            let mail = tr.find('td:eq(2)').text();
            let role = tr.find('td:eq(3)').text();
        
            UIController.renderEditUserSection(id, name, mail, role, tr);
        
            $(".edit-mode #submit-btn").click(function(){
                let row = $('tr.edit-mode');

                let updateRequest = {
                    username : row.find('td:eq(1) input').val(),
                    mail : row.find('td:eq(2) input').val()
                };
                
                userService.updateUserWithID(id, updateRequest);
                
                $(".edit-mode").remove();
            });
        
            $(".edit-mode #cancel-btn").click(function(){
                $(".edit-mode").remove();
            });
            return;
        }

        UIController.renderNotificationBox("warn", "No selected row.")
    }

    function renderProductManagementHandler(){
        productService.loadProduct();
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
        if ($('table tr.selected-value').length <= 0) {
            UIController.renderNotificationBox("warn", "No selected row.")
            return;
        }

        // Attension!!!!!! Khi xóa nhiều User thì bị lặp mã nhiều lần 
        $('table tr.selected-value').each(function(){
            var parentTr = $(this).closest('tr');

            var id = parentTr.find('td:eq(0)').text();

            userService.deleteUser(id);
        })
    }

    function handleEditUser(){
        let selectedValue = $('table tr.selected-value');

        if($(".edit-mode").length == 1 || selectedValue.length > 1){
            UIController.renderNotificationBox("warn", "Edit one row per time.")
            return;
        }

        if(selectedValue.length == 1) {
            let tr = selectedValue.closest('tr');
            
            let id = tr.find('td:eq(0)').text();
            let name = tr.find('td:eq(1)').text();
            let mail = tr.find('td:eq(2)').text();
            let role = tr.find('td:eq(3)').text();
        
            UIController.renderEditUserSection(id, name, mail, role, tr);
        
            $(".edit-mode #submit-btn").click(function(){
                let row = $('tr.edit-mode');

                let updateRequest = {
                    username : row.find('td:eq(1) input').val(),
                    mail : row.find('td:eq(2) input').val()
                };
                
                userService.updateUserWithID(id, updateRequest);
                
                $(".edit-mode").remove();
            });
        
            $(".edit-mode #cancel-btn").click(function(){
                $(".edit-mode").remove();
            });
            return;
        }

        UIController.renderNotificationBox("warn", "No selected row.")
    }

    function handleAddProduct(){
        UIController.renderFormBackground(UIController.renderAddProductForm());

        $("#add-product-form button[type='submit']").click(function(event){
            event.preventDefault(); 

            const name = $("input#product_name").val();
            const price = $("input#price").val();
            const unit = $("#unit").val();
            const category = $("#category").val();
        
            if (!name || !price || !unit || !category || name.trim() === "" || price.trim() === "" || unit.trim() === "" || category.trim() === "") {
                UIController.renderNotificationBox("warn", "Please fill in all fields!!!")
                return; 
            }

            productService.addProduct(name, price, unit, category);
        });
    }

    function handleDelProduct(){
        if ($('table tr.selected-value').length <= 0) {
            UIController.renderNotificationBox("warn", "No selected row.")
            return;
        }

        $('table tr.selected-value').each(function(){
            var parentTr = $(this).closest('tr');

            var id = parentTr.find('td:eq(0)').text();

            productService.deleteProduct(id);
        })
    }
    
    function handleEditProduct(){
        let selectedValue = $('table tr.selected-value');

        if($(".edit-mode").length == 1 || selectedValue.length > 1){
            UIController.renderNotificationBox("warn", "Edit one row per time.")
            return;
        }

        if(selectedValue.length == 1) {
            let tr = selectedValue.closest('tr');
            
            let id = tr.find('td:eq(0)').text();
            let code = tr.find('td:eq(1)').text();
            let name = tr.find('td:eq(2)').text();
            let price = tr.find('td:eq(3)').text();
            let unit = tr.find('td:eq(4)').text();
            let category = tr.find('td:eq(5)').text();
        
            UIController.renderEditProductSection(id, code, name, price, unit, category, tr);
        
            $(".edit-mode #submit-btn").click(function(){
                let row = $('tr.edit-mode');

                let updateRequest = {
                    code: row.find('td:eq(1) input').val(),
                    name: row.find('td:eq(2) input').val(),
                    price: row.find('td:eq(3) input').val(),
                    unit : row.find('td:eq(4) select').val(),
                    category : row.find('td:eq(5) select').val()
                };
                
                productService.updateProductWithID(id, updateRequest)
                
                $(".edit-mode").remove();
            });
        
            $(".edit-mode #cancel-btn").click(function(){
                $(".edit-mode").remove();
            });
            return;
        }

        UIController.renderNotificationBox("warn", "No selected row.")
    }
})