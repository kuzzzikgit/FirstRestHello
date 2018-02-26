package hello.config;

import hello.core.repository.ItemsRepository;
import hello.core.repository.FirstItemsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public ItemsRepository itemsRepository() {
        return new FirstItemsRepository();
    }
}
