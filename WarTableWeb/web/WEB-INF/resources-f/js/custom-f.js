
/***********************************************************/
/*                 custom-f                                */
/***********************************************************/
var CustomFrontJs = function () {
    var runHeaderNotice = function () {
        var scroll_text;
        $('.alert-notice').animate({opacity: '1'}, 5000).animate({opacity: '0'}, 500);
        $('.close.close-2').click(function () {
            $(this).parent().stop(true, false).slideUp(1000);
        });
    }
    return {
        init: function () {
            runHeaderNotice();
        }
    };
}();
