//package kz.insoft.usercrudapp.config;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/insoftdb?characterEncoding=UTF-8";
//    private static final String DB_USERNAME = "root";
//    private static final String DB_PASSWORD = "root";
//    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
//
//    @Bean
//    public DataSource dataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName(DB_DRIVER);
//        dataSourceBuilder.url(DB_URL);
//        dataSourceBuilder.username(DB_USERNAME);
//        dataSourceBuilder.password(DB_PASSWORD);
//        return dataSourceBuilder.build();
//    }
//
//}
