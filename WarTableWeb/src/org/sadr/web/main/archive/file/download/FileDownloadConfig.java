package org.sadr.web.main.archive.file.download;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class FileDownloadConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FileDownloadDaoImp fileDownloadDaoImp() {
        FileDownloadDaoImp udi = new FileDownloadDaoImp();
        return udi;
    }

    @Bean
    public FileDownloadServiceImp fileDownloadServiceImp() {
        FileDownloadServiceImp usi = new FileDownloadServiceImp();
        usi.setDao(fileDownloadDaoImp());
        return usi;
    }

    @Bean
    public FileDownloadController fileDownloadController() {
        FileDownloadController uc = new FileDownloadController();
        uc.setService(fileDownloadServiceImp());
        return uc;
    }
}
