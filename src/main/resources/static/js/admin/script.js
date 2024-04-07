function handleAddImage(imageInput) {
    var prod = $(imageInput).closest('li');

    if(!isProdInOrder(prod)) addNewProdToOrder(prod);
}

function addNewProdToOrder(prod){
    var prodPrice = prod.find('.product-price').val();
    var prodURL = prod.find('.product-image').attr("src");
    var prodName = prod.find('.product-name').text();

    var newProd = $('<li>').addClass('product-item row p-2').append(
        $('<div>').addClass('col border border-3 rounded').append(
            $('<label>').addClass('col-md order-name').text(prodName)
        ),
        $('<div>').addClass('col').append(
            $('<input>').addClass('col-md order-quantity border border-3 rounded h-100')
            .attr('type', 'number').val(1)
        ),
        $('<div>').addClass('col').append(
            $('<button>').addClass('col-md delete-button').text('Delete').click(
                function(){
                    // delProd
                }
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