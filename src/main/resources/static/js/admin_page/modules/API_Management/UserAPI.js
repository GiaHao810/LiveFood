export function loadUserManagement(){
    return new Promise ((resolve, reject) => {
        $.ajax({
            url: '/api/user/',
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

export function deleteUser(id){
    return new Promise ((resolve, reject) => {
        $.ajax({
            url: `/api/user/${id}`,
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

export function addUser(mail, username, password){
    return new Promise ((resolve, reject) => {
        $.ajax({
            url: `/api/user/register`,
            data: JSON.stringify({
                mail: mail,
                username: username,
                password: password
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

export function updateUserWithID(id, updateRequest){
    return new Promise ((resolve, reject) => {
        $.ajax({
            url: `/api/user/updateWithNameAndMail/${id}`,
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