function handleAddImage(imageInput) {
    var prod = $(imageInput).closest('li');

    if(!isProdInOrder(prod)) 
        addNewProdToOrder(prod);
        handleTotalChange();
}

function addNewProdToOrder(prod){
    var prodPrice = prod.find('input.product-price').val();
    var prodURL = prod.find('.product-image').attr("src");
    var prodName = prod.find('.product-name').text();

    var newProd = $('<li>')
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
        $('<input>').addClass('order-price').attr('type', 'hidden').val(prodPrice)
    );

    $('#ordertable').append(newProd);
}   

function isProdInOrder(prod){
    var prodItems = $('#ordertable').find('li.product-item');
    var isFound = false;

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
    var prodItems = $('#ordertable').find('li.product-item');
    var total = 0.0;
    var curTotal = 0.0;

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
    var tax = $('.product-sub-total-value p').text();

    var numb = parseFloat(tax) / 10;

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
    var subTotal = handleSubTotalChange();
    var tax = handleTaxChange();
    var discount = handleDiscountChange();

    var total = (subTotal + tax) - discount;

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
    var duration = 1000;

    var board =$("<div>").addClass("board border border-2 rounded shadow")
                    .html(boardContent);

    var background = $("<div>").addClass("background");

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
            var list = '';

            response.forEach(customer => {
                list += '<button type="button" class="row board-customer" onclick="handlePickCustomer(this)"><div class="col-4 customer-avatar"><i class="fa-solid fa-circle-user"></i></div><div class="col customer-info"><div class="row customer-name">'+ customer.name +'</div><div class="row customer-address">' + customer.address + '</div></div></button>';
            });
        
            var html = '<div class="customer-board"><div class="row"><div class="col-4"><div class="customer-board-list">' + list + '</div></div><div class="col-8"><div class="customer-board-info"><div class="row"><ul class="main-menu"><li class="menu-item m-3"><button type="button" class="add-user-btn" onclick="createMenuAddUser()"><i class="fa-solid fa-user-plus"></i></button></li><li class="menu-item m-3"><button type="button" class="search-user-btn"><i class="fa-solid fa-magnifying-glass"></i></button></li></ul></div><div class="row menu-content"></div></div></div></div></div></div>';
        
            handleCreateBoard(html);
        },
        error: function(xhr, status, error) { // Xử lý lỗi khi yêu cầu thất bại
            console.error('Lỗi khi gửi yêu cầu đến API /customer/getAll:', error);
        }
    });
}

function handleMenuContent(menu){
    if($(".menu-content").children().length === 0) {
        $(".menu-content").html(menu);
    } else {
        $(".menu-content").empty().html(menu);
    }
}

function createMenuAddUser(){
    var menu = $("<div>").addClass("menu-add-user").html('<form action="#":action="@{/addCustomer}" method="post"><div class="row"><h1 class="text-center">Form Add Customer</h1></div><div class="row"><label for="name">Name</label><input type="text" name="user" id="name"></div><div class="row"><label for="address">Address</label><input type="text" name="user" id="address"></div><button class="menu-add-user-btn" type="submit">Submit</button></form>');

    handleMenuContent(menu);
}

function search(){
    var searchText = $('.board input#search-user-input').val().toLowerCase();

    $('.customer-board-list button.board-customer').each(function(){
        var itemText = $(this).text().toLowerCase();

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
    var customerName = $(button).find(".customer-info .customer-name").text();
    var customerAddress = $(button).find(".customer-info .customer-address").text();
    var customerInfo = $("<div>").addClass("customer-tag").append(
        $("<div>").addClass("customer-name").text(customerName),
        $("<div>").addClass("customer-address").text(customerAddress)
    );
    $(".customer-tag").remove();
    $("#customer-tag").append(customerInfo);
}