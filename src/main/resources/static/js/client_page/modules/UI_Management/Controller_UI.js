import * as productUI from './Product_UI.js'

export function renderMainSection(headerText, data) {
    $('#main').html(
        productUI.createProductUI(
            productUI.createProductSection1(headerText, data)
        )
    );
    // Add Effect
}