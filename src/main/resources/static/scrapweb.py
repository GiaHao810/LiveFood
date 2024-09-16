import json
import requests
import os
from bs4 import BeautifulSoup

# url = 'https://3sach.vn/collections/rau-cu-cac-loai'
url = 'https://3sach.vn/collections/trai-cay-tuoi'

# Gửi yêu cầu HTTP GET đến URL
response = requests.get(url)

# Kiểm tra mã trạng thái HTTP
if response.status_code == 200:
    # Phân tích cú pháp HTML của trang
    soup = BeautifulSoup(response.text, 'html.parser')
    
    # Tìm các phần tử cần thiết (ví dụ: các sản phẩm trong div có class 'product-loop')
    products = soup.find_all('div', class_='product-loop')
    
    product_data = []
    
    upload_dir = 'C:/Projects/client/src/main/resources/static/image'
    
    for product in products:
        product_name = product.find('h3').get_text(strip=True)
        product_price = product.find('span', class_='price').get_text(strip=True).replace('₫', '').replace(',', '').strip()
        
        product_data.append({
            'name': product_name,
            'price': float(product_price),
            'category': 'FRUIT'  # Thay thế bằng thông tin thực tế nếu có
        })
        
        picture_tag = product.find('picture')
        if picture_tag:
            img_tag = picture_tag.find('img')
            if img_tag and img_tag.has_attr('data-src'):
                product_image_url = img_tag['data-src']
            else:
                product_image_url = img_tag['src']
        else:
            product_image_url = None
        
        if product_image_url:
            image_response = requests.get("https:" + product_image_url)
            if image_response.status_code == 200:
                # Đảm bảo thư mục tồn tại
                os.makedirs('images', exist_ok=True)
                # Xác định tên tệp hình ảnh
                image_filename = f"{upload_dir}/{product_name.replace(' ', '_')}.png"
                # Lưu hình ảnh
                with open(image_filename, 'wb') as file:
                    file.write(image_response.content)
                print(f"Image for product '{product_name}' saved as '{image_filename}'")
            else:
                print(f"Failed to download image for product '{product_name}'")
else:
    print(f'Failed to retrieve the webpage. Status code: {response.status_code}')
    

api_url = 'http://localhost:8080/api/product/add'

headers = {
    'Content-Type': 'application/json'
}

for product in product_data:
    response = requests.post(api_url, headers=headers, data=json.dumps(product))
    
    # Kiểm tra mã trạng thái HTTP
    if response.status_code == 201:
        print(f"Product '{product['name']}' added successfully!")
    else:
        print(f"Failed to add product '{product['name']}'. Status code: {response.status_code}")