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
        addNewRowItem(labelValue, itemPrice, imageURL);
        adjustTotalPrice(itemPrice, 'add');
    }
}