package kz.insoft.usercrudapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@Configuration
public class SimpleJdbcInsertConfig {

    @Bean
    public SimpleJdbcInsert userJdbcInsert(DataSource dataSource) {
        return new SimpleJdbcInsert(dataSource)
                .withCatalogName("insoftdb")
                .withTableName("user");
    }
}
