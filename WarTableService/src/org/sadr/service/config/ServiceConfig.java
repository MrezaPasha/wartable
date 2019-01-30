package org.sadr.service.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.hibernate.SessionFactory;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;
import org.sadr._core.utils.Cryptor;
import org.sadr._core.utils.OutLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesView;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * @author masoud
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableScheduling
@PropertySource("classpath:/org/sadr/service/props/system/system-conf.properties")
public class ServiceConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Bean
    public UrlBasedViewResolver viewResolver() {
        OutLog.so();
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(TilesView.class);
        return viewResolver;
    }


    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        OutLog.so();
        LocalSessionFactoryBean sessionFactoryRest = new LocalSessionFactoryBean();
        sessionFactoryRest.setDataSource(dataSource());
        sessionFactoryRest.setHibernateProperties(hibernateProperties());
        ServiceConfigHandler.loadModels();
        sessionFactoryRest.setAnnotatedClasses(ServiceConfigHandler.getModelClassArrays());
        return sessionFactoryRest;
    }

    //===================================================  DB

    @Bean
    public DataSource dataSource() {
        OutLog.so();
        if (ServiceConfigHandler.getDatabaseParamRest() == null) {
            ServiceConfigHandler.setDatabaseParamsRest(env);
        }
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");

        String[] dp = ServiceConfigHandler.getDatabaseParamRest();
        dataSource.setUrl(dp[0]);
        dataSource.setUsername(dp[1]);
        dataSource.setPassword(dp[2]);
        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        OutLog.so();
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    //=====================================================
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        OutLog.so();
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties hibernateProperties() {
        OutLog.so();
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.show_sql",
                        "false");
                setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
                setProperty("hibernate.dialect", Cryptor.decrypt(env.getProperty("hibernate.dialect")));
                setProperty("hibernate.globally_quoted_identifiers", env.getProperty("hibernate.globally_quoted_identifiers"));
                setProperty("hibernate.connection.autocommit", "true");

                setProperty("hibernate.cache.region.factory_class", env.getProperty("hibernate.cache.region.factory_class"));
                setProperty("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
                setProperty("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
            }
        };
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        OutLog.so();
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        try {
            commonsMultipartResolver.setMaxUploadSize(50000000);
        } catch (Exception e) {
            commonsMultipartResolver.setMaxUploadSize(50000000);
        }
        return commonsMultipartResolver;
    }

    @Bean
    public PooledPBEStringEncryptor pBEStringEncryptor() {
        OutLog.so();
        PooledPBEStringEncryptor pooledPBEStringEncryptor = new PooledPBEStringEncryptor();
        pooledPBEStringEncryptor.setProvider(new BouncyCastleProvider());
        pooledPBEStringEncryptor.setAlgorithm(Cryptor.decrypt(env.getProperty("pooledPBEStringEncryptor.algorithm")));
        pooledPBEStringEncryptor.setPoolSize(4);
        pooledPBEStringEncryptor.setPassword(Cryptor.decrypt(env.getProperty("pooledPBEStringEncryptor.password")));
        pooledPBEStringEncryptor.setSaltGenerator(new ZeroSaltGenerator());
        return pooledPBEStringEncryptor;
    }

    @Bean
    public HibernatePBEStringEncryptor bEStringEncryptor() {
        OutLog.so();
        HibernatePBEStringEncryptor hibernatePBEStringEncryptor = new HibernatePBEStringEncryptor();
        hibernatePBEStringEncryptor.setEncryptor(pBEStringEncryptor());
        hibernatePBEStringEncryptor.setRegisteredName("strongHibernateStringEncryptor");
        return hibernatePBEStringEncryptor;
    }




}
