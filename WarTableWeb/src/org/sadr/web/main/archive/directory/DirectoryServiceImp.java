package org.sadr.web.main.archive.directory;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtInitResult;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.Environment;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils._type.TtOutLog;
import org.sadr.web.main.archive._type.TtDirectoryOrigin;
import org.sadr.web.main.archive._type.TtRepoDirectory;
import org.springframework.stereotype.Service;

/**
 * @author masoud
 */
@Service
//@Component
public class DirectoryServiceImp extends GenericServiceImpl<Directory, DirectoryDao> implements DirectoryService {

    private final String subPath = "/_asd_";

    @Override
    public Directory getDirectory(TtRepoDirectory repoDirectory) {
        Directory d = dao.getDirectory(repoDirectory);
        if (d == null) {
            OutLog.po("-->> direc is null");
            d = new Directory();
            d.setLevel(1);
            d.setIsPermanent(true);
            d.setComment("پیش ساز");
            d.setName(repoDirectory.getKey());
            d.setRepoDirectory(repoDirectory);
            d.setPath(Environment.FILE_SEPARATOR + repoDirectory.getKey());
            this.save(d);
        }
        OutLog.p(d.getName() + " " + d.getPath());
        java.io.File dir = new java.io.File(d.getAbsolutePath());
        if (!dir.exists()) {
            OutLog.p("dir create on server file directory");
            dir.mkdirs();
        }
        return d;
    }

    @Override
    public Directory getDirectory(String path) {
        return getDirectory(path, TtDirectoryOrigin.Repository);
    }

    @Override
    public Directory getDirectory(String path, TtDirectoryOrigin directoryOrigin) {
        Directory d = dao.getDirectory(path);
        if (d == null) {
            String[] pt = path.split(Environment.FILE_SEPARATOR);
            OutLog.po("-->> direc is null");
            d = new Directory();
            d.setLevel(1);
            d.setDirectoryOrigin(directoryOrigin);
            d.setIsPermanent(true);
            d.setComment("پیش ساز ۲");
            d.setName(pt[pt.length - 1]);
            d.setPath(path);
            if (pt.length > 1) {
                OutLog.pl("ll " + path.substring(0, path.lastIndexOf(Environment.FILE_SEPARATOR)));
                d.setParent(getDirectory(path.substring(0, path.lastIndexOf(Environment.FILE_SEPARATOR))));
            }
            this.save(d);

            java.io.File dir = new java.io.File(d.getAbsolutePath());
            if (!dir.exists()) {
                OutLog.p("dir create on server file directory");
                dir.mkdirs();
            }
        }
        return d;
    }

    @Override
    public Directory getDirectoryAndSubs(TtRepoDirectory repoDirectory) {
        Directory d = dao.getDirectory(repoDirectory);
        if (d == null) {
            OutLog.po("-->> direc is null");
            d = new Directory();
            d.setLevel(1);
            d.setIsPermanent(true);
            d.setComment("پیش ساز");
            d.setName(repoDirectory.getKey());
            d.setRepoDirectory(repoDirectory);
            d.setPath(Environment.FILE_SEPARATOR + repoDirectory.getKey());
            this.save(d);
        } else {
            Hibernate.initialize(d.getDirectorys());
        }
        OutLog.p(d.getName() + " " + d.getPath());
        java.io.File dir = new java.io.File(d.getAbsolutePath());
        if (!dir.exists()) {
            OutLog.p("dir create on server file directory");
            dir.mkdirs();
        }
        return d;
    }



    @Override
    public Directory getAutoSubDirectory(Directory d) {
        OutLog.pl("");
        if (d == null) {
            return null;
        }
        OutLog.pl("");
        int max = 1;
        String s;
        for (Directory di : d.getDirectorys()) {
            s = di.getPath();
            OutLog.p("");
            if (s.startsWith(subPath)) {
                max++;
                OutLog.p("");
            }
        }
        while (isExist(
                Restrictions.and(
                        Restrictions.eq(Directory.PATH, subPath + max),
                        Restrictions.eq(Directory._PARENT, d)
                ))) {
            max++;
            OutLog.p("");
        }

        OutLog.po("-->> direc is null");
        Directory dd = new Directory();
        dd.setLevel(2);
        dd.setIsPermanent(true);
        dd.setComment("زیرپوشه اتوماتیک");
        dd.setName(subPath.substring(1) + max);
        dd.setParent(d);
        dd.setPath(subPath + max);
        this.save(dd);

        OutLog.p(dd.getName() + " " + dd.getPath());
        java.io.File dir = new java.io.File(dd.getAbsolutePath());
        if (!dir.exists()) {
            OutLog.p("dir create on server file directory");
            dir.mkdirs();
        }
        return dd;
    }


    @Override
    public TtInitResult init() {
        String rap = Environment.getInstance().getProjectRepositoryAP();
        OutLog.pl(rap, TtOutLog.Info);
        for (TtRepoDirectory value : TtRepoDirectory.values()) {
            OutLog.po(value.getKey());
            Directory d = this.findBy(Restrictions.eq("repoDirectory", value));
            if (d == null) {
                OutLog.p("-->> direc is null");
                d = new Directory();
                d.setLevel(1);
                d.setIsPermanent(true);
                d.setComment("عنوان");
                d.setName(value.getKey());
                d.setRepoDirectory(value);
                d.setPath(Environment.FILE_SEPARATOR + value.getKey());
                this.save(d);
            }
            OutLog.p(d.getName() + " " + d.getPath());
            java.io.File dir = new java.io.File(rap + d.getPath());
            if (!dir.exists()) {
                OutLog.p("dir create on server file directory");
                dir.mkdirs();
            }
        }
        return TtInitResult.Success;
    }
}
