function getImageLabel(imageInput) {
    // Lấy đối tượng label tương ứng với input image
    var label = imageInput.nextElementSibling;

    var labelValue = label.textContent || label.innerText;

    var itemNames = getValues();

    var isTrue = false;

    itemNames.forEach(function(itemName) {
    if(itemName == labelValue){
        addQuantity(itemName);
        isTrue = true;
    }
    });

    if(!isTrue){
        addNewRow(labelValue);
    }
}

// Hàm để lấy giá trị từ mỗi dòng
function getValues() {
    // Lấy tất cả các phần tử có class "quantityInput"
    var inputElements = document.querySelectorAll('#quantity');
    var row, itemName, quantity;
    var itemNames = [];

    // Duyệt qua từng phần tử để lấy giá trị
    inputElements.forEach(function(inputElement) {
    row = inputElement.closest('.row'); // Tìm phần tử gần nhất có class "row"
    itemName = row.querySelector('.col-md').innerText;
    quantity = inputElement.value;

    if(!itemNames.includes(itemName)) {
        itemNames.push(itemName);
    }
    });

    return itemNames;
}

function addQuantity(labelValue){
    var inputElements = document.querySelectorAll('#quantity');

    inputElements.forEach(function(inputElement) {
        var row = inputElement.closest('.row'); // Tìm phần tử gần nhất có class "row"
        var itemName = row.querySelector('.col-md').innerText;
        var quantity = parseInt(inputElement.value);

         if(itemName == labelValue){
            quantity += 1;
            inputElement.value = quantity;
         }
    });
}

function addNewRow(labelValue){
    var newRow = document.createElement("div");
    newRow.className = "row";
    newRow.innerHTML = `
        <div class="row">
            <div class="col-md">${labelValue}</div>
            <div class="col-md">
                <input type="number" id="quantity" value="1" min="1">
            </div>
            <div class="col-md">
                <button class="delete-button" onclick="delItem(this)">Del</button>
            </div>
        </div>
    `;

    document.getElementById("ordertable").appendChild(newRow);
}

function delItem(button){
    var row = button.closest('.row');
    if (row) {
        row.parentNode.removeChild(row);
    }
}

function sendItemsToApi(items) {
    fetch('/makePayment', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(items),
    })
    .then(response => response.json())
    .then(data => {
        console.log('API Response:', data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function makePayment(){
    var listItems = getItems();

    // In mảng ra console để kiểm tra
    console.log(listItems);

    sendItemsToApi(listItems);
}

// Thêm một đối tượng mới vào mảng
function addItem(quantity, labelName, items) {
    var newItem = {
        quantity: quantity,
        labelName: labelName
    };

    items.push(newItem);
}

function getItems() {
    // Lấy tất cả các phần tử có class "quantityInput"
    var inputElements = document.querySelectorAll('#quantity');
    var row, itemName, quantity;
    var listItems = [];

    // Duyệt qua từng phần tử để lấy giá trị
    inputElements.forEach(function(inputElement) {
    row = inputElement.closest('.row'); // Tìm phần tử gần nhất có class "row"
    itemName = row.querySelector('.col-md').innerText;
    quantity = inputElement.value;

    addItem(quantity, itemName, listItems);
    });

    return listItems;
}