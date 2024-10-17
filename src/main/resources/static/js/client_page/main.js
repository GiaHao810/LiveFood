import * as productService from './modules/service/ProductService.js'
import * as prodAPI from './modules/API_Management/Product_API.js';
import * as logger from '../utilities/Logger.js'

$(document).ready(function(){
    prodAPI.getProducts("VEGETABLE")
    .then(response => {
        let text = ``;

        logger.logInfo("Load Product API called", { 
            status: response.status,
            message: response.message,
            data: response.data
        })

        response.data.forEach(data => {
            text += `
                <div class="col-lg-8 col-sm-2 product-item flex-column m-1 border border-2 rounded shadow-sm d-flex justify-content-between">
                    <a href="#" title="${data.product.name}">
                        <div class="item-image">
                            <img src="${data.url}" alt="Temp" style="color: transparent;width: 100%;height: auto;">
                        </div>
                    </a>
                    <div class="item-name mb-3">${data.product.name}</div>
                    <div class="item-price text-start">${data.product.price}<span id="currency"> ₫</span></div>
                    <button type="button" class="add-cart fw-bold fs-5">Add To Cart</button>
                </div>`
        });
        $("div#VEGETABLE").next("div").append(text);
    })
    .catch(error => {
        logger.logError("Error Load User API", { 
            message: "Error",
            data: error
        });
    })
    prodAPI.getProducts("FRUIT")
    .then(response => {
        let text = ``;

        logger.logInfo("Load Product API called", { 
            status: response.status,
            message: response.message,
            data: response.data
        })

        response.data.forEach(data => {
            text += `
                <div class="col-lg-8 col-sm-2 product-item flex-column m-1 border border-2 rounded shadow-sm d-flex justify-content-between">
                    <a href="#" title="${data.product.name}">
                        <div class="item-image">
                            <img src="${data.url}" alt="Temp" style="color: transparent;width: 100%;height: auto;">
                        </div>
                    </a>
                    <div class="item-name mb-3">${data.product.name}</div>
                    <div class="item-price text-start">${data.product.price}<span id="currency"> ₫</span></div>
                    <button type="button" class="add-cart fw-bold fs-5">Add To Cart</button>
                </div>`
        });
        $("div#FRUIT").next("div").append(text);
    })
    .catch(error => {
        logger.logError("Error Load User API", { 
            message: "Error",
            data: error
        });
    })
    prodAPI.getProducts("OTHER")
    .then(response => {
        let text = ``;

        logger.logInfo("Load Product API called", { 
            status: response.status,
            message: response.message,
            data: response.data
        })

        response.data.forEach(data => {
            text += `
                <div class="col-lg-8 col-sm-2 product-item flex-column m-1 border border-2 rounded shadow-sm d-flex justify-content-between">
                    <a href="#" title="${data.product.name}">
                        <div class="item-image">
                            <img src="${data.url}" alt="Temp" style="color: transparent;width: 100%;height: auto;">
                        </div>
                    </a>
                    <div class="item-name mb-3">${data.product.name}</div>
                    <div class="item-price text-start">${data.product.price}<span id="currency"> ₫</span></div>
                    <button type="button" class="add-cart fw-bold fs-5">Add To Cart</button>
                </div>`
        });
        $("div#OTHER").next("div").append(text);
    })
    .catch(error => {
        logger.logError("Error Load User API", { 
            message: "Error",
            data: error
        });
    })
})