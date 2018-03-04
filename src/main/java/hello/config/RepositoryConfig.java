package hello.config;

import hello.core.repository.DatabaseItemsRepository;
import hello.core.repository.ItemsRepository;
import hello.core.repository.FirstItemsRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public ItemsRepository itemsRepository(
            @Qualifier("itemsJdbcOperations")
                    JdbcOperations jdbcOperations) {
        return new DatabaseItemsRepository(jdbcOperations);
    }
}
