/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/
var CustomPanelJs = function () {
    var runNotice = function () {
        var nc = $('#n-count-id').html();
        for (i = 0; i < nc; i++) {
            $('#notice-id-' + i).animate({
                top: '+=' + (100 + (60 * nc) - (i * 60)),
                opacity: '1'
            }, 1000 + 1000 * i).animate({opacity: '1'}, 3000).animate({
                opacity: '0.5'
            }, 1000);
        }
        ;
        $('.alert-notice').hover(function () {
            $(this).animate({opacity: '1'});
        });
        $('.alert-notice').mouseleave(function () {
            $(this).animate({opacity: '0.3'});
        });
        $('.alert-notice').click(function () {
            $(this).hide();
        });
    };
    var runRightMenu = function () {
        if ($('#amId').val() !== undefined && $('#amId').val() !== "") {
            var sp = $('#amId').val().split(",");
            for (i = 0; i < sp.length; i++) {
                $('[data-menu-id*="|' + sp[i] + '|"],[data-menu-id="' + sp[i] + '"]').css({"display": "list-item", "visibility": "visible"});
                $('[data-menu-id*="|' + sp[i] + '|"],[data-menu-id="' + sp[i] + '"]').parents("li").css({"display": "block", "visibility": "visible"});
            }
        }
        $('[data-menu-id*="|' + $("#rightMenuIdHandler").val() + '|"],[data-menu-id="' + $("#rightMenuIdHandler").val() + '"]').each(function () {
            $(this).addClass("active");
            $(this).parents("li").addClass("active").addClass("open");
        });
    };
    var runPropertor = function (prop) {
        var saveSuccessAnimate = function () {
            $(".save-success").fadeIn();
            setTimeout(function () {
                $('.save-success').fadeOut();
            }, 1000);
            setTimeout(function () {
                $('.save-success').remove();
            }, 1200);
        }
        var saveFailedAnimate = function () {
            $(".save-failed").fadeIn();
            setTimeout(function () {
                $('.save-failed').fadeOut();
            }, 1000);
            setTimeout(function () {
                $('.save-failed').remove();
            }, 1200);
        }
        var setProp = function (_this) {
            var isOk;
            var formData = new FormData();
            formData.append("id", _this.data("id"));
            formData.append("type", _this.data("type"));
            formData.append("value", (_this.attr("type") === "checkbox") ? _this.is(':checked') : _this.val());
            $.ajax({
                url: $("#cpId").val() + "/panel/propertor/" + prop + "/set",
                Type: 'json',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    isOk = data.result.isOk;
                },
                error: function () {
                    isOk = false;
                },
                async: false
            });

            if (isOk) {
                if (_this.attr("type") === "checkbox") {
                    _this.parent().before("<i class='clip-checkmark-2 save-success'></i>");
                    saveSuccessAnimate();
                } else {
                    _this.before("<i class='clip-checkmark-2 save-success'></i>");
                    saveSuccessAnimate();
                }
            } else {
                if (_this.attr("type") === "checkbox") {
                    _this.parent().before("<i class='clip-notification save-failed'></i>");
                    saveFailedAnimate();
                } else {
                    _this.before("<i class='clip-notification save-failed'></i>");
                    saveFailedAnimate();
                }
            }
        };
        $("input[type='checkbox']").change(function () {
            setProp($(this));
        });
        $("input[type='text'],input[type='number']").change(function () {
            setProp($(this));
        });
        $("textarea").change(function () {
            setProp($(this));
        });
        $("select").change(function () {
            setProp($(this));
        });
        $("[i-propertor-default]").click(function () {
            var name = $(this).attr("i-propertor-name");
            var target = $("[name='" + name + "']");
            if ((target.is(':checkbox') && target.data("toggle") != undefined)) {
                if ((target.prop("checked") && $(this).attr("i-propertor-default") === "true") ||
                    (!target.prop("checked") && $(this).attr("i-propertor-default") === "false")
                ) {
                    return;
                }
                target.val($(this).attr("i-propertor-default"));
                target.bootstrapToggle(target.val() === "true" ? "on" : "off");
            } else {
                if (target.val() == $(this).attr("i-propertor-default")) {
                    return;
                }
                target.val($(this).attr("i-propertor-default"));
                setProp(target);
            }
        });
    };
    var runPwLength = function () {
        var options = {
            onLoad: function () {
                $('label').html('Start typing password');
            }
        };
        options.ui = {
            bootstrap3: true,
            container: "#pwd-container",
            showVerdictsInsideProgressBar: true,
            viewports: {
                progress: ".pwstrength_viewport_progress"
            },
        };
        $('#newPassword').pwstrength(options);
    };
    var runRandomUsername = function () {
        var generate = function () {
            var un;
            $.ajax({
                url: $("#cpId").val() + "/panel/user/username/random",
                Type: 'json',
                type: 'POST',
                success: function (data) {
                    console.log(data);
                    un = data.result.username;
                },
                error: function (err) {
                    alert("خطا در تولید نام کاربری!");
                },
                async: false
            });
            return un;
        }
        $("#randomButton").click(function () {
            var un = generate();
            if (un !== "") {
                $("input[name=username]").val(un);
            }
        })
    };
    var runNoteSync = function () {
        var syncNotes = function () {
            var un;
            $.ajax({
                url: $("#cpId").val() + "/panel/note/sync",
                Type: 'json',
                type: 'POST',
                success: function (data) {
                    // console.log(data);
                    if (data !== undefined && data.status === "ok") {
                        $('#noteSyncModal').modal('show');
                        console.log("ookk");
                        var hx = "<table class='note-sync-table'><thead><tr><th>عنوان</th><th>زمان</th><th>اهمیت</th><th></th></tr></thead><tbody>";
                        $.each(data.result.notes, function (i, v) {
                            console.log(v);
                            hx +=
                                '<tr><td class="note-title">' + v.title + '</td>'
                                + '<td class="note-time">' + v.dateTime + '</td>'
                                + '<td class="note-importance">' + v.importance + '</td>'
                                + '<td class="note-show"><a href="' + $('#cpId').val() + '/panel/note/details/' + v.id + '">مشاهده</a> </td></tr>'
                        });
                        hx += "</tbody></table>"
                        $("#noteSyncTitle").html(data.result.header);
                        $("#noteSyncBody").html(hx);
                    }
                },
                error: function (err) {
                    console.log(err);
                },
                async: false
            });
            return un;
        }
        if ($("#noteMenu").length > 0 && $("#noteMenu").css("visibility") == "visible") {
            syncNotes();
            setInterval(syncNotes, 3000);
        }
    };


    return {
        init: function () {
            runNotice();
            runRightMenu();
            runNoteSync();
        },
        initPropertor: function (prop) {
            runPropertor(prop);
        },
        intPwLength: function () {
            runPwLength();
        },
        initNoteSync: function () {
            runNoteSync();
        },
        initRandomUsername: function () {
            runRandomUsername();
        }
    };
}
();
/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/
