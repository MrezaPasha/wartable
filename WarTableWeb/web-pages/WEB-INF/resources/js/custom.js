/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/
var CustomJs = function () {
    var runTime = function () {
        var $dOut = $('#date'),
            $hOut = $('#hours'),
            $mOut = $('#minutes'),
            $sOut = $('#seconds'),
            $ampmOut = $('#ampm');
        var months = [
            'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'
        ];

        var days = [
            'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'
        ];

        function update() {
            var date = new Date();

            var ampm = date.getHours() < 12
                ? 'صبح'
                : 'عصر';

            var hours = date.getHours() == 0
                ? 12
                : date.getHours() > 12
                    ? date.getHours() - 12
                    : date.getHours();

            var minutes = date.getMinutes() < 10
                ? '0' + date.getMinutes()
                : date.getMinutes();

            var seconds = date.getSeconds() < 10
                ? '0' + date.getSeconds()
                : date.getSeconds();

            var dayOfWeek = days[date.getDay()];
            var month = months[date.getMonth()];
            var day = date.getDate();
            var year = date.getFullYear();

            var dateString = dayOfWeek + ', ' + month + ' ' + day + ', ' + year;

            $hOut.text(hours);
            $mOut.text(minutes);
            $sOut.text(seconds);
            $ampmOut.text(ampm);
        }

        update();
        window.setInterval(update, 1000);
    };
    var runAjaxSelect = function () {
        $(".i-select").irSelect();
    };
    var runAjaxSearch = function () {
        $("[i-search-opt-url]").irSearch();
    };
    var runAjaxFileExplorer = function () {
        $(".i-file-explorer").irFileExplorer();
    };
    var runRelatedList = function () {
        var clearDropTails = function (_dst) {
            if (_dst.prev().find(".select2-chosen").html() !== undefined) {
                _dst.prev().find(".select2-chosen").html("");
                _dst.prev().removeClass("select2-allowclear");
            }
            _dst.find("option").remove();
            _dst.append($("<option></option>")
                .attr("value", "")
                .text(""));
            var srcIds = _dst.data("ajax-src");
            if (srcIds !== undefined) {
                var srcId = srcIds.split(",");
                var lenx = srcId.length;
                var _dst2;
                for (var i = 0; i < lenx; i++) {
                    _dst2 = $("[data-ajax-dst='" + srcId[i] + "']");
                    if (_dst2 !== undefined) {
                        clearDropTails(_dst2);
                    }
                }
            }
        };

        var fetchList = function (_this) {
            var srcId = _this.data("ajax-src").split(",");
            var srcUrl = _this.data("ajax-uri").split(",");
            var srcTitle = _this.data("ajax-title");
            if (srcTitle !== undefined) {
                srcTitle = srcTitle.split(",");
            }
            var lenx = srcId.length;
            for (var i = 0; i < lenx; i++) {
                var _dst = $("[data-ajax-dst='" + srcId[i] + "']");
                if (_this.val() === "" || _this.val() <= 0) {
                    clearDropTails(_dst);
                    continue;
                }
                $.ajax({
                    url: srcUrl[i] + "/" + _this.val(),
                    Type: 'json',
                    success: function (data) {
                        clearDropTails(_dst);
                        if (data.list !== undefined) {
                            data = data.list;
                        }
                        if (srcTitle !== undefined && srcTitle[i] !== undefined) {
                            $.each(data, function (j, item) {
                                _dst.append($("<option></option>")
                                    .attr("value", item.id)
                                    .text(item[srcTitle[i]]));
                            });
                        } else {
                            $.each(data, function (j, item) {
                                _dst.append($("<option></option>")
                                    .attr("value", item.id)
                                    .text(item.title));
                            });
                        }
                    },
                    error: function () {
                        clearDropTails(_dst);
                    },
                    async: false
                });
            }
        };
        $("[data-ajax-src]").change(function () {
            fetchList($(this));
        });
        $("[data-ajax-auto-fetch='true']").each(function () {
            fetchList($(this));
        });
    };
    var runVisibility = function () {
        var vfun = function (_this) {
            var dpd = _this.data("visible-src");
            var vv = (_this.data("visible-value") + "").split(",");
            var lenx = vv.length;
            if (lenx > 1) {
                for (var i = 0; i < lenx; i++) {
                    $("[data-visible-dst='" + dpd + "']").addClass('hidden').find("*").attr('disabled', true);
                    if (_this.val() === vv[i]) {
                        $("[data-visible-dst='" + dpd + "'][data-visible-align-" + (i + 1) + "='false']").addClass('hidden').find("*").prop('disabled', true);
                        $("[data-visible-dst='" + dpd + "'][data-visible-align-" + (i + 1) + "='true']").removeClass('hidden').find("*").attr("disabled", false);
                        break;
                    }
                }
            } else {
                if (_this.val() === "") {
                    $("[data-visible-dst='" + dpd + "']").addClass('hidden').find("*").attr('disabled', true);
                } else if (_this.val() != _this.data("visible-value")) {
                    $("[data-visible-dst='" + dpd + "'][data-visible-align='true']").addClass('hidden').find("*").attr('disabled', true);
                    $("[data-visible-dst='" + dpd + "'][data-visible-align='false']").removeClass('hidden').find("*").attr("disabled", false);
                } else {
                    $("[data-visible-dst='" + dpd + "'][data-visible-align='true']").removeClass('hidden').find("*").attr("disabled", false);
                    $("[data-visible-dst='" + dpd + "'][data-visible-align='false']").addClass('hidden').find("*").attr('disabled', true);
                }
            }
        };
        var vcheck = function (_this) {
            var dpd = _this.data("visible-src");
            if (_this.val() === "") {
                $("[data-visible-dst='" + dpd + "']").addClass('hidden');
            } else if (_this.prop("checked")) {
                $("[data-visible-dst='" + dpd + "'][data-visible-align='true']").removeClass('hidden');
                $("[data-visible-dst='" + dpd + "'][data-visible-align='false']").addClass('hidden');
            } else {
                $("[data-visible-dst='" + dpd + "'][data-visible-align='true']").addClass('hidden');
                $("[data-visible-dst='" + dpd + "'][data-visible-align='false']").removeClass('hidden');
            }
        };
        $("[data-visible-src]").each(function () {
            if ($(this).attr("type") === "checkbox") {
                vcheck($(this));
            } else {
                vfun($(this));
            }
        });
        $("[data-visible-src]").change(function () {
            if ($(this).attr("type") === "checkbox") {
                vcheck($(this));
            } else {
                vfun($(this));
            }
        });


    };
    var runPersianDateTime = function () {
        $(".persian-datetime-format").each(function (i) {
            var _input = $(this);
            $(this).pDatepicker({
                format: "YYYY/MM/DD HH:mm:ss",
                autoClose: true,
                viewMode: _input.data("view-mode"),
                initialValue: (_input.val()) ? false : _input.data("initial-value"),
                fixTime: (_input.val()) ? false : _input.data("fix-time"),
                timePicker: {
                    enabled: true,
                    showMeridian: false
                },
                initPersianDate: function () {
                    if (_input.val()) {
                        try {
                            var dt = _input.val().split(" ");
                            var d = dt[0].split("/");
                            var t = dt[1].split(":");
                            for (var i = 0; i < d.length; i++)
                                d[i] = parseInt(d[i], 10);
                            for (var i = 0; i < t.length; i++)
                                t[i] = parseInt(t[i], 10);
                            return new persianDate([d[0], d[1], d[2], t[0], t[1], t[2]]);
                        } catch (e) {
                        }
                    }
                }
            });
        });
        $(".persian-date-format").each(function (i) {
            var _input = $(this);
            $(this).pDatepicker({
                format: "YYYY/MM/DD",
                autoClose: true,
                viewMode: _input.data("view-mode"),
                initialValue: (_input.val()) ? false : _input.data("initial-value"),
                timePicker: {
                    enabled: true,
                    showMeridian: false
                },
                initPersianDate: function () {
                    if (_input.val()) {
                        try {
                            var dt = _input.val().split(" ");
                            var d = dt[0].split("/");
                            var t = dt[1].split(":");
                            for (var i = 0; i < d.length; i++)
                                d[i] = parseInt(d[i], 10);
                            for (var i = 0; i < t.length; i++)
                                t[i] = parseInt(t[i], 10);
                            return new persianDate([d[0], d[1], d[2], t[0], t[1], t[2]]);
                        } catch (e) {
                        }
                    }
                }
            });
        });

        $(".persian-year-format").each(function (i) {
            var _input = $(this);
            $(this).pDatepicker({
                format: "YYYY",
                autoclose: true,
                initialValue: (_input.val()) ? false : _input.data("initial-value"),
                yearPicker: {
                    enabled: true
                },
                dayPicker: {
                    enabled: false
                },
                monthPicker: {
                    enabled: false
                },
                initPersianYear: function () {
                    if (_input.val()) {
                        try {
                            var dt = _input.val().split(" ");
                            var d = dt[0].split("/");
                            var t = dt[1].split(":");
                            for (var i = 0; i < d.length; i++)
                                d[i] = parseInt(d[i], 10);
                            for (var i = 0; i < t.length; i++)
                                t[i] = parseInt(t[i], 10);
                            return new persianDate([d[0], d[1], d[2], t[0], t[1], t[2]]);
                        } catch (e) {
                        }
                    }
                }
            });
        });
    };
    var runCKEditor = function () {
        /*CKEDITOR.disableAutoInline = true;*/
        var cke = $('textarea.ckeditor');
        CKEDITOR.config.customConfig = 'config.js';
        cke.ckeditor();
        if ($('.editor-tail')) {
            try {
                CKEDITOR.instances[cke.attr('name')].on('focus', function (e) {
                    $('.editor-tail').slideDown(750);
                });
            } catch (e) {
            }
        }
    };
    var runCKEditorMin = function () {
        var cke = $('textarea.ckeditor');
        CKEDITOR.config.customConfig = 'config-min.js';
        cke.ckeditor();
        if ($('.editor-tail')) {
            try {
                CKEDITOR.instances[cke.attr('name')].on('focus', function (e) {
                    $('.editor-tail').slideDown(750);
                });
            } catch (e) {
            }
        }
    };
    var runCKEditorZero = function () {
        var cke = $('textarea.ckeditor');
        CKEDITOR.config.customConfig = 'config-zero.js';
        cke.ckeditor();
        if ($('.editor-tail')) {
            try {
                CKEDITOR.instances[cke.attr('name')].on('focus', function (e) {
                    $('.editor-tail').slideDown(750);
                });
            } catch (e) {
            }
        }
    };
    var runDropzone = function () {
        var myDropzone = $('.dropzone');
        var previewTemp;
        var clickable;
        var dictDefaultMessage;
        var mappedElemCls = myDropzone.data("mapped-class");
        var changeStatusButtons = function (thisDrpZn) {
            if (!mappedElemCls) {
                return;
            }

            var statusSuccess = true;
            $.each(thisDrpZn.files, function (i, item) {

                if (item.status != "success") {
                    statusSuccess = false;
                }
            });
            $("." + mappedElemCls).attr("disabled", statusSuccess ? false : "disabled");

        };
        if (myDropzone.data("view-type") === "view") {
            dictDefaultMessage = 'فایل های خود را کشیده و در این قسمت رها کنید';
            clickable = true;
            myDropzone.addClass("view")
            previewTemp = "<div class=\"dz-preview dz-file-preview\">\n "
                + "<div class=\"dz-image\"><img data-dz-thumbnail  style=\"cursor: move\"  draggable=\"true\"/></div>\n "
                + "<div class=\"dz-details\">\n  <div class=\"dz-filename\"><a target=\"_blank\" draggable=\"true\" data-dz-name></a></div>\n <div class=\"dz-size\"><span data-dz-size></span></div>\n </div>\n "
                + "<div class=\"dz-progress\"><span class=\"dz-upload\" data-dz-uploadprogress></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n  <div class=\"dz-success-mark\">\n    <svg width=\"54px\" height=\"54px\" viewBox=\"0 0 54 54\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:sketch=\"http://www.bohemiancoding.com/sketch/ns\">\n      <title>Check</title>\n      <defs></defs>\n      <g id=\"Page-1\" stroke=\"none\" stroke-width=\"1\" fill=\"none\" fill-rule=\"evenodd\" sketch:type=\"MSPage\">\n        <path d=\"M23.5,31.8431458 L17.5852419,25.9283877 C16.0248253,24.3679711 13.4910294,24.366835 11.9289322,25.9289322 C10.3700136,27.4878508 10.3665912,30.0234455 11.9283877,31.5852419 L20.4147581,40.0716123 C20.5133999,40.1702541 20.6159315,40.2626649 20.7218615,40.3488435 C22.2835669,41.8725651 24.794234,41.8626202 26.3461564,40.3106978 L43.3106978,23.3461564 C44.8771021,21.7797521 44.8758057,19.2483887 43.3137085,17.6862915 C41.7547899,16.1273729 39.2176035,16.1255422 37.6538436,17.6893022 L23.5,31.8431458 Z M27,53 C41.3594035,53 53,41.3594035 53,27 C53,12.6405965 41.3594035,1 27,1 C12.6405965,1 1,12.6405965 1,27 C1,41.3594035 12.6405965,53 27,53 Z\" id=\"Oval-2\" stroke-opacity=\"0.198794158\" stroke=\"#747474\" fill-opacity=\"0.816519475\" fill=\"#FFFFFF\" sketch:type=\"MSShapeGroup\"></path>\n      </g>\n    </svg>\n  </div>\n  <div class=\"dz-error-mark\">\n    <svg width=\"54px\" height=\"54px\" viewBox=\"0 0 54 54\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:sketch=\"http://www.bohemiancoding.com/sketch/ns\">\n      <title>Error</title>\n      <defs></defs>\n      <g id=\"Page-1\" stroke=\"none\" stroke-width=\"1\" fill=\"none\" fill-rule=\"evenodd\" sketch:type=\"MSPage\">\n        <g id=\"Check-+-Oval-2\" sketch:type=\"MSLayerGroup\" stroke=\"#747474\" stroke-opacity=\"0.198794158\" fill=\"#FFFFFF\" fill-opacity=\"0.816519475\">\n          <path d=\"M32.6568542,29 L38.3106978,23.3461564 C39.8771021,21.7797521 39.8758057,19.2483887 38.3137085,17.6862915 C36.7547899,16.1273729 34.2176035,16.1255422 32.6538436,17.6893022 L27,23.3431458 L21.3461564,17.6893022 C19.7823965,16.1255422 17.2452101,16.1273729 15.6862915,17.6862915 C14.1241943,19.2483887 14.1228979,21.7797521 15.6893022,23.3461564 L21.3431458,29 L15.6893022,34.6538436 C14.1228979,36.2202479 14.1241943,38.7516113 15.6862915,40.3137085 C17.2452101,41.8726271 19.7823965,41.8744578 21.3461564,40.3106978 L27,34.6568542 L32.6538436,40.3106978 C34.2176035,41.8744578 36.7547899,41.8726271 38.3137085,40.3137085 C39.8758057,38.7516113 39.8771021,36.2202479 38.3106978,34.6538436 L32.6568542,29 Z M27,53 C41.3594035,53 53,41.3594035 53,27 C53,12.6405965 41.3594035,1 27,1 C12.6405965,1 1,12.6405965 1,27 C1,41.3594035 12.6405965,53 27,53 Z\" id=\"Oval-2\" sketch:type=\"MSShapeGroup\"></path>\n        </g>\n      </g>\n    </svg>\n  </div>\n</div>";
        } else {
            clickable = ".dz-add-file";
            dictDefaultMessage = '';
            myDropzone.html('<div class="dz-header">'
                + '<a href="javascript:void(0)" class="dz-add-file"><i class="icon-attach"></i>افزودن فایل...</a>'
                + '<span class="dz-add-file-des">..< فایل های خود را کشیده و در این قسمت رها کنید >..</span>'
                + '<div class="dz-buttons">'
                /*                + '<input class="dz-select-all" type="checkbox" name="selectBoxAll" data-dz-select-all/>'
                 + '<a class="dz-remove-all" href="javascript:undefined;" data-dz-remove-all>حذف</a>'
                 + '<a class="dz-append-all" href="javascript:undefined;" data-dz-append-all>افزودن به متن</a>'
                 */ + '</div></div>'
            );
            myDropzone.addClass("list")
            previewTemp = "<div class=\"dz-preview dz-file-preview\">\n "
                + "<div class=\"dz-image\"><img data-dz-thumbnail  style=\"cursor: move\"  draggable=\"true\"/></div>\n "
                + "<div class=\"dz-file-box\">"
                + "<div class=\"dz-filename\"><a target=\"_blank\" draggable=\"true\" data-dz-name></a></div>\n"
                + "<div class=\"dz-size\"><span data-dz-size></span></div>\n"
                + "</div>"
                + "<div class=\"dz-result-box\">"
                + "<div class=\"dz-progress\"><span class=\"dz-upload\" data-dz-uploadprogress></span></div>\n"
                + "<div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n"
                + "</div>"
                + "<div class=\"dz-success-mark\">\n"
                + "<svg width=\"30px\" height=\"30px\" viewBox=\"0 0 54 54\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:sketch=\"http://www.bohemiancoding.com/sketch/ns\">\n "
                + " <title>Check</title>\n      <defs></defs>\n      <g id=\"Page-1\" stroke=\"#25F82D\" stroke-width=\"1\" fill=\"#25F82D\" fill-rule=\"evenodd\" sketch:type=\"MSPage\">\n "
                + "<path d=\"M23.5,31.8431458 L17.5852419,25.9283877 C16.0248253,24.3679711 13.4910294,24.366835 11.9289322,25.9289322 C10.3700136,27.4878508 10.3665912,30.0234455 11.9283877,31.5852419 L20.4147581,40.0716123 C20.5133999,40.1702541 20.6159315,40.2626649 20.7218615,40.3488435 C22.2835669,41.8725651 24.794234,41.8626202 26.3461564,40.3106978 L43.3106978,23.3461564 C44.8771021,21.7797521 44.8758057,19.2483887 43.3137085,17.6862915 C41.7547899,16.1273729 39.2176035,16.1255422 37.6538436,17.6893022 L23.5,31.8431458 Z M27,53 C41.3594035,53 53,41.3594035 53,27 C53,12.6405965 41.3594035,1 27,1 C12.6405965,1 1,12.6405965 1,27 C1,41.3594035 12.6405965,53 27,53 Z\" id=\"Oval-2\" stroke-opacity=\"0.198794158\" stroke=\"#25F82D\" fill-opacity=\"0.816519475\" fill=\"#25F82D\" sketch:type=\"MSShapeGroup\"></path>\n      </g>\n    </svg>\n  </div>\n"

                + "<div class=\"dz-error-mark\">\n "
                + "<svg width=\"30px\" height=\"30px\" viewBox=\"0 0 54 54\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:sketch=\"http://www.bohemiancoding.com/sketch/ns\">\n      <title>Error</title>\n      <defs></defs>\n      <g id=\"Page-1\" stroke=\"none\" stroke-width=\"1\" fill=\"#25f82d\" fill-rule=\"evenodd\" sketch:type=\"MSPage\">\n        <g id=\"Check-+-Oval-2\" sketch:type=\"MSLayerGroup\" stroke=\"red\" stroke-opacity=\"0.198794158\" fill=\"red\" fill-opacity=\"0.816519475\">\n          <path d=\"M32.6568542,29 L38.3106978,23.3461564 C39.8771021,21.7797521 39.8758057,19.2483887 38.3137085,17.6862915 C36.7547899,16.1273729 34.2176035,16.1255422 32.6538436,17.6893022 L27,23.3431458 L21.3461564,17.6893022 C19.7823965,16.1255422 17.2452101,16.1273729 15.6862915,17.6862915 C14.1241943,19.2483887 14.1228979,21.7797521 15.6893022,23.3461564 L21.3431458,29 L15.6893022,34.6538436 C14.1228979,36.2202479 14.1241943,38.7516113 15.6862915,40.3137085 C17.2452101,41.8726271 19.7823965,41.8744578 21.3461564,40.3106978 L27,34.6568542 L32.6538436,40.3106978 C34.2176035,41.8744578 36.7547899,41.8726271 38.3137085,40.3137085 C39.8758057,38.7516113 39.8771021,36.2202479 38.3106978,34.6538436 L32.6568542,29 Z M27,53 C41.3594035,53 53,41.3594035 53,27 C53,12.6405965 41.3594035,1 27,1 C12.6405965,1 1,12.6405965 1,27 C1,41.3594035 12.6405965,53 27,53 Z\" id=\"Oval-2\" sketch:type=\"MSShapeGroup\"></path>\n  "
                + "   </g>\n      </g>\n    </svg>\n  </div>\n</div>";
        }

        var ckeditorId = myDropzone.data("ckeditor-id");
        if (!ckeditorId) {
            ckeditorId = "text";
        }
        myDropzone.dropzone({
            ckeditorId: ckeditorId,
            url: myDropzone.data("url"),
            additionalParams: {},
            paramName: "file", /* The name that will be used to transfer the file*/
            maxFilesize: 25.0, /* MB*/
            maxFiles: 100,
            multiSelectFile: myDropzone.data("multi-select"),
            appendToText: myDropzone.data("append-text"),
            addRemoveLinks: true,
            createImageThumbnails: true,
            clickable: clickable, /* Define the element that should be used as click trigger to select files.*/
            dictDefaultMessage: dictDefaultMessage,
            dictFallbackMessage: 'مرورگر شما درگ-اند-دراپ را پشتیبانی نمی کند!',
            dictFallbackText: 'لطفا از فرم fallback برای بارگذاری فایل استفاده کنید.',
            dictInvalidFileType: 'فرمت فایل معتبر نمی باشد.',
            dictFileTooBig: 'حجم فایل بیش از حد مجاز ({{maxFilesize}}مگابایت) است.',
            dictResponseError: 'خطا در بارگذاری فایل: {{statusCode}}',
            dictCancelUpload: 'لغو',
            dictCancelUploadConfirmation: 'آیا لغو بارگذاری را تایید می کنید؟',
            dictRemoveFile: 'حذف',
            dictAppendToText: 'افزودن به متن',
            dictMaxFilesExceeded: 'شما می توانید حداکثر {{maxFiles}} فایل بارگذاری کنید.',
            dictLinkMovement: 'برای استفاده از لینک، آن را داخل ویرایشگر کشیده و رها کنید.',
            dictImageMovement: 'برای استفاده از عکس، آن را داخل ویرایشگر کشیده و رها کنید.',
            previewTemplate: previewTemp,
            init: function () {
                var thisDropzone = this;
                if ($('#files_tail').val() != null && $('#files_tail').val().length > 0) {
                    $.getJSON($('#cpId').val() + "/panel/file/downloadSerFi?params=" + $('#files_tail').val()).done(function (data) {
                        if (data != '') {
                            $.each(data, function (index, item) {
                                /* Create the mock file:*/
                                var file = {
                                    name: item.name,
                                    size: item.size,
                                    customId: item.id,
                                    customName: item.name,
                                    orginalPath: item.orginalPath,
                                    path: item.path,
                                    status: 'success',
                                    accepted: true
                                };
                                /* add file to list*/
                                thisDropzone.files.push(file);
                                thisDropzone.emit("addedfile", file);
                                thisDropzone.emit("resize", file);
                                /*                            thisDropzone.emit("success", file);*/
                                thisDropzone.emit("complete", file);
                                /* If you use the maxFiles option, make sure you adjust it to the correct amount:*/
                                /*                            var existingFileCount = 1; /* The number of files already uploaded*/
                                /*                            thisDropzone.options.maxFiles = thisDropzone.options.maxFiles - existingFileCount;*/

                                if (file.previewElement) {
                                    if (item.isImage == true) {
                                        thisDropzone.emit("thumbnail", file, $('#cpId').val() + item.path);
                                        /*                                    $(file.previewElement).find('[data-dz-thumbnail]').attr("title", thisDropzone.options.dictImageMovement);*/
                                    }
                                    $(file.previewElement).find('[data-dz-name]').attr("href", $('#cpId').val() + item.orginalPath);
                                    $(file.previewElement).find('[data-dz-name]').attr("title", thisDropzone.options.dictLinkMovement);
                                    $(file.previewElement).find('[data-dz-name]').html(item.name);
                                    file.previewElement.classList.add("dz-success");
                                }
                            });
                        }
                    });
                }

                $('.dz-select-all').change(function () {
                    var chb = $('.dz-select-all').prop('checked');
                    $.each(thisDropzone.getAcceptedFiles(), function (i, item) {
                        item._selectFile.checked = chb;
                    });
                });

                /** Append all the additional input data of your form here!  **/
                this.on("sending", function (file, xhr, formData) {
                    var addParams = myDropzone.attr("data-params");
                    if (addParams != undefined) {
                        console.log(addParams);
                        addParams = JSON.parse(addParams);
                        for (key in addParams) {
                            formData.append(key, addParams[key]);
                        }
                    }
                });
            },
            removedfile: function (file) {
                var _ref;
                var thisDropzone = this;
                if (file.previewElement) {
                    if ((_ref = file.previewElement) != null) {
                        $.ajax({
                            url: $('#cpId').val() + "/panel/file/trashSerFi/" + file.customId,
                            Type: 'json',
                            success: function (data) {
                                var filesTail = "";
                                $.each(thisDropzone.getAcceptedFiles(), function (i, item) {
                                    filesTail += item.customId + ",";
                                });
                                $('#files_tail').val(filesTail.substr(0, filesTail.length - 1));
                                _ref.parentNode.removeChild(file.previewElement);
                            },
                            error: function (data) {
                                var filesTail = "";
                                $.each(thisDropzone.getAcceptedFiles(), function (i, item) {
                                    filesTail += item.customId + ",";
                                });
                                $('#files_tail').val(filesTail.substr(0, filesTail.length - 1));
                                _ref.parentNode.removeChild(file.previewElement);
                            }
                        });
                    }
                }
                changeStatusButtons(this);
                return this._updateMaxFilesReachedClass();
            },
            success: function (file, data) {
                var thisDropzone = this;
                file.customName = data.name;
                file.customId = data.id;
                file.path = data.path;
                file.orginalPath = data.orginalPath;
                var filesTail = "";
                changeStatusButtons(this);
                $.each(this.getAcceptedFiles(), function (i, item) {
                    filesTail += item.customId + ",";
                });
                $('#files_tail').val(filesTail.substr(0, filesTail.length - 1));
                if (file.previewElement) {
                    if (data.isImage == true) {
                        thisDropzone.emit("thumbnail", file, $('#cpId').val() + data.path);
                    }
                    $(file.previewElement).find('[data-dz-name]').attr("href", $('#cpId').val() + data.orginalPath);
                    $(file.previewElement).find('[data-dz-name]').attr("title", thisDropzone.options.dictLinkMovement);
                    $(file.previewElement).find('[data-dz-name]').html(data.name);
                    return file.previewElement.classList.add("dz-success");
                }
            },
            thumbnail: function (file, dataUrl) {
                var thumbnailElement, _i, _len, _ref;
                if (file.previewElement) {
                    file.previewElement.classList.remove("dz-file-preview");
                    _ref = file.previewElement.querySelectorAll("[data-dz-thumbnail]");
                    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                        thumbnailElement = _ref[_i];
                        thumbnailElement.alt = file.name;
                        thumbnailElement.src = dataUrl;
                        /*                        thumbnailElement.style = "width: " + this.options.thumbnailWidth
                         + ";height: " + this.options.thumbnailHeight + ";";
                         */
                    }
                    return setTimeout(((function (_this) {
                        return function () {
                            return file.previewElement.classList.add("dz-image-preview");
                        };
                    })(this)), 1);
                }
            },
            addedfiles: function () {
                changeStatusButtons(this);
            }
        });
    };
    var runFarsiInput = function () {
        $(".text-fa").farsiInput();
    };
    var runSearchLoader = function () {

        var cSpeed = 9;
        var cWidth = 64;
        var cHeight = 64;
        var cTotalFrames = 15;
        var cFrameWidth = 64;
        var cImageSrc = $('#cpId').val() + '/resources/images/loader/sprites.gif';

        var cImageTimeout = false;
        var cIndex = 0;
        var cXpos = 0;
        var cPreloaderTimeout = false;
        var SECONDS_BETWEEN_FRAMES = 0;

        startAnimation = function () {

            document.getElementById('loaderImage').style.backgroundImage = 'url(' + cImageSrc + ')';
            document.getElementById('loaderImage').style.width = cWidth + 'px';
            document.getElementById('loaderImage').style.height = cHeight + 'px';

            FPS = Math.round(100 / cSpeed);
            SECONDS_BETWEEN_FRAMES = 1 / FPS;

            cPreloaderTimeout = setTimeout('continueAnimation()', SECONDS_BETWEEN_FRAMES / 1000);

        };

        continueAnimation = function () {

            cXpos += cFrameWidth;
            cIndex += 1;

            if (cIndex >= cTotalFrames) {
                cXpos = 0;
                cIndex = 0;
            }

            if (document.getElementById('loaderImage'))
                document.getElementById('loaderImage').style.backgroundPosition = (-cXpos) + 'px 0';

            cPreloaderTimeout = setTimeout('continueAnimation()', SECONDS_BETWEEN_FRAMES * 1000);
        }

        function stopAnimation() {
            clearTimeout(cPreloaderTimeout);
            cPreloaderTimeout = false;
        }

        function imageLoader(s, fun) {
            clearTimeout(cImageTimeout);
            cImageTimeout = 0;
            genImage = new Image();
            genImage.onload = function () {
                cImageTimeout = setTimeout(fun, 0)
            };
            genImage.onerror = new Function('alert(\'Could not load the image\')');
            genImage.src = s;
        }

        new imageLoader(cImageSrc, 'startAnimation()');
    };
    var runFarsiInputNum = function () {
        $(".input-number-fa").farsiInputNum();
    };
    var runSelect2 = function () {
        if ($(".search-select").length > 0) {
            $(".search-select").select2({
                placeholder: " ",
                allowClear: true,
                dir: "rtl",
                language: "fa"
            });
        }
    };
    var runTooltip = function () {
        $('.tooltips').tooltip();
    };
    var runJsonTable = function () {
        $.each($("[ir-json-table]"), function (i, v) {
            try {
                var dt = $(this).attr("ir-json-table-data");
                var jdt = JSON.parse(dt);
                var res = "<table class='json-table'><tr>";
                for (var key in jdt[0]) {
                    res += "<th>" + key + "</th>";
                }
                res += "</tr>";

                $.each(jdt, function (i, vv) {
                    res += "<tr>";
                    for (var key in vv) {
                        res += "<td>" + vv[key] + "</td>";
                    }

                    res += "</tr>";
                });

                res += "</table>";

                $(this).html(res);
            } catch (e) {
            }
        });
    };
    var runTagsInput = function (tagInput, tagList) {
        var substringMatcher = function (strs) {
            return function findMatches(q, cb) {
                var matches, substringRegex;
                matches = [];
                substrRegex = new RegExp(q, 'i');
                $.each(strs, function (i, str) {
                    if (substrRegex.test(str)) {
                        matches.push(str);
                    }
                });
                cb(matches);
            };
        };
        tagInput.tagsinput({
            typeaheadjs: {
                name: 'tagList',
                source: substringMatcher(tagList)
            },
            freeInput: tagInput.data("free"),
            maxTags: tagInput.data("max-tags"),
            trimValue: true
        });
        $('#form').on('keyup keypress', function (e) {
            var keyCode = e.keyCode || e.which;
            if (keyCode === 13) {
                e.preventDefault();
                return false;
            }
        });
    };
    var runTable = function () {
        var runPost = function (page, size, query) {
            $.ajax({
                    type: "POST",
                    url: "",
                    data: {
                        "page": page,
                        "size": size,
                        "query": query
                    },
                    dataType: "json",
                    extend: 'print',
                    dom: 'Bfrtip',
                    buttons: [
                        'print'
                    ],
                    success: function (data) {
                        alert(data);
                    }
                }
            );
        };
        $('#table_list_x').on('click', '.delete-row', function (e) {
            var bu = $(this).data("back");
            var prefix = "";
            for (i = 0; i < bu; i++) {
                prefix += "../";
            }
            e.preventDefault();
            var nRow = $(this).parents('tr')[0];
            var aData = oTable.fnGetData(nRow);
            bootbox.confirm("آیا از حذف اطلاعات " + "<b style='color:red'>" + aData[1] + "</b>" + " با شناسه " + "<b style='color:red'>" + aData[0] + "</b>" + " اطمینان دارید؟", function (result) {
                if (result) {
                    window.location.href = prefix + 'trash/' + aData[0];
                }
            });
        });
        $('#myTable').DataTable({
            buttons: [
                'print'
            ]
        });
        var tb = $('#table_list_x');
        var asI = tb.data("sort-index") ? tb.data("sort-index") : 0;
        var asS = tb.data("sort-type") ? tb.data("sort-type") : 'desc';
        var adL = tb.data("display-len") ? tb.data("display-len") : 10;
        var disableSort = tb.data("disable-sort") ? tb.data("disable-sort") : false;
        var oTable = tb.dataTable({
            "aoColumnDefs": [{
                "aTargets": [0]
            }],
            "oLanguage": {
                "sLengthMenu": "نمایش _MENU_ ردیف",
                "sSearch": "",
                "oPaginate": {
                    "sPrevious": "",
                    "sNext": ""
                }
            },
            "aaSorting": [[asI, asS]],
            "enableSort": !disableSort,
            "enableFirstSort": true,
            "enableLastSort": true,
            dom: 'Bfrtip',
            buttons: [
                'print'
            ],
            "aLengthMenu": [[5, 10, 15, 20, -1], ["5", "10", "15", "20", "همه"] /* change per page values here*/
            ],
            /* set the initial value*/
            "iDisplayLength": adL
        });
        $('#table_list_x_wrapper .dataTables_filter input').addClass("form-control input-sm").attr("placeholder", "جستجو");
        /* modify table search input*/
        $('#table_list_x_wrapper .dataTables_length select').addClass("m-wrap small");
        /* modify table per page dropdown*/
        $('#table_list_x_wrapper .dataTables_length select').select2();
        /* initialzie select2 dropdown*/
        $('#table_list_x_column_toggler input[type="checkbox"]').change(function () {
            /* Get the DataTables object again - this is not a recreation, just a get of the object */
            var iCol = parseInt($(this).attr("data-column"));
            var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
            oTable.fnSetColumnVis(iCol, (bVis ? false : true));
        });
    };
    var runTableEditable = function (tableId, tailId, modelClass, params) {
        var etable = $('#' + tableId);
        var tail = $('#' + tailId).val();
        var jsdata = [];
        if (tail != null && tail.length > 0) {
            if (/^[\],:{}\s]*$/.test(tail.replace(/\\["\\\/bfnrtu]/g, '@').replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
                jsdata = jQuery.parseJSON(tail);
            }
        }
        var newRow = false;
        var actualEditingRow = null;

        function fillDate() {
            for (k = 0; k < jsdata.length; k++) {
                newRow = true;

                var inps = [];
                var j = 0;
                for (var y in jsdata[k]) {
                    inps[j] = jsdata[k][y];
                    j++;
                }
                inps[j] = '';
                inps[j + 1] = '';
                var aiNew = oTable.fnAddData(inps);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow);
                actualEditingRow = nRow;
                saveRow(oTable, nRow);
            }
        }

        function restoreRow(oTable, nRow) {
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);

            for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                oTable.fnUpdate(aData[i], nRow, i, false);
            }
            oTable.fnDraw();
        }

        function editRow(oTable, nRow) {
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);
            jqTds.addClass("tlist-col");

            for (i = 0; i < params.length; i++) {
                if (params[i][0] === 'tx') {
                    jqTds[i].innerHTML = '<input type="text"'
                        + ((params[i][2] != null && params[i][2].length > 0) ? ' style="width:' + params[i][2] + ';"' : '')
                        + ' class="form-control string-required" value="' + aData[i] + '">';
                } else if (params[i][0] === 'nu') {
                    jqTds[i].innerHTML = '<input type="number" min="' + params[i][3] + '" max="' + params[i][4] + '" class="form-control"'
                        + ((params[i][2] != null && params[i][2].length > 0) ? ' style="width:' + params[i][2] + ';"' : '')
                        + ' value="' + aData[i] + '">';
                } else if (params[i][0] === 'da') {
                    jqTds[i].innerHTML = '<div class="input-group" data-mddatetimepicker="true" data-trigger="click" data-targetselector="#tDate_' + i + '">'
                        + '<input id="tDate_' + i + '" type="text" '
                        + ((params[i][2] != null && params[i][2].length > 0) ? ' style="width:' + params[i][2] + ';"' : '')
                        + ' class="form-control" value="'
                        + aData[i] + '" data-mddatetimepicker="true" data-placement="right" data-englishnumber="true" >'
                        + '<span class="input-group-addon"><span class="clip-calendar"></span></span></div>';
                } else if (params[i][0] === 'sl') {
                    var opts = "";
                    for (j = 0; j < params[i][3].length; j++) {

                        opts += '<option ' + (aData[i] === params[i][3][j] ? 'selected="selected"' : '') + ' value=' + (params[i][3][j]).replace(/ /g, "_") + '>' + params[i][3][j] + '</option>';
                    }
                    jqTds[i].innerHTML = '<select '
                        + ((params[i][2] != null && params[i][2].length > 0) ? ' style="width:' + params[i][2] + ';"' : '')
                        + ' class="form-control search-select">'
                        + '<option value=""></option>'
                        + opts
                        + ' </select>';
                }
            }
            jqTds[i].innerHTML = '<a class="save-row" href="">ذخیره</a>';
            jqTds[i + 1].innerHTML = '<a class="cancel-row" href="">لغو</a>';
        }

        function saveRow(oTable, nRow) {
            var jqInputs = $('input', nRow);
            var jqSelects = $('select', nRow);
            for (i = 0, p = 0, s = 0; i < params.length; i++) {
                if (params[i][0] === 'tx' || params[i][0] === 'nu' || params[i][0] === 'da') {
                    oTable.fnUpdate(jqInputs[p].value, nRow, i, false);
                    p++;
                } else if (params[i][0] === 'sl') {
                    oTable.fnUpdate(jqSelects[s].value.replace(/_/g, " "), nRow, i, false);
                    s++;
                }
            }
            oTable.fnUpdate('<a class="edit-row" href="">ویرایش</a>', nRow, i, false);
            oTable.fnUpdate('<a class="delete-row" href="">حذف</a>', nRow, i + 1, false);
            oTable.fnDraw();
            newRow = false;
            actualEditingRow = null;
        }

        function toJson() {
            var tHead = oTable.fnSettings().aoColumns;
            var nRow = oTable.fnGetNodes();
            var sjs = "[";
            var rowlen = nRow.length - 1;
            for (i = 0; i <= rowlen; i++) {
                sjs += "{";
                var aData = oTable.fnGetData(nRow[i]);
                var colln = aData.length - 3;
                for (j = 0; j <= colln; j++) {
                    sjs += '"' + tHead[j].sTitle + '" : "' + aData[j] + '"';
                    sjs += j !== colln ? "," : "";
                }
                sjs += i !== rowlen ? "}," : "}";
            }
            sjs += "]";
            $('#' + tailId).val(sjs);
        }

        $('body').on('click', '.add-row.' + modelClass, function (e) {
            e.preventDefault();
            if (newRow == false) {
                if (actualEditingRow) {
                    restoreRow(oTable, actualEditingRow);
                }
                newRow = true;
                var inps = [];
                for (i = 0; i < params.length + 2; i++) {
                    inps[i] = '';
                }
                var aiNew = oTable.fnAddData(inps);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow);
                actualEditingRow = nRow;
            }
        });
        etable.on('click', '.cancel-row', function (e) {

            e.preventDefault();
            if (newRow) {
                newRow = false;
                actualEditingRow = null;
                var nRow = $(this).parents('tr')[0];
                oTable.fnDeleteRow(nRow);

            } else {
                restoreRow(oTable, actualEditingRow);
                actualEditingRow = null;
            }
        });
        etable.on('click', '.delete-row', function (e) {
            e.preventDefault();
            if (newRow && actualEditingRow) {
                oTable.fnDeleteRow(actualEditingRow);
                newRow = false;

            }
            var nRow = $(this).parents('tr')[0];
            bootbox.confirm("آیا شما از حذف اطلاعات مطمئن هستید؟", function (result) {
                if (result) {
                    oTable.fnDeleteRow(nRow);
                    toJson();
                }
            });

        });
        etable.on('click', '.save-row', function (e) {
            e.preventDefault();

            var nRow = $(this).parents('tr')[0];
            saveRow(oTable, nRow);
            toJson();

        });
        etable.on('click', '.edit-row', function (e) {
            e.preventDefault();
            if (actualEditingRow) {
                if (newRow) {
                    oTable.fnDeleteRow(actualEditingRow);
                    newRow = false;
                } else {
                    restoreRow(oTable, actualEditingRow);

                }
            }
            var nRow = $(this).parents('tr')[0];
            editRow(oTable, nRow);
            actualEditingRow = nRow;
        });
        var oTable = $('#' + tableId).dataTable({
            "aoColumnDefs": [{
                "aTargets": [0]
            }],
            "oLanguage": {
                "sLengthMenu": "نمایش _MENU_ ردیف",
                "sSearch": "",
                "oPaginate": {
                    "sPrevious": "",
                    "sNext": ""
                }
            },
            "aaSorting": [[0, 'asc']],
            "enableSort": false,
            "enableFirstSort": false,
            "enableLastSort": false,
            "aLengthMenu": [[5, 10, 15, 20, -1], ["5", "10", "15", "20", "همه"] /* change per page values here*/
            ],
            /* set the initial value*/
            "iDisplayLength": 10
        });
        $('#' + tableId + '_wrapper .dataTables_filter input').addClass("form-control input-sm").attr("placeholder", "جستجو");
        /* modify table search input*/
        $('#' + tableId + '_wrapper .dataTables_length select').addClass("m-wrap small");
        /* modify table per page dropdown*/
        $('#' + tableId + '_wrapper .dataTables_length select').select2();
        /* initialzie select2 dropdown*/
        $('#' + tableId + '_column_toggler input[type="checkbox"]').change(function () {
            /* Get the DataTables object again - this is not a recreation, just a get of the object */
            var iCol = parseInt($(this).attr("data-column"));
            var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
            oTable.fnSetColumnVis(iCol, (bVis ? false : true));
        });
        fillDate();
    };
    var runCloseBrowser = function () {
        var signout = function () {
            $.ajax({
                async: false,
                error: function () {
                },
                success: function () {
                    return "signed out!";
                },
                url: '/signout'
            });
        }

        window.onunload = function (ev) {
            signout();
        };

        window.onbeforeunload = function (event) {
            if (typeof event == 'undefined') {
                event = window.event;
            }
            return true;
        };

        $(function () {
            $(document).on('click', "a", function () {
                window.onbeforeunload = null;
                window.onunload = null;
            });
            $(document).on('click', "button", function () {
                window.onbeforeunload = null;
                window.onunload = null;
            });
            $(document).on('click', ".btn", function () {
                window.onbeforeunload = null;
                window.onunload = null;
            });
            $(document).keydown(function (e) {
                if (e.key == "F5") {
                    window.onbeforeunload = null;
                    window.onunload = null;
                }
            });
        });


    }
    return {
        init: function () {
        },
        initTime: function () {
            runTime();
        },
        initVisibility: function () {
            runVisibility();
        },
        initTimepicker: function () {
            runTimepicker();
        },
        initCKEditorMin: function () {
            runCKEditorMin();
        },
        initCKEditorZero: function () {
            runCKEditorZero();
        },
        initCKEditor: function () {
            runCKEditor();
        },
        initDropzone: function () {
            runDropzone();
        },
        initSearchLoader: function () {
            runSearchLoader();
        },
        initFarsiInput: function () {
            runFarsiInput();
        },
        initFarsiInputNum: function () {
            runFarsiInputNum();
        },
        initSelect2: function () {
            runSelect2();
        },
        initPersianDateTime: function () {
            runPersianDateTime();
        },
        initTagsInput: function (tagInput, tagList) {
            runTagsInput(tagInput, tagList);
        },
        initTooltip: function () {
            runTooltip();
        },
        initRelatedList: function () {
            runRelatedList();
        },
        initAjaxSearch: function () {
            runAjaxSearch();
        },
        initAjaxSelect: function () {
            runAjaxSelect();
        },
        initAjaxFileExplorer: function () {
            runAjaxFileExplorer();
        },
        initJsonTable: function () {
            runJsonTable();
        },
        initTable: function () {
            runTable();
        },
        initTableEditable: function (modelName, params) {
            runTableEditable(modelName + "Table", modelName + "Tail", modelName + "-class", params);
        },
        initCloseBrowser: function () {
            runCloseBrowser();
        }
    };
}();
/*************************************************************
 * Ir Search - ajaxBase--------------
 *************************************************************/
(function ($) {
    $.fn.irSearch = function (options) {
        var defaultOptions = {
            id: "ajLix1",
            url: "",
            submitOnLoad: true,
            editUrl: "",
            submitOnEmptyQuery: true,
            pageSize: 10,
            paginationRange: 2,
            mode: "table",
            toggleColumns: false
        }

        var initOptions = function (_isearch) {
            var key, val;
            _isearch.options = {};
            for (key in defaultOptions) {
                if (_isearch.attr("i-search-opt-" + key) !== undefined) {
                    val = _isearch.attr("i-search-opt-" + key);
                    if (jQuery.type(defaultOptions[key]) === "boolean") {
                        _isearch.options[key] = val == "true";
                    } else if (jQuery.type(defaultOptions[key]) === "array") {
                        try {
                            _isearch.options[key] = $.parseJSON(val);
                        } catch (er) {
                            console.log("Error in convert to array: " + er);
                        }
                    } else if (jQuery.type(defaultOptions[key]) === "number") {
                        _isearch.options[key] = Number(val);
                    } else {
                        _isearch.options[key] = val;
                    }
                    _isearch.removeAttr("i-search-opt-" + key);

                } else {
                    _isearch.options[key] = defaultOptions[key];
                }
            }
        };
        var initSearchFields = function (_isearch) {
            _isearch.fields = {};
            _isearch.find("[i-search-field-id]").each(function () {
                _isearch.fields[$(this).attr("i-search-field-id")] =
                    {
                        "param": $.parseJSON($(this).attr("i-search-field-param")),
                        "submitOnChange": $(this).attr("i-search-field-submitOnChange") !== undefined,
                        "defaultQuery": $(this).attr("i-search-field-defaultQuery") !== undefined
                    };
                if ($(this).attr("i-search-field-defaultQuery") !== undefined) {
                    createSearchQuery(_isearch, $(this));
                }
                $(this).removeAttr("i-search-field-param")
                    .removeAttr("i-search-field-submitOnChange")
                    .removeAttr("i-search-field-defaultQuery");
            });
        };
        var updateColToggleStatus = function (_isearch) {
            var listCol = $.cookie("listCol" + $("[i-user-id]").attr("i-user-id") + "_" + _isearch.options.id);
            if (listCol != null) {
                listCol = listCol.split("_");
            }
            for (var key in _isearch.cols) {
                _isearch.cols[key].hide = (listCol != null && listCol[_isearch.cols[key].index] != 1);
                _isearch.box.colToggleBox.find("input#col_" + _isearch.cols[key].index).prop("checked", !_isearch.cols[key].hide);
            }
        }
        var setColToggleStatus = function (_isearch) {
            for (var key in _isearch.cols) {
                _isearch.box.bodyx.find("th[i-search-body-col='" + key + "']").css("display", _isearch.cols[key].hide ? "none" : "table-cell");
                _isearch.box.bodyx.list.find("td:nth-child(" + (_isearch.cols[key].index + 1) + ")").css("display", _isearch.cols[key].hide ? "none" : "table-cell");
                _isearch.template.itemList.find("td:nth-child(" + (_isearch.cols[key].index + 1) + ")").css("display", _isearch.cols[key].hide ? "none" : "table-cell");
            }
        }

        var initColumns = function (_isearch) {
            var listCol = $.cookie("listCol" + $("[i-user-id]").attr("i-user-id") + "_" + _isearch.options.id);
            if (listCol != null) {
                listCol = listCol.split("_");
            }
            _isearch.cols = {};
            _isearch.find("th[i-search-body-col]").each(function (i) {
                var jol = $.parseJSON($(this).attr("i-search-body-col"));
                _isearch.cols[jol.path] = jol;
                _isearch.cols[jol.path].hide = (listCol != null && listCol[i] != 1);
                $(this).attr("i-search-body-col", jol.path);
            });
        };
        var initToggleColumn = function (_isearch) {
            var tUl = $("<ul></ul>");
            for (var key in _isearch.cols) {
                tUl.append("<li>" +
                    "<input type='checkbox' id='col_" + _isearch.cols[key].index + "' " + (_isearch.cols[key].hide ? "" : "checked") + " />" +
                    "<label for='col_" + _isearch.cols[key].index + "'>" + _isearch.cols[key].title + "</label>" +
                    "</li>");
            }
            var tDiv = $("<div i-search-bodyx-toggle-box class='c-search-toggle-box' style='display: none'></div>");
            tDiv.append(tUl);
            tDiv.append("<button i-search-bodyx-toggle-button class='btn btn-info'>اعمال</button>");
            _isearch.box.bodyx.find("table").before(
                $("<a href='javascript:void(0)' i-search-bodyx-toggle class='clip-grid-4 c-search-toggle'></a>"))
                .before(tDiv);
        }
        var initTemplate = function (_isearch) {
            _isearch.template = _isearch.find("[i-search-template]").clone();
            _isearch.template.itemList = _isearch.template.find("[i-search-template-body-list]");
            _isearch.template.itemList.find("tr").css("display", "table-row");
            _isearch.template.paginationItem = _isearch.template.find("[i-search-template-pagination] [i-search-pagination-item]");


            /***********/
            _isearch.box = _isearch.find("[i-search-template]");

            _isearch.box.find("[i-search-template-pageSize] select").val(_isearch.options.pageSize);

            _isearch.box.messageText = _isearch.box.find("[i-search-message-text] span");
            _isearch.box.messageText.liveCount = 0;
            _isearch.box.bodyx = _isearch.box.find("[i-search-template-body]");
            _isearch.box.bodyx.list = _isearch.box.bodyx.find("[i-search-template-body-list]");
            _isearch.box.bodyx.list.html("");
            _isearch.box.paginationNext = _isearch.box.find("[i-search-template-pagination] [i-search-pagination-next]");
            _isearch.box.paginationPrev = _isearch.box.find("[i-search-template-pagination] [i-search-pagination-prev]");
            _isearch.box.pageInfoFrom = _isearch.box.find("[i-search-pagination-info-from]");
            _isearch.box.pageInfoTo = _isearch.box.find("[i-search-pagination-info-to]");
            _isearch.box.pageInfoCount = _isearch.box.find("[i-search-pagination-info-count]");

            if (_isearch.options.toggleColumns) {
                initToggleColumn(_isearch);
                _isearch.box.colToggle = _isearch.box.bodyx.find("[i-search-bodyx-toggle]");
                _isearch.box.colToggleBox = _isearch.box.bodyx.find("[i-search-bodyx-toggle-box]");
                setColToggleStatus(_isearch);
            }
            _isearch.box.bodyx.find("thead>tr").css("display", "table-row");

            _isearch.find("[i-search-template-body-list]").html("");
        };
        var initAjaxParams = function (_isearch) {
            _isearch.ap = {
                c: "",
                o: [],
                df: _isearch.options.submitOnEmptyQuery,
                pi: 1,
                ps: _isearch.options.pageSize,
                pr: _isearch.options.paginationRange,
                list: []
            };

            _isearch.ixp = {
                tp: "",
                en: true,
                sg: true,
                st: 0,
                zp: false,
                ps: -1,
                pi: 1
            }
        };
        /*************   **************/
        var createSearchQuery = function (_isearch, _input) {
            var fieldId = _input.attr("i-search-field-id");
            var name = _isearch.fields[fieldId].param[0];
            var tp = _isearch.fields[fieldId].param[1];
            var opr = _isearch.fields[fieldId].param[2];
            var ignoreSp = _isearch.fields[fieldId].param[3];
            var index = _isearch.ap.list.length;
            var value = _input.val();
            $.each(_isearch.ap.list, function (i, v) {
                if (v.n === name && v.o === opr) {
                    index = i;
                }
            });
            if (opr === 'in') {
                if (index === _isearch.ap.list.length) {
                    _isearch.ap.list[index] = {n: name, v: [value], o: opr, t: tp, s: ignoreSp};
                } else {
                    var invar = _isearch.ap.list[index].v;
                    var isExist = false;
                    $.each(invar, function (ii, vv) {
                        if (vv === value) {
                            _isearch.ap.list[index].v.splice(ii, 1);
                            if (_isearch.ap.list[index].v.length === 0) {
                                _isearch.ap.list.splice(index, 1);
                            }
                            isExist = true;
                        }
                    });
                    if (!isExist) {
                        _isearch.ap.list[index].v[_isearch.ap.list[index].v.length] = value;
                    }
                }
            } else {
                if (value === '' && index !== _isearch.ap.list.length) {
                    _isearch.ap.list.splice(index, 1);
                } else if (_input.is(':checkbox') && _input.is(':checked') === false) {
                    _isearch.ap.list.splice(index, 1);
                } else if (value !== "") {
                    _isearch.ap.list[index] = {n: name, v: value, o: opr, t: tp, s: ignoreSp};
                }
            }
        };
        var replaceObjectValues = function (ke, elem, nItem) {
            $.each(elem, function (key, element) {
                if ($.type(element) === "object") {
                    nItem = replaceObjectValues(ke + "." + key, element, nItem);
                }
                nItem = nItem.replace(new RegExp("@{" + ke + "." + key + "}", "g"), element);
            });
            return nItem;
        };
        var clearBody = function (_isearch) {
            _isearch.box.bodyx.list.html("");
            if (_isearch.box.messageText.liveCount < 1) {
                _isearch.box.messageText.html("").removeClass(function (index, className) {
                    return (className.match(/(^|\s)alert-\S+/g) || []).join(' ');
                });
            } else {
                _isearch.box.messageText.liveCount = _isearch.box.messageText.liveCount - 1;
            }
            _isearch.box.find("[i-search-pagination-item]").remove();
            _isearch.box.pageInfoFrom.html("0");
            _isearch.box.pageInfoTo.html("0");
            _isearch.box.pageInfoCount.html("0");
        }
        /*************   **************/

        var fillMessageBody = function (_isearch, message, cssClass, liveCount) {
            if (message) {
                if (typeof (message) != "string") {
                    cssClass = message[0].cssClass;
                    message = message[0].text;
                }
                _isearch.box.messageText.html(message).addClass(cssClass);
                _isearch.box.messageText.liveCount = liveCount != undefined ? liveCount : 0;
            }

        };
        var fillBody = function (_isearch, list) {
            // /***  applying fixed col width ***/
            // if (colsWidth.length > 0) {
            //     bodyElm.find("[ir-search-order-col]").each(function (index) {
            //         $(this).width(colsWidth[index]);
            //     });
            // }
            // /****/
            var itemTxt = _isearch.template.itemList.html();
            itemTxt = itemTxt.replace(new RegExp("i-search-img-src", "g"), "src");
            var items = "";
            var newItem;
            $.each(list, function (i, value) {
                newItem = itemTxt;
                $.each(value, function (key, element) {
                    if ($.type(element) === "object") {
                        newItem = replaceObjectValues(key, element, newItem);
                    }
                    newItem = newItem.replace(new RegExp("@{" + key + "}", "g"), element);
                });
                try {
                    newItem = pluginFillBody(i, value, newItem);
                } catch (ee) {
                }
                newItem = newItem.replace(new RegExp("@{[a-zA-Z0-9._]*}", "g"), "");
                items += newItem;
            });
            _isearch.box.bodyx.list.html(items);

            // /***  finding fixed col width ***/
            // if (colsWidth.length <= 0) {
            //     sxContainer.find("[ir-search-order-col]").each(function (index) {
            //         colsWidth[index] = $(this).width();
            //         colsName[index] = $(this).attr("ir-search-order-col");
            //     });
            // }
            // /****/
        };
        var fillPagination = function (_isearch, paging) {
            _isearch.box.paginationPrev.attr("i-search-pagination-value", paging.index - 1).removeClass("disable")
                .addClass(paging.index === 1 ? "disable" : "");
            _isearch.box.paginationNext.attr("i-search-pagination-value", paging.index + 1).removeClass("disable")
                .addClass(paging.index === paging.pageCount ? "disable" : "");

            _isearch.box.find("[i-search-pagination-item]").remove();

            $.each(paging.pages, function (i, v) {
                var itmElm = _isearch.template.paginationItem.clone();
                itmElm.attr('i-search-pagination-value', v);
                itmElm.find('a').html(v);
                if (v === paging.index) {
                    itmElm.addClass("active");
                } else {
                    itmElm.removeClass("active");
                }
                itmElm.insertBefore(_isearch.box.paginationNext);
            });

            _isearch.box.pageInfoFrom.html(paging.from);
            _isearch.box.pageInfoTo.html(paging.to);
            _isearch.box.pageInfoCount.html(paging.searchCount);
        };
        var fillColumnSorting = function (_isearch) {
            if (_isearch.ap.o !== undefined && _isearch.ap.o.length > 0) {
                $("[i-search-body-col]").removeClass("sorted-desc").removeClass("sorted-asc");
                if (_isearch.ap.o[0][1] === -1) {
                    $("[i-search-body-col='" + _isearch.ap.o[0][0] + "']").addClass("sorted-desc");
                } else {
                    $("[i-search-body-col='" + _isearch.ap.o[0][0] + "']").addClass("sorted-asc");
                }
            }
        }
        /*************   **************/
        var submitSearch = function (_isearch, submitValues, submitUrl) {
            if (_isearch.xhr !== undefined) {
                _isearch.xhr.abort();
            }
            if (submitValues == undefined || submitValues == "") {
                _isearch.ixp.tp = undefined;
            } else {
                _isearch.ixp.tp = submitValues;
                _isearch.ixp.en = $("input[i-search-submit-opt-encrypt]").prop("checked");
                _isearch.ixp.sg = $("input[i-search-submit-opt-sign]").prop("checked");
                _isearch.ixp.st = $("select[i-search-submit-opt-security] option:selected").val();
            }
            console.log(_isearch.ixp);
            _isearch.xhr = $.ajax({
                url: (submitUrl != undefined ? submitUrl : _isearch.options.url),
                Type: 'json',
                type: 'POST',
                data: "ap=" + JSON.stringify(_isearch.ap) + (_isearch.ixp.tp === undefined ? "" : "&ixp=" + JSON.stringify(_isearch.ixp)),
                success: function (jData, jStatus, jXhr) {
                    console.log(jData);
                    if (jXhr.getResponseHeader("file-path") != null) {
                        if (jData) {
                            fillMessageBody(_isearch, $.parseJSON(jData).messages);
                        }
                        if (jXhr.getResponseHeader("file-path") != "") {
                            window.open(
                                jXhr.getResponseHeader("file-path"),
                                '_blank'
                            );
                        }

                        return;
                    }
                    clearBody(_isearch);
                    if (jData === undefined || jData === null) {
                        fillMessageBody(_isearch, "اطلاعات یافت نشد!", "alert-warning");
                        return;
                    }
                    if (jData.status == "nok") {
                        fillMessageBody(_isearch, jData.messages);
                        return;
                    }
                    else if (jData.result.search === undefined || jData.result.search.list.length === 0) {
                        fillMessageBody(_isearch, "موردی یافت نشد!", "alert-info");
                        return;
                    }
                    fillBody(_isearch, jData.result.search.list);
                    fillPagination(_isearch, jData.result.search.paging);
                    fillColumnSorting(_isearch);
                },
                error: function (e) {
                    if (e.statusText != "abort") {
                        clearBody(_isearch);
                        fillMessageBody(_isearch, "خطا در پردازش اطلاعات!", "alert-danger");
                    }
                }
            });


        };
        /*************   **************/

        return this.each(function () {
            var _isearch = $(this);
            initOptions(_isearch);
            initAjaxParams(_isearch);
            initSearchFields(_isearch);
            initColumns(_isearch);
            initTemplate(_isearch);
            _isearch.on('click', "[i-search-pagination-value]", function () {
                if (!$(this).hasClass("disable") && !$(this).hasClass("active")) {
                    _isearch.ap.pi = $(this).attr("i-search-pagination-value");
                    submitSearch(_isearch);
                }
            });
            _isearch.on('change', "[i-search-template-pageSize] select", function () {
                _isearch.ap.ps = $(this).val();
                submitSearch(_isearch);
            });
            _isearch.on('change', "[i-search-field-id]", function () {
                debugger;
                createSearchQuery(_isearch, $(this));
                if (_isearch.fields[$(this).attr("i-search-field-id")].submitOnChange) {
                    submitSearch(_isearch);
                }
            });
            _isearch.on('keyup', '[i-search-field-id]', function (e) {
                debugger;
                if (e.keyCode === 13) {
                    createSearchQuery(_isearch, $(this));
                    submitSearch(_isearch);
                } else if (e.keyCode === 27) {
                    $(this).val(undefined);
                    createSearchQuery(_isearch, $(this));
                    submitSearch(_isearch);
                } else if (_isearch.fields[$(this).attr("i-search-field-id")].submitOnChange) {
                    createSearchQuery(_isearch, $(this));
                    submitSearch(_isearch);
                }
            });
            _isearch.on('click', "[i-search-submit-btn]", function () {
                $("[i-search-submit-opt]").modal("hide");
                submitSearch(_isearch, $(this).attr("i-search-submit-btn"), $(this).attr("i-search-url"));
            });
            _isearch.on('click', "a[i-search-body-item-trash-id]", function () {
                var url = $(this).attr("i-search-body-item-trash-url");
                var did = $(this).attr("i-search-body-item-trash-id");
                var title = $(this).attr("i-search-body-item-trash-title");
                bootbox.confirm("آیا از حذف اطلاعات " + "<b style='color:red'>" + title + "</b>" + " با شناسه " + "<b style='color:red'>" + did + "</b>" + " اطمینان دارید؟", function (result) {
                    if (result) {
                        console.log(((url !== undefined && url !== "" ? url + "/" : 'trash/') + did));
                        $.ajax({
                            url: (url !== undefined && url !== "" ? url + "/" : 'trash/') + did,
                            async: false
                        }).done(function (result) {
                            console.log(result);
                            if (result.status == "ok") {
                                submitSearch(_isearch);
                            }
                            fillMessageBody(_isearch, result.messages, undefined, 1);
                        });
                    }
                });
            });
            _isearch.on('click', "[i-search-body-col]", function () {
                var v = $(this).attr("i-search-body-col");
                var x = _isearch.cols[v].path;
                if (_isearch.ap.o[0] !== undefined && _isearch.ap.o[0][0] === x) {
                    _isearch.ap.o[0] = [x, -_isearch.ap.o[0][1]];
                } else {
                    _isearch.ap.o[0] = [x, 1];
                }
                submitSearch(_isearch);
            });
            _isearch.on('click', "[i-search-bodyx-toggle-button]", function () {
                var colTail = "";
                _isearch.box.colToggleBox.find("input").each(function () {
                    colTail += "_" + ($(this).is(":checked") ? "1" : "0");
                });
                if (colTail.indexOf("0") == -1) {
                    $.removeCookie("listCol" + $("[i-user-id]").attr("i-user-id") + "_" + _isearch.options.id, {path: _isearch.options.url});
                } else {
                    var expDate = new Date();
                    expDate.setTime(expDate.getTime() + 3600000);
                    $.cookie("listCol" + $("[i-user-id]").attr("i-user-id") + "_" + _isearch.options.id, colTail.substr(1), {path: _isearch.options.url, expires: expDate});
                }
                updateColToggleStatus(_isearch);
                setColToggleStatus(_isearch);
                _isearch.box.colToggleBox.hide();
                fillMessageBody(_isearch, "تنظیمات نمایش ستون ها با موفقیت اعمال شد.", "alert-info");
            });
            _isearch.on('click', "[i-search-message-text]", function () {
                _isearch.box.messageText.html("").removeClass(function (index, className) {
                    return (className.match(/(^|\s)alert-\S+/g) || []).join(' ');
                });

            });
            if (_isearch.options.toggleColumns) {
                _isearch.on('click', "[i-search-bodyx-toggle]", function () {
                    if (_isearch.box.colToggleBox.is(":visible")) {
                        _isearch.box.colToggleBox.hide();
                    } else {
                        _isearch.box.colToggleBox.show();
                    }
                });
                _isearch.on('click', "tbody,[i-search-template-pagination],[i-search-template-pagesize]", function () {
                    _isearch.box.colToggleBox.hide();
                    updateColToggleStatus(_isearch);
                });
            }

            if (_isearch.options.submitOnLoad) {
                submitSearch(_isearch);
            }

        });
    };
})(jQuery);

/*************************************************************
 * Ir Select - ajaxBase
 *************************************************************/
(function ($) {
    $.fn.irSelect = function (options) {
        var defaultOptions = {
            autoFocus: false, /* focus on input on loading page */
            type: "text",
            min: -1, /* min  of input for type of number */
            max: -1, /* max  of input for type of number */
            maxLength: 0, /* max length of input - maximum allowed char to input */
            pattern: "", /* pattern of input characters */
            name: "selectName", /* name of input which is used for submitting <form> */
            placeHolder: "لطفا متن خود را وارد نمایید",
            listSize: 50, /* size of result list in loading */
            submitOnChange: true, /* submit query and find and show result if any key pressed in input */
            submitMinLength: -1, /* if auto submit is on, submit act is triggered if len of input is equal or bigger than this  */
            submitPeriodLength: 1, /* if auto submit is on, submit act is triggered if len if input mod(%) this is Zero: [len%submitPeriodLength=0]*/
            submitEmptyQuery: false, /* submit result if any key pressed and input is Empty. if there is no query to submit */
            label: "@{title}", /* label of result to show */
            placeMode: false, /* show result as placeHolder */
            liId: "@{id}", /* id of result which set in <li> */
            field: [], /* information which received from controller for filling field parameters */
            url: ""                         /* url of controller which receive query and send result */
        }

        var intOptions = function (_iselect) {
            var key, val;
            _iselect.options = {};
            for (key in defaultOptions) {
                if (_iselect.attr("i-" + key) !== undefined) {
                    val = _iselect.attr("i-" + key);
                    if (jQuery.type(defaultOptions[key]) === "boolean") {
                        _iselect.options[key] = val == "true";
                    } else if (jQuery.type(defaultOptions[key]) === "array") {
                        try {
                            _iselect.options[key] = $.parseJSON(val);
                        } catch (er) {
                            console.log("Error in convert to array: " + er);
                        }
                    } else if (jQuery.type(defaultOptions[key]) === "number") {
                        _iselect.options[key] = Number(val);
                    } else {
                        _iselect.options[key] = val;
                    }
                    _iselect.removeAttr("i-" + key);

                } else {
                    _iselect.options[key] = defaultOptions[key];
                }
            }
            if (_iselect.options.type === "number" || _iselect.options.type === "tel") {
                _iselect.options.placeMode = true;
            }
        }
        var render = function (_iselect) {
            var $inputs = $(
                '<input id="' + _iselect.options.name + '" name="' + _iselect.options.name
                + '" placeholder="' + _iselect.options.placeHolder + '"  type="hidden" class="i-select-hidden" />' +
                '<i class="i-clean clip-close-2" style="display: none"></i>' +
                '<input '
                + (_iselect.options.autoFocus === true ? ' autofocus ' : '')
                + (_iselect.options.maxLength !== 0 ? (' maxlength="' + _iselect.options.maxLength + '" ') : '')
                + (_iselect.options.min !== -1 ? (' min="' + _iselect.options.min + '" ') : '')
                + (_iselect.options.max !== -1 ? (' max="' + _iselect.options.max + '" ') : '')
                + (_iselect.options.pattern !== "" ? (' pattern="' + _iselect.options.pattern + '" ') : '')
                + ' name="' + _iselect.options.name
                + '_search" type="' + _iselect.options.type
                + '" class="i-select-input col-xs-12 form-control" '
                + 'placeholder="' + _iselect.options.placeHolder
                + '" />' +
                '<div id="' + _iselect.options.name + '_list" class="i-select-list" style="display: none"></div>'
            );
            _iselect.append($inputs);
            _iselect.selectList = $(_iselect.find(".i-select-list"));
        }
        /*************   **************/
        var initAjaxParams = function (_iselect) {
            _iselect.ap = {
                c: "",
                o: [],
                df: _iselect.options.submitEmptyQuery,
                pi: 1,
                ps: _iselect.options.listSize,
                list: []
            };
        }
        var createSearchQuery = function (_iselect, _input) {
            var name = _iselect.options.field[0];
            var tp = _iselect.options.field[1];
            var opr = _iselect.options.field[2];
            var ignoreSp = _iselect.options.field[3];
            var index = _iselect.ap.list.length;
            var value = _input.val();
            $.each(_iselect.ap.list, function (i, v) {
                if (v.n === name && v.o === opr) {
                    index = i;
                }
            });
            if (opr === 'in') {
                if (index === _iselect.ap.list.length) {
                    _iselect.ap.list[index] = {n: name, v: [value], o: opr, t: tp, s: ignoreSp};
                } else {
                    var invar = _iselect.ap.list[index].v;
                    var isExist = false;
                    $.each(invar, function (ii, vv) {
                        if (vv === value) {
                            _iselect.ap.list[index].v.splice(ii, 1);
                            if (_iselect.ap.list[index].v.length === 0) {
                                _iselect.ap.list.splice(index, 1);
                            }
                            isExist = true;
                        }
                    });
                    if (!isExist) {
                        _iselect.ap.list[index].v[_iselect.ap.list[index].v.length] = value;
                    }
                }
            } else {
                if (value === '' && index !== _iselect.ap.list.length) {
                    _iselect.ap.list.splice(index, 1);
                } else if (_input.is(':checkbox') && _input.is(':checked') === false) {
                    _iselect.ap.list.splice(index, 1);
                } else if (value !== "") {
                    _iselect.ap.list[index] = {n: name, v: value, o: opr, t: tp, s: ignoreSp};
                }
            }
        };
        var replaceObjectValues = function (ke, elem, nItem) {
            $.each(elem, function (key, element) {
                if ($.type(element) === "object") {
                    nItem = replaceObjectValues(ke + "." + key, element, nItem);
                }
                nItem = nItem.replace(new RegExp("@{" + ke + "." + key + "}", "g"), element);
            });
            return nItem;
        };
        var fillEmptyBody = function (_iselect) {
            _iselect.find(".i-select-list").hide().html("").removeClass("error");
            _iselect.isFill = false;
        };
        var fillErrorBody = function (_iselect) {
            _iselect.find(".i-select-list").show().addClass("error").html("خطا در جستجو");
            _iselect.isFill = false;
            _iselect.find(".i-clean").hide();
        };
        var fillBody = function (_iselect, list) {
            _iselect.isFill = true;
            var itemTxt = '<li id="' + _iselect.options.liId + '">' + _iselect.options.label + '</li>';
            itemTxt = itemTxt.replace(new RegExp("i-search-img-src", "g"), "src");
            var items = "";
            var newItem;
            $.each(list, function (i, value) {
                newItem = itemTxt;
                $.each(value, function (key, element) {
                    if ($.type(element) === "object") {
                        newItem = replaceObjectValues(key, element, newItem);
                    }
                    newItem = newItem.replace(new RegExp("@{" + key + "}", "g"), element);
                });
                newItem = newItem.replace(new RegExp("@{[a-zA-Z0-9._]*}", "g"), "");
                items += newItem;
            });

            _iselect.find(".i-select-list").show().html("").append($("<ul>" + items + "</ul>"));
            _iselect.find(".i-clean").show();
        };
        var submitSearch = function (_iselect) {
            if (_iselect.xhr !== undefined) {
                _iselect.xhr.abort();
            }
            _iselect.xhr = $.ajax({
                url: _iselect.options.url,
                Type: 'json',
                type: 'POST',
                data: "ap=" + JSON.stringify(_iselect.ap),
                success: function (myjson) {
                    if (myjson === undefined || myjson === null || myjson === ""
                        || myjson.list === undefined || myjson.list.length === 0) {
                        fillEmptyBody(_iselect);
                        return;
                    }
                    fillBody(_iselect, myjson.list);
                },
                error: function (e) {
                    if (e.statusText != "abort") {
                        fillErrorBody(_iselect);
                    }
                }
            });
        };
        var setResult = function (_iselect, selectedLi) {
            if (selectedLi !== undefined && selectedLi.length > 0) {
                var txt = "";
                selectedLi.children().each(function () {
                    txt += $(this).text() + "   ";
                });
                if (_iselect.options.placeMode === true) {
                    _iselect.find("[name=" + _iselect.options.name + "_search]").focus()
                        .attr("placeholder", txt.trim()).val("");
                } else {
                    _iselect.find("[name=" + _iselect.options.name + "_search]").focus()
                        .val(txt.trim());
                }
                _iselect.find("[name=" + _iselect.options.name + "]").val(selectedLi.attr("id"));
                _iselect.find(".i-select-list").hide();
                _iselect.find(".i-clean").show();
            } else {
                cleanResult(_iselect)
            }
        }
        var cleanResult = function (_iselect) {
            _iselect.find(".i-select-list").removeClass("error");
            _iselect.find(".i-clean").hide();
            if (_iselect.options.placeMode === true) {
                _iselect.find("[name=" + _iselect.options.name + "_search]").focus()
                    .attr("placeholder", _iselect.options.placeHolder);
            }
            _iselect.find("[name=" + _iselect.options.name + "_search]").val("");
            _iselect.find("[name=" + _iselect.options.name + "]").val("");
        }
        /*************   **************/
        return this.each(function () {
            var _iselect = $(this);
            intOptions(_iselect);
            render(_iselect);
            initAjaxParams(_iselect);
            _iselect.on("keydown", ".i-select-input", function (e) {
                if (e.which == 40 || e.which == 38
                    || (_iselect.options.maxLength > 0 && $(this).val().length >= _iselect.options.maxLength && e.which != 8 && e.which != 46)) {
                    e.preventDefault();
                }
            });
            _iselect.on("keyup", ".i-select-input", function (e) {
                if (e.keyCode === 27) {
                    cleanResult(_iselect);
                    if (_iselect.options.submitMinLength > 0) {
                        createSearchQuery(_iselect, $(this));
                        submitSearch(_iselect);
                    }
                } else if ((e.keyCode === 13 ||
                        (e.keyCode === 9 && $(this).val().length > 1 && $(this).val().length === _iselect.options.maxLength))
                    && _iselect.find(".i-select-list").is(':visible')) {
                    if (_iselect.find(".i-select-list li.active").length != 0) {
                        setResult(_iselect, _iselect.find(".i-select-list li.active"));
                    }
                    else {
                        setResult(_iselect, _iselect.find("li:first-child"));
                    }
                }
                else if (e.which == 40 && _iselect.find(".i-select-list").is(':visible')) {
                    e.preventDefault();
                    if (_iselect.find(".i-select-list li.active").length != 0) {
                        var storeTarget = _iselect.find(".i-select-list li.active").next();
                        if (storeTarget.length != 0) {
                            _iselect.find(".i-select-list li.active").removeClass("active");
                            storeTarget.focus().addClass("active");
                        }
                    }
                    else {
                        _iselect.find(".i-select-list li:first").focus().addClass("active");
                    }
                    _iselect.find(".i-select-list")
                        .scrollTop(_iselect.find(".i-select-list").scrollTop() + _iselect.find(".i-select-list li.active").position().top - 100);

                }
                else if (e.which == 38 && _iselect.find(".i-select-list").is(':visible')) {
                    e.preventDefault();
                    if (_iselect.find(".i-select-list li.active").length != 0) {
                        var storeTarget = _iselect.find(".i-select-list li.active").prev();
                        if (storeTarget.length != 0) {
                            _iselect.find(".i-select-list li.active").removeClass("active");
                            storeTarget.focus().addClass("active");
                        }
                    }
                    else {
                        _iselect.find(".i-select-list li:last").focus().addClass("active");
                    }
                    _iselect.find(".i-select-list")
                        .scrollTop(_iselect.find(".i-select-list").scrollTop() + _iselect.find(".i-select-list li.active").position().top - 100);
                }
                else if ((_iselect.options.submitMinLength == -1 || _iselect.options.submitMinLength <= $(this).val().length)
                    && (_iselect.options.submitPeriodLength < 2
                        || (_iselect.options.submitPeriodLength > 1 && $(this).val().length > 1 && $(this).val().length % _iselect.options.submitPeriodLength == 0))
                ) {
                    createSearchQuery(_iselect, $(this));
                    submitSearch(_iselect);
                }
            });
            _iselect.on("click", ".i-select-input", function () {
                if (_iselect.isFill) {
                    _iselect.find(".i-select-list").show();
                }
                _iselect.find("li:first-child").focus();
            });
            _iselect.on('click', "li", function () {
                setResult(_iselect, $(this));
            });
            _iselect.on('blur', ".i-select-list,.i-select-list>ul", function () {
                _iselect.find(".i-select-list").hide();
            });
            _iselect.on('click', ".i-clean", function () {
                fillEmptyBody(_iselect);
                cleanResult(_iselect);
            });

        });
    };
})(jQuery);
/*************************************************************
 * Ir File Explorer - ajaxBase
 *************************************************************/
(function ($) {
    $.fn.irFileExplorer = function (options) {

        /*************
         Default Init Render
         **************/
        var defaultOptions = {
            width: "100%",
            minWidth: "100px",
            height: "100%",
            minHeight: "100px",
            autoFocus: false, /* focus on input on loading page */
            direction: "rtl", /* direction of tree and container body */
            showContainer: true, /* is show container */
            /**/
            url: "", /* url of controller which receive query and send result */
            query: "ls", /* from query list */
            currentPath: "", /* from query list */
            /*ls query*/
            ls_filesInTree: false, /* is show files in directory tree view */
            ls_depth: -1, /*  */
            ls_directoriesInContent: false /*  */
        }
        var intOptions = function (_ifile) {
            var key, val;
            _ifile.options = {};
            for (key in defaultOptions) {
                if (_ifile.attr("i-" + key) !== undefined) {
                    val = _ifile.attr("i-" + key);
                    if (jQuery.type(defaultOptions[key]) === "boolean") {
                        _ifile.options[key] = val == "true";
                    } else if (jQuery.type(defaultOptions[key]) === "array") {
                        try {
                            _ifile.options[key] = $.parseJSON(val);
                        } catch (er) {
                            console.log("Error in convert to array: " + er);
                        }
                    } else if (jQuery.type(defaultOptions[key]) === "number") {
                        _ifile.options[key] = Number(val);
                    } else {
                        _ifile.options[key] = val;
                    }
                    _ifile.removeAttr("i-" + key);

                } else {
                    _ifile.options[key] = defaultOptions[key];
                }
            }
        }
        var render = function (_ifile) {
            _ifile.css("width", _ifile.options.width)
                .css("min-width", _ifile.options.minWidth)
                .css("height", _ifile.options.height)
                .css("min-height", _ifile.options.minHeight);

            var $explorer = $(
                '<div class="explorer-body">' +
                '   <div class="explorer-head">' +
                '       <span i-headMenu class="head-menu"><i class="clip-menu-2"></i></span>' +
                '       <span class="head-path-title">مسیر: </span>' +
                '       <span class="head-path">' +
                '       </span>' +
                '       <div i-headModal class="menu head-menu-modal" style="display: none;">' +
                '           <ul>' +
                '               <li i-upload>بارگذاری در مسیر</li>' +
                '               <li i-download>دانلود</li>' +
                '           </ul>' +
                '       </div>' +
                '       <span class="head-message">' +
                '               <span style="display: block;"></span>' +
                '       </span>' +
                '   </div>' +
                '   <div class="tree-container">' +
                '       <div class="tree-body">' +
                '       </div>' +
                '   </div>' +
                '   <div class="content-container">' +
                '       <div class="content-body">' +
                '       </div>' +
                '       <div i-contentItemMenu class="menu content-item-menu">' +
                '           <ul style="display:block;position:static;">' +
                '               <li i-edit>ویرایش</li>' +
                '               <li i-remove>حذف</li>' +
                '               <li i-download>دانلود</li>' +
                '           </ul>' +
                '       </div>' +
                '       <div i-contentMenu class="menu content-menu">' +
                '           <ul style="display:block;position:static;">' +
                '               <li i-mkdir>ایجاد پوشه</li>' +
                '               <li i-createFile>ایجاد فایل</li>' +
                '               <li i-download>دانلود محتویات</li>' +
                '               <li i-upload>بارگذاری</li>' +
                '           </ul>' +
                '       </div>' +
                '       <div i-nameModal class="modal-name">' +
                '           <span>نام:</span>' +
                '           <input type="text"/>' +
                '           <button i-submit class="modal-submit">ایجاد</button>' +
                '           <button i-cancel class="modal-cancel">لغو</button>' +
                '       </div>' +
                '       <div class="dropzone"' +
                '           data-url="' + _ifile.options.url + 'ulf"' +
                '           data-view-type="list"' +
                '           data-params={"path":""}' +
                '           data-multi-select="false">' +
                '       </div>' +
                '   </div>' +
                '</div>'
                )
            ;
            _ifile.append($explorer);

            /**** Define *****/
            _ifile.tree = $(_ifile.find(".tree-body"));
            _ifile.content = $(_ifile.find(".content-body"));
            _ifile.head = $(_ifile.find(".explorer-head"));
            _ifile.headMessage = $(_ifile.find(".head-message"));
            _ifile.headModal = $(_ifile.find("[i-headModal]"));
            _ifile.contentMenu = $(_ifile.find("[i-contentMenu]"));
            _ifile.nameModal = $(_ifile.find("[i-nameModal]"));
            _ifile.contentItemMenu = $(_ifile.find("[i-contentItemMenu]"));
            _ifile.dropzone = $(_ifile.find(".dropzone"));

            /**** Event Handler *****/
            $('html').on('click', function (e) {
                _ifile.headModal.hide();
                _ifile.contentMenu.hide();
                _ifile.contentItemMenu.hide();
                _ifile.selectedContentItem = undefined;
            });
            _ifile.head.on('click', '[i-headMenu]', function (e) {
                e.preventDefault();
                _ifile.headModal.toggle();
                return false;
            });
            _ifile.headModal.on('click', '[i-upload]', function (e) {
                e.preventDefault();
                alert("ss");
                return false;
            });
            _ifile.headModal.on('click', '[i-download]', function (e) {
                e.preventDefault();
                var ur = _ifile.options.currentPath;
                ur = ur.replace(new RegExp("/", "g"), "!@");
                window.open($(cpId).val() + "/panel/file/dl/assets/" + ur);
                return false;
            });
            _ifile.contentItemMenu.on('click', '[i-edit]', function (e) {
                e.preventDefault();
                submitEditFile(_ifile);
                _ifile.contentItemMenu.hide();
                return false;
            });
            _ifile.contentItemMenu.on('click', '[i-remove]', function (e) {
                e.preventDefault();
                if (_ifile.selectedContentItem.isDir) {
                    var result = confirm('آیا از حذف پوشه ' + _ifile.selectedContentItem.name + ' و محتویات آن مطمئن هستید؟');
                    if (result === true) {
                        submitRemoveDir(_ifile);
                    }
                } else {
                    var result = confirm('آیا از حذف فایل ' + _ifile.selectedContentItem.name + ' مطمئن هستید؟');
                    if (result === true) {
                        submitRemoveFile(_ifile);
                    }
                }

                _ifile.contentItemMenu.hide();
                return false;
            });
            _ifile.contentItemMenu.on('click', '[i-download]', function (e) {
                e.preventDefault();
                submitDownloadFile(_ifile);
                _ifile.contentItemMenu.hide();
                return false;
            });
            _ifile.content.on("contextmenu", function (e) {
                _ifile.contentItemMenu.hide();
                _ifile.selectedContentItem = undefined;
                _ifile.contentMenu.css({
                    display: "block",
                    left: e.pageX,
                    top: e.pageY
                });
                return false;
            });
            _ifile.contentMenu.on('click', '[i-mkdir]', function (e) {
                e.preventDefault();
                _ifile.selectedContentItem = undefined;
                _ifile.nameModal.css({
                    display: "block",
                    left: e.pageX,
                    top: e.pageY
                });
                _ifile.contentMenu.hide();
                return false;
            });
            _ifile.nameModal.on('click', '[i-submit]', function (e) {
                e.preventDefault();
                _ifile.selectedContentItem = undefined;
                submitMakeDirectory(_ifile);
                _ifile.nameModal.find("input").val("");
                _ifile.contentMenu.hide();
                _ifile.nameModal.hide();
                return false;
            });
            _ifile.nameModal.on('click', '[i-cancel]', function (e) {
                e.preventDefault();
                _ifile.contentMenu.hide();
                _ifile.nameModal.hide();
                return false;
            });
            _ifile.contentMenu.on('click', '[i-download]', function (e) {
                e.preventDefault();
                _ifile.selectedContentItem = undefined;
                submitDownloadFile(_ifile);
                _ifile.contentMenu.hide();
                return false;
            });
            _ifile.contentMenu.on('click', '[i-upload]', function (e) {
                e.preventDefault();
                _ifile.selectedContentItem = undefined;
                alert("sds");
                _ifile.contentMenu.hide();
                return false;
            });
        }
        var initTree = function (_ifile) {
            _ifile.tree.find("ul").first()
                .addClass('file-list')
                .find('li[i-dir]')
                .addClass('folder-root closed')
                .on('click', '[i-name]', function (e) {
                    e.preventDefault();
                    $(this).parent().toggleClass('closed').toggleClass('open');
                    console.log(getPath($(this).parent(), 1));
                    _ifile.options.currentPath = getPath($(this).parent(), 1);
                    console.log(_ifile.dropzone.attr("data-params"));
                    _ifile.dropzone.attr("data-params", '{"path":"' + _ifile.options.currentPath + '"}');
                    submitContent(_ifile, _ifile.options.currentPath);
                    return false;
                })
            ;
            _ifile.tree.on('click', function (e) {
                _ifile.headModal.hide();
                return false;
            });
        }
        var initContentItem = function (_ifile) {
            _ifile.contentItems = $(_ifile.find(".content-body ul>li"));
            _ifile.contentItems.on("contextmenu", function (e) {
                _ifile.contentMenu.hide();
                _ifile.contentItemMenu.css({
                    display: "block",
                    left: e.pageX,
                    top: e.pageY
                });
                _ifile.selectedContentItem = {};
                _ifile.selectedContentItem.name = $(this).find('[i-name]').html();
                _ifile.selectedContentItem.isDir = $(this).attr("i-dir") !== undefined;
                return false;
            });

        }

        /*************
         Public
         **************/
        var getPath = function (pr, i) {
            if (i > 30) {
                return "";
            }
            return (pr.parents("li") !== undefined && pr.parents("li").length > 0 ? getPath(pr.parents("li"), ++i) + "/" : "") +
                pr.children("[i-name]").html();
        }
        var fillErrorBody = function (_iselect) {
            _ifile
                .headMessage.find("span").removeClass().addClass("danger")
                .finish()
                .fadeIn()
                .html("خطا در فراخوانی دستور")
                .fadeOut(5000);
        };
        var fillHeadMessage = function (_ifile, myJson) {
            if (myJson.messages !== undefined && myJson.messages.length !== 0) {
                _ifile
                    .headMessage.find("span").removeClass().addClass(myJson.messages[0].cssClass)
                    .finish()
                    .fadeIn()
                    .html(myJson.messages[0].text)
                    .fadeOut(5000);
            }
        }
        /****************
         Remove
         **********/
        var submitRemoveFile = function (_ifile) {
            if (_ifile.xhr !== undefined) {
                _ifile.xhr.abort();
            }
            var query = {
                q: "rmf",
                a: {
                    "path": _ifile.options.currentPath + "/" + _ifile.selectedContentItem.name,
                    "file": true,
                    "directory": _ifile.options.ls_directoriesInContent,
                }
            };
            _ifile.xhr = $.ajax({
                url: _ifile.options.url + query.q,
                Type: 'json',
                type: 'POST',
                data: "fsp=" + JSON.stringify(query),
                success: function (myjson) {
                    if (myjson === undefined || myjson === null) {
                        fillEmptyContent(_ifile);
                        return;
                    } else if (myjson.status !== "ok") {
                        fillHeadMessage(_ifile, myjson);
                        return;
                    }
                    fillContent(_ifile, myjson);
                    fillHeadMessage(_ifile, myjson);

                },
                error: function (e) {
                    if (e.statusText != "abort") {
                        fillErrorBody(_ifile);
                    }
                }
            });
        };
        var submitRemoveDir = function (_ifile) {
            if (_ifile.xhr !== undefined) {
                _ifile.xhr.abort();
            }
            var query = {
                q: "rmd",
                a: {
                    "path": _ifile.options.currentPath + "/" + _ifile.selectedContentItem.name,
                    "file": true,
                    "directory": _ifile.options.ls_directoriesInContent,
                }
            };
            _ifile.xhr = $.ajax({
                url: _ifile.options.url + query.q,
                Type: 'json',
                type: 'POST',
                data: "fsp=" + JSON.stringify(query),
                success: function (myjson) {
                    if (myjson === undefined || myjson === null) {
                        fillEmptyContent(_ifile);
                        return;
                    } else if (myjson.status !== "ok") {
                        fillHeadMessage(_ifile, myjson);
                        return;
                    }
                    fillContent(_ifile, myjson);
                    fillHeadMessage(_ifile, myjson);

                },
                error: function (e) {
                    if (e.statusText != "abort") {
                        fillErrorBody(_ifile);
                    }
                }
            });
        };

        /****************
         Edit
         **********/
        var submitEditFile = function (_ifile) {
            if (_ifile.xhr !== undefined) {
                _ifile.xhr.abort();
            }
            var query = {
                q: "edt",
                a: {
                    "path": _ifile.options.currentPath + "/" + _ifile.selectedContentItem.name,
                }
            };
            _ifile.xhr = $.ajax({
                url: _ifile.options.url + query.q,
                Type: 'json',
                type: 'POST',
                data: "fsp=" + JSON.stringify(query),
                success: function (myjson) {
                    console.log(myjson);
                    if (myjson === undefined || myjson === null) {
                        fillEmptyContent(_ifile);
                        return;
                    } else if (myjson.status !== "ok") {
                        fillHeadMessage(_ifile, myjson);
                        return;
                    }
                    var ur = _ifile.options.currentPath + "/" + _ifile.selectedContentItem.name;
                    window.open($(cpId).val() + "/panel/file/explorer/assets/edit?p=" + ur, '_blank');
                },
                error: function (e) {
                    if (e.statusText != "abort") {
                        fillErrorBody(_ifile);
                    }
                }
            });
        };

        /****************
         Make Directory
         **********/
        var submitMakeDirectory = function (_ifile) {
            if (_ifile.xhr !== undefined) {
                _ifile.xhr.abort();
            }
            var query = {
                q: "mkdir",
                a: {
                    "path": _ifile.options.currentPath,
                    "file": true,
                    "directory": _ifile.options.ls_directoriesInContent,
                    "name": _ifile.nameModal.find("input").val()
                }
            };
            _ifile.xhr = $.ajax({
                url: _ifile.options.url + query.q,
                Type: 'json',
                type: 'POST',
                data: "fsp=" + JSON.stringify(query),
                success: function (myjson) {
                    console.log(myjson);
                    if (myjson === undefined || myjson === null) {
                        fillErrorBody(_ifile);
                        return;
                    } else if (myjson.status !== "ok") {
                        fillHeadMessage(_ifile, myjson);
                        return;
                    }
                    fillContent(_ifile, myjson);
                    fillHeadMessage(_ifile, myjson);
                },
                error: function (e) {
                    if (e.statusText != "abort") {
                        fillErrorBody(_ifile);
                    }
                }
            });
        };


        /****************
         Download
         **********/
        var submitDownloadFile = function (_ifile) {
            if (_ifile.xhr !== undefined) {
                _ifile.xhr.abort();
            }
            var query = {
                q: "dlf",
                a: {
                    "path": _ifile.options.currentPath + (_ifile.selectedContentItem !== undefined ? "/" + _ifile.selectedContentItem.name : "")
                }
            };
            $.AjaxDownloader({
                url: _ifile.options.url + query.q,
                data: {fsp: query}
            });
        };

        /****************
         Tree
         **********/
        var getItemIcon = function (extention, isDir) {
            if (isDir) {
                return 'clip-folder-2';
            }
            switch (extention) {
                case '.js':
                    return 'clip-code';
                case '.html':
                case '.htm':
                case '.xhtml':
                    return 'clip-html5';
                case '.css':
                case '.scss':
                    return 'clip-css3';
                case '.png':
                case '.gif':
                    return 'clip-pictures';
                case '.jpg':
                case '.jpeg':
                    return 'clip-image';
                case '.txt':
                case '.text':
                    return 'clip-file-2';
                case '.xml':
                    return 'clip-file-xml';
                case '.doc':
                case '.docx':
                    return 'clip-file-word';
                case '.pdf':
                    return 'clip-file-pdf';
                case '.json':
                    return 'clip-file-css';
                case '.zip':
                case '.rar':
                    return 'clip-file-zip';
            }
            return '';
        }
        var getTree = function (treeList) {
            if (treeList !== undefined) {
                var ts = "<ul>";
                var dirfile;
                $.each(treeList, function (i, v) {
                    dirfile =
                        ts += '<li title="' + v.name + '"' + (v.isDir ? 'i-dir' : 'i-file') + ' >' +
                            '<span i-name>' + v.name + '</span>' +
                            '<i class="' + getItemIcon(v.type, v.isDir) + '"></i>' +
                            (v.list === undefined || v.list.length === 0 ? '' : getTree(v.list)) +
                            '</li>'
                });
                ts += "</ul>";
                return ts;
            }
        }
        var fillTree = function (_ifile, myJson) {
            if (myJson.result.dir === undefined) {
                console.log("myJson.result.dir===undefined");
                return;
            }
            _ifile.tree.html(getTree(myJson.result.dir.list));
            initTree(_ifile);

        };
        var submitLs = function (_ifile) {
            if (_ifile.xhr !== undefined) {
                _ifile.xhr.abort();
            }
            var query = {
                q: "ls",
                a: {
                    "file": _ifile.options.ls_filesInTree,
                    "directory": true,
                    "depth": _ifile.options.ls_depth,
                }
            };
            _ifile.xhr = $.ajax({
                url: _ifile.options.url + query.q,
                Type: 'json',
                type: 'POST',
                data: "fsp=" + JSON.stringify(query),
                success: function (myjson) {
                    console.log(myjson);
                    if (myjson === undefined || myjson === null) {
                        fillErrorBody(_ifile);
                        return;
                    } else if (myjson.status !== "ok") {
                        fillEmptyBody(_ifile);
                        return;
                    }
                    fillTree(_ifile, myjson);
                },
                error: function (e) {
                    if (e.statusText != "abort") {
                        fillErrorBody(_ifile);
                    }
                }
            });
        };

        /****************
         Content
         **********/
        var fillEmptyContent = function (_ifile) {
            _ifile.content.html("");
        };
        var fillContent = function (_ifile, myJson) {
            if (myJson.result.dir === undefined) {
                console.log("myJson.result.dir===undefined");
                return;
            }
            if (myJson.result.dir.list !== undefined) {
                _ifile.content.html(getTree(myJson.result.dir.list));
            } else {
                fillEmptyContent(_ifile);
            }
            _ifile.head.find(".head-path").html(_ifile.options.currentPath);
            initContentItem(_ifile);

        };
        var submitContent = function (_ifile, path) {
            if (_ifile.xhr !== undefined) {
                _ifile.xhr.abort();
            }
            var query = {
                q: "cnt",
                a: {
                    "path": path,
                    "file": true,
                    "directory": _ifile.options.ls_directoriesInContent,
                }
            };
            _ifile.xhr = $.ajax({
                url: _ifile.options.url + query.q,
                Type: 'json',
                type: 'POST',
                data: "fsp=" + JSON.stringify(query),
                success: function (myjson) {
                    console.log(myjson);
                    if (myjson === undefined || myjson === null) {
                        fillErrorBody(_ifile);
                        return;
                    } else if (myjson.status !== "ok") {
                        fillEmptyContent(_ifile);
                        return;
                    }
                    fillContent(_ifile, myjson);
                    fillHeadMessage(_ifile, myjson);
                },
                error: function (e) {
                    if (e.statusText != "abort") {
                        fillErrorBody(_ifile);
                    }
                }
            });
        };

        /************* Each  **************/
        return this.each(function () {
            var _ifile = $(this);
            intOptions(_ifile);
            render(_ifile);
            submitLs(_ifile);
        });
    }
})
(jQuery);

/*************************************************************
 * Ir Ajax Downloader
 *************************************************************/
(function ($, undefined) {
    $.AjaxDownloader = function (options) {
        var settings = $.extend(true, {}, {
            data: $.ajaxSetup()["data"] || {},
            url: $.ajaxSetup()["url"]
        }, options);

        var form = $("<form>", {
            action: settings.url,
            method: "POST",
            target: "AjaxDownloaderIFrame",
        }).appendTo("body");
        $.each(settings.data, function (key, val) {
            $("<input>", {
                type: "hidden",
                name: key,
                value: (typeof val == "object") ? JSON.stringify(val) : val
            }).appendTo(form);
        });
        form.submit();
        form.remove();
    };
    $(document).ready(function () {
        $("<iframe>", {
            name: "AjaxDownloaderIFrame"
        })
            .hide()
            .appendTo("body");
    });
}(jQuery));
/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/
