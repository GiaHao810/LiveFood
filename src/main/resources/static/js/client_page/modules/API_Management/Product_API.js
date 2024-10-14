export function getProducts(category){
    return new Promise ((resolve, reject) => {
        $.ajax({
            url:`/api/media/getMediaWithPSC?category=${category}`,
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