import json
import requests
import os
from bs4 import BeautifulSoup

# URL của trang cần crawl
url = 'https://3sach.vn/collections/thuy-hai-san-tuoi-song'

category = "OTHER"

# Gửi yêu cầu HTTP GET đến URL
response = requests.get(url)
product_data = []
upload_directory = 'C:/Projects/client/src/main/resources/static/image/temp'

# Kiểm tra mã trạng thái HTTP
if response.status_code == 200:
    # Phân tích cú pháp HTML của trang
    soup = BeautifulSoup(response.text, 'html.parser')
    
    # Tìm các phần tử cần thiết (ví dụ: các sản phẩm trong div có class 'product-loop')
    products = soup.find_all('div', class_='product-loop')
    
    
    for product in products:
        product_name = product.find('h3').get_text(strip=True)
        product_price = product.find('span', class_='price').get_text(strip=True).replace('₫', '').replace(',', '').strip()
        
        product_data.append({
            'name': product_name,
            'price': float(product_price),
            'category': category  # Thay thế bằng thông tin thực tế nếu có
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
                sanitized_product_name = product_name.replace(' ', '_').replace('/', '-')
                image_filename = f"{upload_directory}/{sanitized_product_name}.png"
                
                # Lưu hình ảnh
                with open(image_filename, 'wb') as file:
                    file.write(image_response.content)

                print(f"Image for product '{product_name}' saved as '{image_filename}'")
            else:
                print(f"Failed to download image for product '{product_name}'")
else:
    print(f'Failed to retrieve the webpage. Status code: {response.status_code}')

# API URL để thêm sản phẩm và upload hình ảnh
api_url = 'http://localhost:8080/api/product/add'
upload_api_url = 'http://localhost:8080/api/media/uploadproductMedia/'

upload_dir = 'C:/Projects/client/src/main/resources/static/image/product'
   
headers = {
    'Content-Type': 'application/json'
}

# Gửi từng sản phẩm qua API
for product in product_data:
    # Gửi thông tin sản phẩm đến API thêm sản phẩm
    response = requests.post(api_url, headers=headers, data=json.dumps(product))

    # Bước 1: Giải mã byte string thành string thông thường
    response_str = response.json();

    # Bước 3: Truy cập các phần tử trong JSON
    message = response_str.get('message')
    status = response_str.get('status')
    product_d = response_str.get('data')

    product_id = product_d.get('id')

    if response.status_code == 201:
        # Kiểm tra nếu sản phẩm đã được tạo thành công và có ID
        if product_id:
            print(f"Product '{product['name']}' added successfully with ID {product_id}!")
            
            # Tìm file hình ảnh của sản phẩm để upload
            image_filename = f"{upload_directory}/{product['name'].replace(' ', '_')}.png"
            
            if os.path.exists(image_filename):
                # Mở file hình ảnh
                with open(image_filename, 'rb') as file:
                    # Chuẩn bị files payload để gửi tới API upload hình ảnh
                    files_payload = {'files': (os.path.basename(image_filename), file, 'image/png')}
                    
                    # Gửi hình ảnh qua API upload
                    upload_response = requests.post(f"{upload_api_url}{product_id}", files=files_payload)
                    
                    if upload_response.status_code == 200:
                        print(f"Image for product '{product['name']}' uploaded successfully!")
                    else:
                        print(f"Failed to upload image for product '{product['name']}'. Status code: {upload_response.status_code}")
            else:
                print(f"Image file '{image_filename}' does not exist.")
        else:
            print(f"Failed to retrieve product ID for '{product['name']}'")
    else:
        print(f"Failed to add product '{product['name']}'. Status code: {response.content}")
