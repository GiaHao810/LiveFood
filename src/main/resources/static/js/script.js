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

        console.log(itemName)
        console.log(quantity)
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
        <li class="row">
            <div class="col-md order-name" >${labelValue}</div>
            <input class="col-md order-quantity" type="number" value="1" min="1">
            <button class="col-md delete-button" onclick="delItem(this)">Del</button>
        </li>
    `;

    document.getElementById("ordertable").appendChild(newRow);
}

function delItem(button){
    var row = button.closest('.row');
    if (row) {
        row.parentNode.removeChild(row);
    }
}

function submitProductList(){
    var productList = [];
    var listItems = document.querySelectorAll("#ordertable li");

    listItems.forEach(function(item){
        var itemNameElement = item.querySelector('.item-name');
        var itemName = itemNameElement.textContent || itemNameElement.innerText;

        var itemQuantity = item.querySelector('.item-quantity').value;

        productList.push({
            name: itemName,
            quantity: itemQuantity
        });
    });

    axios.post('/submitProducts', productList)
        .then(function (response) {
            console.log(response.data);
        })
        .catch(function (error){
            console.error(error);
        })
}