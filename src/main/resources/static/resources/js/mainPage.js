$(function () {
    // ----------------------------------- Position

    $('#viscaPositionUpLeft').on('click', function () {
        console.log("viscaPositionUpLeft");
        var data = {
            'direction': 'UPLEFT',
            'tiltSpeed': $('#tiltSpeed').eq(0).val(),
            'panSpeed': $('#panSpeed').eq(0).val(),
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/position', data);
    });

    $('#viscaPositionUp').on('click', function () {
        console.log("viscaPositionUp");
        var data = {
            'direction': 'UP',
            'tiltSpeed': $('#tiltSpeed').eq(0).val(),
            'panSpeed': $('#panSpeed').eq(0).val(),
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/position', data);
    });

    $('#viscaPositionUpRight').on('click', function () {
        console.log("viscaPositionUpRight");
        var data = {
            'direction': 'UPRIGHT',
            'tiltSpeed': $('#tiltSpeed').eq(0).val(),
            'panSpeed': $('#panSpeed').eq(0).val(),
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/position', data);
    });

    $('#viscaPositionLeft').on('click', function () {
        console.log("viscaPositionLeft");
        var data = {
            'direction': 'LEFT',
            'tiltSpeed': $('#tiltSpeed').eq(0).val(),
            'panSpeed': $('#panSpeed').eq(0).val(),
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/position', data);
    });
    $('#viscaPositionRight').on('click', function () {
        console.log("viscaPositionRight");
        var data = {
            'direction': 'RIGHT',
            'tiltSpeed': $('#tiltSpeed').eq(0).val(),
            'panSpeed': $('#panSpeed').eq(0).val(),
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/position', data);
    });

    $('#viscaPositionDownLeft').on('click', function () {
        console.log("viscaPositionDownLeft");
        var data = {
            'direction': 'DOWNLEFT',
            'tiltSpeed': $('#tiltSpeed').eq(0).val(),
            'panSpeed': $('#panSpeed').eq(0).val(),
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/position', data);
    });

    $('#viscaPositionDown').on('click', function () {
        console.log("viscaPositionDown");
        var data = {
            'direction': 'DOWN',
            'tiltSpeed': $('#tiltSpeed').eq(0).val(),
            'panSpeed': $('#panSpeed').eq(0).val(),
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/position', data);
    });

    $('#viscaPositionDownRight').on('click', function () {
        console.log("viscaPositionDownRight");
        var data = {
            'direction': 'DOWNRIGHT',
            'tiltSpeed': $('#tiltSpeed').eq(0).val(),
            'panSpeed': $('#panSpeed').eq(0).val(),
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/position', data);
    });

    // ----------------------------------- Zoom
    $('#viscaZoomTele').on('click', function () {
        console.log("viscaZoomTele");
        var data = {
            'zoom': 'TELE',
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/zoom', data);
    });
    $('#viscaZoomWide').on('click', function () {
        console.log("viscaZoomWide");
        var data = {
            'zoom': 'WIDE',
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/zoom', data);
    });

    $('#viscaHome').on('click', function () {
        console.log("viscaHome");
        var data = {
            'command': 'HOME',
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/other', data);
    });

    $('#viscaSendMacro').on('click', function () {
        console.log("viscaSendMacro");
        var data = {
            'macro': $('#viscaMacro').eq(0).val()
        };
        postAPI('/macro', data);
    });
});

function postAPI(endpoint, data) {
    $.ajax({
        type: 'POST',
        url: '/api' + endpoint,
        data: data,
        success: function (msg) {
            $('#response').val(msg);
        }
    });
}