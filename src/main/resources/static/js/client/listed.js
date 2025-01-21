$(document).ready(function() {
    
});

function loginForm(){
    
}

function toggleCart() {
    let cart = $('.cart-wrapper');
    
    // $.ajax({
    //     url:`/api/shoppingcart/user`,
    //     type: 'GET',
    //     success: function(response){
    //         console.log(response);
    //     },
    //     error: function(xhr) {
    //         console.error(xhr);
    //     }
    // });

    cart.toggle();
}

let cart = []; // Mảng lưu trữ giỏ hàng

// Hàm cập nhật giỏ hàng
function updateCart() {
    $('#cartItems').empty();
    let total = 0;

    cart.forEach(item => {
        total += item.price;

        const cartItem = `
            <div class="cart-item">
                <p>${item.name}</p>
                <p>Giá: ${item.price} đ</p>
            </div>
        `;
        $('#cartItems').append(cartItem);
    });

    $('#cartTotal').text(total);
}

function addToCart(btn){
    const product = $(btn).closest("product-card");

}

function preloadProducts() {
    $.ajax({
        url:`/api/product/`,
        type: 'GET',
        success: function(response){
            if(response.status) {
                $('.product-list').empty();

                response.data.forEach(product => {
                    const item = `
                        <div class="col-md-4 mb-4">
                            <div class="product-card">
                                <img src="https://via.placeholder.com/150" alt="Product" class="img-fluid">
                                <h5 class="mt-2">${product.name}</h5>
                                <p class="product-price">${product.price} đ</p>
                                <button class="btn btn-primary btn-sm" onclick="updateCart() data-name="${product.name}" data-price="${product.price}">Thêm vào giỏ</button>
                            </div>
                        </div>`
                    $('.product-list').append(item);

                    $('.addToCart').on('click', function () {
                        const name = $(this).data('name');
                        const price = $(this).data('price');

                        // Thêm sản phẩm vào giỏ hàng
                        cart.push({ name, price });
                        updateCart(); // Cập nhật giỏ hàng
                    });
                })
            } else {
                alert("Can not load product!!!")
            }
        },
        error: function(xhr) {
            const response = xhr.responseJSON || {
                message: `Something went wrong. Please contact the Developers`,
                status: null,
                data: null
            };
            console.log({
                status: xhr.status || `Unknown`,
                message: response.message,
                data: response.data
            });
        }
    });
}