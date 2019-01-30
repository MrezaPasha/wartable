/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */
CKEDITOR.editorConfig = function (config) {
    // Define changes to default configuration here.
    // 
// For the complete reference:
    // http://docs.ckeditor.com/#!/api/CKEDITOR.config
    config.contentsLanguage = 'fa';
    config.language = 'fa';
    config.height = 300;

    //config.language_list = [ 'fa:Farsi:rtl', 'en:English' ];
    //config.scayt_sLang = 'fa_IR';

    // The toolbar groups arrangement, optimized for two toolbar rows.
    config.toolbarGroups = [
        {name: 'clipboard', groups: ['clipboard', 'undo']},
//        {name: 'editing', groups: ['find', 'selection', 'spellchecker']},
        {name: 'links'},
        {name: 'insert'},
        {name: 'forms'},
        {name: 'tools'},
        {name: 'document', groups: ['mode', 'document', 'doctools']},
        {name: 'others'},
        '/',
        {name: 'basicstyles', groups: ['basicstyles', 'cleanup']},
        {name: 'paragraph', groups: ['list', 'indent', 'blocks', 'align', 'bidi']},
        {name: 'styles'},
        {name: 'colors'}
    ];
    config.contentsCss = $('#cpId').val() + '/resources/plugins/ckeditor/contents.css';
    config.font_names =
        'ایران سنس/IRANSans,Tahoma;تاهما/Tahoma, IRANSans, sans-serif; آریال/Arial,IRANSans;Times New Roman/Times New Roman, Times, serif;'
        + 'Comic Sans MS/Comic Sans MS, cursive;'
        + 'Courier New/Courier New, Courier, monospace;Georgia/Georgia, serif;'
        + 'Lucida Sans Unicode/Lucida Sans Unicode, Lucida Grande, sans-serif;'
        + 'Trebuchet MS/Trebuchet MS, Helvetica, sans-serif;'
        + 'Verdana/Verdana, Geneva, sans-serif';

    config.font_defaultLabel = "ایران سنس/IRANSans";
    // Remove some buttons, provided by the standard plugins, which we don't
    // need to have in the Standard(s) toolbar.
    config.removeButtons = 'Subscript,Superscript,Strike';

    // Se the most common block elements.
    config.format_tags = 'p;h1;h2;h3;pre';

    // Make dialogs simpler.
    config.removeDialogTabs = 'image:advanced;link:advanced';
};
