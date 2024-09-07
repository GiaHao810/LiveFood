import * as productManagement from '../API_Management/ProductAPI.js'
import * as logger from '../utilities/Logger.js'
import * as UIController from '../UI_Management/UIController.js'

export function loadProduct(){
    productManagement.loadProductManagement()
    .then(response => {

        logger.logInfo("Load Product API called", { 
            message: response.message,
            status: response.status, 
            data: response.data 
        });

        UIController.renderProductManagement(response.data);

        UIController.renderNotificationBox("success", "Loading Product Infomation");
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

export function addProduct(name, price, unit, category){
    productManagement.addProduct(name, price, unit, category)
    .then(response => {
        logger.logInfo("Add Product API called", { 
            message: response.message, 
            status: response.status, 
            data: response.data 
        });

        UIController.renderNotificationBox("info", `${response.message}`);

        setTimeout(() => {
            UIController.removeForm(0);
            loadProduct();
        }, 1000);
    })
    .catch(error => {
        logger.logError("Error Add Product API", { 
            status: error.status,  
            message: error.message,
            data: error.data 
        });

        UIController.renderNotificationBox("warn", `${error.message}`);
    });
}

// export function deleteUser(id){
//     userManagement.deleteUser(id)
//         .then(response => {
//             loadUser();

//             logger.logInfo("Delete User API called", { 
//                 message: response.message,
//                 status: response.status, 
//                 data: response.data 
//             });

//             UIController.renderNotificationBox("success", `${response.message}`);
//         })
//         .catch(error => {
//             logger.logError("Error Delete User API", { 
//                 status: error.status,
//                 message: error.message,
//                 data: error.data
//             });

//             UIController.renderNotificationBox("warn", `${error.message}`);
//         })
// }

// export function updateUserWithID(id, updateRequest){
//     userManagement.updateUserWithID(id, updateRequest)
//             .then(response => {
//                 loadUser();
    
//                 logger.logInfo("Update User API called", { 
//                     message: response.message,
//                     status: response.status, 
//                     data: response.data 
//                 });
    
//                 UIController.renderNotificationBox("succeess", `${response.message}`)
//             })
//             .catch(error => {
//                 logger.logError("Error Update User API", { 
//                     status: error.status,
//                     message: error.message,
//                     data: error.data
//                 });
    
//                 UIController.renderNotificationBox("error", `${error.message}`)
//             })
// }