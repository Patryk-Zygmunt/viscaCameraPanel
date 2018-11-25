$(function () {
    // ----------------------------------- Position
    $('.visca-position-up').on('click', function () {
        console.log("visca-position-up");
        var data = {
            'direction': 'UP',
            'speed': $('input').eq(0).val()
        };
        postAPI('/position', data);
    });

    $('.visca-position-down').on('click', function () {
        console.log("visca-position-down");
        var data = {
            'direction': 'DOWN',
            'speed': $('input').eq(0).val()
        };
        postAPI('/position', data);
    });
    $('.visca-position-left').on('click', function () {
        console.log("visca-position-left");
        var data = {
            'direction': 'LEFT',
            'speed': $('input').eq(0).val()
        };
        postAPI('/position', data);
    });
    $('.visca-position-right').on('click', function () {
        console.log("visca-position-right");
        var data = {
            'direction': 'RIGHT',
            'speed': $('input').eq(0).val()
        };
        postAPI('/position', data);
    });
    // ----------------------------------- Zoom
    $('.visca-zoom-tele').on('click', function () {
        console.log("visca-zoom-tele");
        var data = {
            'zoom': 'TELE',
            'speed': $('input').eq(0).val()
        };
        postAPI('/zoom', data);
    });
    $('.visca-zoom-wide').on('click', function () {
        console.log("visca-zoom-wide");
        var data = {
            'zoom': 'WIDE',
            'speed': $('input').eq(0).val()
        };
        postAPI('/zoom', data);
    });
    $('.visca-other-home').on('click', function () {
        console.log("visca-other-home");
        var data = {
            'command': 'HOME',
            'speed': $('input').eq(0).val()
        };
        postAPI('/other', data);
    });
});

function postAPI(endpoint, data) {
    $.ajax({
        type: 'POST',
        url: '/api' + endpoint,
        data: data,
        success: function (msg) {
            $('.response').val(msg);
        }
    });
}

