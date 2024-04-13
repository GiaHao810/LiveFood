function handleAddImage(imageInput) {
    var prod = $(imageInput).closest('li');

    if(!isProdInOrder(prod)) 
        addNewProdToOrder(prod);
        handleTotalPriceChange();
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
                    .change(handleTotalPriceChange)
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
    handleTotalPriceChange();
}

function delProdFromOrder(button){
    $(button).closest('.product-item').remove();
}

function handleTotalPriceChange(){
    var prodItems = $('#ordertable').find('li.product-item');
    var total = 0.0;
    var curTotal = 0.0;

    if(prodItems.length === 0) {
        $('#total-price-value').text('0 đ');
        return;
    }
    
    prodItems.each(function(){
        curTotal = $(this).find('.order-price').val()
         * $(this).find('.order-quantity').val();

        total += curTotal;
    });

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