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

export function addProduct(name, price, category){
    productManagement.addProduct(name, price, category)
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

export function deleteProduct(id){
    productManagement.deleteProduct(id)
        .then(response => {
            loadProduct();

            logger.logInfo("Delete Product API called", { 
                message: response.message,
                status: response.status, 
                data: response.data 
            });

            UIController.renderNotificationBox("success", `${response.message}`);
        })
        .catch(error => {
            logger.logError("Error Delete Product API", { 
                status: error.status,
                message: error.message,
                data: error.data
            });

            UIController.renderNotificationBox("warn", `${error.message}`);
        })
}

export function updateProductWithID(id, updateRequest){
    productManagement.updateProductWithID(id, updateRequest)
            .then(response => {
                loadProduct();
    
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