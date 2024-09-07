import * as userManagement from "../API_Management/UserAPI.js";
import * as notiManagement from "../UI_Management/NotificationUI.js"
import * as notiEffect from "../effect/NotificationEffect.js"
import * as userUI from "./UserUI.js"
import * as productUI from "./ProductUI.js"

export function renderFormBackground(formType){
    let duration = 1000;
    let background = $("<div>").addClass("background position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center").fadeIn(duration);
    
    $("#main").append(background, formType);

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

export function renderNotificationBox(type, message){
    let types = {
        'success': () => notiManagement.createSuccessBox(message),
        'warn': () => notiManagement.createWarningBox(message),
        'error': () => notiManagement.createErrorBox(message),
        'info': () => notiManagement.createInfoBox(message) 
    };

    let action = types[type];
    if (action) {
        $("#notification-container").append(action());
        notiEffect.fadeInAndOut("#notification-container #notification-box");
    }
}

export function renderAddUserForm(){
    return $("<div>").addClass("form border border-2 rounded shadow col position-fixed").append(
        userUI.createFormAddUser()
    );
}

export function renderEditUserSection(id, username, mail, role, row){
    $(row).after(
        userUI.createEditUserSection(id, username, mail, role)
    );
}

export function renderUserManagement(dataList){
    $("#main").html(userUI.createUserManagement(dataList));

    $('table tr').on('click',function(){
        $(this).toggleClass('selected-value');
    })

    $('.caret-icon-filter-search').click(function(){
        let dropSearch = $('#dropdownSearch');

        if(dropSearch.css('display') != 'none') {
            dropSearch.fadeOut('slow');
            return;
        } 
        dropSearch.fadeIn('slow');
    })

    $('.input-filter-search').on('keydown', function(event) {
        if(event.key == "Enter"){
            let filter = $(this).val().toLowerCase();
            let $table = $('#user-table');
            let $tr = $table.find('tr');
        
            $tr.each(function(index) {
                if (index === 0) return; // Bỏ qua hàng đầu tiên là tiêu đề cột
        
                let $td = $(this).find('td');
                let match = false;
                
                $td.each(function() {
                    let textValue = $(this).text().toLowerCase();
                    if (textValue.indexOf(filter) > -1) {
                        match = true;
                        return false; // Thoát khỏi vòng lặp each khi tìm thấy
                    }
                });
        
                $(this).toggle(match);
            });
        }
    });

    $('button[name="form-search-button"]').on('click', function() {
        let nameFilter = $('#dropdownSearch input[name="search_by_username"]').val().trim().toLowerCase();
        let mailFilter = $('#dropdownSearch input[name="search_by_mail"]').val().trim().toLowerCase();
        let idFilter = $('#dropdownSearch input[name="search_by_id"]').val().trim().toLowerCase();
        
        console.log(nameFilter)

        let $table = $('#user-table');
        let $rows = $table.find('tr');
    
        // Lặp qua từng hàng trong table
        $rows.each(function(index) {
            if (index === 0) return; 
    
            let $cells = $(this).find('td');
    
            let idMatch = idFilter ? $cells.eq(1).text().toLowerCase().includes(idFilter) : true;
            let nameMatch = nameFilter ? $cells.eq(2).text().toLowerCase().includes(nameFilter) : true;
            let mailMatch = mailFilter ? $cells.eq(3).text().toLowerCase().includes(mailFilter) : true;
            
            if (idMatch && nameMatch && mailMatch) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
}

export function renderProductManagement(dataList){
    $("#main").html(productUI.createProductManagement(dataList));

    $('table tr').on('click',function(){
        $(this).toggleClass('selected-value');
    })

    $('.caret-icon-filter-search').click(function(){
        let dropSearch = $('#dropdownSearch');

        if(dropSearch.css('display') != 'none') {
            dropSearch.fadeOut('slow');
            return;
        } 
        dropSearch.fadeIn('slow');
    })

    $('.input-filter-search').on('keydown', function(event) {
        if(event.key == "Enter"){
            let filter = $(this).val().toLowerCase();
            let $table = $('#product-table');
            let $tr = $table.find('tr');
        
            $tr.each(function(index) {
                if (index === 0) return; // Bỏ qua hàng đầu tiên là tiêu đề cột
        
                let $td = $(this).find('td');
                let match = false;
                
                $td.each(function() {
                    let textValue = $(this).text().toLowerCase();
                    if (textValue.indexOf(filter) > -1) {
                        match = true;
                        return false; // Thoát khỏi vòng lặp each khi tìm thấy
                    }
                });
        
                $(this).toggle(match);
            });
        }
    });

    $('button[name="form-search-button"]').on('click', function() {
        let nameFilter = $('#dropdownSearch input[name="search_by_username"]').val().trim().toLowerCase();
        let mailFilter = $('#dropdownSearch input[name="search_by_mail"]').val().trim().toLowerCase();
        let idFilter = $('#dropdownSearch input[name="search_by_id"]').val().trim().toLowerCase();
        
        console.log(nameFilter)

        let $table = $('#product-table');
        let $rows = $table.find('tr');
    
        // Lặp qua từng hàng trong table
        $rows.each(function(index) {
            if (index === 0) return; 
    
            let $cells = $(this).find('td');
    
            let idMatch = idFilter ? $cells.eq(1).text().toLowerCase().includes(idFilter) : true;
            let nameMatch = nameFilter ? $cells.eq(2).text().toLowerCase().includes(nameFilter) : true;
            let mailMatch = mailFilter ? $cells.eq(3).text().toLowerCase().includes(mailFilter) : true;
            
            if (idMatch && nameMatch && mailMatch) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
}

export function renderAddProductForm(){
    return $("<div>").addClass("form border border-2 rounded shadow col position-fixed").append(
        productUI.createFormAddProduct()
    );
}