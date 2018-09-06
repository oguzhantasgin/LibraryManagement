
package com.config;


import javax.annotation.Resource;
import javax.sql.DataSource;

import com.model.library.Book;
import com.model.library.Publisher;
import com.model.user.PersistentLogin;
import com.model.user.User;
import com.model.user.UserProfile;
import com.model.user.UserProfileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement

@ComponentScan(basePackages = "com")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setAnnotatedClasses(Book.class, Publisher.class, PersistentLogin.class, User.class, UserProfile.class, UserProfileType.class);
        factoryBean.setHibernateProperties(putProperties(new Properties()));
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("mysql.driver"));
        dataSource.setUrl(env.getRequiredProperty("mysql.url"));
        dataSource.setUsername(env.getRequiredProperty("mysql.username"));
        dataSource.setPassword(env.getRequiredProperty("mysql.password"));
        return dataSource;
    }


    @Bean
    public Properties putProperties(Properties properties) {


        // Setting Hibernate properties
        properties.put(SHOW_SQL, env.getRequiredProperty("hibernate.show_sql"));
        properties.put(FORMAT_SQL, env.getRequiredProperty("hibernate.format_sql"));
        properties.put(HBM2DDL_AUTO, env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put(DIALECT, env.getRequiredProperty("hibernate.dialect"));

        // Setting C3P0 properties
        properties.put(C3P0_MIN_SIZE, env.getRequiredProperty("hibernate.c3p0.min_size"));
        properties.put(C3P0_MAX_SIZE, env.getRequiredProperty("hibernate.c3p0.max_size"));
        properties.put(C3P0_ACQUIRE_INCREMENT, env.getRequiredProperty("hibernate.c3p0.acquire_increment"));
        properties.put(C3P0_TIMEOUT, env.getRequiredProperty("hibernate.c3p0.timeout")); // seconds
        properties.put(C3P0_MAX_STATEMENTS, env.getRequiredProperty("hibernate.c3p0.max_statements"));
        properties.put(C3P0_CONFIG_PREFIX + ".initialPoolSize", env.getRequiredProperty("hibernate.c3p0.initialPoolSize"));
        properties.put(C3P0_IDLE_TEST_PERIOD, env.getRequiredProperty("hibernate.c3p0.idle_test_period")); // seconds
        properties.put(C3P0_CONFIG_PREFIX + ".acquireRetryAttempts", env.getRequiredProperty("hibernate.c3p0.acquireRetryAttempts"));
        properties.put(C3P0_CONFIG_PREFIX + ".acquireRetryDelay", env.getRequiredProperty("hibernate.c3p0.acquireRetryDelay")); // milliseconds


        return properties;
    }


}