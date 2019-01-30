package org.sadr.web.main.archive.file.file;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericControllerImpl;
import org.sadr._core.utils.OutLog;
import org.sadr.web.main._core.meta.annotation.TaskAccessLevel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    @TaskAccessLevel
    @PersianName("دانلود فایل با استفاده از شناسه")
    @RequestMapping(_PANEL_URL + "/dl/{id}")
    @ResponseBody
    public void fDownloadSourceById(
            @PathVariable("id") long id,
            HttpServletResponse response) throws IOException {
        File f = this.service.findBy(Restrictions.and(Restrictions.eq(File.ID, id)));
        if (f == null) {
            OutLog.pl("");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
//        if (f.getAccessLevel() != TtTaskAccessLevel.Free4Gusts) {
//            OutLog.pl("forbid");
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
        OutLog.pl(f.getAbsolutePathName());
        String apn = f.getAbsolutePathName();
        Path fpath = Paths.get(apn);
        if (Files.exists(fpath)) {
            OutLog.pl("");
            response.setContentType(f.getContentType());
            response.addHeader("Content-Disposition", "attachment; filename=" + f.getOrginalName());
            try {
                Files.copy(fpath, response.getOutputStream());

                this.service.update(f);
                response.getOutputStream().flush();
            } catch (IOException ex) {
                OutLog.pl(ex.getMessage());
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }


}
