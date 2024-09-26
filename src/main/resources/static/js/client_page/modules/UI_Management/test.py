import os

# Đường dẫn đến thư mục chứa các file
directory = 'C:/Projects/client/src/main/resources/static/image/product/'

# Lặp qua từng file trong thư mục
for filename in os.listdir(directory):
    if filename.startswith('[RTE]_'):  # Kiểm tra xem tên file có bắt đầu bằng '[RTE] ' hay không
        # Tạo tên file mới bằng cách loại bỏ '[RTE] '
        new_filename = filename.replace('[RTE]_', '')

        # Tạo đường dẫn đầy đủ cho file cũ và file mới
        old_file_path = os.path.join(directory, filename)
        new_file_path = os.path.join(directory, new_filename)

        # Đổi tên file
        os.rename(old_file_path, new_file_path)
        print(f'Renamed: {old_file_path} to {new_file_path}')
    else:
        print('none')
