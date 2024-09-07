export function createFormAddProduct(){
    let content = `
    <span class="close-button align-self-end">×</span>
            <h2>Add Product</h2>
            <div id="add-product-form">
                <div class="row m-1">
                    <div class="col"><label for="product_name">Name:</label></div>
                    <div class="col"><input id="product_name" name="product_name" required="" autofocus=""></div>
                </div>
                <div class="row m-1">
                    <div class="col"><label for="price">Price:</label></div>
                    <div class="col">
                        <input type="number" id="price" name="price" required="">
                    </div>
                </div>
                <div class="row m-1">
                    <div class="col"><label for="unit">Unit:</label></div>
                    <div class="col">
                        <select name="unit" id="unit">
                            <option value="gram">gram</option>
                            <option value="kilogram">kilogram</option>
                            <option value="box">box</option>
                        </select>
                    </div>
                </div>
                <div class="row m-1">
                    <div class="col"><label for="category">Category:</label></div>
                    <div class="col">
                        <select name="category" id="category">
                            <option value="fruit">fruit</option>
                            <option value="vegetable">vegetable</option>
                        </select>
                    </div>
                </div>
                <div class="row m-1">
                    <div class="col"><button type="submit">Submit</button></div>
                </div>
            </div>`;

    return $("<form>").addClass("modal-content p-4 rounded shadow").html(content);
}

export function createEditProductSection(id, code, name, price, unit, category){
    return `
    <tr class="edit-mode">
        <td><input value="${id}" disabled="disabled"></td>
        <td><input value="${code}" required></td>
        <td><input value="${name}" required></td>
        <td><input value="${price}" required></td>
        <td>
            <select name="unit" id="unit">
                <option value="gram" ${unit === 'gram' ? 'selected' : ''}>gram</option>
                <option value="kilogram" ${unit === 'kilogram' ? 'selected' : ''}>kilogram</option>
                <option value="box" ${unit === 'box' ? 'selected' : ''}>box</option>
            </select>
        </td>
        <td>
            <select name="category" id="category">
                <option value="fruit" ${category === 'FRUIT' ? 'selected' : ''}>fruit</option>
                <option value="vegetable" ${category === 'VEGETABLE' ? 'selected' : ''}>vegetable</option>
            </select>
        </td>
        <td><span><button type="button" id="submit-btn"><i class="fa-solid fa-check"></i></button></span><span><button type="button" id="cancel-btn"><i class="fa-solid fa-xmark"></i></button></span></td>
    </tr>`;
}

export function createProductManagement(dataList){
    let content = '';

    dataList.forEach(data => {
        content += `
        <tr class="product-info">
            <td><span>${data.id}</span></td>
            <td><span>${data.code}</span></td>
            <td><span>${data.name}</span></td>
            <td><span>${data.price}</span></td>
            <td><span>${data.unit}</span></td>
            <td><span>${data.category}</span></td>
        </tr>`
    });

    return `
        <div class="container">
            <div class="row">
                <div class="col-4">
                    <div class="col-3">Side Bar</div>
                </div>
                <div class="col-8">
                    <div class="row toolbar d-flex">
                        <div class="col d-flex">
                            <div class="header-filter-search d-flex justify-content-center">
                                <div class="filter-search">
                                    <div class="search-icon-filter-search">
                                        <i class="fa-solid fa-magnifying-glass"></i>
                                    </div>

                                    <div class="caret-icon-filter-search">
                                        <i class="fa-solid fa-caret-down"></i>
                                    </div>

                                    <input placeholder="Search by Product name" type="search" name="search_by_product_name" class="input-filter-search">

                                    <div id="dropdownSearch" class="shadow bg-body rounded p-3">
                                        <div class="form-search">
                                            <input placeholder="Search by Username" type="search" name="search_by_product_name">
                                            <input placeholder="Search by ID" type="search" name="search_by_id">
                                            <input placeholder="Search by Mail" type="search" name="search_by_mail">  
                                        </div>
                                        <div class="form-footer d-flex justify-content-end">
                                            <button type="button" name="form-search-button"'>Search</button>
                                        </div>                              
                                    </div>  
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="header-filter-button d-flex justify-content-end">
                                <button type="button" id="add-product" onclick="handleToolBar(this)"><i class="fa-solid fa-plus"></i></button> 
                                <button type="button" id="del-product" onclick="handleToolBar(this)"><i class="fa-solid fa-minus"></i></button> 
                                <button type="button" id="edit-product" onclick="handleToolBar(this)"><i class="fa-solid fa-gear"></i></button> 
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <table id="product-table">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Unit</th>
                                <th>Category</th>
                            </tr>
                            </thead>
                            <tbody> ${content}</tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>`;
}