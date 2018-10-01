/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */
CKEDITOR.editorConfig = function (config) {
    config.contentsLanguage = 'fa';
    config.language = 'fa';
    config.height = 120;
    config.toolbarGroups = [
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
    config.removeButtons = 'Subscript,Superscript,Strike';
    config.format_tags = 'p;h1;h2;h3;pre';
    config.removeDialogTabs = 'image:advanced;link:advanced';
};
       
