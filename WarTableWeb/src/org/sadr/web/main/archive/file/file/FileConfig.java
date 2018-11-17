/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main.archive.file.file;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class FileConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FileDaoImp fileDaoImp() {
        FileDaoImp udi = new FileDaoImp();
        return udi;
    }

    @Bean
    public FileServiceImp fileServiceImp() {
        FileServiceImp usi = new FileServiceImp();
        usi.setDao(fileDaoImp());
        return usi;
    }

    @Bean
    public FileController fileController() {
        FileController uc = new FileController();
        uc.setService(fileServiceImp());
        return uc;
    }
}
