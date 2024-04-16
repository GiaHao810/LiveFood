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
        .addClass('col border border-3 rounded')
        .append(
            $('<label>')
                .addClass('col-md order-name')
                .text(prodName)
        ),
        $('<div>')
            .addClass('col')
            .append(
                $('<input>')
                    .addClass('col-md order-quantity border border-3 rounded h-100')
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

function handleCreateBoard(){
    var duration = 1000;

    var board = $("<div>").addClass("board");

    var background = $("<div>").addClass("background");

    $("body").append(board, background);

    board.fadeIn(duration);
    background.fadeIn(duration);

    background.click(function(){
        removeBoard(duration);
    });
}

function removeBoard(duration){
    $('.board').fadeOut(duration, function(){
        $(this).remove();
    });
    $('.background').fadeOut(duration, function(){
        $(this).remove();
    });
}