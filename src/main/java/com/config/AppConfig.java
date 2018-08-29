package com.config;

import com.model.Book;
import com.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
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
    public Properties putProperties(Properties properties)
    {
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