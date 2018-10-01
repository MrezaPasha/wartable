package org.sadr.web.main.archive.file.download;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 * @version 1.95.05.06
 */
@RestController
@PersianName("مدیریت دانلود فایل")
public class FileDownloadController extends GenericControllerImpl<FileDownload, FileDownloadService> {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/file/download";
    //===================================================
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;
    ////////////////////
    ///////////////////////////////////////////
}
