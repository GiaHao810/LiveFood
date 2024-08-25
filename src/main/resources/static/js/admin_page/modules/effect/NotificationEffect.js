export function fadeInAndOut(element, duration = 3000) {
    $(element).fadeIn('slow').delay(duration).fadeOut('slow', function() {
        $(this).remove();
    });
}