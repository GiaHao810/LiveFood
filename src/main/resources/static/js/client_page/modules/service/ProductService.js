import * as productAPI from '../API_Management/Product_API.js'
import * as UIController from '../UI_Management/Controller_UI.js'
import * as logger from '../../../utilities/Logger.js'

export function loadProductSection(category){
    productAPI.loadProduct(category)
    .then(response => {

        logger.logInfo("Load Product API called", { 
            status: response.status,
            message: response.message,
            data: response.data
        });
        UIController.renderMainSection("VEGETABLE", response.data);
    })
    .catch(error => {
        logger.logError("Error Load User API", { 
            status: error.status,
            message: error.message,
            data: error.data
        });
    });
}