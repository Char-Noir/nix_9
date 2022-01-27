function back() {
    window.history.back();
}

function details(who, id) {
    window.location.href = '/' + who + '/details/' + id;
}

function deleteId(who, id) {
    window.location.href = '/' + who + '/delete/' + id;
}

function deleted() {
    return false;
}

function add() {
    return window.location.href = '/' + who + '/new';
}


$(document).ready(function () {
    $('.dropdown-toggle').dropdown();
});

$(window).load(function () {
    $(".update").click(function () {
        top.window.location.href = window.location.origin + '/' + $(this).data('who') + '/update/' + $(this).attr('id');
        return true;
    })
    $(".details").click(function () {
        top.window.location.href = window.location.origin + '/' + $(this).data('who') + '/details/' + $(this).attr('id');
        return true;
    })
    $(".add").click(function () {
        top.window.location.href = window.location.origin + '/' + $(this).data('who') + '/new/';
        return true;
    })
})

