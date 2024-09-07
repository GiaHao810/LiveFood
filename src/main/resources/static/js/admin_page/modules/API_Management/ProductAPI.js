export function loadProductManagement(){
    return new Promise ((resolve, reject) => {
        $.ajax({
            url: '/api/product/',
            type: 'GET',
            success: function(response){
                resolve(response);
            },
            error: function(xhr) {
                const response = xhr.responseJSON || {
                    message: `Something went wrong. Please contact the Developers`,
                    status: null,
                    data: null
                };
                reject({
                    status: xhr.status || `Unknown`,
                    message: response.message,
                    data: response.data
                });
            }
        });
    }) 
}

export function deleteProduct(id){
    return new Promise ((resolve, reject) => {
        $.ajax({
            url: `/api/product/${id}`,
            type: 'DELETE',
            success: function(response){
                resolve(response);
            },
            error: function(xhr) {
                const response = xhr.responseJSON || {
                    message: `Something went wrong. Please contact the Developers`,
                    status: null,
                    data: null
                };
                reject({
                    status: xhr.status || `Unknown`,
                    message: response.message,
                    data: response.data
                });
            }
        })
    })
}

export function addProduct(name, price, unit, category){
    return new Promise ((resolve, reject) => {
        $.ajax({
            url: `/api/product/add`,
            data: JSON.stringify({
                name: name,
                price: price,
                unit: unit,
                category: category
            }),
            contentType: 'application/json; charset=UTF-8; ',
            type: 'POST',
            success: function(response){
                resolve(response);
            },
            error: function(xhr) {
                const response = xhr.responseJSON || {
                    message: `Something went wrong. Please contact the Developers`,
                    status: null,
                    data: null
                };
                reject({
                    status: xhr.status || `Unknown`,
                    message: response.message,
                    data: response.data
                });
            }
        })
    })
}

export function updateProductWithID(id, updateRequest){
    return new Promise ((resolve, reject) => {
        $.ajax({
            url: `/api/product/update/${id}`,
            data: JSON.stringify(updateRequest),
            contentType: 'application/json; charset=UTF-8; ',
            type: 'PUT',
            success: function(response){
                resolve(response);
            },
            error: function(xhr) {
                const response = xhr.responseJSON || {
                    message: `Something went wrong. Please contact the Developers`,
                    status: null,
                    data: null
                };
                reject({
                    status: xhr.status || `Unknown`,
                    message: response.message,
                    data: response.data
                });
            }
        })
    })
}