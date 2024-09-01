import * as userManagement from "../API_Management/UserAPI.js";
import * as notiManagement from "../UI_Management/NotificationUI.js"
import * as notiEffect from "../effect/NotificationEffect.js"

export function renderFormBackground(formType){
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
    let content = `
    <span class="close-button align-self-end">&times;</span>
    <h2>Add User</h2>
    <div id="add-user-form">
        <div class="row m-1">
            <div class="col"><label for="username">Username:</label></div>
            <div class="col"><input id="username" name="username" required autofocus></div>
        </div>
        <div class="row m-1">
            <div class="col"><label for="email">Email:</label></div>
            <div class="col"><input type="email" id="email" name="email" required></div>
        </div>
        <div class="row m-1">
            <div class="col"><label for="password">Password:</label></div>
            <div class="col"><input type="password" id="password" name="password" required></div>
        </div>
        <div class="row m-1">
            <div class="col"><button type="submit">Submit</button></div>
        </div>
    </div>`;

    return $("<form>").addClass("modal-content p-4 rounded shadow").html(content);
}

export function createFormAddProduct(){
    let content = '<span class="close-button align-self-end">×</span><h2>Add Product</h2><div id="add-product-form"><div class="row m-1"><div class="col"><label for="name">Name:</label></div><div class="col"><input id="name"name="name"required></div></div><div class="row m-1"><div class="col"><label for="price">Price:</label></div><div class="col"><input type="number"id="price"name="price"required></div></div><div class="row m-1"><div class="col"><label for="unit">Unit:</label></div><div class="col"><select name="unit"id="unit"><option value="gram">Gram</option><option value="kilogram">Kilogram</option><option value="miligram">Miligram</option><option value="liter">Liter</option><option value="milliliter">Milliliter</option><option value="package">Package</option></select></div></div><div class="row m-1"><div class="col"><label for="category">Category:</label></div><div class="col"><select name="category"id="category"><option value="FRUIT">FRUIT</option><option value="VEGETABLE">VEGETABLE</option><option value="OTHER">OTHER</option></select></div></div><div class="row m-1"><div class="col"><button type="submit">Submit</button></div></div></div>';

    return $("<div>").addClass("modal-content p-4 rounded shadow").html(content);
}

export function renderEditUserSection(id, username, mail, role, row){
    let editSection = `
    <tr class="edit-mode">
        <td><span><button type="button" id="submit-btn"><i class="fa-solid fa-check"></i></button> </span><span><button type="button" id="cancel-btn"><i class="fa-solid fa-xmark"></i></button></span></td>
        <td><input value="${id}" disabled="disabled"></td>
        <td><input value="${username}" required></td>
        <td><input value="${mail}" required></td>
        <td><input value="${role}" disabled="disabled"></td>
    </tr>`;

    $(row).after(editSection);
}

export function renderNotificationBox(type, message){
    let types = {
        'success': () => notiManagement.renderSuccessBox(message),
        'warn': () => notiManagement.renderWarningBox(message),
        'error': () => notiManagement.renderErrorBox(message),
        'info': () => notiManagement.renderInfoBox(message) 
    };

    let action = types[type];
    if (action) {
        action();
        notiEffect.fadeInAndOut("#notification-container #notification-box");
    }
}

export function renderUserManagement(dataList){
    let content = '';
    let counter = 0;

    dataList.forEach(data => {
        content += `
        <tr>
            <td><input type="checkbox" class="manage-checkbox" data-id="${counter}"></td>
            <td><span>${data.id}</span></td>
            <td><span>${data.username}</span></td>
            <td><span>${data.mail}</span></td>
            <td><span>${data.role}</span></td>
        </tr>`

        counter++;
    });

    $('#main').html(`
        <div class="container">
            <div class="row">
                <div class="col-4">
                    <div class="col-3">Side Bar</div>
                </div>
                <div class="col-8">
                    <div class="row toolbar d-flex">
                        <div class="col d-flex">
                            <div class="header-filter-search d-flex justify-content-center">
                                <div class="filter-search">
                                    <div class="search-icon-filter-search">
                                        <i class="fa-solid fa-magnifying-glass"></i>
                                    </div>

                                    <div class="caret-icon-filter-search">
                                        <i class="fa-solid fa-caret-down"></i>
                                    </div>

                                    <input placeholder="Search by Username" type="search" name="search_by_username" class="input-filter-search">

                                    <div id="dropdownSearch" class="shadow bg-body rounded p-3">
                                        <div class="form-search">
                                            <input placeholder="Search by ID" type="search" name="search_by_id">
                                            <input placeholder="Search by Mail" type="search" name="search_by_mail">  
                                        </div>
                                        <div class="form-footer d-flex justify-content-end">
                                            <button type="button" name="form-search-button">Search</button>
                                        </div>                              
                                    </div>  
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="header-filter-button d-flex justify-content-end">
                                <button type="button" id="add-user" onclick="handleToolBarBtn(this)"><i class="fa-solid fa-user-plus"></i></button> 
                                <button type="button" id="del-user" onclick="handleToolBarBtn(this)"><i class="fa-solid fa-user-minus"></i></button> 
                                <button type="button" id="edit-user" onclick="handleToolBarBtn(this)"><i class="fa-solid fa-user-gear"></i></button> 
                                <button type="button" id="none" onclick="handleToolBarBtn(this)"></button>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <table>
                            <thead>
                            <tr>
                                <th></th>
                                <th>ID</th>
                                <th>Username</th>
                                <th>Mail</th>
                                <th>Role</th>
                            </tr>
                            </thead>
                            <tbody> ${content}</tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>`);

        $('tr').click(function(){
            let checkbox = $(this).find('input.manage-checkbox');
            if (checkbox.length) {
                checkbox.prop('checked', !checkbox.prop('checked'));
            }        
        })

        $('.caret-icon-filter-search').click(function(){
            let dropSearch = $('#dropdownSearch');

            if(dropSearch.css('display') != 'none') {
                dropSearch.fadeOut('slow');
                return;
            } 
            dropSearch.fadeIn('slow');
        })


}