package org.sadr.web.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.hibernate.SessionFactory;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;
import org.sadr._core.utils.Cryptor;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.SpringMessager;
import org.sadr.share.config.ShareConfigHandler;
import org.sadr.web.main._core.propertor.PropertorInBoot;
import org.sadr.web.main._core.propertor.PropertorInControl;
import org.sadr.web.main._core.propertor._type.TtPropertorInBootList;
import org.sadr.web.main._core.propertor._type.TtPropertorInControlList;
import org.sadr.web.main._core.tools.authorizer.AuthorizerAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author masoud
 */
@Configuration
//@ComponentScan("ir.co.iras")
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
@PropertySource("/WEB-INF/conf/system/system-conf.properties")
public class Config extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Bean
    public UrlBasedViewResolver viewResolver() {
        OutLog.so();
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(TilesView.class);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        OutLog.so();
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
        registry.addResourceHandler("/resources-f/**").addResourceLocations("/WEB-INF/resources-f/");
        registry.addResourceHandler("/resources-p/**").addResourceLocations("/WEB-INF/resources-p/");
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        OutLog.so();
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[]{
                "/WEB-INF/conf/layouts/tiles.xml",
                "/WEB-INF/conf/layouts/tiles-core.xml",
        });
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryRest() {
        OutLog.so();
        LocalSessionFactoryBean sessionFactoryRest = new LocalSessionFactoryBean();
        sessionFactoryRest.setDataSource(dataSourceRest());
        sessionFactoryRest.setHibernateProperties(hibernateProperties());
        WebConfigHandler.loadModelsRest();
        ShareConfigHandler.loadModels();
        WebConfigHandler.getModelClassesRest().addAll(ShareConfigHandler.getModelClasses());
        sessionFactoryRest.setAnnotatedClasses(WebConfigHandler.getModelClassArraysRest());
        initModelFields();
        return sessionFactoryRest;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryLog() {
        OutLog.so();
        LocalSessionFactoryBean sessionFactoryLog = new LocalSessionFactoryBean();
        sessionFactoryLog.setDataSource(dataSourceLog());
        sessionFactoryLog.setHibernateProperties(hibernateProperties());
        WebConfigHandler.loadModelsLog();
        sessionFactoryLog.setAnnotatedClasses(WebConfigHandler.getModelClassArraysLog());
//        initModelFields();
        return sessionFactoryLog;
    }

    //===================================================  DB

    @Bean
    public DataSource dataSourceRest() {
        OutLog.so();
        if (WebConfigHandler.getDatabaseParamRest() == null) {
            WebConfigHandler.setDatabaseParamsRest(env);
        }
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");

        String[] dp = WebConfigHandler.getDatabaseParamRest();
        dataSource.setUrl(dp[0]);
        dataSource.setUsername(dp[1]);
        dataSource.setPassword(dp[2]);
        return dataSource;
    }

    @Autowired
    @Primary
    public HibernateTransactionManager transactionManagerRest(SessionFactory sessionFactoryRest) {
        OutLog.so();
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactoryRest);
        return txManager;
    }
    @Bean
    public DataSource dataSourceLog() {
        OutLog.so();
        if (WebConfigHandler.getDatabaseParamLog() == null) {
            WebConfigHandler.setDatabaseParamsLog(env);
        }
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");

        String[] dp = WebConfigHandler.getDatabaseParamLog();
        dataSource.setUrl(dp[0]);
        dataSource.setUsername(dp[1]);
        dataSource.setPassword(dp[2]);
        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManagerLog(SessionFactory sessionFactoryLog) {
        OutLog.so();
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactoryLog);
        return txManager;
    }

    // Returns Routing DataSource (MyRoutingDataSource)
//    @Autowired
//    @Bean(name = "dataSource")
//    public DataSource getDataSource(DataSource dataSource1, DataSource dataSource2) {
//
//        OutLog.pl("## Create DataSource from dataSource1 & dataSource2");
//
//        MyRoutingDataSource dataSource = new MyRoutingDataSource();
//
//        Map<Object, Object> dsMap = new HashMap<>();
//        dsMap.put("PUBLISHER_DS", dataSource1);
//        dsMap.put("ADVERTISER_DS", dataSource2);
//
//        dataSource.setTargetDataSources(dsMap);
//
//        return dataSource;
//    }

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
                setProperty("hibernate.show_sql", PropertorInBoot.getInstance().isOnProperty(TtPropertorInBootList.HibernateSHowSQL) ? "true" : "false");
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
            commonsMultipartResolver.setMaxUploadSize(Long.parseLong(PropertorInControl.getInstance().getProperty(TtPropertorInControlList.FileMaxUploadSize)));
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

    @Bean
    public MessageSource messageSource() {
        OutLog.so();
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames(
                "/WEB-INF/conf/message/message-model",
                "/WEB-INF/conf/message/message-notice",
                "/WEB-INF/conf/message/message-all"
        );
        WebConfigHandler.setMessageSource(messageSource);
        SpringMessager.setMessageSource(messageSource);
        return messageSource;
    }

    @Bean
    public AuthorizerAspect authorizerAspect() {
        OutLog.so();
        return new AuthorizerAspect();
    }

    @Bean
    public LocaleResolver localeResolver() {
        OutLog.so();
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale("fa"));
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }

    private void initModelFields() {
        OutLog.so();
        List<Class<?>> acs = WebConfigHandler.getModelClasses();
        String actSes, virSes, relSes, sget;
        Method m;
        Object get;
        for (Class<?> ac : acs) {
            OutLog.p(ac.getName());
            try {
                actSes = relSes = virSes = "";
                Field[] dfs = ac.getSuperclass().getDeclaredFields();
                for (Field df : dfs) {
                    if (!java.lang.reflect.Modifier.isStatic(df.getModifiers())
                            || !java.lang.reflect.Modifier.isFinal(df.getModifiers())
                            || df.getType() != String.class) {
                        continue;
                    }

                    df.setAccessible(true);
                    get = df.get(null);
                    if (get != null) {
                        sget = get.toString();
//                        OutLog.p(df.getName(), sget);
                        if (df.getName().startsWith("$")) {
                            virSes += "," + sget;
                        } else if (df.getName().startsWith("_")) {
                            relSes += "," + sget;
                        } else {
                            actSes += "," + sget;
                        }
                    }
                }
                dfs = ac.getDeclaredFields();
                for (Field df : dfs) {
                    if (!java.lang.reflect.Modifier.isStatic(df.getModifiers())
                            || !java.lang.reflect.Modifier.isFinal(df.getModifiers())
                            || df.getType() != String.class) {
                        continue;
                    }
                    df.setAccessible(true);
                    get = df.get(null);
                    if (get != null) {
                        sget = get.toString();
                        if (df.getName().startsWith("$")) {
                            virSes += "," + sget;
                        } else if (df.getName().startsWith("_")) {
                            relSes += "," + sget;
                        } else {
                            actSes += "," + sget;
                        }
                    }
                }
                if (!actSes.isEmpty()) {
                    actSes = actSes.substring(1);
                }
                if (!virSes.isEmpty()) {
                    virSes = virSes.substring(1);
                }
                if (!relSes.isEmpty()) {
                    relSes = relSes.substring(1);
                }
                m = ac.getMethod("setAvrColumns", String.class, String.class, String.class);
                m.invoke(ac.newInstance(), actSes, virSes, relSes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
