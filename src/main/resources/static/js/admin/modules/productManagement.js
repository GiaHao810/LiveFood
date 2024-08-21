export function loadProductManagement(){
    $.ajax({
        url: '/api/product/',
        type: 'GET',
        success: function(response) {
            console.log(response);
            let content = '';
            let counter = 1;
            response.data.forEach(data => {
                content += `<tr><td><input type="checkbox"class="manage-checkbox"data-id="${counter}"></td><td>${data.id}</td><td>${data.code}</td><td>${data.name}</td><td>${data.price}</td><td>${data.unit}</td><td>${data.category}</td></tr>`
                counter++;
            });
            $('#main').html('<div class="container"><div class="top row"><div class="col toolbar d-flex justify-content-end"><button type="button"id="add-product"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-plus"aria-hidden="true"></i></button> <button type="button"id="del-product"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-minus"aria-hidden="true"></i></button> <button type="button"id="edit-product"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-gear"aria-hidden="true"></i></button> <button type="button"id="search-product"onclick="handleToolBarBtn(this)"><i class="fa-solid fa-magnifying-glass"aria-hidden="true"></i></button></div></div><div class="row bot"><div class="col-3">Side Bar</div><div class="col-9"><table><thead><tr><th></th><th>ID</th><th>Code</th><th>Name</th><th>Price</th><th>Unit</th><th>Category</th></tr></thead><tbody>' + content + '</tbody></table></div></div></div>');
        },
        error: function(xhr) {
            console.error('Error loading Product management UI: ' + xhr.statusText);
        }
    });
}

export function createFormAddProduct(){
    let content = '<span class="close-button align-self-end">×</span><h2>Add Product</h2><form id="add-product-form"><div class="row m-1"><div class="col"><label for="name">Name:</label></div><div class="col"><input id="name"name="name"required></div></div><div class="row m-1"><div class="col"><label for="price">Price:</label></div><div class="col"><input type="number"id="price"name="price"required></div></div><div class="row m-1"><div class="col"><label for="unit">Unit:</label></div><div class="col"><select name="unit"id="unit"><option value="gram">Gram</option><option value="kilogram">Kilogram</option><option value="miligram">Miligram</option><option value="liter">Liter</option><option value="milliliter">Milliliter</option><option value="package">Package</option></select></div></div><div class="row m-1"><div class="col"><label for="category">Category:</label></div><div class="col"><select name="category"id="category"><option value="FRUIT">FRUIT</option><option value="VEGETABLE">VEGETABLE</option><option value="OTHER">OTHER</option></select></div></div><div class="row m-1"><div class="col"><button type="submit">Submit</button></div></div></form>';

    let form = $("<div>").addClass("modal-content p-4 rounded shadow").html(content);
    return form;
}

export function deleteProduct(){
    
}