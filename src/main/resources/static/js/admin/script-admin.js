function handleContent(item){
    console.log($(item).children("span").text());
    if($(item).children("span").text() === "Invoice") getOrders();
    else if($(item).children("span").text() === "Sale") getSales();
}

function getOrders(){
    $.ajax({
        url: "/getOrders",
        type: "GET",
        contentType: "application/json",
        error: function(xhr, status, error) {
            console.error("Phản hồi từ máy chủ: " + xhr.responseText);
        }
    }).done(function(data){
        createInvoice(data);
    })
}

function createInvoice(orders){
    let content = '';

    orders.forEach(order => {
        let card = '<div class="invoice-card"><div class="header"><div class="customer-id"><span>' + order.id + '</span></div><div class="customer"><span class="name">' + order.customer.name + '</span> <span>| </span><span class="address">' + order.customer.address + '</span></div></div><div class="body"><div class="subtotal">Sub Total: <span>' + order.subTotal + '</span></div><div class="discount">Discount: <span>' + order.disCount + '</span></div><div class="total">Total: <span>' + order.totalPrice + '</span></div></div><div class="footer"><button type="button">View Details</button></div></div>';

        content += card;
    });

    let container = '<div class="content-container">' + content + '</div>';

    if($("#content").html().length === 0) {
        $("#content").append(container);
    } else {
        $("#content").empty().append(container);
    }
}

//Thong ke theo ngay
function getSaleData(){
    $.ajax({
        url: "/getOrders",
        type: "GET",
        contentType: "application/json",
        error: function(xhr, status, error) {
            console.error("Phản hồi từ máy chủ: " + xhr.responseText);
        }
    }).done(function(data){
        getData("day", data)
    })
}

function getData(time, orders){
    let label = []
    let data = []

    orders.forEach(order => {
        let orderTime = seperateTime(order.orderDate);
        label.push(orderTime[0]);
        data.push(order.totalPrice);
    })

    const ctx = $('#myChart').get(0).getContext('2d');

    const myLineChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: label,
            datasets: [
                {
                    label: 'Daily Revenue',
                    data: data,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }                
            ]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function seperateTime(time){
    let day = time.slice(0, 2);
    let month = time.slice(2, 4);
    let year = time.slice(4);

    return [day, month, year];
}