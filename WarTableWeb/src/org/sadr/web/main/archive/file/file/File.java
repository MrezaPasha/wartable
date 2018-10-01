package org.sadr.web.main.archive.file.file;

import org.sadr._core._type.TtYesNo;
import org.sadr._core.meta.annotation.PersianName;
import org.sadr._core.meta.generic.GenericDataModel;
import org.sadr._core.utils.Environment;
import org.sadr.web.main._core._type.TtTaskAccessLevel;
import org.sadr.web.main._core.tools._type.TtThumbnailSize;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.archive._type.TtFileFetchManner;
import org.sadr.web.main.archive._type.TtFileUploadStatus;
import org.sadr.web.main.archive.directory.Directory;
import org.sadr.web.main.archive.file.download.FileDownload;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @author masoud
 */
@PersianName("فایل")
@Entity
@Table(name = "Web.Archive.File")
public class File extends GenericDataModel<File> implements Serializable {
//#########++++++#######// StaticFields: Start //
public static final String TITLE = "title";public static final String ORGINAL_NAME = "orginalName";public static final String CONTENT_TYPE = "contentType";public static final String THUMBNAILS = "thumbnails";public static final String SIZE = "size";public static final String IS_TEMPORARY = "isTemporary";public static final String IS_CONTAIN_ORGINAL = "isContainOrginal";public static final String UPLOAD_LINK = "uploadLink";public static final String UPLOAD_LINK_COMMENT = "uploadLinkComment";public static final String FETCH_MANNER = "fetchManner";public static final String UPLOAD_STATUS = "uploadStatus";public static final String ACCESS_LEVEL = "accessLevel";public static final String TRY_TO_DOWNLOAD_COUNT = "tryToDownloadCount";public static final String DOWNLOAD_COUNT = "downloadCount";public static final String TRY_TO_DOWNLOAD_COUNT_GUEST = "tryToDownloadCountGuest";public static final String DOWNLOAD_COUNT_GUEST = "downloadCountGuest";public static final String ACCESS_RULE = "accessRule";public static final String LAST_UPLOAD_DATE_TIME = "lastUploadDateTime";public static final String DIRECTORY_RELATIVE_PATH = "directoryRelativePath";public static final String DIRECTORY_ABSOLUTE_PATH = "directoryAbsolutePath";public static final String _OWNER = "owner";public static final String _FILE_DOWNLOADS = "fileDownloads";public static final String _DIRECTORY = "directory";public static final String $NAME = "name";public static final String $RELATIVE_PATH_NAME25_X25 = "relativePathName25X25";public static final String $RELATIVE_PATH_NAME50_X50 = "relativePathName50X50";public static final String $RELATIVE_PATH_NAME150_X200 = "relativePathName150X200";public static final String $RELATIVE_PATH_NAME100_X100 = "relativePathName100X100";public static final String $RELATIVE_PATH_NAME200_X150 = "relativePathName200X150";public static final String $RELATIVE_PATH_NAME90_X120 = "relativePathName90X120";public static final String $RELATIVE_PATH_NAME600_X450 = "relativePathName600X450";public static final String $RELATIVE_PATH_NAME = "relativePathName";public static final String $RELATIVE_PATH_NAME250_X250 = "relativePathName250X250";public static final String $RELATIVE_PATH_NAME2400_X800 = "relativePathName2400X800";public static final String $RELATIVE_PATH_NAME350_X250 = "relativePathName350X250";public static final String $RELATIVE_PATH_NAME75_X50 = "relativePathName75X50";public static final String $RELATIVE_PATH_NAME500_X200 = "relativePathName500X200";public static final String $INFO_FOR_UPLOADER_JSON = "infoForUploaderJson";public static final String $RELATIVE_PATH_NAME150_X100 = "relativePathName150X100";public static final String $RELATIVE_PATH_NAME600_X400 = "relativePathName600X400";public static final String $RELATIVE_PATH_NAME100_X50 = "relativePathName100X50";public static final String $IS_FROM_UPLOAD_LINK = "isFromUploadLink";public static final String $ABSOLUTE_PATH_NAME = "absolutePathName";public static final String $THUMBNAILS_ARRAY = "thumbnailsArray";public static final String $RELATIVE_PATH_NAME30_X30 = "relativePathName30X30";public static final String $RELATIVE_PATH_NAME30_X40 = "relativePathName30X40";public static final String $RELATIVE_PATH_NAME40_X30 = "relativePathName40X30";public static final String $RELATIVE_PATH_NAME900_X300 = "relativePathName900X300";public static final String $RELATIVE_PATH_NAME200_X200 = "relativePathName200X200";public static final String $RELATIVE_PATH_NAME1000_X500 = "relativePathName1000X500";public static final String $RELATIVE_PATH_NAME500_X500 = "relativePathName500X500";public static final String $RELATIVE_PATH_NAME150_X150 = "relativePathName150X150";public static final String $RELATIVE_PATH_NAME120_X40 = "relativePathName120X40";public static final String $RELATIVE_PATH_NAME60_X60 = "relativePathName60X60";public static final String $RELATIVE_PATH_NAME120_X160 = "relativePathName120X160";public static final String $RELATIVE_PATH_NAME1000_X400 = "relativePathName1000X400";public static final String $RELATIVE_PATH_NAME500_X700 = "relativePathName500X700";public static final String $FULL_TITLE = "fullTitle";public static final String $SIZE_S = "sizeS";public static final String $THUMBNAIL_NAME = "thumbnailName";public static final String $IS_UPLOADED = "isUploaded";public static final String $EXTENTION = "extention";public static final String $IS_TEMPORARY_Y = "isTemporaryY";public static final String $SECRET_NOTE = "secretNote";public static final String $ACT_COLUMNS = "actColumns";public static final String $SOURCE_FILE = "sourceFile";public static final String $REL_COLUMNS = "relColumns";public static final String $VIR_COLUMNS = "virColumns";private static String[] actColumns;private static String[] relColumns;private static String[] virColumns;public static void setAvrColumns(String acts, String virts, String rels) {if (acts != null) {actColumns = acts.split(",");}if (virts != null) {virColumns = virts.split(",");}if (rels != null) {relColumns = rels.split(",");}}public static String[] getActColumns() {return actColumns;} public static String[] getVirColumns() {return virColumns;} public static String[] getRelColumns() {return relColumns;} 
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
    @OneToMany(mappedBy = "file")
    Set<FileDownload> fileDownloads;

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

    public void addDownloadCount() {
        downloadCount++;
    }

    public void addTryToDownloadCount() {
        tryToDownloadCount++;
    }

    public void addTryToDownloadCountGuest() {
        tryToDownloadCountGuest++;
    }

    public void addDownloadCountGuest() {
        downloadCountGuest++;
    }

    public String getFullTitle() {
        return getId() + "  |  " + title + "  |  " + getFetchManner().getTitle();
    }

    public boolean getIsUploaded() {
        return uploadStatus == TtFileUploadStatus.Uploaded;
    }

    public boolean getIsFromUploadLink() {
        return fetchManner == TtFileFetchManner.UploadLink;
    }

    public Object getSecretNote() {
        return "{\"fileId\":" + getId() + "}";
    }

    public String getSizeS() {
        return (size < 1000 ? size + " B"
            : (size < 1000000 ? ((double) Math.round((double) size / 10) / 100) + " KB"
            : (size < 1000000000 ? ((double) Math.round((double) size / 10000) / 100) + " MB"
            : ((double) Math.round((double) size / 10000000) / 100) + " GB")));
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

    public String getRelativePathName() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + this.orginalName;
    }

    public String getInfoForUploaderJson() {
        return "{"
            + "\"id\":" + getId() + ","
            + "\"name\":\"" + getOrginalName() + "\","
            + "\"size\":\"" + size + "\","
            + "\"isImage\":" + contentType.contains("image") + ","
            + "\"orginalPath\":\"" + getRelativePathName() + "\","
            + "\"path\":\"" + getRelativePathName150X150() + "\""
            + "}";
    }

    ////// 1x1
    public String getRelativePathName25X25() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._25X25.getDirectoryName() + Environment.FILE_SEPARATOR + this.getThumbnailName();
    }

    public String getRelativePathName30X30() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._30X30.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName50X50() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._50X50.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName75X50() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._75X50.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName100X100() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._100X100.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName150X150() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._150X150.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName200X200() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._200X200.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName250X250() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._250X250.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName500X500() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._500X500.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    ////// 2x1
    public String getRelativePathName100X50() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._100X50.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName1000X500() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._1000X500.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    ////// 3x1
    public String getRelativePathName120X40() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._120X40.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName900X300() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._900X300.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName2400X800() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._2400X800.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    ////// 3x2
    public String getRelativePathName150X100() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._150X100.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName600X400() {
        return directoryRelativePath == null ? null : directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._600X400.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    ////// 3x4
    public String getRelativePathName30X40() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._30X40.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName60X60() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._60X60.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName90X120() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._90X120.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName120X160() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._120X160.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName150X200() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._150X200.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    ////// 4x3
    public String getRelativePathName40X30() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._40X30.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName200X150() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._200X150.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName600X450() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._600X450.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    ////// 5x2
    public String getRelativePathName500X200() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._500X200.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    public String getRelativePathName1000X400() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._1000X400.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    ////// 5x7
    public String getRelativePathName500X700() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._500X700.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
    }

    ////// 7x5
    public String getRelativePathName350X250() {
        return directoryRelativePath + Environment.FILE_SEPARATOR + TtThumbnailSize._350X250.getDirectoryName() + Environment.FILE_SEPARATOR + this.getName() + ".jpg";
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

    public Set<FileDownload> getFileDownloads() {
        return fileDownloads;
    }

    public void setFileDownloads(Set<FileDownload> fileDownloads) {
        this.fileDownloads = fileDownloads;
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
