package org.sadr.web.main.archive.directory;

import org.sadr._core._type.TtYesNo;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr._core.utils.Environment;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.archive._type.TtDirectoryOrigin;
import org.sadr.web.main.archive._type.TtRepoDirectory;
import org.sadr.web.main.archive.file.file.File;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author masoud
 */
@PersianName("پوشه")
@Entity
@Table(name = "Web.Archive.Directory")
public class Directory extends GenericDataModel<Directory> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String NAME = "name";public static final String COMMENT = "comment";public static final String PATH = "path";public static final String REPO_DIRECTORY = "repoDirectory";public static final String DIRECTORY_ORIGIN = "directoryOrigin";public static final String CLASS_NAME = "className";public static final String URL = "url";public static final String LEVEL = "level";public static final String CONTAINED_FILE_COUNT = "containedFileCount";public static final String CONTAINED_DIRECTORY_COUNT = "containedDirectoryCount";public static final String IS_PERMANENT = "isPermanent";public static final String ICON = "icon";public static final String _PARENT = "parent";public static final String _DIRECTORYS = "directorys";public static final String _FILES = "files";public static final String _OWNER = "owner";public static final String $ABSOLUTE_PATH = "absolutePath";public static final String $RELATIVE_PATH = "relativePath";public static final String $PERMANENT_Y = "permanentY";public static final String $ACT_COLUMNS = "actColumns";public static final String $VIR_COLUMNS = "virColumns";public static final String $REL_COLUMNS = "relColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
//#########******#######// StaticFields: End //

    @NotNull
    @PersianName("نام")
    private String name;

    @PersianName("توضیحات")
    private String comment;

    @PersianName("آدرس")
    private String path;

    @PersianName("نوع پوشه")
    private TtRepoDirectory repoDirectory;

    @PersianName("مبدا پوشه بندی")
    private TtDirectoryOrigin directoryOrigin;

    @PersianName("نام کلاس")
    private String className;

    // ممکن است نیاز باشد که با کلیک بر روی  پوشه به یک آدرس خاص ارجاع داده شود
    @PersianName("URL")
    private String url;

    @PersianName("سطح")
    private int level;

    // مجموع کل فایلهایی که در این پوشه قرار دارد
    @PersianName("نام کلاس")
    private int containedFileCount;

    // مجموع کل دایرکتوری هایی که درون این پوشه قرار دارد
    @PersianName("تعداد پوشه های درونی")
    private int containedDirectoryCount;

    // آیا پوشه پایدار است؟- پوشه پایدار قابل حذف نیست
    @PersianName("پایا")
    private Boolean isPermanent;

    @PersianName("آیکون")
    private String icon;

    @ManyToOne
    @PersianName("پوشه ی بالاسری")
    private Directory parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @PersianName("دایرکتوری ها")
    private Set<Directory> directorys;

    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL)
    @PersianName("فیلدها")
    private Set<File> files;
    ///##############################  OWNER
    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("مالک")
    private User owner;

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS
    public String getAbsolutePath() {
        return
                Environment.getInstance().getProjectRepositoryAP() + this.path;
    }

    public String getRelativePath() {
        return Environment.getInstance().getRepositoryAddress() + this.path;
    }

    ///#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=  METHODS

    public TtRepoDirectory getRepoDirectory() {
        return repoDirectory;
    }

    public void setRepoDirectory(TtRepoDirectory repoDirectory) {
        this.repoDirectory = repoDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<Directory> getDirectorys() {
        return directorys;
    }

    public void setDirectorys(Set<Directory> directorys) {
        this.directorys = directorys;
    }

    public int getContainedFileCount() {
        return containedFileCount;
    }

    public void setContainedFileCount(int containedFileCount) {
        this.containedFileCount = containedFileCount;
    }

    public int getContainedDirectoryCount() {
        return containedDirectoryCount;
    }

    public void setContainedDirectoryCount(int containedDirectoryCount) {
        this.containedDirectoryCount = containedDirectoryCount;
    }

    public Boolean getIsPermanent() {
        return isPermanent;
    }

    public void setIsPermanent(Boolean isPermanent) {
        this.isPermanent = isPermanent;
    }

    public TtYesNo getPermanentY() {
        return TtYesNo.getValueByBool(isPermanent);
    }

    public void setPermanentY(TtYesNo permanent) {
        this.isPermanent = permanent.getBoolean();
    }

    public TtDirectoryOrigin getDirectoryOrigin() {
        return directoryOrigin;
    }

    public void setDirectoryOrigin(TtDirectoryOrigin directoryOrigin) {
        this.directoryOrigin = directoryOrigin;
    }
}
