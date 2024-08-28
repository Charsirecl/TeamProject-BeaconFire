package com.example.applicationservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private HibernateProperty hibernateProperty;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.example.applicationservice.domain");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(hibernateProperty.getDriver());
        dataSource.setUrl(hibernateProperty.getDbUrl());
        dataSource.setUsername(hibernateProperty.getUsername());
        dataSource.setPassword(hibernateProperty.getPassword());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", hibernateProperty.getShowsql());
        properties.setProperty("hibernate.dialect", hibernateProperty.getDialect());
        properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }
}

//
//
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
////import org.springframework.orm.jpa.JpaTransactionManager;
////import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
////import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
////import org.springframework.transaction.PlatformTransactionManager;
////
////import javax.sql.DataSource;
////import java.util.Properties;
////
////@Configuration
////public class HibernateConfig {
////
////    @Bean
////    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
////        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
////        sessionFactory.setDataSource(dataSource);
////        sessionFactory.setPackagesToScan("com.example.applicationservice.domain"); // Update with your domain package
////        sessionFactory.setHibernateProperties(hibernateProperties());
////
////        return sessionFactory;
////    }
////
////    @Bean
////    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
////        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
////        em.setDataSource(dataSource);
////        em.setPackagesToScan("com.example.applicationservice.domain"); // Update with your domain package
////
////        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
////        em.setJpaVendorAdapter(vendorAdapter);
////        em.setJpaProperties(hibernateProperties());
////
////        return em;
////    }
////
////    @Bean
////    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
////        JpaTransactionManager transactionManager = new JpaTransactionManager();
////        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
////        return transactionManager;
////    }
////
////    private Properties hibernateProperties() {
////        Properties properties = new Properties();
////        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // Change based on your database
////        properties.put("hibernate.show_sql", "true");
////        properties.put("hibernate.hbm2ddl.auto", "update");
////        properties.put("hibernate.format_sql", "true");
////        properties.put("hibernate.use_sql_comments", "true");
////
////        return properties;
////    }
////}
