export function createProductUI(section){
    return `
        <div class="row">
            <div class="col-4">
                Danh muc san pham
            </div>

            <div class="col-8">
                <div class="row product-section">
                    ${section}
                </div>
            </div>
        </div>`
}

export function createProductSection1(hText, datas){
    let pContent = ``;

    datas.forEach(data => {
        pContent += `
            <div class="col-lg-8 col-sm-2 product-item flex-column m-1 border border-2 rounded shadow-sm d-flex justify-content-between">
                <a href="#" title="${data.name}">
                    <div class="item-image">
                        <img src="" alt="Temp" style="color: transparent;width: 100%;height: auto;">
                    </div>
                </a>
                <div class="item-name mb-3">${data.name}</div>
                <div class="item-price text-start">${data.price}<span id="currency"> ₫</span></div>
                <button type="button" class="add-cart fw-bold fs-5">Add To Cart</button>
            </div>`
    });

    return `
    <div class="row product-header justify-content-center">
            <div id="header" class="border rounded-pill text-center text-uppercase fs-4 fw-bold">VEGETABLE</div>
        </div>
        <div class="row product-body justify-content-evenly pt-4 m-1 flex-wrap">
            ${pContent}
        </div>
    `;
}