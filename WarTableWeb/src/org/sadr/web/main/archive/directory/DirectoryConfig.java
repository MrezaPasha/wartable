/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template directory, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr.web.main.archive.directory;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author masoud
 */
public class DirectoryConfig extends WebMvcConfigurerAdapter {

    @Bean
    public DirectoryDaoImp directoryDaoImp() {
        DirectoryDaoImp udi = new DirectoryDaoImp();
        return udi;
    }

    @Bean
    public DirectoryServiceImp directoryServiceImp() {
        DirectoryServiceImp usi = new DirectoryServiceImp();
        usi.setDao(directoryDaoImp());
        return usi;
    }

    @Bean
    public DirectoryController directoryController() {
        DirectoryController uc = new DirectoryController();
        uc.setService(directoryServiceImp());

        return uc;
    }
}
