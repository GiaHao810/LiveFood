export function loadProductManagement(){
    $.ajax({
        url: '/api/product/',
        type: 'GET',
        success: function(response) {
            console.log(response);
            let content = '';
            let counter = 1;
            response.data.forEach(data => {
                content += `<tr><td><input type="checkbox"class="manage-checkbox"data-id="${counter}"></td><td><span>${data.id}</span></td><td><span>${data.code}</span></td><td><span>${data.name}</span></td><td><span>${data.price}</span></td><td><span>${data.unit}</span></td><td><span>${data.category}</span></td></tr>`
                counter++;
            });
            $('#main').html(`<div class="container"><div class="row top"><div class="col toolbar d-flex justify-content-end"><button type="button"id="add-product"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-plus"aria-hidden="true"></i></button> <button type="button"id="del-product"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-minus"aria-hidden="true"></i></button> <button type="button"id="edit-product"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-gear"aria-hidden="true"></i></button> <button type="button"id="search-product"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-magnifying-glass"aria-hidden="true"></i></button></div></div><div class="row bot"><div class="col-3">Side Bar</div><div class="col-9"><table><thead><tr><th></th><th>ID</th><th>Code</th><th>Name</th><th>Price</th><th>Unit</th><th>Category</th></tr></thead><tbody>${content}</tbody></table></div></div></div>`);
        },
        error: function(xhr) {
            console.error('Error loading Product management UI: ' + xhr.statusText);
        }
    });
}

export function addProduct(){
    $.ajax({
        url: `/api/product/add`,
        data: JSON.stringify({
            name: $("input#name").val(),
            price: $("input#price").val(),
            unit: $("select#unit").val(),
            category: $("select#category").val()
        }),
        contentType: 'application/json; charset=UTF-8; ',
        type: 'POST',
        success: function(response){
            location.reload();
            console.log(response);
        },
        error: function(xhr, message) {
            console.error("Status: " + xhr.statusText)
            console.error("Message: " + message)
        }
    })
}

export function deleteProduct(){
    
}