//package ru.spb.anohin.webrise_test_task.config.loggercfg;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConnectionLoggerToDatabase {
//
//    public static final String DB_URL = "jdbc:postgresql://localhost:5433/webrise-test-task-db";
//    public static final String USERNAME = "postgres";
//    public static final String PASSWORD = "postgres";
//
//    public static Connection getLoggingPostgresqlBaseConnection() throws SQLException {
//        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//    }
//
//}
