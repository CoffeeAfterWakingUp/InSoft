package kz.insoft.usercrudapp.repository.db;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class DbManager {

    private static Connection connection;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/insoftdb?characterEncoding=UTF-8";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    static {
        connectToDb();
    }

    public static Connection getConnection() {
        try {
            if (connection != null) {
                if (connection.isClosed()) {
                    connectToDb();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception: {}", e.getMessage());
        }
        return connection;
    }

    private static void connectToDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception: {}", e.getMessage());
        }
    }
}
