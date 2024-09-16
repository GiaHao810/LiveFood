import * as userManagement from '../API_Management/UserAPI.js'
import * as logger from '../../../utilities/Logger.js'
import * as UIController from '../UI_Management/UIController.js'

export function loadUser(){
    userManagement.loadUserManagement()
    .then(response => {

        logger.logInfo("Load User API called", { 
            message: response.message,
            status: response.status, 
            data: response.data 
        });

        UIController.renderUserManagement(response.data);

        UIController.renderNotificationBox("success", "Loading User Infomation");
    })
    .catch(error => {
        logger.logError("Error Load User API", { 
            status: error.status,
            message: error.message,
            data: error.data
        });

        UIController.renderNotificationBox("warn", `${error.message}`)
    });
}

export function addUser(mail, username, password){
    userManagement.addUser(mail, username, password)
    .then(response => {
        logger.logInfo("Add User API called", { 
            message: response.message, 
            status: response.status, 
            data: response.data 
        });

        UIController.renderNotificationBox("info", `${response.message}`);

        setTimeout(() => {
            UIController.removeForm(0);
            loadUser();
        }, 1000);
    })
    .catch(error => {
        logger.logError("Error Add User API", { 
            status: error.status,  
            message: error.message,
            data: error.data 
        });

        UIController.renderNotificationBox("warn", `${error.message}`);
    });
}

export function deleteUser(id){
    userManagement.deleteUser(id)
        .then(response => {
            loadUser();

            logger.logInfo("Delete User API called", { 
                message: response.message,
                status: response.status, 
                data: response.data 
            });

            UIController.renderNotificationBox("success", `${response.message}`);
        })
        .catch(error => {
            logger.logError("Error Delete User API", { 
                status: error.status,
                message: error.message,
                data: error.data
            });

            UIController.renderNotificationBox("warn", `${error.message}`);
        })
}

export function updateUserWithID(id, updateRequest){
    userManagement.updateUserWithID(id, updateRequest)
            .then(response => {
                loadUser();
    
                logger.logInfo("Update User API called", { 
                    message: response.message,
                    status: response.status, 
                    data: response.data 
                });
    
                UIController.renderNotificationBox("success", `${response.message}`)
            })
            .catch(error => {
                logger.logError("Error Update User API", { 
                    status: error.status,
                    message: error.message,
                    data: error.data
                });
    
                UIController.renderNotificationBox("error", `${error.message}`)
            })
}