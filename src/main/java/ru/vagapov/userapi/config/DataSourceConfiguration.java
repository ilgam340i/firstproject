package ru.vagapov.userapi.config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.hibernate.query.criteria.ValueHandlingMode.BIND;

@Configuration
@ComponentScan(basePackages = {"ru.vagapov.userapi"})
@EnableTransactionManagement
@EnableJpaRepositories(value = "ru.vagapov.userapi.repository")
public class DataSourceConfiguration {
    private static final String[] PACKAGES_TO_SCAN = {"ru.vagapov.userapi.entity"};

    //Main datasource configs
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties mainDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource mainDataSource() {
        return mainDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    //Common bean
    @Bean
    @ConfigurationProperties("spring.jpa.properties")
    public Properties hibernateJpaProperties() {
        return new Properties();
    }

    @Bean
    public LocalSessionFactoryBean entityManagerFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(mainDataSource());
        sessionFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
        sessionFactoryBean.setHibernateProperties(hibernateJpaProperties());
        sessionFactoryBean.getHibernateProperties().put(
                AvailableSettings.CRITERIA_VALUE_HANDLING_MODE,
                BIND.toString()
        );
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setDataSource(mainDataSource());
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
