package hello.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ItemsDatabaseConfig {

    @Bean
    @Qualifier("itemsJdbcOperations")
    public JdbcOperations itemsJdbcOperations(
            @Qualifier("itemsDataSource")
                    javax.sql.DataSource itemsDataSource
    ) {
        return new JdbcTemplate(itemsDataSource);
    }


        @Bean
    @Qualifier("itemsDataSource")
    @ConfigurationProperties(
            prefix = "spring.datasource.items")
    public javax.sql.DataSource itemsDataSource() {
        return DataSourceBuilder.create().build();
    }

}
