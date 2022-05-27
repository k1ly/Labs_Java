
let passwordInput = $('#password');
let repeatPswInput = $('#repeat-psw');
let showPassword = $('#show-password');

passwordInput.focus(function () {
    $('#password-check').show();
})

passwordInput.blur(function () {
    $('#password-check').hide();
})

passwordInput.keyup(function () {
    let letter = $('#letter');
    let capital = $('#capital');
    let number = $('#number');
    let length = $('#length');

    if (passwordInput.val().match(/[a-z]/g)) {
        letter.removeClass('invalid');
        letter.addClass('valid');
    } else {
        letter.removeClass('valid');
        letter.addClass('invalid');
    }

    if (passwordInput.val().match(/[A-Z]/g)) {
        capital.removeClass('invalid');
        capital.addClass('valid');
    } else {
        capital.removeClass('valid');
        capital.addClass('invalid');
    }

    if (passwordInput.val().match(/[0-9]/g)) {
        number.removeClass('invalid');
        number.addClass('valid');
    } else {
        number.removeClass('valid');
        number.addClass('invalid');
    }

    if (passwordInput.val().length >= 8) {
        length.removeClass('invalid');
        length.addClass('valid');
    } else {
        length.removeClass('valid');
        length.addClass('invalid');
    }
})

showPassword.mousedown(function () {
    $('#password').prop('type', 'text');
    $('#repeat-psw').prop('type', 'text');
})

showPassword.mouseup(function () {
    $('#password').prop('type', 'password');
    $('#repeat-psw').prop('type', 'password');
})

showPassword.mouseleave(function () {
    $('#password').prop('type', 'password');
    $('#repeat-psw').prop('type', 'password');
})

repeatPswInput.keyup(function () {
    let repeatCheck = $('#repeat-check');
    repeatCheck.show();
    if ($(this).val() === passwordInput.val()) {
        repeatCheck.css('color', 'green');
        repeatCheck.text('✔');
    } else {
        repeatCheck.css('color', 'red');
        repeatCheck.text('❌');
    }
})

$('#register-form').submit(function (event) {
    event.preventDefault();
    if (repeatPswInput.val() !== passwordInput.val()) {
        alert('Повторите ваш пароль еще раз');
        return false;
    } else this.submit();
})

$('#user-drop-btn').click(function () {
    $('#user-actions-dropdown').toggle();
})

$(window).click(function (event) {
    if (event.target === registerWindow[0]) {
        registerWindow.hide();
    } else if (event.target === checkoutWindow[0]) {
        checkoutWindow.hide();
    } else {
        if (event.target !== $('#user-drop-btn')[0] && $(event.target).parents('#user-drop-btn').length === 0)
            $('#user-actions-dropdown').hide();
    }
})