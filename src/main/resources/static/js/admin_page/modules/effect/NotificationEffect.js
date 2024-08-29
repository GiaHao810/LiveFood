export function fadeInAndOut(element, duration = 3000) {
    $(element)
        .fadeIn('slow')                     // Hiệu ứng xuất hiện
        .delay(duration)            // Giữ lại trong khoảng thời gian (duration - thời gian hiệu ứng mờ dần)
        .fadeTo('slow', 0.5)                // Làm mờ dần thông báo (50% độ mờ)
        .fadeOut('slow', function() {
            $(this).remove();               // Xóa phần tử sau khi hiệu ứng hoàn tất
    });
}