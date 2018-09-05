
package com.config;


import javax.sql.DataSource;

import com.model.library.Book;
import com.model.library.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = {"com"})
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setHibernateProperties(putProperties(new Properties()));
        factoryBean.setAnnotatedClasses(Book.class, Publisher.class);
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
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        return dataSource;
    }


    @Bean
    public Properties putProperties(Properties properties) {
        // Setting JDBC properties
        properties.put(DRIVER, env.getProperty("mysql.driver"));
        properties.put(URL, env.getProperty("mysql.url"));
        properties.put(USER, env.getProperty("mysql.username"));
        properties.put(PASS, env.getProperty("mysql.password"));

        // Setting Hibernate properties
        properties.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        properties.put(FORMAT_SQL, env.getProperty("hibernate.format_sql"));
        properties.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put(DIALECT, env.getProperty("hibernate.dialect"));

        // Setting C3P0 properties
        properties.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
        properties.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
        properties.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
        properties.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout")); // seconds
        properties.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
        properties.put(C3P0_CONFIG_PREFIX + ".initialPoolSize", env.getProperty("hibernate.c3p0.initialPoolSize"));
        properties.put(C3P0_IDLE_TEST_PERIOD, env.getProperty("hibernate.c3p0.idle_test_period")); // seconds
        properties.put(C3P0_CONFIG_PREFIX + ".acquireRetryAttempts", env.getProperty("hibernate.c3p0.acquireRetryAttempts"));
        properties.put(C3P0_CONFIG_PREFIX + ".acquireRetryDelay", env.getProperty("hibernate.c3p0.acquireRetryDelay")); // milliseconds


        return properties;
    }


}