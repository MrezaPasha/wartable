package org.sadr.web.main.system.backup;

import org.sadr._core._type.TtDataType;
import org.sadr._core._type.TtRestrictionOperator;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GB;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.meta.generic.JB;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.Searchee;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main._core._type.TtTile___;
import org.sadr.web.main._core.tools._type.TtUploadIfExist;
import org.sadr.web.main._core.tools.uploader.Uploader;
import org.sadr.web.main._core.utils.CacheStatic;
import org.sadr.web.main._core.utils.Notice2;
import org.sadr.web.main._core.utils.Referer;
import org.sadr.web.main._core.utils._type.TtNotice;
import org.sadr.web.main.archive._type.TtFileUploadStatus;
import org.sadr.web.main.archive._type.TtRepoDirectory;
import org.sadr.web.main.archive.file.file.File;
import org.sadr.web.main.archive.file.file.FileService;
import org.sadr.web.main.system._type.*;
import org.sadr.web.main.system.irror.IrrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author masoud
 */
@RestController
@PersianName("پشتیبان گیری")
public class BackupController extends GenericControllerImpl<Backup, BackupService> {

    ////////////////////
    private final String _PANEL_URL = "/panel/backup";

    public BackupController() {
    }

    private IrrorService irrorService;
    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setIrrorService(IrrorService irrorService) {
        this.irrorService = irrorService;
    }

    ///////////////////////////////////////////////////////


    @PersianName("بارگذاری فایل")
    @RequestMapping(value = _PANEL_URL + "/upload")
    public ModelAndView pUpload() {
        return TtTile___.p_backup_upload.___getDisModel(_PANEL_URL + "/upload", null, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/upload", method = RequestMethod.POST)
    public ModelAndView pUpload(@RequestParam(value = "attachment") MultipartFile attachment,
                                final RedirectAttributes redirectAttributes) {

        if (!attachment.getOriginalFilename().endsWith(".dbk")
                || !attachment.getContentType().equals("application/octet-stream")) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.file.upload.format.invalid", TtNotice.Danger)));
            return Referer.redirect(_PANEL_URL + "/upload", null, TtTaskActionStatus.Failure, notice2s);
        }

        File file = Uploader.getInstance().upload(attachment, TtRepoDirectory.Db_Backup, TtUploadIfExist.RenameNewFile);

        if (file == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.file.upload.failed", TtNotice.Danger)));
            return Referer.redirect(_PANEL_URL + "/upload", null, TtTaskActionStatus.Failure, notice2s);
        }
        file.setIsTemporary(false);
        file.setIsContainOrginal(true);
        file.setAccessLevel(TtTaskAccessLevel.Grant);
        file.setTitle(attachment.getOriginalFilename());
        file.setUploadStatus(TtFileUploadStatus.Uploaded);

        fileService.save(file);

        Backup backup = new Backup();
        backup.setFile(file);
        backup.setBackupDateTime(ParsCalendar.getInstance().getShortDateTime());
        backup.setBackupType(TtBackupType.Uploaded);
        this.service.save(backup);

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.backup.upload.success", file.getSecretNote(), TtNotice.Success, file.getTitle())));
        return Referer.redirect(_PANEL_URL + "/list", null, TtTaskActionStatus.Success, notice2s);

    }


    @PersianName("پشتیبان گیری")
    @RequestMapping(value = _PANEL_URL + "/backup")
    public ModelAndView pBackup(RedirectAttributes redirectAttributes) throws InterruptedException {

        CacheStatic.setDevelopingMode(true);
        Thread.sleep(3000);
        String name = "";

        Backup backup = this.service.backup(TtBackupType.Manual, false);
        if (backup != null) {
            name = backup.getTitle();
        }

        Thread.sleep(5000);
        CacheStatic.setDevelopingMode(false);

        if (backup == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.backup.create.failed", TtNotice.Danger)));
            return Referer.redirect(_PANEL_URL + "/list", null, TtTaskActionStatus.Failure, notice2s);
        }

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.backup.create.success", backup.getTitle(), TtNotice.Success, name)));
        return Referer.redirect(_PANEL_URL + "/list", null, TtTaskActionStatus.Success, notice2s);
    }


    @PersianName("بازیابی")
    @RequestMapping(value = _PANEL_URL + "/restore/{uid}")
    public ModelAndView pRestore(@PathVariable("uid") long uid,
                                 RedirectAttributes redirectAttributes) throws InterruptedException {
        Backup backup = this.service.findById(uid, Backup._FILE);

        if (backup == null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.backup.restore.failed", TtNotice.Danger)));
            return Referer.redirect(_PANEL_URL + "/list", null, TtTaskActionStatus.Failure, notice2s);
        }

        CacheStatic.setDevelopingMode(true);
        Thread.sleep(1000);

        String filePath = this.service.restore(backup);
        Thread.sleep(1000);

        CacheStatic.setDevelopingMode(false);

        if (filePath != null) {
            Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N1.backup.restore.success", backup.getTitle(), TtNotice.Success, backup.getTitle())));
            return Referer.redirect(_PANEL_URL + "/list", null, TtTaskActionStatus.Success, notice2s);
        }

        Notice2[] notice2s = Notice2.initRedirectAttr(redirectAttributes, Notice2.addNotices(new Notice2("N.backup.restore.failed", TtNotice.Danger)));
        return Referer.redirect(_PANEL_URL + "/list", null, TtTaskActionStatus.Success, notice2s);
    }

    @PersianName("لیست")
    @RequestMapping(value = _PANEL_URL + "/list")
    public ModelAndView pList(Model model) {
//int i=1/0;

        Searchee.getInstance().setAttributeArray(
                model,
                "f_title",
                TtDataType.String,
                TtRestrictionOperator.Like_ANY,
                true,
                Backup._FILE, File.TITLE
        );

        GB.searchTableColumns(model,
                Backup.class,
                GB.col(Backup.ID),
                GB.col(Backup.BACKUP_DATE_TIME),
                GB.col(Backup.$TITLE),
                GB.col(Backup.BACKUP_TYPE)
        );
        return TtTile___.p_backup_list.___getDisModel(TtTaskActionSubType.Take_Report, TtTaskActionStatus.Success);
    }

    @RequestMapping(value = _PANEL_URL + "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> pList(HttpServletRequest request, HttpSession session,
                                 @RequestParam(value = "ap", required = false) String ajaxParam) {
        try {
            GB gb = GB.init(Backup.class)
                    .set(
                            Backup.BACKUP_DATE_TIME,
                            Backup.BACKUP_TYPE
                    )
                    .setGbs(
                            GB.init(File.class, Backup._FILE)
                                    .set(
                                            File.TITLE
                                    )
                    )
                    .setSearchParams(ajaxParam);
            JB jb = JB.init()
                    .set(
                            Backup.BACKUP_DATE_TIME,
                            Backup.$TITLE,
                            Backup.BACKUP_TYPE
                    )
                    .setJbs(
                            JB.init(File.class, Backup._FILE)
                                    .set(File.ID)
                    );

            String json = this.service.findAllJson(gb, jb);
            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", "application/json; charset=utf-8");
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (Exception e) {
            irrorService.submit(e, request, TtIrrorPlace.Controller, TtIrrorLevel.Error);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>("", headers, HttpStatus.OK);
    }

}
