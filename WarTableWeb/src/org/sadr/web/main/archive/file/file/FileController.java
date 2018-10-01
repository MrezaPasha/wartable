package org.sadr.web.main.archive.file.file;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtEntityState;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.utils.JsonBuilder;
import org.sadr._core.utils.OutLog;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.meta.annotation.TaskAccessLevel;
import org.sadr.web.main._core.tools.uploader.Uploader;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.archive._type.TtFileDownloadStatus;
import org.sadr.web.main.archive._type.TtFileFetchManner;
import org.sadr.web.main.archive._type.TtFileUploadStatus;
import org.sadr.web.main.archive._type.TtRepoDirectory;
import org.sadr.web.main.archive.directory.Directory;
import org.sadr.web.main.archive.directory.DirectoryService;
import org.sadr.web.main.archive.file.download.FileDownload;
import org.sadr.web.main.archive.file.download.FileDownloadService;
import org.sadr.web.main.system._type.TtHttpErrorCode___;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author masoud
 */
@RestController
@PersianName("مدیریت فایل (فایل سیستم)")
public class FileController extends GenericControllerImpl<File, FileService> {

    ////////////////////
    private final String REQUEST_MAPPING_BASE = "/file";
    //===================================================
    private final String _FRONT_URL = "" + REQUEST_MAPPING_BASE;
    private final String _PANEL_URL = "/panel" + REQUEST_MAPPING_BASE;

}
