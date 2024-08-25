function handleAddImage(imageInput) {
    let prod = $(imageInput).closest('li');

    if(!isProdInOrder(prod)) 
        addNewProdToOrder(prod);
        handleTotalChange();
}

function addNewProdToOrder(prod){
    let prodPrice = prod.find('input.product-price').val();
    let prodID = prod.find('input.product-id').val();
    let prodURL = prod.find('.product-image').attr("src");
    let prodName = prod.find('.product-name').text();
    

    let newProd = $('<li>')
    .addClass('product-item row p-2')
    .append(
        $('<div>')
        .addClass('col border border-3 rounded-pill')
        .append(
            $('<label>')
                .addClass('col-md order-name')
                .text(prodName)
        ),
        $('<div>')
            .addClass('col')
            .append(
                $('<input>')
                    .addClass('col-md order-quantity border border-3 rounded-pill text-center h-100')
                    .attr('type', 'number')
                    .attr('min', '1')
                    .val(1)
                    .change(handleTotalChange)
        ),
        $('<div>')
        .addClass('col')
        .append(
            $('<button>')
                .addClass('col-md delete-button')
                .click(function(){
                    handleDelItemOrder(this)
                })
            .append(
                $('<i>')
                    .addClass('fa-solid fa-trash')
            )
        ),
        $('<input>').addClass('order-url').attr('type', 'hidden').val(prodURL),
        $('<input>').addClass('order-price').attr('type', 'hidden').val(prodPrice),
        $('<input>').addClass('order-id').attr('type', 'hidden').val(prodID)
    );

    $('#ordertable').append(newProd);
}   

function isProdInOrder(prod){
    let prodItems = $('#ordertable').find('li.product-item');
    let isFound = false;

    prodItems.each(function(){
        if(prod.find('.product-name').text() === $(this).find('.order-name').text()) 
            isFound = true;
            return;
    });

    return isFound;
}

function handleDelItemOrder(button){
    delProdFromOrder(button);
    handleTotalChange();
}

function delProdFromOrder(button){
    $(button).closest('.product-item').remove();
}

function handleSubTotalChange(){
    let prodItems = $('#ordertable').find('li.product-item');
    let total = 0.0;
    let curTotal = 0.0;

    if(prodItems.length === 0) {
        $('.product-sub-total-value p').text('0 đ');
        return 0;
    }
    
    prodItems.each(function(){
        curTotal = $(this).find('.order-price').val()
         * $(this).find('.order-quantity').val();

        total += curTotal;
    });

    $('.product-sub-total-value p').text(formatCurrency(total));

    return total;
}

function handleTaxChange(){
    let tax = $('.product-sub-total-value p').text();

    let numb = parseFloat(tax) / 10;

    if(numb === 0){
        $('.product-tax-value p').text("0 đ");
        return numb;
    }

    $('.product-tax-value p').text(formatCurrency(numb));

    return numb;
}

function handleDiscountChange(){
    return 0;
}

function handleTotalChange(){
    let subTotal = handleSubTotalChange();
    let tax = handleTaxChange();
    let discount = handleDiscountChange();

    let total = (subTotal + tax) - discount;

    if(total === 0){
        $('#total-price-value').text('0 đ');
        return;
    }

    $('#total-price-value').text(formatCurrency(total));
}

function formatCurrency(number) {
    // Sử dụng Intl.NumberFormat để định dạng số thành tiền tệ Việt Nam Đồng
    const formatter = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
        minimumFractionDigits: 3
    });

    return formatter.format(number);
}

function handleCreateBoard(boardContent){
    let duration = 1000;

    let board =$("<div>").addClass("board border border-2 rounded shadow")
                    .html(boardContent);

    let background = $("<div>").addClass("background");

    board.append($('<button>').addClass('close-btn').append(
        $('<i>').addClass('fa-regular fa-circle-xmark')
    ));

    $("body").append(board, background);

    board.fadeIn(duration);
    background.fadeIn(duration);

    background.click(function(){
        removeBoard(duration);
    });

    $('.close-btn').click(function(){
        removeBoard(duration);
    })
}

function removeBoard(duration){
    $('.board').fadeOut(duration, function(){
        $(this).remove();
    });
    $('.background').fadeOut(duration, function(){
        $(this).remove();
    });
}

function createCustomerBoard(){
    $.ajax({
        url: '/customer/getAll',
        method: 'GET', 
        dataType: 'json', 
        success: function(response) { 
            let list = '';

            response.forEach(customer => {
                list += '<button type="button" class="row board-customer" onclick="handlePickCustomer(this)"><div class="col-4 customer-avatar"><i class="fa-solid fa-circle-user"></i></div><div class="col customer-info"><div class="row customer-name">'+ customer.name +'</div><div class="row customer-address">' + customer.address + '</div></div></button>';
            });
        
            let html = '<div class="customer-board"><div class="row"><div class="col-4"><div class="customer-board-list">' + list + '</div></div><div class="col-8"><div class="customer-board-info"><div class="row"><ul class="main-menu"><li class="menu-item m-3"><button type="button" class="add-user-btn" onclick="createMenuAddUser()"><i class="fa-solid fa-user-plus"></i></button></li><li class="menu-item m-3"><button type="button" class="search-user-btn" onclick="handleSearchBar()"><i class="fa-solid fa-magnifying-glass"></i></button></li></ul></div><div class="row menu-content"></div></div></div></div></div></div>';
        
            handleCreateBoard(html);
        },
        error: function(xhr, status, error) { // Xử lý lỗi khi yêu cầu thất bại
            console.error('Lỗi khi gửi yêu cầu đến API /customer/getAll:', error);
        }
    });
}

function handleSearchBar(){
    let searchInput = $("<div>").addClass("search-user-input").append(
        $("<input>")
            .attr({"id": "search-user-input",
                    "placeholder" : "Customer Name"})
            .keyup(searchUser)
    )
    let board = $(".customer-board");

    if(!board.find(".search-user-input").length > 0) {
        board.append(searchInput);
    } else {
        board.find(".search-user-input").remove();
    }
}

function handleMenuContent(menu){
    if($(".menu-content").children().length === 0) {
        $(".menu-content").html(menu);
    } else {
        $(".menu-content").empty().html(menu);
    }
}

function createMenuAddUser(){
    let menu = $("<div>").addClass("menu-add-user").html('<form action="#":action="@{/addCustomer}" method="post"><div class="row"><h1 class="text-center">Form Add Customer</h1></div><div class="row"><label for="name">Name</label><input type="text" name="user" id="name"></div><div class="row"><label for="address">Address</label><input type="text" name="user" id="address"></div><button class="menu-add-user-btn" type="submit">Submit</button></form>');

    handleMenuContent(menu);
}

function searchUser(){
    let searchText = $('.board input#search-user-input').val().toLowerCase();

    $('.customer-board-list button.board-customer').each(function(){
        let itemText = $(this).text().toLowerCase();

        console.log(itemText)
        console.log(searchText)
        if (itemText.indexOf(searchText) === -1) { // Kiểm tra xem mục có chứa từ khóa tìm kiếm không
            $(this).hide(); // Nếu không, ẩn mục
        } else {
            $(this).show(); // Nếu có, hiển thị mục
        }
    });
}

function handlePickCustomer(button){
    let customerName = $(button).find(".customer-info .customer-name").text();
    let customerAddress = $(button).find(".customer-info .customer-address").text();
    let customerInfo = $("<div>").addClass("customer-tag").append(
        $("<div>").addClass("customer-name").text(customerName),
        $("<div>").addClass("customer-address").text(customerAddress)
    );
    $(".customer-tag").remove();
    $("#customer-tag").append(customerInfo);
}
function handlePlaceOrder(){    
    let listProd = []

    $("#ordertable .product-item").each(function(){
        newProd = {
            id : $(this).find(".order-id").val(),
            amount: $(this).find(".order-quantity").val()
        }

        listProd.push(newProd);
    })

    let orderRequest = {
        customer: {
            name: $(".customer-tag > .customer-name").text(),
            address: $(".customer-tag > .customer-address").text()
        },
        orderDTO : listProd
    }

    $.ajax({
        url: "/submitOrder",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(orderRequest),
        success: function(response) {
            console.log("Submit order thành công" + response);
        },
        error: function(xhr, status, error) {
            console.error("Phản hồi từ máy chủ: " + xhr.responseText);
        }
    });
}

// function handlePlaceOrder(){
//     let listItems = []
//     let subTotal;
//     let disCount;
//     let total;
//     let customer = {
//         name : "Guest",
//         address : "none"
//     };

//     $("#ordertable > .product-item").each(function(){
//         listItems.push({
//             orderName: $(this).find(".order-name").text(),
//             orderQuantity: $(this).find(".order-quantity").val()
//         })
//     });

//     $(".order-detail").map(function(){
//         subTotal = parseFloat($(this).find(".product-sub-total-value p").text().replace(" ₫", "").replace(",", "."));
//         disCount = parseFloat($(this).find(".product-discount-value p").text().replace(" ₫", "").replace(",", "."));
//         total = parseFloat($(this).find("#total-price-value").text().replace(" ₫", "").replace(",", "."));

//         if(!$(this).find(".customer-tag").length === 0) {
//             customer.name =  $(this).find(".customer-tag .customer-name").text();
//             customer.address = $(this).find(".customer-tag .customer-address").text();
//         }
//     })

//     let order = {
//         "customer": customer,
//         "totalPrice": total,
//         "subTotal": subTotal,
//         "disCount": disCount,
//         "productDTOList": listItems
//     }

//     $.ajax({
//         url: "/submitOrder",
//         type: "POST",
//         contentType: "application/json",
//         data: JSON.stringify(order),
//         success: function(response) {
//             console.log("Đơn hàng đã được lưu thành công!" + response);
//         },
//         error: function(xhr, status, error) {
//             console.error("Phản hồi từ máy chủ: " + xhr.responseText);
//         }
//     });
// }