package org.sadr.web.main.archive.directory;

import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت پوشه (فایل سیستم)")
public class DirectoryController extends GenericControllerImpl<Directory, DirectoryService> {

    public DirectoryController() {

    }
}
