import * as productService from './modules/service/ProductService.js'

$(document).ready(function(){
    productService.loadProductSection();
})