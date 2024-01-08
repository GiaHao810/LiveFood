function getImageLabel(imageInput) {
    // Lấy đối tượng label tương ứng với input image
    var listItems = imageInput.closest('li');
    var imageURL = imageInput.src;
    var itemPrice = listItems.querySelector('.product-price').value;
    var labelValue = listItems.querySelector('.product-name').innerText;

//  Kiểm tra sản phẩm đã có trên Order Table
    var itemNames = getValues();
    var isTrue = false;

    itemNames.forEach(function(itemName) {
        if(itemName == labelValue){
            addQuantity(itemName);
            adjustTotalPrice(itemPrice, 'add');
            isTrue = true;
        }
    });

//  Nếu sản phẩm chưa có thì thêm vào Order Table
    if(!isTrue){
        addNewRow(labelValue, itemPrice, imageURL);
        adjustTotalPrice(itemPrice, 'add');
    }
}

// Hàm để lấy giá trị từ mỗi dòng
function getValues() {
    // Lấy tất cả các phần tử có class "quantityInput"
    var inputElements = document.querySelectorAll('.order-quantity');
    var row, itemName, quantity;
    var itemNames = [];

    // Duyệt qua từng phần tử để lấy giá trị
    inputElements.forEach(function(inputElement) {
        row = inputElement.closest('.row'); // Tìm phần tử gần nhất có class "row"
        itemName = row.querySelector('.order-name').innerText;
        quantity = inputElement.value;

        if(!itemNames.includes(itemName)) {
            itemNames.push(itemName);
        }
    });

    return itemNames;
}

function addQuantity(labelValue){
    var inputElements = document.querySelectorAll('.order-quantity');

    inputElements.forEach(function(inputElement) {
        var row = inputElement.closest('.row'); // Tìm phần tử gần nhất có class "row"
        var itemName = row.querySelector('.order-name').innerText;
        var quantity = parseInt(inputElement.value);

         if(itemName == labelValue){
            quantity += 1;
            inputElement.value = quantity;
         }
    });
}

function addNewRow(labelValue, itemPrice, imageURL){
    var newRow = document.createElement("li");
    newRow.className = "row p-2";
    newRow.innerHTML = `
            <div class="col border border-3 rounded">
                <label class="col-md order-name" >${labelValue}</label>
            </div>
            <div class="col">
                <input class="col-md order-quantity border border-3 rounded h-100" type="number" value="1" min="1" readonly>
            </div>
            <div class="col">
                <button class="col-md delete-button" onclick="delItem(this)">Del</button>
            </div>
            <input class="order-url" type="hidden" value="${imageURL}">
            <input class="order-price" type="hidden" value="${itemPrice}">
    `;
    document.getElementById("ordertable").appendChild(newRow);
    handleInputChange(itemPrice, newRow);
}

function delItem(button){
    var row = button.closest('.row');
    var itemPrice = row.querySelector('.order-price').value;
    var itemQuantity = row.querySelector('.order-quantity').value;
    var tempPrice = itemPrice * itemQuantity;

    if (row) {
        row.parentNode.removeChild(row);
        adjustTotalPrice(tempPrice, 'sub');
    }
}

function submitOrderAPI(){
    var order = {};
    var productDTOList = [];
    var totalPrice = 0.0;
    var listItems = document.querySelectorAll("#ordertable li");
    var customerName = document.querySelector('#customer-name input').value;

// Tạo danh sách sản phẩm
    listItems.forEach(function(item){
        var itemNameElement = item.querySelector('.order-name');
        var itemName = itemNameElement.textContent || itemNameElement.innerText;
        var itemPrice = parseFloat(item.querySelector('.order-price').value);
        var itemQuantity = parseFloat(item.querySelector('.order-quantity').value);

        totalPrice += itemPrice * itemQuantity;

        productDTOList.push({
            name: itemName,
            price: itemPrice,
            quantity: itemQuantity
        });
    });

//  Tạo Order
    order.customerName = customerName;
    order.totalPrice = totalPrice.toFixed(1);
    order.productDTOList = productDTOList;

    axios.post('/submitOrder', order)
        .then(function (response) {
            console.log(response.data);
        })
        .catch(function (error){
            console.error(error);
        })

}

function toggleOrderInformationTable() {
    var orderInformationTable = document.getElementById("orderInformationTable-container");

    if(orderInformationTable.style.display === '') {
        openOrderInfoTable();
    } else {
        closeOrderInfoTable();
    }
}

function openOrderInfoTable(){
    var orderInformationTable = document.getElementById("orderInformationTable-container");

    var ul = document.getElementById('orderInformation');
    var customerTag = document.querySelector('#orderInformationTable .customer-name');
    var customerName = document.querySelector('#customer-name input');

    orderInformationTable.style.display = 'block';

    var order = {};
    var productDTOList = [];
    var totalPrice = 0.0;
    var listItems = document.querySelectorAll("#ordertable li");

    customerTag.innerText = customerName.value;
// Tạo danh sách sản phẩm
    listItems.forEach(function(item){
        var itemNameElement = item.querySelector('.order-name');
        var itemName = itemNameElement.textContent || itemNameElement.innerText;
        var itemPrice = parseFloat(item.querySelector('.order-price').value);
        var itemQuantity = parseFloat(item.querySelector('.order-quantity').value);

        totalPrice += itemPrice * itemQuantity;

        var newRow = document.createElement('li');
        newRow.className = 'row';
        newRow.innerHTML = `
            <div class="col-md">${itemName}</div>
            <div class="col-md">${itemPrice}</div>
            <div class="col-md">${itemQuantity}</div>
        `
        ul.appendChild(newRow);
    });
        totalPrice = totalPrice.toFixed(3);
        totalPrice = totalPrice.toString().replace(/[^\d]/g, '');

        var totalPriceRow = document.createElement('p');
        totalPriceRow.className = 'row';
        totalPriceRow.innerText = `
            Tổng giá tiền: ${formatCurrency(totalPrice)}
        `
        ul.appendChild(totalPriceRow);
}

function closeOrderInfoTable(){
    var orderInformationTable = document.getElementById("orderInformationTable-container");
    var ul = document.getElementById('orderInformation');
    var customerTag = document.querySelector('#orderInformationTable .customer-name');

    orderInformationTable.style.display = '';
    customerTag.innerText = ``;

    ul.innerHTML = `
       <li class="row">
            <div class="col-md">Tên sản phẩm</div>
            <div class="col-md">Giá</div>
            <div class="col-md">Số lượng</div>
        </li>
    `
}

function submitOrder(){
    toggleConfirmBox('Vui lòng kiểm tra kỹ Order và nếu không có gì sai sót có thể ấn [YES] để tiếp tục', submitOrderAPI);
}

function openConfirmBox(message, openEvent){
    var confirm_container = document.querySelector('.confirmbox-container');
    var confirm_content = document.getElementById('confirm-content');
    var t_btn = document.getElementById('t-btn');

    confirm_container.style.display = 'block';

    confirm_content.textContent = message;
    t_btn.addEventListener("click", openEvent);
    t_btn.addEventListener("click", function(){
        confirm_container.style.display = '';
        closeOrderInfoTable();
    });
}

function closeConfirmBox(){
    var confirm_container = document.querySelector('.confirmbox-container');
    var confirm_content = document.querySelector('.confirmbox');

    confirm_container.style.display = '';
}

function toggleConfirmBox(message, openEvent){
    var confirm_container = document.querySelector('.confirmbox-container');

    if (confirm_container.style.display === 'block') {
//    Disappear
        closeConfirmBox();
    } else {
//    Appear
        openConfirmBox(message, openEvent);
    }
}

function formatCurrency(number) {
    var tempNumb = parseFloat(number).toFixed(3);
    // Sử dụng Intl.NumberFormat để định dạng số thành tiền tệ Việt Nam Đồng
    const formatter = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
        minimumFractionDigits: 0
    });

    // Áp dụng định dạng và trả về chuỗi định dạng
    return formatter.format(tempNumb);
}

function adjustTotalPrice(itemPrice, event){
    var totalPriceElement = document.getElementById('total-price-value');
    var totalPriceContent = totalPriceElement.textContent.replace(/[^\d]/g,'');
    var totalPriceValue = parseFloat(totalPriceContent);

    itemPrice = parseFloat(itemPrice).toFixed(3);
    itemPrice = itemPrice.toString().replace(/[^\d]/g,'');

    console.log("Current: " + totalPriceValue);
    console.log("Item: " + itemPrice);
    if(event === 'add'){
        totalPriceValue += parseFloat(itemPrice);
        console.log("Final: " + totalPriceValue);
    } else if(event === 'sub') {
        totalPriceValue -= parseFloat(itemPrice);
    } else {
        return;
    }
    totalPriceElement.textContent = formatCurrency(totalPriceValue);
}

function handleInputChange(itemPrice, row){
    var quantityInput = row.querySelector('.order-quantity');
    var totalPriceElement = document.getElementById('total-price-value');
    var totalPriceContent = totalPriceElement.innerText || totalPriceElement.textContent;
    var quantityInputValue = parseInt(quantityInput.value);

    quantityInput.addEventListener('keydown', function(event) {
        var currentQuantity = parseInt(quantityInput.value);

        if (event.keyCode === 38) {
            adjustTotalPrice(itemPrice, 'add');
            quantityInput.value = currentQuantity + 1;
        } else if(event.keyCode === 40){
            if(parseInt(quantityInput.value) > parseInt(quantityInput.min)){
                adjustTotalPrice(itemPrice, 'sub');
                quantityInput.value = currentQuantity - 1;
            } else if(parseInt(quantityInput.value) === parseInt(quantityInput.min)){
                delItem(row.querySelector('.delete-button'));
            }
        }
    });
}