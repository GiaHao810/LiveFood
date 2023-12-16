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
            isTrue = true;
        }
    });

//  Nếu sản phẩm chưa có thì thêm vào Order Table
    if(!isTrue){
        addNewRow(labelValue, itemPrice, imageURL);
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

//        console.log(itemName)
//        console.log(quantity)
         if(itemName == labelValue){
            quantity += 1;
            inputElement.value = quantity;
         }
    });
}

function addNewRow(labelValue, itemPrice, imageURL){
    var newRow = document.createElement("li");
    newRow.className = "row";
    newRow.innerHTML = `
            <label class="col-md order-name" >${labelValue}</label>
            <input class="order-url" type="hidden" value="${imageURL}">
            <input class="order-price" type="hidden" value="${itemPrice}">
            <input class="col-md order-quantity" type="number" value="1" min="1">
            <button class="col-md delete-button" onclick="delItem(this)">Del</button>
    `;

    document.getElementById("ordertable").appendChild(newRow);
}



function delItem(button){
    var row = button.closest('.row');
    if (row) {
        row.parentNode.removeChild(row);
    }
}

function submitProductDTOList(){
    var order = [];
    var productDTOList = [];
    var totalPrice = 0.0;
    var listItems = document.querySelectorAll("#ordertable li");

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
    order.push({
        customerName: "John",
        totalPrice: totalPrice,
        productDTOList: productDTOList
    });

    axios.post('/submitOrder', order)
        .then(function (response) {
            console.log(response.data);
        })
        .catch(function (error){
            console.error(error);
        })
}