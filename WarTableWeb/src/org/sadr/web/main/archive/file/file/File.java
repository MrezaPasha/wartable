package org.sadr.web.main.archive.file.file;

import org.sadr._core._type.TtYesNo;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr._core.utils.Environment;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.archive._type.TtFileFetchManner;
import org.sadr.web.main.archive._type.TtFileUploadStatus;
import org.sadr.web.main.archive.directory.Directory;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author masoud
 */
@PersianName("فایل")
@Entity
@Table(name = "Web.Archive.File")
public class File extends GenericDataModel<File> implements Serializable {
    //#########++++++#######// StaticFields: Start //
    public static final String TITLE = "title";
    public static final String ORGINAL_NAME = "orginalName";
    public static final String CONTENT_TYPE = "contentType";
    public static final String THUMBNAILS = "thumbnails";
    public static final String SIZE = "size";
    public static final String IS_TEMPORARY = "isTemporary";
    public static final String IS_CONTAIN_ORGINAL = "isContainOrginal";
    public static final String UPLOAD_LINK = "uploadLink";
    public static final String UPLOAD_LINK_COMMENT = "uploadLinkComment";
    public static final String FETCH_MANNER = "fetchManner";
    public static final String UPLOAD_STATUS = "uploadStatus";
    public static final String ACCESS_LEVEL = "accessLevel";
    public static final String TRY_TO_DOWNLOAD_COUNT = "tryToDownloadCount";
    public static final String DOWNLOAD_COUNT = "downloadCount";
    public static final String TRY_TO_DOWNLOAD_COUNT_GUEST = "tryToDownloadCountGuest";
    public static final String DOWNLOAD_COUNT_GUEST = "downloadCountGuest";
    public static final String ACCESS_RULE = "accessRule";
    public static final String LAST_UPLOAD_DATE_TIME = "lastUploadDateTime";
    public static final String DIRECTORY_RELATIVE_PATH = "directoryRelativePath";
    public static final String DIRECTORY_ABSOLUTE_PATH = "directoryAbsolutePath";
    public static final String _OWNER = "owner";
    public static final String _FILE_DOWNLOADS = "fileDownloads";
    public static final String _DIRECTORY = "directory";
    public static final String $NAME = "name";
    public static final String $SIZE_S = "sizeS";
    public static final String $SOURCE_FILE = "sourceFile";
    public static final String $IS_UPLOADED = "isUploaded";
    public static final String $THUMBNAIL_NAME = "thumbnailName";
    public static final String $IS_TEMPORARY_Y = "isTemporaryY";
    public static final String $EXTENTION = "extention";
    public static final String $RELATIVE_PATH_NAME30_X40 = "relativePathName30X40";
    public static final String $RELATIVE_PATH_NAME60_X60 = "relativePathName60X60";
    public static final String $RELATIVE_PATH_NAME120_X160 = "relativePathName120X160";
    public static final String $RELATIVE_PATH_NAME150_X200 = "relativePathName150X200";
    public static final String $RELATIVE_PATH_NAME40_X30 = "relativePathName40X30";
    public static final String $RELATIVE_PATH_NAME600_X450 = "relativePathName600X450";
    public static final String $RELATIVE_PATH_NAME500_X200 = "relativePathName500X200";
    public static final String $RELATIVE_PATH_NAME500_X700 = "relativePathName500X700";
    public static final String $RELATIVE_PATH_NAME350_X250 = "relativePathName350X250";
    public static final String $RELATIVE_PATH_NAME2400_X800 = "relativePathName2400X800";
    public static final String $RELATIVE_PATH_NAME90_X120 = "relativePathName90X120";
    public static final String $RELATIVE_PATH_NAME200_X150 = "relativePathName200X150";
    public static final String $RELATIVE_PATH_NAME25_X25 = "relativePathName25X25";
    public static final String $RELATIVE_PATH_NAME500_X500 = "relativePathName500X500";
    public static final String $RELATIVE_PATH_NAME1000_X400 = "relativePathName1000X400";
    public static final String $RELATIVE_PATH_NAME250_X250 = "relativePathName250X250";
    public static final String $THUMBNAILS_ARRAY = "thumbnailsArray";
    public static final String $INFO_FOR_UPLOADER_JSON = "infoForUploaderJson";
    public static final String $RELATIVE_PATH_NAME50_X50 = "relativePathName50X50";
    public static final String $RELATIVE_PATH_NAME150_X150 = "relativePathName150X150";
    public static final String $RELATIVE_PATH_NAME = "relativePathName";
    public static final String $RELATIVE_PATH_NAME30_X30 = "relativePathName30X30";
    public static final String $RELATIVE_PATH_NAME75_X50 = "relativePathName75X50";
    public static final String $RELATIVE_PATH_NAME1000_X500 = "relativePathName1000X500";
    public static final String $IS_FROM_UPLOAD_LINK = "isFromUploadLink";
    public static final String $RELATIVE_PATH_NAME120_X40 = "relativePathName120X40";
    public static final String $RELATIVE_PATH_NAME100_X100 = "relativePathName100X100";
    public static final String $RELATIVE_PATH_NAME900_X300 = "relativePathName900X300";
    public static final String $RELATIVE_PATH_NAME600_X400 = "relativePathName600X400";
    public static final String $RELATIVE_PATH_NAME200_X200 = "relativePathName200X200";
    public static final String $RELATIVE_PATH_NAME150_X100 = "relativePathName150X100";
    public static final String $RELATIVE_PATH_NAME100_X50 = "relativePathName100X50";
    public static final String $ABSOLUTE_PATH_NAME = "absolutePathName";
    public static final String $FULL_TITLE = "fullTitle";
    public static final String $ACT_COLUMNS = "actColumns";
    public static final String $SECRET_NOTE = "secretNote";
    public static final String $REL_COLUMNS = "relColumns";
    public static final String $VIR_COLUMNS = "virColumns";
    private static String[] actColumns;
    private static String[] relColumns;
    private static String[] virColumns;

    public static void setAvrColumns(String acts, String virts, String rels) {
        if (acts != null) {
            actColumns = acts.split(",");
        }
        if (virts != null) {
            virColumns = virts.split(",");
        }
        if (rels != null) {
            relColumns = rels.split(",");
        }
    }

    public static String[] getActColumns() {
        return actColumns;
    }

    public static String[] getVirColumns() {
        return virColumns;
    }

    public static String[] getRelColumns() {
        return relColumns;
    }
//#########******#######// StaticFields: End //

    @Size(max = 100)
    @PersianName("عنوان")
    private String title;
    @Size(max = 100)
    @PersianName("نام اصلی")
    private String orginalName;
    @Size(max = 250)
    @PersianName("نوع محتوا")
    private String contentType;
    @Size(max = 512)
    @PersianName("بند انگشتی")
    private String thumbnails;
    @PersianName("اندازه")
    private long size;

    @PersianName("موقت")
    private Boolean isTemporary;
    @PersianName("فایل اصلی")
    private Boolean isContainOrginal;

    @PersianName("لینک بارگذاری")
    private String uploadLink;
    @PersianName("شرح لینک بارگذاری")
    private String uploadLinkComment;
    @PersianName("روش بارگذاری")
    private TtFileFetchManner fetchManner;
    @PersianName("وضعیت بارگذاری")
    private TtFileUploadStatus uploadStatus;

    @PersianName("سطح دسترسی")
    private TtTaskAccessLevel accessLevel;

    @PersianName("تعداد تلاش برای دانلود")
    private int tryToDownloadCount;

    @PersianName("تعداد دانلود")
    private int downloadCount;

    @PersianName("تعداد تلاش برای دانلود (مهمان)")
    private int tryToDownloadCountGuest;

    @PersianName("تعداد دانلود (مهمان)")
    private int downloadCountGuest;

    @Size(max = 250)
    @PersianName("قواعد دسترسی")
    private String accessRule;

    @Size(max = 30)
    @PersianName("زمان آخرین بارگذاری")
    private String lastUploadDateTime;

    @Size(max = 255)
    @PersianName("آدرس نسبی پوشه")
    private String directoryRelativePath;

    @Size(max = 255)
    @PersianName("آدرس مطلق پوشه")
    private String directoryAbsolutePath;

    ///##############################  OWNER
    @ManyToOne(fetch = FetchType.LAZY)
    @PersianName("مالک")
    private User owner;

    /////////////////////////////////////////////
    @ManyToOne
    @PersianName("پوشه ها")
    private Directory directory;

    //////////////////////////
    public File(String orginalName, String contentType, long size, Directory directory, Boolean temporary) {
        this.orginalName = orginalName;
        this.contentType = contentType;
        this.size = size;
        this.directory = directory;
        this.isTemporary = temporary;
        if (directory != null) {
            this.directoryAbsolutePath = this.directory.getAbsolutePath();
            this.directoryRelativePath = this.directory.getRelativePath();
        }
    }

    public File() {
    }
    //////////////////////////


    public Object getSecretNote() {
        return "{\"fileId\":" + getId() + "}";
    }

    public String getUploadLinkComment() {
        return uploadLinkComment;
    }

    public void setUploadLinkComment(String uploadLinkComment) {
        this.uploadLinkComment = uploadLinkComment;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public java.io.File getSourceFile() {
        return new java.io.File(directoryAbsolutePath + Environment.FILE_SEPARATOR + this.orginalName);
    }

    public String getAbsolutePathName() {
        return directoryAbsolutePath + Environment.FILE_SEPARATOR + this.orginalName;
    }

    //////////////////////////
    public String getExtention() {
        if (orginalName == null || orginalName.lastIndexOf(".") == -1) {
            return "";
        }
        return orginalName.substring(orginalName.lastIndexOf(".") + 1);
    }

    public String getOrginalName() {
        return orginalName;
    }

    public void setOrginalName(String orginalName) {
        this.orginalName = orginalName;
    }

    public String getName() {
        if (orginalName == null || orginalName.isEmpty()) {
            return "newFile";
        }
        if (orginalName.lastIndexOf(".") == -1) {
            return orginalName;
        }
        return orginalName.substring(0, orginalName.lastIndexOf("."));

    }

    public String getThumbnailName() {
        if (orginalName.lastIndexOf(".") == -1) {
            return orginalName + ".jpg";
        }
        return orginalName.substring(0, orginalName.lastIndexOf(".")) + ".jpg";

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Boolean getIsTemporary() {
        return isTemporary == null ? false : isTemporary;
    }

    public void setIsTemporary(Boolean isTemporary) {
        this.isTemporary = isTemporary;
    }

    public TtYesNo getIsTemporaryY() {
        return TtYesNo.getValueByBool(isTemporary);
    }

    public void setIsTemporaryY(TtYesNo temporary) {
        this.isTemporary = temporary.getBoolean();
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
        if (directory != null) {
            this.directoryAbsolutePath = this.directory.getAbsolutePath();
            this.directoryRelativePath = this.directory.getRelativePath();
        }
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void addThumbnails(String thumbnail) {
        this.thumbnails = (this.thumbnails == null || this.thumbnails.isEmpty()) ? thumbnail
                : this.thumbnails + "," + thumbnail;
    }

    public void emtyThumbnails() {
        this.thumbnails = "";
    }

    public String[] getThumbnailsArray() {
        return thumbnails != null ? thumbnails.split(",") : new String[1];
    }

    public Boolean getIsContainOrginal() {
        return isContainOrginal;
    }

    public void setIsContainOrginal(Boolean isContainOrginal) {
        this.isContainOrginal = isContainOrginal;
    }

    public TtTaskAccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(TtTaskAccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccessRule() {
        return accessRule;
    }

    public void setAccessRule(String accessRule) {
        this.accessRule = accessRule;
    }

    public String getUploadLink() {
        return uploadLink;
    }

    public void setUploadLink(String uploadLink) {
        this.uploadLink = uploadLink;
    }

    public TtFileFetchManner getFetchManner() {
        return fetchManner;
    }

    public void setFetchManner(TtFileFetchManner fetchManner) {
        this.fetchManner = fetchManner;
    }

    public TtFileUploadStatus getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(TtFileUploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public int getDownloadCountGuest() {
        return downloadCountGuest;
    }

    public void setDownloadCountGuest(int downloadCountGuest) {
        this.downloadCountGuest = downloadCountGuest;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getLastUploadDateTime() {
        return lastUploadDateTime;
    }

    public void setLastUploadDateTime(String lastUploadDateTime) {
        this.lastUploadDateTime = lastUploadDateTime;
    }

    public int getTryToDownloadCount() {
        return tryToDownloadCount;
    }

    public void setTryToDownloadCount(int tryToDownloadCount) {
        this.tryToDownloadCount = tryToDownloadCount;
    }

    public int getTryToDownloadCountGuest() {
        return tryToDownloadCountGuest;
    }

    public void setTryToDownloadCountGuest(int tryToDownloadCountGuest) {
        this.tryToDownloadCountGuest = tryToDownloadCountGuest;
    }

    public String getDirectoryRelativePath() {
        return directoryRelativePath;
    }

    public void setDirectoryRelativePath(String directoryRelativePath) {
        this.directoryRelativePath = directoryRelativePath;
    }

    public String getDirectoryAbsolutePath() {
        return directoryAbsolutePath;
    }

    public void setDirectoryAbsolutePath(String directoryAbsolutePath) {
        this.directoryAbsolutePath = directoryAbsolutePath;
    }

}
