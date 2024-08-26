package com.az.authenticationservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:application.properties")
public class HibernateProperty {

    @Value("${database.hibernate.url}")
    private String dbUrl;

    @Value("${database.hibernate.driver}")
    private String driver;

    @Value("${database.hibernate.username}")
    private String username;

    @Value("${database.hibernate.password}")
    private String password;

    @Value("${database.hibernate.dialect}")
    private String dialect;

    @Value("${database.hibernate.showsql}")
    private String showsql;

//    public String getDbUrl() {
//        return dbUrl;
//    }
//
//    public void setDbUrl(String dbUrl) {
//        this.dbUrl = dbUrl;
//    }
//
//    public String getDriver() {
//        return driver;
//    }
//
//    public void setDriver(String driver) {
//        this.driver = driver;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getDialect() {
//        return dialect;
//    }
//
//    public void setDialect(String dialect) {
//        this.dialect = dialect;
//    }
//
//    public String getShowsql() {
//        return showsql;
//    }
//
//    public void setShowsql(String showsql) {
//        this.showsql = showsql;
//    }
}


