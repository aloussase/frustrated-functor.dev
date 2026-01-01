const canvasElement = document.getElementById("matrix")

if (window.location.pathname === '/') {
    matrix(canvasElement, {
        chars: matrix.range(0x30A1, 0x30F6).concat(matrix.range(0x0030, 0x0039)),
        font_size: 10,
        exit: false,
        color: '#ff0000',
    });
}
