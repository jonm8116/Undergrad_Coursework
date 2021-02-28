// When the user scrolls the page, execute myFunction
window.onscroll = function() {fix_header()};

// Get the header
var header = document.getElementById("genre_nav");

// Get the offset position of the navbar
var sticky = header.offsetTop;

// Add the sticky class to the header when you reach its scroll position. Remove "sticky" when you leave the scroll position
function fix_header() {
    if (window.pageYOffset > sticky) {
        header.classList.add("sticky");
    } else {
        header.classList.remove("sticky");
    }
}
