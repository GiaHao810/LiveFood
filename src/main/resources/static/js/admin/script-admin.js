const { Callbacks } = require("jquery");

function handleContent(){
    $.ajax({
        url: "/getOrders",
        type: "GET",
        contentType: "application/json",
        success: function(response) {
            createContent(response);
        },
        error: function(xhr, status, error) {
            console.error("Phản hồi từ máy chủ: " + xhr.responseText);
        }
    })
}

function createContent(orders){
    let content = '';

    orders.forEach(order => {
        let card = '<div class="invoice-card"><div class="header"><div class="customer-id"><span>' + order.id + '</span></div><div class="customer"><span class="name">' + order.customer.name + '</span> <span>| </span><span class="address">' + order.customer.address + '</span></div></div><div class="body"><div class="subtotal">Sub Total: <span>' + order.subTotal + '</span></div><div class="discount">Discount: <span>' + order.disCount + '</span></div><div class="total">Total: <span>' + order.totalPrice + '</span></div></div><div class="footer"><button type="button">View Details</button></div></div>';

        content += card;
    });

    let container = '<div class="content-container">' + content + '</div>';

    $("#content").append(container);
}