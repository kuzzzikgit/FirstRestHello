package hello.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class ItemsDatabaseConfig {

    @Bean
    @Qualifier("itemsJdbcOperations")
    public JdbcOperations itemsJdbcOperations(
            @Qualifier("itemsDataSource") DataSource itemsDataSource) {
        return new JdbcTemplate(itemsDataSource);
    }

    @Bean
    @Qualifier("itemsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.items")
    public DataSource itemsDataSource() {
        return DataSourceBuilder.create().build();
    }
}
