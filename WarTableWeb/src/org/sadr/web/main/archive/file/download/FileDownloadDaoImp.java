package org.sadr.web.main.archive.file.download;

import org.sadr._core.meta.generic.GenericDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author masoud
 */
@Repository
@Component
public class FileDownloadDaoImp extends GenericDaoImpl<FileDownload> implements FileDownloadDao {
}
