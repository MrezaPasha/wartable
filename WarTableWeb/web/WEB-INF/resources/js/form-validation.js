/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/
var FormValidator = function () {
    /* function to initiate Validation Sample 1*/
    var runValidator1 = function (form1) {
        var errorHandler1 = $('.errorHandler', form1);
        var successHandler1 = $('.successHandler', form1);

        var validateMelliCode = function (value) {
            if (value == "" || value.length == 0) {
                return true;
            }

            if (value.length == 10) {

                var newValue = "";
                for (var i = 0; i < value.length; i++) {
                    var ch = value.charCodeAt(i);
                    if (ch >= 1776 && ch <= 1785) /* For Persian digits.*/
                    {
                        var newChar = ch - 1728;
                        newValue = newValue + String.fromCharCode(newChar);
                    }
                    else if (ch >= 1632 && ch <= 1641) /* For Arabic & Unix digits.*/
                    {
                        var newChar = ch - 1584;
                        newValue = newValue + String.fromCharCode(newChar);
                    }
                    else
                        newValue = newValue + String.fromCharCode(ch);
                }

                if (newValue == '1111111111' ||
                    newValue == '0000000000' ||
                    newValue == '2222222222' ||
                    newValue == '3333333333' ||
                    newValue == '4444444444' ||
                    newValue == '5555555555' ||
                    newValue == '6666666666' ||
                    newValue == '7777777777' ||
                    newValue == '8888888888' ||
                    newValue == '9999999999') {
                    return false;
                }

                c = parseInt(newValue.charAt(9));
                n = parseInt(newValue.charAt(0)) * 10 +
                    parseInt(newValue.charAt(1)) * 9 +
                    parseInt(newValue.charAt(2)) * 8 +
                    parseInt(newValue.charAt(3)) * 7 +
                    parseInt(newValue.charAt(4)) * 6 +
                    parseInt(newValue.charAt(5)) * 5 +
                    parseInt(newValue.charAt(6)) * 4 +
                    parseInt(newValue.charAt(7)) * 3 +
                    parseInt(newValue.charAt(8)) * 2;
                r = n - parseInt(n / 11) * 11;
                if ((r == 0 && r == c) || (r == 1 && c == 1) || (r > 1 && c == 11 - r)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        };

        var validateMobile = function (value) {
            if (value.length == 0 || value.match("^[0][9][0-9]{9}$") || value.match("^[۰][۹][۰-۹]{9}$"))
                return true;
            else
                return false;
        };

        var validateEmail = function (value) {
            var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
            return (value.length == 0 || pattern.test(value));
        };

        /*    Prenetation*/
        $('.disable-paste').bind('paste', function (e) {
            e.preventDefault();
        });

        $(".number-format").keypress(function (e) {
            var keycode = e.keyCode ? e.keyCode : e.which;
            return (keycode >= 48 && keycode <= 57)
                || (keycode >= 1776 && keycode <= 1785)
                || keycode == 9 || keycode == 8
                || keycode == 46 || keycode == 37 || keycode == 39
                || keycode == 17 || keycode == 86 || keycode == 67 || keycode == 99 || keycode == 91 || keycode == 118 || keycode == 83
        });

        $(".percent-format").keypress(function (e) {
            var keycode = e.keyCode ? e.keyCode : e.which;
            return (keycode >= 48 && keycode <= 57)
                || (keycode >= 1776 && keycode <= 1785)
                || keycode == 9 || keycode == 8
                || keycode == 46 || keycode == 37 || keycode == 39
        });

        $(".not-number").keypress(function (e) {
            var keycode = e.keyCode ? e.keyCode : e.which;
            return !((keycode >= 48 && keycode <= 57)
                || (keycode >= 1776 && keycode <= 1785));
        });

        $(".mobile-format").keypress(function (e) {
            var keycode = e.keyCode ? e.keyCode : e.which;
            return (keycode >= 48 && keycode <= 57)
                || (keycode >= 1776 && keycode <= 1785)
                || keycode == 9 || keycode == 8 || keycode == 13
                || keycode == 46 || keycode == 37 || keycode == 39
        });
        $(".shenasNameh-format").keypress(function (e) {
            var keycode = e.keyCode ? e.keyCode : e.which;
            return (keycode >= 48 && keycode <= 57)
                || (keycode >= 1776 && keycode <= 1785)
                || keycode == 9 || keycode == 8
                || keycode == 46 || keycode == 37 || keycode == 39
        });
        $(".shenSerialNum-format").keypress(function (e) {
            var keycode = e.keyCode ? e.keyCode : e.which;
            return (keycode >= 48 && keycode <= 57)
                || (keycode >= 1776 && keycode <= 1785)
                || keycode == 9 || keycode == 8
                || keycode == 46 || keycode == 37 || keycode == 39
        });
        $(".melliCode-format").keypress(function (e) {
            var keycode = e.keyCode ? e.keyCode : e.which;
            return (keycode >= 48 && keycode <= 57)
                || (keycode >= 1776 && keycode <= 1785)
                || keycode == 9 || keycode == 8
                || keycode == 46 || keycode == 37 || keycode == 39
        });
        $(".postalcode-format").keypress(function (e) {
            var keycode = e.keyCode ? e.keyCode : e.which;
            return (keycode >= 48 && keycode <= 57)
                || (keycode >= 1776 && keycode <= 1785)
                || keycode == 9 || keycode == 8
                || keycode == 46 || keycode == 37 || keycode == 39
        });
        /*  Validation Method Definition (Rules)*/

        $.validator.addMethod("melliCode",
            function (value) {
                return validateMelliCode(value);
            },
            "کد ملی وارد شده معتبر نمی باشد!");

        $.validator.addMethod("greatThan",
            function (value, element) {
                var sval = $("#" + ($(element).data("great-dst"))).val();
                var eval = $(element).val();
                if (eval.length == 0 || sval.length == 0
                    || Number(eval) > Number(sval)
                ) {
                    return true;
                }
                return false;
            },
            "مقدار را بزرگتر از مقدار قبل وارد نمایید.");

        $.validator.addMethod("mobile",
            function (value) {
                return validateMobile(value);
            },
            "لطفا شماره موبایل را به صورت صحیح وارد نمایید - مثال ۰۹۱۳۳۳۳۳۳۳۳");

        $.validator.addMethod("email",
            function (value) {
                return validateEmail(value);
            },
            "لطفا آدرس ایمیل خود را به صورت صحیح وارد نمایید");

        $.validator.addMethod("mobile_email",
            function (value) {
                return validateMobile(value) || validateEmail(value);
            },
            "لطفا شماره تلفن همراه یا ایمیل خود را به صورت صحیح وارد نمایید.");

        $.validator.addMethod("mobile_email_melliCode",
            function (value) {
                return validateMobile(value) || validateEmail(value) || validateMelliCode(value);
            },
            "لطفا شماره تلفن همراه، ایمیل یا کد ملی خود را به صورت صحیح وارد نمایید.");

        $.validator.addMethod("mobile_melliCode",
            function (value) {
                return validateMobile(value) || validateMelliCode(value);
            },
            "لطفا شماره تلفن همراه یا کد ملی خود را به صورت صحیح وارد نمایید.");

        $.validator.addMethod("email_melliCode",
            function (value) {
                return validateEmail(value) || validateMelliCode(value);
            },
            "لطفا ایمیل یا کد ملی خود را به صورت صحیح وارد نمایید.");


        $.validator.addMethod("postalcode",
            function (value) {
                if (value.length == 0 || value.match("^[1-9][0-9]{9}$") || value.match("^[‍۱-۹][۰-۹]{9}$"))
                    return true;
                else
                    return false;
            },
            "کد پستی وارد شده صحیح نیست. لطفا کد پستی را به صورت ۱۰ رقمی وارد نمایید. مثال: ۸۳۴۱۱۱۱۱۱۱");

        $.validator.addMethod("tagKeyFormat",
            function (value) {
                if (value.length == 0 || value.match("^[a-zA-Z][a-zA-Z\-_]*$"))
                    return true;
                else
                    return false;
            },
            "لطفاکلید را در قالب تعریف شده وارد نمایید. کاراکترهای مجاز (حروف انگلیسی، _ -)");


        $.validator.addMethod("englishFormat",
            function (value) {
                if (value.length == 0 || value.match("^[a-zA-Z][a-zA-Z0-9\-_]*$"))
                    return true;
                else
                    return false;
            },
            "لطفا به صورت انگلیسی وارد نمایید. کاراکترهای مجاز (حروف انگلیسی،اعداد، _ -)");
        $.validator.addMethod("latinFormat",
            function (value) {
                if (value.length == 0 || value.match("^[a-zA-Z0-9]([a-zA-Z0-9\-_ ])*$"))
                    return true;
                else
                    return false;
            },
            "لطفا به صورت لاتین وارد نمایید. کاراکترهای مجاز (حروف انگلیسی،اعداد، _ -)");

        $.validator.addMethod("numberFormat",
            function (value) {
                if (value.length == 0 || value.match("[+-]?([0-9]*[.])?[0-9]+"))
                    return true;
                else
                    return false;
            },
            "لطفا عدد  را به صورت صحیح وارد نمایید.");
        $.validator.addMethod("englishCharacter",
            function (value) {
                if (value.length == 0 || value.match("^[a-zA-Z\s]*$"))
                    return true;
                else
                    return false;
            },
            "لطفا به صورت انگلیسی وارد نمایید. کاراکترهای مجاز (حروف انگلیسی)");
        $.validator.addMethod("farsiFormat",
            function (value) {
                if (value.length == 0 || value.match("^[آ-ی][ءآ-ی۰-۹\-_]*$"))
                    return true;
                else
                    return false;
            },
            "لطفا به صورت فارسی وارد نمایید. کاراکترهای مجاز (حروف فارسی،اعداد، _ -)");

        $.validator.addMethod("persianDateFormat",
            function (value) {
                if (value.length == 0 ||
                    value.match(/^([1][2-4][0-9]{2})\/(0[1-9]|1[0-2])\/(0[1-9]|[12][0-9]|3[01])$/g)
                    || value.match(/^([۱][۲-۴][۰-۹]{2})\/(۰[۱-۹]|۱[۰-۲])\/(۰[۱-۹]|[۱۲][۰-۹]|۳[۰۱])$/g))
                    return true;
                else
                    return false;
            },
            "تاریخ را با ساختار صحیح وارد نمایید. ۱۳۹۵/۰۲/۲۱");
        $.validator.addMethod("persianYearFormat",
            function (value) {
                if (value.length == 0 ||
                    value.match(/^([1][2-4][0-9]{2})$/g)
                    || value.match(/^([۱][۲-۴][۰-۹]{2})$/g))
                    return true;
                else
                    return false;
            },
            "سال را با ساختار صحیح وارد نمایید. ۱۳۹۶");

        $.validator.addMethod("persianDateTimeFormat",
            function (value) {
                if (value.length == 0 ||
                    value.match(/^([1][2-4][0-9]{2})\/(0[1-9]|1[0-2])\/(0[1-9]|[12][0-9]|3[01])\s(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])$/g)
                    || value.match(/^([۱][۲-۴][۰-۹]{2})\/(۰[۱-۹]|۱[۰-۲])\/(۰[۱-۹]|[۱۲][۰-۹]|۳[۰۱])\s(۲[۰-۳]|[۰۱][۰-۹]):([۰-۵][۰-۹]):([۰-۵][۰-۹])$/g))
                    return true;
                else
                    return false;
            },
            "زمان وتاریخ را را با ساختار صحیح وارد نمایید. ۱۳۹۵/۰۲/۲۱ ۱۲:۰۰:۰۰");

        $.validator.addMethod("timeFormat",
            function (value) {
                if (value.length == 0 ||
                    value.match("^([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$")
                    || value.match("^[۰-۱][۰-۹]|۲[۰-۳]:[۰-۵][۰-۹]:[۰-۵][۰-۹]$"))
                    return true;
                else
                    return false;
            },
            "زمان را با ساختار صحیح وارد نمایید. ۱۲:۰۰:۰۰");

        $.validator.addMethod("farsiCharacter",
            function (value) {
                if (value.length == 0 || value.match("^[آ-ی][ءآ-ی\s ]*$"))
                    return true;
                else
                    return false;
            },
            "لطفا به صورت فارسی وارد نمایید. کاراکترهای مجاز (حروف فارسی)");
        $.validator.addMethod("farsiCharacters",
            function (value) {
                if (value.length == 0 || value.match("^[آ-ی][ءآ-ی\s -_ ،]*$"))
                    return true;
                else
                    return false;
            },
            "لطفا به صورت فارسی وارد نمایید. کاراکترهای مجاز (حروف فارسی ، _ -)");

        $.validator.addMethod("phone",
            function (value) {
                if (value.length == 0 || value.match("^[0][0-8][0-9]{9}$") || value.match("^[۰][۰-۸][۰-۹]{9}$"))
                    return true;
                else
                    return false;
            },
            "لطفا شماره تلفن  را به صورت صحیح وارد نمایید - مثال ۰۳۱۳۶۶۶۶۶۶۶");

        $.validator.addMethod("passwordMin",
            function (value) {
                if (value.length > 3)
                    return true;
                else
                    return false;
            },
            "لطفا رمز ورود را با حداقل طول ۴ کاراکتر وارد نمایید.");

        $.validator.addMethod("passwordMax",
            function (value) {
                if (value.length < 51)
                    return true;
                else
                    return false;
            },
            "لطفا رمز ورود را با حداکثر طول ۵۰ کاراکتر وارد نمایید.");

        $.validator.addMethod("passidMin",
            function (value) {
                if (value.length > 3)
                    return true;
                else
                    return false;
            },
            "لطفا شناسه ورود را با حداقل طول ۴ کاراکتر وارد نمایید.");

        $.validator.addMethod("passidMax",
            function (value) {
                if (value.length < 51)
                    return true;
                else
                    return false;
            },
            "لطفا شناسه ورود را با حداکثر طول ۵۰ کاراکتر وارد نمایید.");

        /*        برای اعتبارسنجی dropdown ها*/
        $.validator.addMethod("itemList",
            function (value) {
                if (value === "" || value === null)
                    return false;
                else
                    return true;
            },
            "لطفا یک مورد را انتخاب کنید!!");

        $.validator.addMethod("ipAddressFormat",
            function (value) {
                if (value.length == 0 ||
                    value.match(/^(25[0-5]|2[0-4][0-9]|1?[0-9][0-9]{1,2})(\.(25[0-5]|2[0-4][0-9]|1?[0-9]{1,2})){3}$/)
                || value.match(/^((?=.*::)(?!.*::.+::)(::)?([\dA-F]{1,4}:(:|\b)|){5}|([\dA-F]{1,4}:){6})((([\dA-F]{1,4}((?!\3)::|:\b|$))|(?!\2\3)){2}|(((2[0-4]|1\d|[1-9])?\d|25[0-5])\.?\b){4})$/i)
                )

                    return true;
                else
                    return false;
            },
            "آدرس IP را به صورت صحیح وارد نمایید.");


        /*  Main Configuration*/

        form1.validate({
            errorElement: "span", /* contain the error msg in a span tag*/
            errorClass: 'help-block',
            errorPlacement: function (error, element) { /* render error placement for each input type*/
                if (element.attr("type") == "radio" || element.attr("type") == "checkbox") { /* for chosen elements, need to insert the error after the chosen container*/
                    error.insertAfter($(element).closest('.form-group').children('div').children().last());
                } else if (element.hasClass("time-format")
                    || element.hasClass("time-required")
                    || element.hasClass("persian-datetime-format")
                    || element.hasClass("persian-datetime-required")
                    || element.hasClass("persian-date-format")
                    || element.hasClass("persian-date-required")) {
                    ($(element).closest('.form-group').append(error));
                } else if (element.hasClass("table-input")) {
                    if (!element.hasClass("error")) {
                        error.insertAfter(element);
                        element.addClass("error");
                    }
                    /* for other inputs, just perform default behavior*/
                } else {
                    /*                    alert(element.text());*/
                    error.insertAfter(element);
                    /* for other inputs, just perform default behavior*/
                }
            },
            ignore: "",
            rules: {},
            messages: {},
            groups: {
                DateofBirth: "dd mm yyyy"
            },
            invalidHandler: function (event, validator) { /*display error alert on form submit*/
                successHandler1.hide();
                errorHandler1.show();
            },
            highlight: function (element) {
                var elem = $(element);
                if (elem.hasClass("select2-offscreen")) {
                    elem.closest('.help-block').removeClass('valid');
                    elem.closest('.form-group').removeClass('has-success').addClass('has-error').find('.symbol').removeClass('ok').addClass('required');
                    elem.closest('.form-group').find(".select2-choice").css("border", "#b94a48 thin solid");
                    elem.closest('.form-group').css("color", "#b94a48");
                    /*                        alert("elem  " + elem.attr("id")+" > " + $( "#"+elem.attr("id")).html());*/
                } else {
                    elem.closest('.help-block').removeClass('valid');
                    /* display OK icon*/
                    elem.closest('.form-group').removeClass('has-success').addClass('has-error').find('.symbol').removeClass('ok').addClass('required');
                    /* add the Bootstrap error class to the control group*/
                }
            },
            unhighlight: function (element) { /* revert the change done by hightlight*/
                var elem = $(element);
                if (elem.hasClass("select2-offscreen")) {
                    elem.closest('.form-group').removeClass('has-error');

                    elem.closest('.form-group').find(".select2-choice").css("border", "green thin solid");
                    elem.closest('.form-group').css("color", "green");
                } else {
                    elem.closest('.form-group').removeClass('has-error');
                }
            },
            success: function (label, element) {
                label.addClass('help-block valid');
                /* mark the current input as valid and display OK icon*/
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success').find('.symbol').removeClass('required').addClass('ok');
            }

        });
        /* Usability*/
        $('.string-required').each(function () {
            $(this).rules('add', {
                required: true,
                minlength: 2,
                messages: {
                    required: "لطفا  مقدار را وارد کنید.",
                    minlength: "لطفا مقدار را با حداقل طول ۲ وارد کنید"
                }
            });
        });
        $('.license-customer').each(function () {
            $(this).rules('add', {
                required: true,
                minlength: 2,
                messages: {
                    required: "لطفا  مقدار را وارد کنید.",
                    minlength: "شناسه لایسنس حداقل بایستی ۲ کاراکتر باشد."
                }
            });
        });
        $('.string-max-2048').each(function () {
            $(this).rules('add', {
                maxlength: 2048,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر ۲۰۴۸ کارکتر باشد."
                }
            });
        });
        $('.string-max-1024').each(function () {
            $(this).rules('add', {
                maxlength: 1024,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر ۱۰۲۴ کارکتر باشد."
                }
            });
        });
        $('.string-max-255').each(function () {
            $(this).rules('add', {
                maxlength: 255,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر ۲۵۵ کارکتر باشد."
                }
            });
        });
        $('.string-max-250').each(function () {
            $(this).rules('add', {
                maxlength: 250,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر ۲۵۰ کارکتر باشد."
                }
            });
        });
        $('.string-max-150').each(function () {
            $(this).rules('add', {
                maxlength: 100,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر ۱۵۰ کارکتر باشد."
                }
            });
        });
        $('.string-max-100').each(function () {
            $(this).rules('add', {
                maxlength: 100,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر ۱۰۰ کارکتر باشد."
                }
            });
        });
        $('.string-max-50').each(function () {
            $(this).rules('add', {
                maxlength: 50,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر ۵۰ کارکتر باشد."
                }
            });
        });
        $('.string-max-40').each(function () {
            $(this).rules('add', {
                maxlength: 40,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر ۴۰ کارکتر باشد."
                }
            });
        });
        $('.string-max-30').each(function () {
            $(this).rules('add', {
                maxlength: 30,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر ۳۰ کارکتر باشد."
                }
            });
        });
        $('.string-max-25').each(function () {
            $(this).rules('add', {
                maxlength: 30,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر ۳۰ کارکتر باشد."
                }
            });
        });
        $('.string-max-20').each(function () {
            $(this).rules('add', {
                maxlength: 20,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر ۲۰ کارکتر باشد."
                }
            });
        });
        $('.string-max-15').each(function () {
            $(this).rules('add', {
                maxlength: 15,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر {0} کارکتر باشد."
                }
            });
        });
        $('.string-max-10').each(function () {
            $(this).rules('add', {
                maxlength: 10,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر  ۱۰ کارکتر باشد."
                }
            });
        });
        $('.string-max-6').each(function () {
            $(this).rules('add', {
                maxlength: 6,
                messages: {
                    maxlength: "مقدار وارد شده نباید بیشتر از ۶ کاراکتر باشد"
                }
            });
        });
        $('.string-max-2').each(function () {
            $(this).rules('add', {
                maxlength: 2,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر  ۲ کارکتر باشد."
                }
            });
        });
        $('.string-max-3').each(function () {
            $(this).rules('add', {
                maxlength: 3,
                messages: {
                    maxlength: " مقدار وارد شده نباید بیشتر  ۳ کارکتر باشد."
                }
            });
        });

        $('.number-format').each(function () {
            $(this).rules('add', {
                numberFormat: true
            });
        });
        $('.number-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا عدد را وارد کنید."
                }
            });
        });
        $('.number-max-1000').each(function () {
            $(this).rules('add', {
                max: 1000,
                messages: {
                    max: "مقدار ورودی نمی تواند بیشتر از ۱۰۰۰ باشد"
                }
            });
        });
        $('.number-max-100').each(function () {
            $(this).rules('add', {
                max: 100,
                messages: {
                    max: "مقدار وارد شده نمی تواند بیشتر از ۱۰۰باشد."
                }
            });
        });
        $('.number-max-10').each(function () {
            $(this).rules('add', {
                max: 10,
                messages: {
                    max: "مقدار وارد شده نمی تواند بیشتر از ۱۰باشد."
                }
            });
        });

        $('.number-max-int').each(function () {
            $(this).rules('add', {
                max: 2147000000,
                messages: {
                    max: "مقدار وارد شده بیشتر از حد مجاز است ."
                }
            });
        });
        $('.number-max-long').each(function () {
            $(this).rules('add', {
                max: 9000000000000000000,
                messages: {
                    max: "مقدار وارد شده بیشتر از حد مجاز است ."
                }
            });
        });

        $('.number-min-0').each(function () {
            $(this).rules('add', {
                min: 0,
                messages: {
                    min: "مفدار ورودی باید بزرگتر یا مساوی صفر باشد."
                }
            });
        });
        $('.number-min-1').each(function () {
            $(this).rules('add', {
                min: 1,
                messages: {
                    min: "مفدار ورودی باید بزرگتر یا مساوی ۱ باشد."
                }
            });
        });

        $('.percent-format').each(function () {
            $(this).rules('add', {
                numberFormat: true,
                min: 0,
                max: 100,
                messages: {
                    min: "مفدار ورودی باید بزرگتر یا مساوی صفر باشد.",
                    max: "مقدار وارد شده نمی تواند بیشتر از ۱۰۰باشد."
                }
            });
        });

        $('.passid-format').each(function () {
            $(this).rules('add', {
                passidMax: true,
                passidMin: true
            });
        });

        $('.repassid-format').each(function () {
            $(this).rules('add', {
                passidMax: true,
                passidMin: true,
                equalTo: ".passid-format",
                messages: {
                    equalTo: "تکرار شناسه عبور را به صورت صحیح وارد نمایید."
                }
            });
        });
        $('.passid-required-minlen').each(function () {
            $(this).rules('add', {
                required: true,
                minlength: 3,
                messages: {
                    required: "لطفا شناسه ورود را وارد کنید ",
                    minlength: "لطفا شناسه ورود حود را وارد کنید ."
                }
            });
        });

        $('.password-format').each(function () {
            $(this).rules('add', {
                passwordMax: true,
                passwordMin: true
            });
        });
        $('.repassword-format').each(function () {
            $(this).rules('add', {
                passwordMax: true,
                passwordMin: true,
                equalTo: "[data-repassword-target]",
                messages: {
                    equalTo: "تکرار رمز عبور را به صورت صحیح وارد نمایید."
                }
            });
        });

        $('.numberGreatThan').each(function () {
            $(this).rules('add', {
                greatThan: true
            });
        });

        $('.password-required-minlen').each(function () {
            $(this).rules('add', {
                required: true,
                minlength: 3,
                messages: {
                    required: "لطفارمز ورود را وارد کنید",
                    minlength: "لطفا رمز ورود حود را وارد کنید ."
                }
            });
        });

        $('.username-required-minlen').each(function () {
            $(this).rules('add', {
                required: true,
                minlength: 3,
                messages: {
                    required: "لطفا نام کاربری ورود را وارد کنید",
                    minlength: "لطفا نام کاربری حود را وارد کنید ."
                }
            });
        });
        $('.username-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا نام کاربری ورود را وارد کنید"
                }
            });
        });

        $('.email-required').each(function () {
            $(this).rules('add', {
                required: true,
                email: true,
                messages: {
                    required: "لطفا پست الکترونیکی را به درستی وارد کنید"
                }
            });
        });
        $('.email-format').each(function () {
            $(this).rules('add', {
                email: true
            });
        });

        $('.mobile-required').each(function () {
            $(this).rules('add', {
                required: true,
                mobile: true,
                messages: {
                    required: "لطفا شماره موبایل خود را وارد نمایید."
                }
            });
        });
        $('.mobile-required-sms').each(function () {
            $(this).rules('add', {
                required: true,
                mobile: true,
                messages: {
                    required: "شماره موبایل به منظور ارسال کد تایید ضروری است"
                }
            });
        });
        $('.mobile-format').each(function () {
            $(this).rules('add', {
                mobile: true
            });
        });
        $('.phone-format').each(function () {
            $(this).rules('add', {
                phone: true
            });
        });

        /*============================== dropdown*/
        $('select.dropdown-required').each(function () {
            $(this).rules('add', {
                itemList: true,
                messages: {
                    itemList: "لطفا یک مورد را انتخاب کنید",
                }
            });
        });
        /*==============================*/

        $('.shenasNameh-format').each(function () {
            $(this).rules('add', {
                minlength: 0,
                maxlength: 10,
                messages: {
                    minlength: "تطفا شماره شناسنامه را با حداقل طول ۰ وارد کنید",
                    maxlength: "لطفا شماره شناسنامه را با حداکثر طول ۱۰ وارد کنید"
                }
            });
        });
        $('.melliCode-format').each(function () {
            $(this).rules('add', {
                melliCode: true,
                messages: {
                    melliCode: "کد ملی وارد شده معتبر نیست. مثال: ۶۶۰۱۱۲۲۳۳۳"

                }
            });
        });
        $('.melliCode-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: " لطفا کد ملی را وارد کنید.",
                }
            });
        });
        $('.postalcode-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: " لطفا کد پستی را وارد کنید.",
                }
            });
        });

        $('.postalcode-format').each(function () {
            $(this).rules('add', {
                postalcode: true
            });
        });

        $('.tag-key-format').each(function () {
            $(this).rules('add', {
                tagKeyFormat: true
            });
        });

        $('.english-format').each(function () {
            $(this).rules('add', {
                englishFormat: true
            });
        });
        $('.latin-format').each(function () {
            $(this).rules('add', {
                latinFormat: true
            });
        });
        $('.activeCode-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا کد فعال سازی را وارد کنید .",
                }
            });
        });
        $('.confirmCode2step-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا کد تایید دو مرحله ای را وارد کنید ."
                }
            });
        });
        $('.confirmCode-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا کد تایید را وارد کنید ."
                }
            });
        });
        $('.verifyCode-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا کد فعال سازی را وارد کنید .",
                }
            });
        });

        $('.url-format').each(function () {
            $(this).rules('add', {
                url: true,
                messages: {
                    url: "لطفا url بافرمت مناسب واردکنید. "
                }
            });
        });
        $('.url-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا url  را وارد کنید .",
                }
            });
        });
        /*==============================* Mobile MeliCode Email Comination */
        $('.mobile-email-format').each(function () {
            $(this).rules('add', {
                mobile_email: true
            });
        });
        $('.mobile-email-melliCode-format').each(function () {
            $(this).rules('add', {
                mobile_email_melliCode: true
            });
        });
        $('.email-melliCode-format').each(function () {
            $(this).rules('add', {
                email_melliCode: true
            });
        });
        $('.mobile-melliCode-format').each(function () {
            $(this).rules('add', {
                mobile_melliCode: true
            });
        });
        $('.mobile-email-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا شماره تلفن همراه یا ایمیل خود را وارد نمایید.",
                }
            });
        });
        $('.mobile-email-melliCode-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا شماره تلفن همراه، ایمیل یا کد ملی خود را وارد نمایید.",
                }
            });
        });
        $('.email-melliCode-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا ایمیل یا کد ملی خود را وارد نمایید.",
                }
            });
        });
        $('.mobile-melliCode-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا شماره تلفن همراه یا کد ملی خود را وارد نمایید.",
                }
            });
        });
        $('.usernames-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "لطفا نام کاربری خود را وارد نمایید.",
                }
            });
        });
        /*====================================  Checkbox*/
        $('.checkbox-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "انتخاب این گزینه الزامی است .",
                }
            });
        });

        /*==============================  Date time*/
        $('.time-format').each(function () {
            $(this).rules('add', {
                timeFormat: true
            });
        });
        $('.time-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "تنظیم ساعت الزامی است.",
                }
            });
        });
        $('.persian-date-format').each(function () {
            $(this).rules('add', {
                persianDateFormat: true
            });
        });
        $('.persian-date-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "تنظیم تاریخ الزامی است.",
                }
            });
        });
        $('.persian-year-format').each(function () {
            $(this).rules('add', {
                persianYearFormat: true
            });
        });
        $('.persian-year-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "تنظیم سال الزامی است.",
                }
            });
        });
        $('.persian-datetime-format').each(function () {
            $(this).rules('add', {
                persianDateTimeFormat: true
            });
        });
        $('.persian-datetime-required').each(function () {
            $(this).rules('add', {
                required: true,
                messages: {
                    required: "تنظیم تاریخ و ساعت الزامی است."
                }
            });
        });
        $('.ip-address-format').each(function () {
            $(this).rules('add', {
                ipAddressFormat: true
            });
        });
    };
    return {
        /*main function to initiate template pages*/
        init: function () {
            if ($('#form').length !== 0) {
                runValidator1($("#form"));
            } else {
                console.log($('#form').length);
            }
            /*            $(".form-validation").each(function (i) {
                            runValidator1($(this));
                        });*/
        }
    };
}();
/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/
